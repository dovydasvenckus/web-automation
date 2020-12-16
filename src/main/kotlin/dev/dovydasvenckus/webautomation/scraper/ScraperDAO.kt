package dev.dovydasvenckus.webautomation.scraper

import org.jdbi.v3.sqlobject.kotlin.BindKotlin
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface ScraperDAO {
    @SqlUpdate(
        "INSERT INTO scrapers " +
            "(id, item_name_selector, item_url_selector, item_price_selector)" +
            "VALUES (:id, :itemNameSelector, :itemUrlSelector, :itemPriceSelector)"
    )
    fun insert(@BindKotlin scraper: Scraper)

    @SqlQuery("SELECT * FROM scrapers")
    @RegisterKotlinMapper(Scraper::class)
    fun findAllScrapers(): List<Scraper>
}