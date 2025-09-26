package com.rcf.usersservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 2, columnDefinition = "CHAR(2)")
    private String code;
    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "country")
    private List<StateRegion> stateRegions;
}
