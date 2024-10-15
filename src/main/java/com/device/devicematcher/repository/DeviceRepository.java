package com.device.devicematcher.repository;

import com.device.devicematcher.model.Device;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends AerospikeRepository<Device, String> {

    List<Device> findByOsName(String osName);

    @Query
    List<Device> findAllDevices();

  //  List<Device> findAll();

    Optional<Device> findByDeviceId(String deviceId);
}
