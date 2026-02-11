package org.kiroff.resource_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ResourceServerApplication
{

    static void main(String[] args)
    {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

}
