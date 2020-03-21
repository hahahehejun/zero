package com.demo.zero.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/28 02:14
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user_profile")
public class UserProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickName;
    private String phone;
    private String email;
    /**
     * 性别
     */
    private String gender;
}
