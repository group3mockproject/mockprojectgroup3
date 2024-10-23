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
@Table(name = "employee_dayoff")
public class EmployeeDayoff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayoff_id")
    private Long dayOffId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser employee;

    @Column(name = "dayoff_date")
    private LocalDate dayoffDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private String status;

    @Column(name = "delflag")
    private Boolean delFlag;
}
