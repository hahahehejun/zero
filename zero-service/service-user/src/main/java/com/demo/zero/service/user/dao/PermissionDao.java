package com.demo.zero.service.user.dao;

import com.demo.zero.user.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author: 哈哈呵呵君
 * @date: 2020/1/18 21:15
 */
public interface PermissionDao extends JpaRepository<Permission,String> {
    @Query("SELECT p "
            +"FROM UserVerification AS u "
            +"LEFT JOIN UserVerificationRole AS ur ON u.id = ur.userId  "
            +"LEFT JOIN Role AS r ON r.id = ur.roleId  "
            +"LEFT JOIN RolePermission AS rp ON r.id = rp.roleId  "
            +"LEFT JOIN Permission AS p ON p.id = rp.permissionId  "
            +"WHERE  u.id = :userId")
    List<Permission> selectByUserId(@Param("userId")Long userId);
}
