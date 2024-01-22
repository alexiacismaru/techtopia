//package be.kdg.prog6.boundedcontextB.adapters.in.web;
//
//import be.kdg.prog6.boundedcontextB.adapters.in.web.dtos.WeatherForecastDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "external-api", url = "http://localhost:9090")
//@Component
//public interface WeatherController {
//    @GetMapping("/weather/date/{date}")
//    WeatherForecastDto getWeather(@PathVariable String date);
//}
