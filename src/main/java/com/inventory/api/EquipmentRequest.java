package com.inventory.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequest {

    private String name;

    private String type;

    private String room;

    private String floor;

    private String status;

    private String manufacturer;
    private String model;
    private String os;
    private String osVersion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdate;
}
