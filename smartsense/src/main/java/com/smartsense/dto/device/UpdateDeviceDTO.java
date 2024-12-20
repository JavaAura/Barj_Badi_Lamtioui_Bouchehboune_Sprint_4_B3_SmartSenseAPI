package com.smartsense.dto.device;

import javax.validation.constraints.Size;

import com.smartsense.dto.zone.ZoneDTO;
import com.smartsense.model.enums.DeviceType;
import com.smartsense.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDeviceDTO {

    @Size(min = 3, max = 100, message = "Device name must be between 3 and 100 characters")
    private String name;

    private DeviceType type;

    private Status status;

    private ZoneDTO zone;
}
