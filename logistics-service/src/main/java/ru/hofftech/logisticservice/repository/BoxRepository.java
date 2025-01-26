package ru.hofftech.logisticservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hofftech.logisticservice.constants.BoxColumn;
import ru.hofftech.logisticservice.constants.BoxQuery;
import ru.hofftech.logisticservice.entity.BoxEntity;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoxRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<BoxEntity> findAll() {
        return jdbcTemplate.query(BoxQuery.FIND_ALL,
                (rs, rowNum) -> new BoxEntity(
                        rs.getLong(BoxColumn.ID),
                        rs.getString(BoxColumn.NAME),
                        rs.getString(BoxColumn.FORM),
                        rs.getString(BoxColumn.SYMBOL)
                )
        );
    }

    public Optional<BoxEntity> findById(Long id) {
        List<BoxEntity> results = jdbcTemplate.query(BoxQuery.FIND_BY_ID,
                preparedStatement -> preparedStatement.setLong(1, id),
                (rs, rowNum) -> new BoxEntity(
                        rs.getLong(BoxColumn.ID),
                        rs.getString(BoxColumn.NAME),
                        rs.getString(BoxColumn.FORM),
                        rs.getString(BoxColumn.SYMBOL)
                ));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
    }

    public Optional<BoxEntity> findByName(String name) {
        List<BoxEntity> results = jdbcTemplate.query(BoxQuery.FIND_BY_NAME,
                preparedStatement -> preparedStatement.setString(1, name),
                (rs, rowNum) -> new BoxEntity(
                        rs.getLong(BoxColumn.ID),
                        rs.getString(BoxColumn.NAME),
                        rs.getString(BoxColumn.FORM),
                        rs.getString(BoxColumn.SYMBOL)
                ));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
    }

    public BoxEntity create(BoxEntity box) {
        return jdbcTemplate.query(BoxQuery.CREATE,
                preparedStatement -> {
                    preparedStatement.setLong(1, getMaxId() + 1);
                    preparedStatement.setString(2, box.getName());
                    preparedStatement.setString(3, box.getForm());
                    preparedStatement.setString(4, box.getSymbol());
                },
                (rs, rowNum) -> new BoxEntity(
                        rs.getLong(BoxColumn.ID),
                        rs.getString(BoxColumn.NAME),
                        rs.getString(BoxColumn.FORM),
                        rs.getString(BoxColumn.SYMBOL)
                )).getFirst();
    }

    public BoxEntity update(String name, BoxEntity box) {
        return jdbcTemplate.query(BoxQuery.UPDATE,
                preparedStatement -> {
                    preparedStatement.setString(1, box.getName());
                    preparedStatement.setString(2, box.getForm());
                    preparedStatement.setString(3, box.getSymbol());
                    preparedStatement.setString(4, name);
                },
                (rs, rowNum) -> new BoxEntity(
                        rs.getLong(BoxColumn.ID),
                        rs.getString(BoxColumn.NAME),
                        rs.getString(BoxColumn.FORM),
                        rs.getString(BoxColumn.SYMBOL)
                )).getFirst();
    }

    private Long getMaxId() {
        List<Long> query = jdbcTemplate.query(BoxQuery.MAX_ID,
                (rs, rowNum) -> rs.getLong(BoxColumn.ID)
        );
        return query.isEmpty() ? 0L : query.getFirst();
    }

    public boolean delete(String name) {
        int rowsAffected = jdbcTemplate.update(BoxQuery.DELETE,
                preparedStatement -> preparedStatement.setString(1, name));
        return rowsAffected > 0;
    }
}
