package ru.hofftech.logisticservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.hofftech.logisticservice.constants.OrderColumn;
import ru.hofftech.logisticservice.model.enums.TypeOrderProcess;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class OrderEntity {

    @Column(OrderColumn.ID)
    private Long id;

    @Column(OrderColumn.TYPE)
    private TypeOrderProcess type;

    @Column(OrderColumn.CLIENT_NAME)
    private String clientName;

    @Column(OrderColumn.DATE)
    private LocalDate date;

    @Column(OrderColumn.TRUCK_COUNT)
    private Long truckCount;

    @Column(OrderColumn.BOX_COUNT)
    private Long boxCount;

    @Column(OrderColumn.SEGMENT_COUNT)
    private Long segmentCount;
}
