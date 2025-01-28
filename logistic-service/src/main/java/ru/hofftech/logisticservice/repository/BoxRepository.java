package ru.hofftech.logisticservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hofftech.logisticservice.entity.BoxEntity;

import java.util.Optional;

public interface BoxRepository extends JpaRepository<BoxEntity, Long>, JpaSpecificationExecutor<BoxEntity> {

    Optional<BoxEntity> findByName(String name);

}
