package com.rw.gql.fetchers;

import com.kaustubh.vertx.gql.starter.AbstractDataFetcher;
import com.kaustubh.vertx.gql.starter.Fetcher;
import com.kaustubh.vertx.gql.starter.Request;
import io.reactivex.rxjava3.core.Single;


@Fetcher(
  consumes = "*",
  field = "hello",
  type = "Query"
)
public class ExampleFetcher extends AbstractDataFetcher<String> {
  @Override
  public Single<String> get(Request request) throws Exception {
    return Single.just(request.getEnvironment().getArguments().get("name").toString());
  }
}
