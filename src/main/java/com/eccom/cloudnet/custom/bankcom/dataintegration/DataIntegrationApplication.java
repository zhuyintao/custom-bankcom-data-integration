package com.eccom.cloudnet.custom.bankcom.dataintegration;

import com.eccom.cloudnet.common.annotation.CloudNetApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@CloudNetApplication
public class DataIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataIntegrationApplication.class, args);
    }

}
