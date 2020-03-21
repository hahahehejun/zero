package com.demo.zero.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/18 22:41
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 父权限
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 权限英文名称
     */
    @Column(name = "enname")
    private String enname;
    /**
     * 备注
     */
    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

}
