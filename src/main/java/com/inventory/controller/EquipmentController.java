package com.inventory.controller;

import com.inventory.api.EquipmentRequest;
import com.inventory.api.EquipmentResponse;
import com.inventory.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<EquipmentResponse>> getAllEquipment() {
        List<EquipmentResponse> equipment = equipmentService.getAllEquipment();
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponse> getEquipmentById(@PathVariable UUID id) {
        EquipmentResponse equipment = equipmentService.getEquipmentById(id);
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("/floor/{floor}")
    public ResponseEntity<List<EquipmentResponse>> getEquipmentByFloor(@PathVariable String floor) {
        List<EquipmentResponse> equipment = equipmentService.getEquipmentByFloor(floor);
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("/room/{room}")
    public ResponseEntity<List<EquipmentResponse>> getEquipmentByRoom(@PathVariable String room) {
        List<EquipmentResponse> equipment = equipmentService.getEquipmentByRoom(room);
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<EquipmentResponse>> getEquipmentByType(@PathVariable String type) {
        List<EquipmentResponse> equipment = equipmentService.getEquipmentByType(type);
        return ResponseEntity.ok(equipment);
    }

    @PostMapping
    public ResponseEntity<EquipmentResponse> createEquipment(@RequestBody EquipmentRequest request) {
        EquipmentResponse created = equipmentService.createEquipment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentResponse> updateEquipment(
            @PathVariable UUID id,
            @RequestBody EquipmentRequest request) {

        EquipmentResponse updated = equipmentService.updateEquipment(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable UUID id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
