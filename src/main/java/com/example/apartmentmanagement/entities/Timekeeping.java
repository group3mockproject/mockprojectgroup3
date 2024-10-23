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
@Table(name = "timekeeping")
public class Timekeeping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timekeeping_id")
    private Long timekeepingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser employee;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String status;

    @Column(name = "delflag")
    private Boolean delFlag;
}
