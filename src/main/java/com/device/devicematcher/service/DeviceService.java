package com.device.devicematcher.service;

import com.device.devicematcher.model.Device;

import java.util.List;
import java.util.Optional;


public interface DeviceService {

    public Device matchOrCreateDevice(String userAgent);

    Optional<Device> getDeviceById(String deviceId);

    List<Device> getDevicesByOsName(String osName);

    void deleteDeviceById(String deviceId);
}
