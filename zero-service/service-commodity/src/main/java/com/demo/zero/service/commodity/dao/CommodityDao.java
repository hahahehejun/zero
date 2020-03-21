package com.demo.zero.service.commodity.dao;

import com.demo.zero.commodity.domain.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/6 23:43
 */
public interface CommodityDao extends JpaRepository<Commodity,String>, JpaSpecificationExecutor<Commodity> {
    @Query("SELECT c "
            +"FROM Commodity AS c "
            +"left JOIN CommodityUser AS cu ON c.id = cu.commodityId  "
            +"WHERE  cu.userId = :userId")
    List<Commodity> getByUserId(@Param("userId") Long userId);

    Commodity getById(Long id);
}
