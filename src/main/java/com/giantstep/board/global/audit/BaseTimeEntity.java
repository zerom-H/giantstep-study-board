package com.giantstep.board.global.audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseTimeEntity {

    /** 생성일 */
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-DD_HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createDate;

    /** 수정일 */
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-DD_HH:mm:ss")
    private LocalDateTime updateDate;
}
