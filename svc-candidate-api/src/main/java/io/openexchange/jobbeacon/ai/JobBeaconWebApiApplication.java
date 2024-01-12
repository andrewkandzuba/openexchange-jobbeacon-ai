package io.openexchange.jobbeacon.ai;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
@Generated
public class JobBeaconWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobBeaconWebApiApplication.class, args);
    }
}
