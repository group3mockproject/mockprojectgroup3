package com.example.apartmentmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_history")
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentResident paymentResident;

    private String amountPaid;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    private String penalties;

    private Integer paymentMethod;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "delflag")
    private Boolean delFlag;

}
