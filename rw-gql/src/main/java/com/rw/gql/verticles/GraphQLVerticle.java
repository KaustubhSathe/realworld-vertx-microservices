package com.rw.gql.verticles;

import com.kaustubh.vertx.gql.starter.AbstractGraphQLVerticle;
import io.reactivex.rxjava3.core.Completable;

public class GraphQLVerticle extends AbstractGraphQLVerticle {
  private static final String packageName = "com.rw.gql";
  public GraphQLVerticle() {
    super(packageName);
  }

  @Override
  public Completable rxStart() {
    return super.rxStart();
  }

  @Override
  public Completable rxStop() {
    return super.rxStop();
  }
}
