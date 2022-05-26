package com.example.trafficlights;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class TrafficLight {
        @NotNull
        private String trafficLightColor;
}