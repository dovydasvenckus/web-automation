package dev.dovydasvenckus.webautomation.scraper

import java.util.UUID

data class Scraper(
    val id: UUID,
    val itemNameSelector: String,
    val itemUrlSelector: String,
    val itemPriceSelector: String? = null
)