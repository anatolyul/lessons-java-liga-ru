package ru.hofftech.logisticbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hofftech.logisticbilling.entity.InboxEventEntity;

import java.util.UUID;

public interface InboxEventRepository extends JpaRepository<InboxEventEntity, Long>, JpaSpecificationExecutor<InboxEventEntity> {

    boolean existsByIdempotentKey(UUID idempotentKey);
}
