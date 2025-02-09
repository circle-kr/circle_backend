package com.circle.circle_backend.circleMember.infrastructure.entity;

import com.circle.circle_backend.circle.infrastructure.entity.CircleEntity;
import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.domain.enums.UserRole;
import com.circle.circle_backend.user.infrastructure.entity.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "circle_members")
public class CircleMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "circle_id")
    private CircleEntity circleEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public static CircleMemberEntity from(CircleMember circleMember) {
        CircleMemberEntity circleMemberEntity = new CircleMemberEntity();
        circleMemberEntity.circleEntity = CircleEntity.from(circleMember.getCircle());
        circleMemberEntity.userEntity = UserEntity.from(circleMember.getUser());
        circleMemberEntity.userRole = circleMember.getUserRole();
        return circleMemberEntity;
    }

    public CircleMember toCircleMember() {
        return CircleMember.builder()
                .circle(this.circleEntity.toCircle())
                .user(this.userEntity.toUser())
                .userRole(this.userRole)
                .build();
    }


}
