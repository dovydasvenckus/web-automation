package dev.dovydasvenckus.webautomation.task

import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class Task(
    @field:NotNull val id: UUID,
    @field:NotBlank val cron: String,
    @field:NotBlank val url: String,
    @field:NotBlank val itemNameSelector: String,
    @field:NotBlank val itemUrlSelector: String,
    @field:NotBlank val itemPriceSelector: String? = null
)