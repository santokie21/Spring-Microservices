package io.noobi.jobapp.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.noobi.jobapp.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Double rating;

    @JsonIgnore
    @ManyToOne
    private Company company;
}
