package dev.dovydasvenckus.webautomation.task

import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateTaskRequest(
    @field:NotNull val scraperId: UUID,
    @field:NotBlank val cron: String?,
    @field:NotBlank val url: String?
)