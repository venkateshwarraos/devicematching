package com.device.devicematcher;

import com.device.devicematcher.model.Device;
import com.device.devicematcher.repository.DeviceRepository;
import com.device.devicematcher.service.impl.DeviceServiceImpl;
import com.device.devicematcher.utils.ParsedUserAgent;
import com.device.devicematcher.utils.UserAgentParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class DeviceMatchingApplicationTests {

	@Test
	void contextLoads() {
	}

	@InjectMocks
	private DeviceServiceImpl deviceService;

	@Mock
	private DeviceRepository deviceRepository;

	//@Mock

	private UserAgentParser userAgentParser;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testMatchOrCreateDevice_existingDevice() {
		// Given
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36";


		ParsedUserAgent parsedUserAgent = new ParsedUserAgent("Windows 10", "MICROSOFT", "Chrome 12", "129.0.0.0");
		Device existingDevice = new Device();
		existingDevice.setDeviceId("1234");
		existingDevice.setOsName(parsedUserAgent.osName);
		existingDevice.setOsVersion(parsedUserAgent.osVersion);
		existingDevice.setBrowserName(parsedUserAgent.browserName);
		existingDevice.setBrowserVersion(parsedUserAgent.browserVersion);
		existingDevice.setHitCount(1);

		ParsedUserAgent agent = UserAgentParser.parse(userAgent);
		when(deviceRepository.findAllDevices()).thenReturn(Arrays.asList(existingDevice));
		when(deviceRepository.save(any(Device.class))).thenReturn(existingDevice);

		// When
		Device result = deviceService.matchOrCreateDevice(userAgent);

		// Then
		assertNotNull(result);
		assertEquals(2, result.getHitCount());
		verify(deviceRepository, times(1)).save(existingDevice);
	}

	@Test
	public void testMatchOrCreateDevice_newDevice() {
		// Given
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36";
		ParsedUserAgent parsedUserAgent = new ParsedUserAgent("Windows 10", "MICROSOFT", "Chrome 12", "129.0.0.0");

		//when(UserAgentParser.parse(userAgent)).thenReturn(parsedUserAgent);

		ParsedUserAgent agent = UserAgentParser.parse(userAgent);
		when(deviceRepository.findAllDevices()).thenReturn(Arrays.asList()); // No existing devices
		when(deviceRepository.save(any(Device.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// When
		Device result = deviceService.matchOrCreateDevice(userAgent);

		// Then
		assertNotNull(result);
		assertEquals(parsedUserAgent.osName, result.getOsName());
		assertEquals(parsedUserAgent.osVersion, result.getOsVersion());
		assertEquals(parsedUserAgent.browserName, result.getBrowserName());
		assertEquals(parsedUserAgent.browserVersion, result.getBrowserVersion());
		assertEquals(1, result.getHitCount());
		verify(deviceRepository, times(1)).save(result);
	}

}
