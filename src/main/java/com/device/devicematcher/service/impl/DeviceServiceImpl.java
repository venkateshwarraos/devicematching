package com.device.devicematcher.service.impl;

import com.device.devicematcher.model.Device;

import com.device.devicematcher.repository.DeviceRepository;
import com.device.devicematcher.service.DeviceService;
import com.device.devicematcher.utils.ParsedUserAgent;
import com.device.devicematcher.utils.UserAgentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    private  DeviceRepository deviceRepository;




    @Override
    public Device matchOrCreateDevice(String userAgent) {
        ParsedUserAgent parsedUserAgent = UserAgentParser.parse(userAgent);
        List<Device> listDevices = deviceRepository.findAllDevices();
     //   Iterable<Device> listDevice = deviceRepository.findAll();

       // Stream<Device> deviceStream =  StreamSupport.stream(listDevice.spliterator(), false);

        Optional<Device> existingDevice = listDevices.stream().filter(device -> device.getOsName().equals(parsedUserAgent.osName) &&
                        device.getOsVersion().equals(parsedUserAgent.osVersion) &&
                        device.getBrowserName().equals(parsedUserAgent.browserName) &&
                        device.getBrowserVersion().equals(parsedUserAgent.browserVersion))
                .findFirst();

        if (existingDevice.isPresent()) {
            Device device = existingDevice.get();
            device.setHitCount(device.getHitCount() + 1);
            return (Device) deviceRepository.save(device);
        } else {
            Device newDevice = new Device();
            newDevice.setDeviceId(UUID.randomUUID().toString());
            newDevice.setOsName(parsedUserAgent.osName);
            newDevice.setOsVersion(parsedUserAgent.osVersion);
            newDevice.setBrowserName(parsedUserAgent.browserName);
            newDevice.setBrowserVersion(parsedUserAgent.browserVersion);
            newDevice.setHitCount(1);
            return (Device) deviceRepository.save(newDevice);
        }
    }

    @Override
    public Optional<Device> getDeviceById(String deviceId) {
        return deviceRepository.findByDeviceId(deviceId);
    }

    @Override
    public List<Device> getDevicesByOsName(String osName) {
        return deviceRepository.findByOsName(osName);
    }

    @Override
    public void deleteDeviceById(String deviceId) {

        deviceRepository.deleteById(deviceId);

    }
}
