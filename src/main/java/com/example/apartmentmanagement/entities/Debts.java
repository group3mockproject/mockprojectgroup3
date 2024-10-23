package com.example.apartmentmanagement.entities;

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
@Table(name = "debts")
public class Debts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deb_id")
    private Long debId;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentResident paymentResident;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "amount_due")
    private Integer amountDue;

    @Column(name = "due_date")
    private LocalDate dueDate;

    private String status;
}
