package dev.dovydasvenckus.webautomation.api.core

import dev.dovydasvenckus.webautomation.App
import dev.dovydasvenckus.webautomation.AppConfiguration
import io.dropwizard.testing.ConfigOverride
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.junit.Rule
import org.testcontainers.containers.PostgreSQLContainer
import spock.lang.Specification

import javax.ws.rs.client.Client

class ApiSpecification extends Specification {
    public static PostgreSQLContainer postgreSQLContainer = startTestDB()

    @Rule
    DropwizardAppRule<AppConfiguration> RULE = new DropwizardAppRule<>(
            App.class,
            ResourceHelpers.resourceFilePath("test-config.yml"),
            ConfigOverride.config('database.url', postgreSQLContainer.jdbcUrl),
            ConfigOverride.config('database.user', postgreSQLContainer.username),
            ConfigOverride.config('database.password', postgreSQLContainer.password)
    )

    Client client

    def setup() {
        RULE.getApplication().run("db", "migrate", ResourceHelpers.resourceFilePath("test-config.yml"))
        client = RULE.client()
    }

    private static PostgreSQLContainer startTestDB() {
        PostgreSQLContainer dbContainer = new PostgreSQLContainer<>('postgres:13-alpine')
        dbContainer.start()
        return dbContainer
    }

    protected int getPort() {
        RULE.getLocalPort()
    }
}
