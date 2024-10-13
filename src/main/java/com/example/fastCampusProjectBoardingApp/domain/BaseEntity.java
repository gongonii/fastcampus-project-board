package com.example.fastCampusProjectBoardingApp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@ToString
@EntityListeners(AuditingEntityListener.class)//
@MappedSuperclass
public class BaseEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false,updatable = false)//업데이트가 수시로 되지않은 즉 업데이트가 안되야하는 곳은 updatable = false 해주기
    private LocalDateTime createdAt;//생성일자

    @CreatedBy
    @Column(nullable = false,length = 100, updatable = false)
    private String createdBy;//생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;//수정일시

    @LastModifiedBy
    @Column(nullable = false)
    private String modifiedBy;//수정자



}
