package ru.hofftech.logisticbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hofftech.logisticbilling.entity.OrderEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    List<OrderEntity> findAllByClientNameAndDateBetween(String clientName, LocalDate dateAfter, LocalDate dateBefore);
}
