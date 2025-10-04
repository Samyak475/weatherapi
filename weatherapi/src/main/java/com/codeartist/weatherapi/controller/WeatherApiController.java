package com.codeartist.weatherapi.controller;

import com.codeartist.weatherapi.dto.ResponseDto;
import com.codeartist.weatherapi.dto.WeatherDto;
import com.codeartist.weatherapi.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController()
@RequestMapping("/weather/")
public class WeatherApiController {

    @Autowired
    WeatherService weatherService;


    @GetMapping("location/{loc}")
    public ResponseEntity<ResponseDto> showWeatherReport(@PathVariable("loc") String loc){
        ResponseDto responseDto;
        WeatherDto weatherDto = new WeatherDto();
        try{
            responseDto = weatherService.getWeatherFrmApi(loc,weatherDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body(responseDto);
    }
}
