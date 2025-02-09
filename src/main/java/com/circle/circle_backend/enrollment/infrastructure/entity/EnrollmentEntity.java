package com.circle.circle_backend.enrollment.infrastructure.entity;

import com.circle.circle_backend.enrollment.domain.Enrollment;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.circle.infrastructure.entity.CircleEntity;
import com.circle.circle_backend.user.infrastructure.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "enrollments")
@Getter
public class EnrollmentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circle_id")
    CircleEntity circle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserEntity user;

    @Column(name = "enrollment_state")
    @Enumerated(EnumType.STRING)
    EnrollmentState enrollmentState;

    public static EnrollmentEntity from(Enrollment enrollment) {
        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.user = UserEntity.from(enrollment.getUser());
        enrollmentEntity.circle = CircleEntity.from(enrollment.getCircle());
        enrollmentEntity.enrollmentState = enrollment.getEnrollmentState();
        return enrollmentEntity;
    }

    public EnrollmentEntity updateEnrollmentState(EnrollmentState enrollmentState) {
        if (enrollmentState == EnrollmentState.PENDING) {
            this.enrollmentState = EnrollmentState.DELETED;
        } else if (enrollmentState == EnrollmentState.DELETED) {
            this.enrollmentState = EnrollmentState.PENDING;
        } else if (enrollmentState == EnrollmentState.REJECTED) {
            this.enrollmentState = EnrollmentState.REJECTED;
        } else if (enrollmentState == EnrollmentState.ACCEPTED) {
            this.enrollmentState = EnrollmentState.ACCEPTED;
        }
        return this;
    }

    public Enrollment toEnrollment() {
        return Enrollment.builder()
                .id(this.id)
                .user(this.user.toUser())
                .circle(this.circle.toCircle())
                .enrollmentState(this.enrollmentState)
                .build();
    }

    public boolean canUpdateState() {
        return this.enrollmentState == EnrollmentState.DELETED ||
                this.enrollmentState == EnrollmentState.PENDING;
    }
}
