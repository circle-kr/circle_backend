package com.circle.circle_backend.circle.service;

import com.circle.circle_backend.circle.domain.CircleMember;

public interface CircleMemberRepository {
    CircleMember save(CircleMember circleMember);
}
