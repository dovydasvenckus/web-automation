package dev.dovydasvenckus.webautomation.api.specs

import dev.dovydasvenckus.webautomation.api.core.ApiSpecification
import dev.dovydasvenckus.webautomation.api.core.John
import dev.dovydasvenckus.webautomation.scraper.CreateScraperRequest
import dev.dovydasvenckus.webautomation.scraper.Scraper

import javax.ws.rs.core.Response

class ScraperApiSpec extends ApiSpecification {

    John john

    def setup() {
        john = new John(client, RULE.getLocalPort())
    }

    def "should create scraper"() {
        given:
            def request = new CreateScraperRequest(
                    "itemNameSelector",
                    "itemUrlSelector",
                    "itemPriceSelector"
            )
        when:
            Response response = john.createScraper(request)

        then:
            Scraper createdScraper = john.getAllScrapers()[0]

            response.getStatus() == 201
            response.getHeaderString("location") == "http://localhost:${getPort()}/api/scrapers/${createdScraper.id}"

            createdScraper.itemNameSelector == request.itemNameSelector
            createdScraper.itemUrlSelector == request.itemUrlSelector
            createdScraper.itemPriceSelector == request.itemPriceSelector
    }
}
