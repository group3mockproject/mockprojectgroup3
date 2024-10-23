package com.example.apartmentmanagement.entities;

import com.example.apartmentmanagement.entities.permission.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "block")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long blockId;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private AppUser employee;

    @Column(name = "num_of_apartment")
    private Integer numOfApartment;

    @Column(name = "delflag")
    private Boolean delFlag;
}
