package com.circle.circle_backend.circleMember.service;

import com.circle.circle_backend.circle.service.CircleRepository;
import com.circle.circle_backend.circleMember.controller.port.CircleMemberService;
import com.circle.circle_backend.circleMember.domain.CircleMember;
import com.circle.circle_backend.circleMember.infrastructure.entity.CircleMemberEntity;
import com.circle.circle_backend.common.response.responseEnum.ErrorResponseEnum;
import com.circle.circle_backend.exception.impl.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CircleMemberServiceImpl implements CircleMemberService {

    private final CircleRepository circleRepository;
    private final CircleMemberRepository circleMemberRepository;

    @Override
    public List<CircleMember> read(Long circleId) {
        circleRepository.findById(circleId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorResponseEnum.CIRCLE_MEMBER_NOT_FOUND));

        return circleMemberRepository.findById(circleId).stream()
                .map(CircleMemberEntity::toCircleMember)
                .toList();
    }
}
