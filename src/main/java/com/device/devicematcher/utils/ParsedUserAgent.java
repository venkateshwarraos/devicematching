package com.device.devicematcher.utils;

import lombok.Data;

@Data
public class ParsedUserAgent {
    public String osName;
    public String osVersion;
    public String browserName;
    public String browserVersion;

    public ParsedUserAgent(String osName, String osVersion, String browserName, String browserVersion) {
        this.osName = osName;
        this.osVersion = osVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }


}
