package com.codeartist.weatherapi.controller;

import com.codeartist.weatherapi.dto.WeatherDto;
import com.codeartist.weatherapi.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/weather/")
public class WeatherApiController {

    @Autowired
    WeatherService weatherService;


    @GetMapping("location/{loc}")
    public String showWeatherReport(@PathVariable("loc") String loc){
        String description;
        WeatherDto weatherDto = new WeatherDto();
        try{
            description = weatherService.getWeatherFrmApi(loc,weatherDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(description);
        return "abc";
    }
}
