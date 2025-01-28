package ru.hofftech.logisticservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.hofftech.logisticservice.entity.OrderEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    @Query("""
                    select c from OrderEntity c
                        where c.clientName = :clientName
                        and c.date >= :startDate
                        and c.date <= :endDate
            """)
    List<OrderEntity> findByNameWithPeriod(String clientName, LocalDate startDate, LocalDate endDate);
}
