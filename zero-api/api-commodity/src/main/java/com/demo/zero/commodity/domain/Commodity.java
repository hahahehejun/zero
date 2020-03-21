package com.demo.zero.commodity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/6 21:06
 *   商品信息
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_commodity")
public class Commodity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="commodity_name")
    private String commodityName;
    @Column(name="unit")
    private String unit;
    @Column(name="number")
    private double number;
    @Column(name="details")
    private String details;

}
