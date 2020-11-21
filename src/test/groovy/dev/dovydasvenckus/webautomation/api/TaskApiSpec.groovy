package dev.dovydasvenckus.webautomation.api

import dev.dovydasvenckus.webautomation.App
import dev.dovydasvenckus.webautomation.AppConfiguration
import dev.dovydasvenckus.webautomation.task.CreateTaskRequest
import dev.dovydasvenckus.webautomation.task.Task
import io.dropwizard.testing.ConfigOverride
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.junit.Rule
import org.testcontainers.containers.PostgreSQLContainer
import spock.lang.Specification

import javax.ws.rs.client.Client
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.GenericType
import javax.ws.rs.core.Response

class TaskApiSpec extends Specification {
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
    WebTarget testTargetApi

    def setup() {
        RULE.getApplication().run("db", "migrate", ResourceHelpers.resourceFilePath("test-config.yml"));
        client = RULE.client()
        testTargetApi = client.target("http://localhost:${RULE.getLocalPort()}/api/tasks")
    }

    def "should return 500 when telegram API is down"() {
        given:
            def requestBody = new CreateTaskRequest(
                    cron: 'crony',
                    url: 'someUrl',
                    itemNameSelector: 'nameSelector',
                    itemUrlSelector: 'urlSelector',
                    itemPriceSelector: 'priceSelector'
            )
        when:
            Response response = testTargetApi
                    .request()
                    .post(Entity.json(requestBody))

        then:
            Task createdTask = getCreatedTask()

            response.getStatus() == 201
            response.getHeaderString("location") == "http://localhost:${getPort()}/api/tasks/${createdTask.id}"

            createdTask.cron == requestBody.cron
            createdTask.url == requestBody.url
            createdTask.itemNameSelector == requestBody.itemNameSelector
            createdTask.itemUrlSelector == requestBody.itemUrlSelector
            createdTask.itemPriceSelector == requestBody.itemPriceSelector
    }

    private Task getCreatedTask() {
        testTargetApi
                .request()
                .get()
                .readEntity(new GenericType<List<Task>>() {})[0]
    }

    private int getPort() {
        RULE.getLocalPort()
    }

    private static PostgreSQLContainer startTestDB() {
        PostgreSQLContainer dbContainer = new PostgreSQLContainer<>('postgres:13-alpine')
        dbContainer.start()
        return dbContainer
    }

}
