package com.circle.circle_backend.common.domain;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String datasource, Long id) {
        super(datasource + "에서 ID " + id + "를 찾을 수 없습니다.");
    }
}
