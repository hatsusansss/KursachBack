package com.inventory.api;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipmentResponse {

    private UUID id;
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
