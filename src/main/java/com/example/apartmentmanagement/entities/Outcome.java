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
@Table(name = "outcomes")
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Long incomeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser employee;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private OutcomeType type;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "spent_date")
    private LocalDate spentDate;

    @Column(name = "description")
    private String description;

    @Column(name = "delflag")
    private Boolean delFlag;
}
