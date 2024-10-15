package com.device.devicematcher.utils;

import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentParser {

    public static ParsedUserAgent parse(String userAgentString) {
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        return new ParsedUserAgent(
                userAgent.getOperatingSystem().getName(),
                userAgent.getOperatingSystem().getManufacturer().toString(),
                userAgent.getBrowser().getName(),
                userAgent.getBrowserVersion().getVersion()
        );
    }
}

