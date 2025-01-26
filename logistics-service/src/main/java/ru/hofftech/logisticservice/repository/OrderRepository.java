package ru.hofftech.logisticservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hofftech.logisticservice.constants.OrderColumn;
import ru.hofftech.logisticservice.constants.OrderQuery;
import ru.hofftech.logisticservice.entity.OrderEntity;
import ru.hofftech.logisticservice.model.enums.TypeOrderProcess;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<OrderEntity> findAll() {
        return jdbcTemplate.query(OrderQuery.FIND_ALL,
                (rs, rowNum) -> new OrderEntity(
                        rs.getLong(OrderColumn.ID),
                        TypeOrderProcess.valueOf(rs.getString(OrderColumn.TYPE)),
                        rs.getString(OrderColumn.CLIENT_NAME),
                        rs.getDate(OrderColumn.DATE).toLocalDate(),
                        rs.getLong(OrderColumn.TRUCK_COUNT),
                        rs.getLong(OrderColumn.BOX_COUNT),
                        rs.getLong(OrderColumn.SEGMENT_COUNT)
                )
        );
    }

    public List<OrderEntity> findByNameWithPeriod(String clientName,
                                                  LocalDate startDate,
                                                  LocalDate endDate) {
        return jdbcTemplate.query(OrderQuery.FIND_BY_NAME_PERIOD,
                preparedStatement -> {
                    preparedStatement.setString(1, clientName);
                    preparedStatement.setDate(2, Date.valueOf(startDate));
                    preparedStatement.setDate(3, Date.valueOf(endDate));
                },
                (rs, rowNum) -> new OrderEntity(
                        rs.getLong(OrderColumn.ID),
                        TypeOrderProcess.valueOf(rs.getString(OrderColumn.TYPE)),
                        rs.getString(OrderColumn.CLIENT_NAME),
                        rs.getDate(OrderColumn.DATE).toLocalDate(),
                        rs.getLong(OrderColumn.TRUCK_COUNT),
                        rs.getLong(OrderColumn.BOX_COUNT),
                        rs.getLong(OrderColumn.SEGMENT_COUNT)
                ));
    }
}
