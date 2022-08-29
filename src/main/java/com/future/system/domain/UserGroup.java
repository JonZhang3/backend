package com.future.system.domain;

import com.future.common.base.BaseEntity;
import com.future.system.domain.enums.State;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_user_group")
public class UserGroup extends BaseEntity {

    @Comment("用户组/部门名称")
    @Column(length = 64, nullable = false)
    private String name;

    @Comment("描述")
    @Column(name = "`desc`", length = 256)
    private String desc;

    private Long parentId;

    @Comment("产品ID,仅一个产品时可忽略")
    private Integer productId = 1;

    @Column(columnDefinition = "int NOT NULL DEFAULT 1")
    private Integer orderNum;

    @Comment("状态")
    @Column(columnDefinition = "int(2) NOT NULL DEFAULT 1")
    private State state = State.VALID;

}
