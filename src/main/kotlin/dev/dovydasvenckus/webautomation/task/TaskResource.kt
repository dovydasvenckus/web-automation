package dev.dovydasvenckus.webautomation.task

import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.Jdbi
import java.net.URI
import java.util.*
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

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
                    cron = createTaskRequest.cron!!,
                    url = createTaskRequest.url!!,
                    itemNameSelector = createTaskRequest.itemNameSelector!!,
                    itemUrlSelector = createTaskRequest.itemUrlSelector!!,
                    itemPriceSelector = createTaskRequest.itemPriceSelector
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