package org.kaustubh.rw.gql;

import graphql.GraphQL;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.graphql.GraphQLHandler;
import io.vertx.ext.web.handler.graphql.GraphiQLHandler;
import io.vertx.ext.web.handler.graphql.GraphiQLHandlerOptions;
import io.vertx.ext.web.handler.graphql.schema.VertxDataFetcher;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Server extends AbstractVerticle {
  public static void main(String[] args){
    Launcher.executeCommand("run", Server.class.getName());
  }

  private List<Link> links;

  @Override
  public void start(){
    prepareData();
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    GraphiQLHandlerOptions options = new GraphiQLHandlerOptions()
      .setEnabled(true);

    router.route("/graphiql/*").handler(GraphiQLHandler.create(options));
    router.route("/graphql").handler(GraphQLHandler.create(createGraphQL()));


    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080);
  }

  private void prepareData(){
    User peter = new User("Peter");
    User paul = new User("Paul");
    User jack = new User("Jack");

    links = new ArrayList<>();
    links.add(new Link("https://vertx.io", "Vert.x project", peter));
    links.add(new Link("https://www.eclipse.org", "Eclipse Foundation", paul));
    links.add(new Link("http://reactivex.io", "ReactiveX libraries", jack));
    links.add(new Link("https://www.graphql-java.com", "GraphQL Java implementation", peter));
  }

  private GraphQL createGraphQL(){
    String schema = vertx.fileSystem().readFileBlocking("graphqls/links.graphqls").toString();
    SchemaParser schemaParser = new SchemaParser();
    TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);
    RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
      .type("Query", builder -> {
        VertxDataFetcher<List<Link>> getAllLinks = VertxDataFetcher.create(this::getAllLinks);
        return builder.dataFetcher("allLinks", getAllLinks);
      })
      .build();

    SchemaGenerator schemaGenerator = new SchemaGenerator();
    GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    return GraphQL.newGraphQL(graphQLSchema)
      .build();
  }

  private Future<List<Link>> getAllLinks(DataFetchingEnvironment env){
    boolean secureOnly = env.getArgument("secureOnly");
    List<Link> result = links.stream().filter(link -> !secureOnly || link.getUrl().startsWith("https://"))
      .collect(Collectors.toList());

    return  Future.succeededFuture(result);
  }

}
