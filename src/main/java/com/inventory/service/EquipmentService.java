package com.inventory.service;

import com.inventory.api.EquipmentRequest;
import com.inventory.api.EquipmentResponse;
import com.inventory.model.Equipment;
import com.inventory.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public List<EquipmentResponse> getAllEquipment() {
        return equipmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public EquipmentResponse getEquipmentById(UUID id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found with id: " + id));
        return mapToResponse(equipment);
    }

    public List<EquipmentResponse> getEquipmentByFloor(String floor) {
        return equipmentRepository.findByFloor(floor)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<EquipmentResponse> getEquipmentByRoom(String room) {
        return equipmentRepository.findByRoom(room)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<EquipmentResponse> getEquipmentByType(String type) {
        return equipmentRepository.findByType(type)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public EquipmentResponse createEquipment(EquipmentRequest request) {
        Equipment equipment = Equipment.builder()
                .name(request.getName())
                .type(request.getType())
                .room(request.getRoom())
                .floor(request.getFloor())
                .status(request.getStatus())
                .manufacturer(request.getManufacturer())
                .model(request.getModel())
                .os(request.getOs())
                .osVersion(request.getOsVersion())
                .lastUpdate(request.getLastUpdate())
                .build();

        Equipment saved = equipmentRepository.save(equipment);
        log.info("Created new equipment with id: {}", saved.getId());

        return mapToResponse(saved);
    }

    public EquipmentResponse updateEquipment(UUID id, EquipmentRequest request) {
        Equipment existingEquipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found with id: " + id));

        Equipment updated = Equipment.builder()
                .id(existingEquipment.getId())
                .name(request.getName())
                .type(request.getType())
                .room(request.getRoom())
                .floor(request.getFloor())
                .status(request.getStatus())
                .manufacturer(request.getManufacturer())
                .model(request.getModel())
                .os(request.getOs())
                .osVersion(request.getOsVersion())
                .lastUpdate(request.getLastUpdate())
                .build();

        Equipment saved = equipmentRepository.save(updated);
        log.info("Updated equipment with id: {}", id);

        return mapToResponse(saved);
    }

    public void deleteEquipment(UUID id) {
        if (!equipmentRepository.existsById(id)) {
            throw new RuntimeException("Equipment not found with id: " + id);
        }
        equipmentRepository.deleteById(id);
        log.info("Deleted equipment with id: {}", id);
    }

    private EquipmentResponse mapToResponse(Equipment equipment) {
        return EquipmentResponse.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .type(equipment.getType())
                .room(equipment.getRoom())
                .floor(equipment.getFloor())
                .status(equipment.getStatus())
                .manufacturer(equipment.getManufacturer())
                .model(equipment.getModel())
                .os(equipment.getOs())
                .osVersion(equipment.getOsVersion())
                .lastUpdate(equipment.getLastUpdate())
                .build();
    }
}
