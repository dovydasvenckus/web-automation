package dev.dovydasvenckus.webautomation.task

import org.jdbi.v3.sqlobject.kotlin.BindKotlin
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface TaskDAO {
    @SqlUpdate("INSERT INTO tasks (id, scraper_id, cron, url) VALUES (:id, :scraperId, :cron, :url)")
    fun insert(@BindKotlin task: Task?)

    @SqlQuery("SELECT * FROM tasks")
    @RegisterKotlinMapper(Task::class)
    fun findAllTasks(): List<Task>
}