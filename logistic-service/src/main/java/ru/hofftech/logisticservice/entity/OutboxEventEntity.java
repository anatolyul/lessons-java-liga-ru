package ru.hofftech.logisticservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import ru.hofftech.logisticservice.model.enums.OrderOutboxStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "order_outbox")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "order"})
public class OutboxEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", unique = true)
    private OrderEntity order;

    @Column(unique = true)
    private UUID idempotentKey;

    @Enumerated(EnumType.STRING)
    private OrderOutboxStatus status;
}
