package com.device.devicematcher.controller;

import com.device.devicematcher.model.Device;
import com.device.devicematcher.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceManagementController {


    @Autowired
    private  DeviceService deviceService;

    

    @PostMapping("/match")
    public ResponseEntity<Device> matchDevice(@RequestHeader("User-Agent") String userAgent) {


        Device resposne = deviceService.matchOrCreateDevice(userAgent);

        return  ResponseEntity.ok(resposne);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable String id) {

        Optional<Device> response = deviceService.getDeviceById(id);
        return ResponseEntity.ok(response.get()) ;
    }

    @GetMapping("/os/{osName}")
    public ResponseEntity<List<Device>> getDevicesByOsName(@PathVariable String osName) {
        List<Device> deviceList = deviceService.getDevicesByOsName(osName);
        return ResponseEntity.ok(deviceList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeviceById(@PathVariable String id) {
        deviceService.deleteDeviceById(id);
        return ResponseEntity.ok("Requested device "+ id +" deleted");
    }


}
