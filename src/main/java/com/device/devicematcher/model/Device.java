package com.device.devicematcher.model;

import lombok.*;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "devices")
public class Device {

    @Id
    private String deviceId;
    private String osName;
    private String osVersion;
    private String browserName;
    private String browserVersion;
    private int hitCount;
}
