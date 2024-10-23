package com.example.apartmentmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilities")
public class Utility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utility_id")
    private Long utilityId;

    @Column(name = "utility_name")
    private String utilityName;

    @Column(name = "utility_desc", columnDefinition = "TEXT")
    private String utilityDesc;

    @Column(name = "usages_rules")
    private String usagesRules;

    @Column(name = "fee_rate")
    private Double feeRate;

    @Column(name = "delflag")
    private Boolean delFlag;


}
