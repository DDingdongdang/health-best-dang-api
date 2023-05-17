package com.healthbest.api.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodInfo {

    @JsonProperty(value = "I2790")
    private Row row;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Row {
        @JsonProperty(value = "row")
        private List<Info> infos;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Info {
        @JsonProperty(value = "DESC_KOR")
        private String name;

        @JsonProperty(value = "SERVING_SIZE")
        private String size;

        @JsonProperty(value = "NUTR_CONT1")
        private double calorie;

        @JsonProperty(value = "NUTR_CONT2")
        private double carbohydrate;

        @JsonProperty(value = "NUTR_CONT3")
        private double protein;

        @JsonProperty(value = "NUTR_CONT4")
        private double fat;

        @JsonProperty(value = "NUTR_CONT5")
        private double sugars;

        @JsonProperty(value = "NUTR_CONT6")
        private double sodium;

        @Override
        public String toString() {
            return "FoodInfo{" +
                    "name='" + name + '\'' +
                    ", size='" + size + '\'' +
                    ", calorie=" + calorie +
                    ", carbohydrate=" + carbohydrate +
                    ", protein=" + protein +
                    ", fat=" + fat +
                    ", sugars=" + sugars +
                    ", sodium=" + sodium +
                    '}';
        }
    }
}
