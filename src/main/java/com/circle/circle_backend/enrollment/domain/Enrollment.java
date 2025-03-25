package com.circle.circle_backend.enrollment.domain;

import com.circle.circle_backend.common.response.responseEnum.ErrorResponseEnum;
import com.circle.circle_backend.enrollment.domain.enums.EnrollmentState;
import com.circle.circle_backend.circle.domain.Circle;
import com.circle.circle_backend.exception.impl.ResourceException;
import com.circle.circle_backend.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "enrollments")
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circle_id")
    Circle circle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "enrollment_state")
    @Enumerated(EnumType.STRING)
    EnrollmentState enrollmentState;

    public Enrollment updateEnrollmentState() {
        if (this.enrollmentState == EnrollmentState.PENDING) {
            this.enrollmentState = EnrollmentState.CANCELED;
        } else if (this.enrollmentState == EnrollmentState.CANCELED) {
            this.enrollmentState = EnrollmentState.PENDING;
        } else if (this.enrollmentState == EnrollmentState.REJECTED || this.enrollmentState == EnrollmentState.ACCEPTED) {
            throw new ResourceException(ErrorResponseEnum.BAD_REQUEST_RESOURCE);
        }
        return this;
    }

    public boolean canUpdateState() {
        return this.enrollmentState == EnrollmentState.CANCELED ||
                this.enrollmentState == EnrollmentState.PENDING;
    }

    public void updateEnrollmentStateByAdmin(EnrollmentState enrollmentState) {
        this.enrollmentState = enrollmentState;
    }
}
