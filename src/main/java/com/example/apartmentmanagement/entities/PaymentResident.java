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
@Table(name = "payment_resident")
public class PaymentResident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser resident;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "usage_id")
    private ResidentUtilityUsage residentUsage;

    @Column(name = "outstanding_amount")
    private Double outstandingAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "delflag")
    private Boolean delFlag;
}
