package com.codeartist.weatherapi.services;

import com.codeartist.weatherapi.dto.Days;
import com.codeartist.weatherapi.dto.Hours;
import com.codeartist.weatherapi.dto.ResponseDto;
import com.codeartist.weatherapi.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service

public class WeatherService {
    @Value("${apikey}")
    private String keyValue;

    ResponseDto responseDto = new ResponseDto();


    @Cacheable(value = "weather", key = "#location")
    public ResponseDto getWeatherFrmApi(String location,WeatherDto weatherDto) throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response = getStringHttpResponse(location);

        ObjectMapper objectMapper = new ObjectMapper() ;
      weatherDto= objectMapper.readValue(response.body(),weatherDto.getClass());
        System.out.println(weatherDto.getAddress());

        convertRequestToResponse(weatherDto,responseDto);
        int count =0;

//        for(Days days :weatherDto.getDays()){
//            System.out.println(days.getDateTime());
//            System.out.println(days.getDescription());
//            System.out.println(days.getIcon());
//            for(Hours hours: days.getHours()){
//                System.out.println(hours.getConditions());
//                System.out.println(hours.getIcon());
//                System.out.println(hours.getTemp());
//            }
//            count++;
//
//            if(count ==2) break;
//        }

        return responseDto;
    }

    private HttpResponse<String> getStringHttpResponse(String location) throws URISyntaxException {
        URI uri = new URI("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+ location
        +"?key="+keyValue);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response= null;
        try {
            response = (httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    public void convertRequestToResponse(WeatherDto weatherDto, ResponseDto responseDto1)
    {
        responseDto1.setAddress(weatherDto.getAddress());
        responseDto1.setDescription(weatherDto.getDescription());
        responseDto1.setTimezone(weatherDto.getTimezone());
        List<Days> days = new ArrayList<>();
        int cnt=0;
        for (Days day : weatherDto.getDays()){
            Days day1 = new Days();
            List<Hours> hoursList = day.getHours();
            day1.setHours(hoursList);
            days.add(day1);
            cnt++;
            if(cnt>2)break;

        }
        responseDto1.setDays(days);

    }
}
