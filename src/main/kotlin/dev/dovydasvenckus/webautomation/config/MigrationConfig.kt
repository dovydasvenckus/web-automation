package dev.dovydasvenckus.webautomation.config;

import dev.dovydasvenckus.webautomation.AppConfiguration;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;

public class MigrationConfig extends MigrationsBundle<AppConfiguration> {

  @Override
  public PooledDataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
    return configuration.getDataSourceFactory();
  }
}