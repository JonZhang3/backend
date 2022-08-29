package com.future.system.domain;

import com.future.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

/**
 * 操作日志表
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
    name = "sys_operation_log",
    indexes = {
        @Index(name = "sys_log_userId", columnList = "userId"),
        @Index(name = "sys_log_type", columnList = "type")
    }
)
public class OperationLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("操作用户ID")
    private Long userId;

    @Comment("操作类型")
    @Column(length = 2)
    private String type;

    @Comment("操作标题")
    private String title;

    @Comment("日志详细内容")
    @Column(columnDefinition = "text")
    private String content;

    @Comment("用户远程地址")
    private String remoteAddr;

    @Comment("请求URL")
    private String requestUri;

    @Comment("请求 HTTP Method")
    private String requestMethod;

}
