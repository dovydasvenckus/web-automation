package dev.dovydasvenckus.webautomation.config

import dev.dovydasvenckus.webautomation.AppConfiguration
import io.dropwizard.db.PooledDataSourceFactory
import io.dropwizard.migrations.MigrationsBundle
import java.lang.IllegalArgumentException

class MigrationConfig : MigrationsBundle<AppConfiguration?>() {
    override fun getDataSourceFactory(configuration: AppConfiguration?): PooledDataSourceFactory {
        return configuration?.dataSourceFactory ?: throw IllegalArgumentException("")
    }
}