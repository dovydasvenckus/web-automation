package dev.dovydasvenckus.webautomation.config

import io.dropwizard.migrations.MigrationsBundle
import dev.dovydasvenckus.webautomation.AppConfiguration
import io.dropwizard.db.PooledDataSourceFactory
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

class MigrationConfig : MigrationsBundle<AppConfiguration?>() {
    override fun getDataSourceFactory(configuration: AppConfiguration?): PooledDataSourceFactory {
        return configuration?.dataSourceFactory ?: throw IllegalArgumentException("")
    }
}