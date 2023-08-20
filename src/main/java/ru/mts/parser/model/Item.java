package ru.mts.parser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("priceU")
    private Long priceU; //use long type for price because wb returns price in pennies
    @JsonProperty("salePriceU")
    private Long salePriceU;
    @JsonProperty("reviewRating")
    private Double rating;
    @JsonProperty("feedbacks")
    private Integer feedbacks;
}
