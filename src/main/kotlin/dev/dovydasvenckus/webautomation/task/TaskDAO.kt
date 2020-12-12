package dev.dovydasvenckus.webautomation.task;

import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface TaskDAO {

  @SqlUpdate(
      "INSERT INTO tasks "
          + "(id, cron, url, item_name_selector, item_url_selector, item_price_selector) "
          + "VALUES (:id, :cron, :url, :itemNameSelector, :itemUrlSelector, :itemPriceSelector)"
  )
  void insert(@BindBean Task task);

  @SqlQuery("SELECT * FROM tasks")
  @RegisterBeanMapper(Task.class)
  List<Task> findAllTasks();
}
