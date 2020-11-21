package dev.dovydasvenckus.webautomation.task;

import javax.validation.constraints.NotBlank;

public class CreateTaskRequest {

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
