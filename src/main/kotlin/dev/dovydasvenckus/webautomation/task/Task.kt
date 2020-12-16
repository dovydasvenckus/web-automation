package dev.dovydasvenckus.webautomation.task

import java.util.UUID

data class Task(
    val id: UUID,
    val scraperId: UUID,
    val cron: String,
    val url: String,
)