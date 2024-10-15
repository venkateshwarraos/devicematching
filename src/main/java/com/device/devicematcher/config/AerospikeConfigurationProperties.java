package com.device.devicematcher.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aerospike")
public class AerospikeConfigurationProperties {
    private String host;
    private int port;
    private String namespace;
}
