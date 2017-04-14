package com.tenx.ms.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tenx.ms.retail.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
