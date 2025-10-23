package com.inventory.repository;

import com.inventory.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {

    @Query("SELECT e FROM Equipment e WHERE e.floor = :floor")
    List<Equipment> findByFloor(@Param("floor")String floor);

    @Query("SELECT e FROM Equipment e WHERE e.room = :room")
    List<Equipment> findByRoom(@Param("room") String room);

    @Query("SELECT e FROM Equipment e WHERE e.type = :type")
    List<Equipment> findByType(@Param("type") String type);

    @Query("SELECT e FROM Equipment e WHERE e.status = :status")
    List<Equipment> findByStatus(@Param("status") String status);

    @Query("SELECT e FROM Equipment e WHERE e.floor = :floor AND e.room = :room")
    List<Equipment> findByFloorAndRoom(@Param("floor") String floor, @Param("room") String room);

    @Query("SELECT e FROM Equipment e WHERE e.id = :id")
    boolean existsById(UUID id);
}
