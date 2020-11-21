package dev.dovydasvenckus.webautomation.task;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface TaskDAO {

  @SqlUpdate(
      "INSERT INTO tasks "
          + "(id, cron, url, item_name_selector, item_url_selector, item_price_selector) "
          + "VALUES (:id, :cron, :url, :itemNameSelector, :itemUrlSelector, :itemPriceSelector)"
  )
  void insert(@BindBean Task task);
}
