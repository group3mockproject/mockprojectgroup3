package com.example.apartmentmanagement.entities;


import com.example.apartmentmanagement.entities.permission.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resident_utility_usages")
public class ResidentUtilityUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usage_id")
    private Long usageId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser resident;

    @ManyToOne
    @JoinColumn(name = "utility_id")
    private Utility utility;

    @Column(name = "usage_date")
    private LocalDate usageDate;

    @Column(name = "usage_fee")
    private Double usageFee;
}
