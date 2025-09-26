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
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "state_region_id", nullable = false)
    private StateRegion stateRegion;

    @OneToMany(mappedBy = "city")
    private List<UserAddress> userAddresses;

}
