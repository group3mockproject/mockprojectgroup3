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
@Table(name = "Apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long apartmentId;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private Block block;

    @Column(name = "num_of_bedroom")
    private Integer numOfBedroom;

    @Column(name = "num_of_bathroom")
    private Integer numOfBathroom;

    @Column(name = "area")
    private Float area;

    @Column(name = "direction")
    private String direction;

    @Column(name = "rentfee")
    private Double rentFee;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "delflag")
    private Boolean delFlag;
}