package dev.dovydasvenckus.webautomation.api.core

import dev.dovydasvenckus.webautomation.App
import dev.dovydasvenckus.webautomation.AppConfiguration
import io.dropwizard.testing.ConfigOverride
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.jdbi.v3.core.Jdbi
import org.junit.Rule
import org.testcontainers.containers.PostgreSQLContainer
import spock.lang.Specification

import javax.ws.rs.client.Client

class ApiSpecification extends Specification {
    private static PostgreSQLContainer postgreSQLContainer = startTestDB()
    private static Jdbi jdbi = Jdbi.create(
            postgreSQLContainer.jdbcUrl,
            postgreSQLContainer.username,
            postgreSQLContainer.password
    )

    @Rule
    protected DropwizardAppRule<AppConfiguration> RULE = new DropwizardAppRule<>(
            App.class,
            ResourceHelpers.resourceFilePath("test-config.yml"),
            ConfigOverride.config('database.url', postgreSQLContainer.jdbcUrl),
            ConfigOverride.config('database.user', postgreSQLContainer.username),
            ConfigOverride.config('database.password', postgreSQLContainer.password)
    )

    protected Client client

    def setup() {
        RULE.getApplication().run("db", "migrate", ResourceHelpers.resourceFilePath("test-config.yml"))
        client = RULE.client()
        truncateTables()
    }

    private static void truncateTables() {
        jdbi.withHandle { handle ->
            handle.execute('DELETE FROM tasks')
            handle.execute('DELETE FROM scrapers')
        }
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
