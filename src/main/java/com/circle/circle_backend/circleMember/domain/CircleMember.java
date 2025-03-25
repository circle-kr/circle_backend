package com.circle.circle_backend.circleMember.domain;

import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.circleMember.domain.enums.UserRole;
import com.circle.circle_backend.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "circle_members")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CircleMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "circle_id")
    private Circle circle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    public CircleMember(Circle circle, User user, UserRole userRole) {
        this.circle = circle;
        this.user = user;
        this.userRole = userRole;
    }

    public boolean isAdmin() {
        return userRole == UserRole.ADMIN;
    }
}
