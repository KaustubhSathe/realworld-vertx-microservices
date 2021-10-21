package com.realworld.auth;

import helloworld.HelloReply;
import helloworld.HelloRequest;
import helloworld.VertxGreeterGrpc;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    VertxGreeterGrpc.GreeterVertxImplBase service = new VertxGreeterGrpc.GreeterVertxImplBase() {
      @Override
      public Future<HelloReply> sayHello(HelloRequest request) {
        return Future.succeededFuture(HelloReply.newBuilder()
          .setMessage("Hello world grpc!!!")
          .build());
      }
    }.withCompression("gzip");

    VertxServer rpcServer = VertxServerBuilder
      .forAddress(vertx,"localhost", 8081)
      .addService(service)
      .build();

    rpcServer.start((ar) -> {
      if(ar.succeeded()){
        System.out.println("Successfully started grpc server on port " + 8081);
      }
    });
  }
}
