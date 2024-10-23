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
@Table(name = "lease_contract")
public class LeaseContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lease_id")
    private Long leaseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser resident;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "rent_amount")
    private Double rentAmount;

    @Column(name = "deposit_amount")
    private Double depositAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "delflag")
    private Boolean delFlag;
}
