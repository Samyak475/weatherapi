package com.codeartist.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Days> getDays() {
        return days;
    }

    public void setDays(List<Days> days) {
        this.days = days;
    }
    @JsonProperty(value = "resolvedAddress")
    private String address;
    @JsonProperty(value = "timezone")
    private String timezone;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "days")
    private List<Days> days;

}

