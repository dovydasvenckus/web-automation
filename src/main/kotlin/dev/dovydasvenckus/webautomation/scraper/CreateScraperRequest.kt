package dev.dovydasvenckus.webautomation.scraper

import javax.validation.constraints.NotBlank

data class CreateScraperRequest(
    @field:NotBlank val itemNameSelector: String,
    @field:NotBlank val itemUrlSelector: String,
    val itemPriceSelector: String? = null
)