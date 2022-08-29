package com.future.system.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import com.future.common.base.BaseEntity;
import com.future.system.domain.enums.State;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_dict_item")
public class DictionaryItem extends BaseEntity {

    @Comment("对应的字典ID")
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "dict_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Dictionary dict;

    @Comment("文本")
    @Column(length = 100, nullable = false)
    private String label;

    @Comment("字典项的值")
    @Column(length = 100, nullable = false)
    private String value;

    @Comment("描述")
    @Column(name = "`desc`")
    private String desc;

    @Comment("排序")
    @Column(columnDefinition = "int NOT NULL DEFAULT 1")
    private Integer orderNum = 1;

    @Comment("状态")
    @Column(columnDefinition = "int(2) NOT NULL DEFAULT 1")
    private State state = State.VALID;

}
