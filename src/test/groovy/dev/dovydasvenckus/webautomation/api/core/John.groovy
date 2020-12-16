package dev.dovydasvenckus.webautomation.api.core

import dev.dovydasvenckus.webautomation.scraper.CreateScraperRequest
import dev.dovydasvenckus.webautomation.scraper.Scraper
import dev.dovydasvenckus.webautomation.task.CreateTaskRequest
import dev.dovydasvenckus.webautomation.task.Task

import javax.ws.rs.client.Client
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.GenericType

class John {
    private Client client
    private int port

    John(Client client, int port) {
        this.client = client
        this.port = port
    }

    def createScraper(CreateScraperRequest createScraperRequest) {
        getScraperApi().request().post(Entity.json(createScraperRequest))
    }

    List<Scraper> getAllScrapers() {
        getScraperApi().request().get().readEntity(new GenericType<List<Scraper>>() {})
    }

    def createTask(CreateTaskRequest createTaskRequest) {
        getTaskApi().request().post(Entity.json(createTaskRequest))
    }

    List<Task> getAllTasks() {
        getTaskApi().request().get().readEntity(new GenericType<List<Task>>() {})
    }

    private WebTarget getScraperApi() {
        return client.target("${getBaseApiUrl()}/scrapers")
    }

    private WebTarget getTaskApi() {
        return client.target("${getBaseApiUrl()}/tasks")
    }

    private GString getBaseApiUrl() {
        return "http://localhost:${port}/api"
    }
}
