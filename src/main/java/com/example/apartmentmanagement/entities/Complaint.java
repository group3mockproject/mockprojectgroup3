package com.example.apartmentmanagement.entities;

import com.example.apartmentmanagement.entities.permission.AppUser;
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
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private Long complaintId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ComplaintType complaintType;

    @Column(name = "complaint_description", columnDefinition = "TEXT")
    private String complaintDescription;

    private String status;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "resolve_at")
    private LocalDateTime resolveAt;

    @Column(name = "delflag")
    private Boolean delFlag;
}
