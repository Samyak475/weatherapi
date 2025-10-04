package com.codeartist.weatherapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


public class ResponseDto implements Serializable {
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
