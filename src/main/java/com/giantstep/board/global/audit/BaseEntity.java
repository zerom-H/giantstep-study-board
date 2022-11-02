package com.giantstep.board.global.audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity extends BaseTimeEntity {

    /** 작성자 */
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    /** 수정자 */
    @LastModifiedBy
    private String updatedBy;
}
