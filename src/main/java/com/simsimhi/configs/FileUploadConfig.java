package com.simsimhi.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig {
    private String path;
    private String url;


}
