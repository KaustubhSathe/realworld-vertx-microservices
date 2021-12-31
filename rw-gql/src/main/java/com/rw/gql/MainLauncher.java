package com.rw.gql;

import com.kaustubh.vertx.commons.app.AbstractLauncher;
import com.google.inject.Module;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

public class MainLauncher extends AbstractLauncher {
  public MainLauncher() {
    super();
  }

  @Override
  public void afterConfigParsed(JsonObject jsonObject) {
  }

  @Override
  public void beforeStartingVertx(VertxOptions vertxOptions) {

  }

  @Override
  public void afterStartingVertx(Vertx vertx) {

  }

  @Override
  public void beforeDeployingVerticle(DeploymentOptions deploymentOptions) {

  }

  @Override
  public void beforeStoppingVertx(Vertx vertx) {

  }

  @Override
  public void afterStoppingVertx() {

  }

  @Override
  public void handleDeployFailed(Vertx vertx, String s, DeploymentOptions deploymentOptions, Throwable throwable) {

  }

  @Override
  protected Module[] getGoogleGuiceModules() {
    return null;
  }
}
