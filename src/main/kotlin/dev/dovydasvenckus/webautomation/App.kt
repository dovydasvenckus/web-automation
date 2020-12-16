package dev.dovydasvenckus.webautomation

import com.fasterxml.jackson.module.kotlin.KotlinModule
import dev.dovydasvenckus.webautomation.config.MigrationConfig
import dev.dovydasvenckus.webautomation.scraper.ScraperResource
import dev.dovydasvenckus.webautomation.task.TaskResource
import io.dropwizard.Application
import io.dropwizard.configuration.EnvironmentVariableSubstitutor
import io.dropwizard.configuration.SubstitutingSourceProvider
import io.dropwizard.jdbi3.JdbiFactory
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin

class App : Application<AppConfiguration>() {
    override fun getName(): String {
        return "web-automation-service"
    }

    override fun initialize(bootstrap: Bootstrap<AppConfiguration>) {
        bootstrap.addBundle(MigrationConfig())
        bootstrap.objectMapper.registerModule(KotlinModule())
        bootstrap.configurationSourceProvider = SubstitutingSourceProvider(
            bootstrap.configurationSourceProvider,
            EnvironmentVariableSubstitutor(false)
        )
    }

    override fun run(configuration: AppConfiguration, environment: Environment) {
        val jdbiFactory = JdbiFactory()
        val jdbi = jdbiFactory.build(environment, configuration.dataSourceFactory, "postgresql")
        jdbi.installPlugin(KotlinPlugin())
        jdbi.installPlugin(KotlinSqlObjectPlugin())
        environment.jersey().register(TaskResource(jdbi))
        environment.jersey().register(ScraperResource(jdbi))
    }

    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            App().run(*args)
        }
    }
}