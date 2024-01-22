//package be.kdg.prog6.boundedcontextB.adapters.in.web;
//
//import be.kdg.prog6.boundedcontextB.adapters.in.web.dtos.TemperatureType;
//import be.kdg.prog6.boundedcontextB.adapters.in.web.dtos.WeatherType;
//import be.kdg.prog6.boundedcontextB.ports.in.CheckWeatherUseCase;
//import io.github.resilience4j.retry.annotation.Retry;
//import lombok.AllArgsConstructor;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Component
//@AllArgsConstructor
//public class CheckWeatherAdapter implements CheckWeatherUseCase {
//    private final WeatherController weatherController;
//
//    @Override
//    @Cacheable("weather")
//    @Retry(name = "isColdRainyStormy", fallbackMethod = "fallback")
//    public boolean isColdRainyStormy() {
//        var result = weatherController.getWeather(LocalDate.now().toString());
//        System.out.println(result.toString());
//        return result.getWeatherType().equals(WeatherType.RAIN) ||
//                result.getWeatherType().equals(WeatherType.STORM) ||
//                result.getTemperatureType().equals(TemperatureType.COLD);
//    }
//
//    public boolean fallback(Throwable throwable){
//        System.out.println("FALLBACK");
//        return false;
//    }
//}
