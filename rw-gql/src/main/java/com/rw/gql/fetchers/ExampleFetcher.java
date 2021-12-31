package com.rw.gql.fetchers;

import com.kaustubh.vertx.gql.starter.AbstractDataFetcher;
import com.kaustubh.vertx.gql.starter.Fetcher;
import graphql.schema.DataFetchingEnvironment;
import io.reactivex.rxjava3.core.Single;


import java.util.concurrent.CompletionStage;

@Fetcher(
  consumes = "*",
  parameter = "hello",
  type = "Query"
)
public class ExampleFetcher extends AbstractDataFetcher<CompletionStage<String>> {
  @Override
  public CompletionStage<String> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
    return Single.just("world").toCompletionStage();
  }
}
