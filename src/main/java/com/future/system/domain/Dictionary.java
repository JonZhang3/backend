package com.future.system.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name = "sys_dict")
public class Dictionary extends BaseEntity {

    @Comment("字典名称")
    @Column(length = 100, nullable = false)
    private String name;

    @Comment("字典编码")
    @Column(length = 100, nullable = false)
    private String code;

    @Comment("描述")
    @Column(name = "`desc`", length = 256)
    private String desc;

    @Comment("状态, 1-有效 0-无效")
    @Column(columnDefinition = "int(2) NOT NULL DEFAULT 1")
    private State state = State.VALID;

    @Comment("产品ID")
    private Integer productId = 1;

    @OneToMany(mappedBy = "dict", cascade = CascadeType.REMOVE)
    private List<DictionaryItem> items = new LinkedList<>();

}
