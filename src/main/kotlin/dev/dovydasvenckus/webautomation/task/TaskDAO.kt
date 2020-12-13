package dev.dovydasvenckus.webautomation.task

import org.jdbi.v3.sqlobject.kotlin.BindKotlin
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface TaskDAO {
    @SqlUpdate(
        "INSERT INTO tasks " +
            "(id, cron, url, item_name_selector, item_url_selector, item_price_selector) " +
            "VALUES (:id, :cron, :url, :itemNameSelector, :itemUrlSelector, :itemPriceSelector)"
    )
    fun insert(@BindKotlin task: Task?)

    @SqlQuery("SELECT * FROM tasks")
    @RegisterKotlinMapper(Task::class)
    fun findAllTasks(): List<Task?>?
}