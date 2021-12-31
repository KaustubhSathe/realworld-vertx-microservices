package com.rw.gql;

import com.kaustubh.vertx.commons.app.AbstractLauncher;
import com.google.inject.Module;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;

public class MainLauncher extends AbstractLauncher {
  public static void main(String[] args) {
    (new MainLauncher()).dispatch(args);
  }
}
