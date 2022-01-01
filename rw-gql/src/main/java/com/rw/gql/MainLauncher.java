package com.rw.gql;

import com.kaustubh.vertx.commons.app.AbstractLauncher;
import com.google.inject.Module;
import io.vertx.core.Vertx;


public class MainLauncher extends AbstractLauncher {

  public static void main(String[] args) {
    (new MainLauncher()).dispatch(args);
  }

  @Override
  protected Module[] getGoogleGuiceModules(Vertx vertx) {
    return new Module[]{};
  }
}
