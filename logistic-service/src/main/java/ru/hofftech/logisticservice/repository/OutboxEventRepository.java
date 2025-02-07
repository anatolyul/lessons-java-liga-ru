package ru.hofftech.logisticservice.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hofftech.logisticservice.entity.OutboxEventEntity;
import ru.hofftech.logisticservice.model.enums.OrderOutboxStatus;

import java.util.List;

public interface OutboxEventRepository extends JpaRepository<OutboxEventEntity, Long>, JpaSpecificationExecutor<OutboxEventEntity> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"order"})
    List<OutboxEventEntity> findAllByStatus(OrderOutboxStatus status);
}
