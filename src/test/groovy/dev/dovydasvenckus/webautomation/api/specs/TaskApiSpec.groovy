package dev.dovydasvenckus.webautomation.api.specs

import dev.dovydasvenckus.webautomation.api.core.ApiSpecification
import dev.dovydasvenckus.webautomation.api.core.John
import dev.dovydasvenckus.webautomation.scraper.CreateScraperRequest
import dev.dovydasvenckus.webautomation.task.CreateTaskRequest
import dev.dovydasvenckus.webautomation.task.Task

import javax.ws.rs.core.Response

class TaskApiSpec extends ApiSpecification {

    John john

    def setup() {
        john = new John(client, RULE.getLocalPort())
    }
    def "should create task"() {
        given:
            john.createScraper(new CreateScraperRequest("test1", "test2", "test3"))

            def requestBody = new CreateTaskRequest(
                    john.getAllScrapers()[0].id,
                    "cron",
                    'someDomain'
            )
        when:
            Response response = john.createTask(requestBody)

        then:
            Task createdTask = john.getAllTasks()[0]

            response.getStatus() == 201
            response.getHeaderString("location") == "http://localhost:${getPort()}/api/tasks/${createdTask.id}"

            createdTask.cron == requestBody.cron
            createdTask.scraperId == requestBody.scraperId
            createdTask.url == requestBody.url
    }

}
