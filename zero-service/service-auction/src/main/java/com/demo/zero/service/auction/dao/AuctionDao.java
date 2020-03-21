package com.demo.zero.service.auction.dao;

import com.demo.zero.auction.domain.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/9 04:55
 */
public interface AuctionDao  extends JpaRepository<Auction,String>, JpaSpecificationExecutor<Auction> {
}
