package be.kdg.prog6.boundedcontextB.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class WeatherForecastDto {
    private LocalDate date;
    private TemperatureType temperatureType;
    private WeatherType weatherType;
}
