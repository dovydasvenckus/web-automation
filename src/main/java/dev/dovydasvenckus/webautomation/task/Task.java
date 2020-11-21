package dev.dovydasvenckus.webautomation.task;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Task {

  @NotNull
  private UUID id;
  @NotBlank
  private String cron;
  @NotBlank
  private String url;
  @NotBlank
  private String itemNameSelector;
  @NotBlank
  private String itemUrlSelector;
  @NotBlank
  private String itemPriceSelector;

  public Task() {
  }

  public Task(
      @NotNull UUID id,
      @NotBlank String cron,
      @NotBlank String url,
      @NotBlank String itemNameSelector,
      @NotBlank String itemUrlSelector,
      @NotBlank String itemPriceSelector) {
    this.id = id;
    this.cron = cron;
    this.url = url;
    this.itemNameSelector = itemNameSelector;
    this.itemUrlSelector = itemUrlSelector;
    this.itemPriceSelector = itemPriceSelector;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getCron() {
    return cron;
  }

  public void setCron(String cron) {
    this.cron = cron;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getItemNameSelector() {
    return itemNameSelector;
  }

  public void setItemNameSelector(String itemNameSelector) {
    this.itemNameSelector = itemNameSelector;
  }

  public String getItemUrlSelector() {
    return itemUrlSelector;
  }

  public void setItemUrlSelector(String itemUrlSelector) {
    this.itemUrlSelector = itemUrlSelector;
  }

  public String getItemPriceSelector() {
    return itemPriceSelector;
  }

  public void setItemPriceSelector(String itemPriceSelector) {
    this.itemPriceSelector = itemPriceSelector;
  }
}
