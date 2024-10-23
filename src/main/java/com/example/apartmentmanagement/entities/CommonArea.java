package com.example.apartmentmanagement.entities;

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
@Table(name = "common_area")
public class CommonArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long areaId;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "description")
    private String description;

    @Column(name = "delflag")
    private Boolean delFlag;
}
