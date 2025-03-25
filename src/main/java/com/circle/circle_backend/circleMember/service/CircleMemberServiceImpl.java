package com.circle.circle_backend.circleMember.service;

import com.circle.circle_backend.circle.service.port.CircleRepository;
import com.circle.circle_backend.circleMember.dto.response.CircleMemberResponse;
import com.circle.circle_backend.circleMember.controller.port.CircleMemberService;
import com.circle.circle_backend.circleMember.service.port.CircleMemberRepository;
import com.circle.circle_backend.common.response.responseEnum.ErrorResponseEnum;
import com.circle.circle_backend.exception.impl.ResourceException;
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
    public List<CircleMemberResponse> read(Long circleId) {
        if (!circleRepository.existsById(circleId)) {
            throw new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND);
        }

        return circleMemberRepository.findById(circleId).stream()
                .map(CircleMemberResponse::from)
                .toList();
    }
}
