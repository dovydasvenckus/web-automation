package dev.dovydasvenckus.webautomation.task;

import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jdbi.v3.core.Jdbi;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

  private final Jdbi jdbi;
  private final TaskDAO taskDAO;

  public TaskResource(Jdbi jdbi) {
    this.jdbi = jdbi;
    this.taskDAO = jdbi.onDemand(TaskDAO.class);
  }

  @POST
  public Response create(@NotNull @Valid CreateTaskRequest createTaskRequest) {
    Task createdTask = jdbi.inTransaction(handle -> {
      Task task = new Task(
          UUID.randomUUID(),
          createTaskRequest.getCron(),
          createTaskRequest.getUrl(),
          createTaskRequest.getItemNameSelector(),
          createTaskRequest.getItemUrlSelector(),
          createTaskRequest.getItemPriceSelector()
      );

      taskDAO.insert(task);
      return task;
    });

    return Response.created(formatUri(createdTask)).build();
  }

  @GET
  public Response getAll() {
    return Response.ok(taskDAO.findAllTasks()).build();
  }

  private URI formatUri(Task createdTask) {
    return URI.create("/api/tasks/" + createdTask.getId().toString());
  }
}
