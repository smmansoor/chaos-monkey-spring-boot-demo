package com.example.chaos.monkey.shopping.hotdeals;

import io.ap4k.kubernetes.annotation.ImagePullPolicy;
import io.ap4k.kubernetes.annotation.KubernetesApplication;
import io.ap4k.kubernetes.annotation.Port;
import io.ap4k.kubernetes.annotation.ServiceType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@KubernetesApplication(
		ports = @Port(name = "http", containerPort = 8083),
		serviceType = ServiceType.NodePort,
		group = "smmansoor",
		imagePullPolicy = ImagePullPolicy.Never)
public class HotDealsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotDealsApplication.class, args);
	}
}
