package com.example.chaos.monkey.shopping.bestseller.toys;

import io.ap4k.kubernetes.annotation.ImagePullPolicy;
import io.ap4k.kubernetes.annotation.KubernetesApplication;
import io.ap4k.kubernetes.annotation.Port;
import io.ap4k.kubernetes.annotation.ServiceType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.cloud.kubernetes.registry.KubernetesServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@EnableDiscoveryClient
@KubernetesApplication(
		ports = @Port(name = "http", containerPort = 8081),
		serviceType = ServiceType.NodePort,
		group = "smmansoor",
		imagePullPolicy = ImagePullPolicy.Never)
public class BestsellerToysApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestsellerToysApplication.class, args);
	}
}
