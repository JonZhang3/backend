package com.future.system.domain;

import com.future.common.base.BaseEntity;
import com.future.system.domain.enums.Sex;
import com.future.system.domain.enums.State;
import com.future.system.domain.enums.UserType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户表
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_user")
@org.hibernate.annotations.Table(appliesTo = "sys_user", comment = "系统用户信息")
@DynamicUpdate
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Comment("用户名")
    @Column(unique = true, nullable = false, length = 64)
    private String username;

    @Comment("用户昵称")
    @Column
    private String nickname;

    @Comment("密码")
    @Column(nullable = false, length = 64)
    private String password;

    @Comment("随机盐")
    private String salt;

    @Comment("邮件")
    @Column(length = 256)
    private String email;

    @Comment("手机号/电话")
    @Column(length = 20)
    private String phone;

    @Comment("头像")
    @Column(length = 256)
    private String avatar;

    @Comment("性别")
    @Column(columnDefinition = "tinyint(1) NULL DEFAULT NULL")
    private Sex sex = Sex.UNKNOW;

    @Comment("工号")
    @Column(length = 100, unique = true)
    private String workNo;

    @Comment("上次登录时间")
    private LocalDateTime lastLoginTime;

    @Comment("用户类型, 0-管理员 1-普通用户")
    @Column(columnDefinition = "int(2) NOT NULL DEFAULT 1")
    private UserType type = UserType.NORMAL;

    @Comment("创建用户")
    private Long createBy;

    @Comment("用户状态, 1-正常 0-删除 -1-锁定")
    @Column(columnDefinition = "int(2) NOT NULL DEFAULT 1")
    private State state = State.VALID;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(
        name = "sys_user_role",
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
        inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();

}
