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
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Long buildingId;

    @Column(name = "size")
    private String size;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "num_of_floor")
    private Integer numOfFloor;

    @Column(name = "construction_year")
    private Integer constructionYear;

    @Column(name = "status")
    private String status;

    @Column(name = "delflag")
    private Boolean delFlag;

}