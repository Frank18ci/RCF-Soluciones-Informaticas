package com.rcf.usersservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    @Column(nullable = false, name = "password_hash")
    private String password;
    @Column(nullable = false, length = 120)
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Rol role;
    @Column(length = 30)
    private String phone;
    @Column(nullable = false, columnDefinition = "boolean default true", name = "is_active", insertable = false)
    private Boolean active = true;
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
