package com.future.system.domain;

import com.future.common.base.BaseEntity;
import com.future.system.domain.enums.ResourceType;
import com.future.system.domain.enums.State;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 资源表，包含菜单、按钮等资源
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_resource")
public class Resource extends BaseEntity {

    @Comment("资源名称")
    private String name;

    @Comment("描述")
    @Column(name = "`desc`", length = 256)
    private String desc;

    @Comment("父资源ID")
    private Long parentId;

    @Comment("资源类型")
    @Column(columnDefinition = "int(11) NOT NULL")
    private ResourceType type;

    @Comment("资源权限标识")
    @Column(length = 64)
    private String permission;

    @Comment("链接")
    @Column(length = 128)
    private String path;

    @Comment("图标")
    @Column(length = 64)
    private String icon;

    @Comment("排序")
    @Column(columnDefinition = "int NOT NULL DEFAULT 1")
    private Integer orderNum;

    @Comment("状态")
    @Column(columnDefinition = "int(2) NOT NULL DEFAULT 1")
    private State state = State.VALID;

    @Comment("产品ID,仅一个产品时可忽略")
    private Integer productId = 1;

}
