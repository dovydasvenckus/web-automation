package dev.dovydasvenckus.webautomation.scraper

import java.net.URI
import java.util.UUID
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import org.jdbi.v3.core.Jdbi

@Path("/scrapers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ScraperResource(jdbi: Jdbi) {
    private val scraperDAO = jdbi.onDemand(ScraperDAO::class.java)

    @POST
    fun create(@Valid createScraperRequest: CreateScraperRequest): Response {
        val createdScraper = Scraper(
            id = UUID.randomUUID(),
            itemNameSelector = createScraperRequest.itemNameSelector,
            itemUrlSelector = createScraperRequest.itemUrlSelector,
            itemPriceSelector = createScraperRequest.itemPriceSelector
        )

        scraperDAO.insert(createdScraper)

        return Response.created(formatUri(createdScraper)).build()
    }

    @GET
    fun getAll(): Response {
        return Response.ok(scraperDAO.findAllScrapers()).build()
    }

    private fun formatUri(createdScraper: Scraper): URI {
        return URI.create("/api/scrapers/" + createdScraper.id.toString())
    }
}