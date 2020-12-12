package dev.dovydasvenckus.webautomation

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import javax.validation.Valid
import javax.validation.constraints.NotNull

class AppConfiguration : Configuration() {
    @get:JsonProperty("database")
    @set:JsonProperty("database")
    var dataSourceFactory: @Valid @NotNull DataSourceFactory? = DataSourceFactory()
}