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
@Table(name = "employee_performance")
public class EmployeePerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Long performanceId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser employee;

    @Column(name = "evaluation_date")
    private LocalDate evaluationDate;

    @Column(name = "score")
    private Double score;

    @Column(name = "comments")
    private String comments;

    @Column(name = "delflag")
    private Boolean delFlag;
}
