package dev.dovydasvenckus.webautomation.task

import javax.validation.constraints.NotBlank

data class CreateTaskRequest(
    @field:NotBlank val cron: String?,
    @field:NotBlank val url: String?,
    @field:NotBlank val itemNameSelector: String?,
    @field:NotBlank val itemUrlSelector: String?,
    val itemPriceSelector: String?
)