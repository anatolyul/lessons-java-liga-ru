package ru.hofftech.logisticservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.hofftech.logisticservice.entity.BoxEntity;

public interface BoxRepository extends JpaRepository<BoxEntity, Long>, JpaSpecificationExecutor<BoxEntity> {

    @Query("select box from BoxEntity box where box.name = :name")
    BoxEntity findByName(String name);

    @Query("delete from BoxEntity where name = :name")
    BoxEntity deleteByName(String name);
}
