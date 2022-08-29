package com.future.system.domain;

import com.future.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 用户角色表
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_role", indexes = {
    @Index(name = "sys_role_product_name", columnList = "name,productId"),
    @Index(name = "sys_role_product_code", columnList = "code,productId")
})
public class Role extends BaseEntity {

    @Comment("角色名称")
    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, length = 64)
    private String code;

    @Comment("描述")
    @Column(name = "`desc`", length = 256)
    private String desc;

    @Comment("产品ID")
    private Integer productId = 1;

}
