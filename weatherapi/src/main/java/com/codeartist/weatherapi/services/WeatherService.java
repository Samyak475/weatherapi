package com.codeartist.weatherapi.services;

import com.codeartist.weatherapi.dto.Days;
import com.codeartist.weatherapi.dto.Hours;
import com.codeartist.weatherapi.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherService {
    @Value("${apikey}")
    private String keyValue;

    public String getWeatherFrmApi(String location,WeatherDto weatherDto) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+location
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
//        System.out.println(response.body());
        ObjectMapper objectMapper = new ObjectMapper() ;
      weatherDto= objectMapper.readValue(response.body(),weatherDto.getClass());
        System.out.println(weatherDto.getAddress());
        int count =0;
        for(Days days :weatherDto.getDays()){
            System.out.println(days.getDateTime());
            System.out.println(days.getDescription());
            System.out.println(days.getIcon());
            for(Hours hours: days.getHours()){
                System.out.println(hours.getConditions());
                System.out.println(hours.getIcon());
                System.out.println(hours.getTemp());
            }
            count++;

            if(count ==2) break;
        }

        return weatherDto.getDescription();
    }
}
