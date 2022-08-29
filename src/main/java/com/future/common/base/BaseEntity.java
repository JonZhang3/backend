package com.future.common.base;

import com.future.common.jpa.SnowflakeIdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = SnowflakeIdGenerator.GENERATOR_NAME)
    @GenericGenerator(name = SnowflakeIdGenerator.GENERATOR_NAME, strategy = "com.future.common.jpa.SnowflakeIdGenerator")
    private Long id;

    @Comment("创建时间")
    @Column(name = "create_time", updatable = false)
    @CreatedDate
    private LocalDateTime createTime;

    @Comment("更新时间")
    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

}
