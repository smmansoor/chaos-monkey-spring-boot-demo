package com.example.chaos.monkey.shopping.bestseller.fashion;

import io.ap4k.kubernetes.annotation.ImagePullPolicy;
import io.ap4k.kubernetes.annotation.KubernetesApplication;
import io.ap4k.kubernetes.annotation.Port;
import io.ap4k.kubernetes.annotation.ServiceType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@KubernetesApplication(
        ports = @Port(name = "http", containerPort = 8082),
        serviceType = ServiceType.NodePort,
        group = "smmansoor",
        imagePullPolicy = ImagePullPolicy.Never)
public class BestsellerFashionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestsellerFashionApplication.class, args);
    }
}
