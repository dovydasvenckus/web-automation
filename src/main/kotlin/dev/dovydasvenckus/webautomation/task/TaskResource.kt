package dev.dovydasvenckus.webautomation.task

import java.net.URI
import java.util.UUID
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.Jdbi

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TaskResource(private val jdbi: Jdbi) {
    private val taskDAO: TaskDAO = jdbi.onDemand(TaskDAO::class.java)

    @POST
    fun create(@Valid createTaskRequest: CreateTaskRequest): Response {
        val createdTask = jdbi.inTransaction<Task, RuntimeException> { _: Handle? ->
            val task = Task(
                id = UUID.randomUUID(),
                scraperId = createTaskRequest.scraperId,
                cron = createTaskRequest.cron!!,
                url = createTaskRequest.url!!
            )
            taskDAO.insert(task)
            task
        }
        return Response.created(formatUri(createdTask)).build()
    }

    @GET
    fun getFall(): Response {
        return Response.ok(taskDAO.findAllTasks()).build()
    }

    private fun formatUri(createdTask: Task): URI {
        return URI.create("/api/tasks/" + createdTask.id.toString())
    }
}