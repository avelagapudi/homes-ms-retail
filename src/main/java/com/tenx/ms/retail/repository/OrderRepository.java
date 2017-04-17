package com.tenx.ms.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tenx.ms.retail.entity.OrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
