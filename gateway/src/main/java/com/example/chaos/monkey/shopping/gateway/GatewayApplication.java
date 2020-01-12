package com.example.chaos.monkey.shopping.gateway;

import com.example.chaos.monkey.shopping.domain.Product;
import io.ap4k.kubernetes.annotation.ImagePullPolicy;
import io.ap4k.kubernetes.annotation.KubernetesApplication;
import io.ap4k.kubernetes.annotation.Port;
import io.ap4k.kubernetes.annotation.ServiceType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@KubernetesApplication(
		ports = @Port(name = "http", containerPort = 8070),
		serviceType = ServiceType.NodePort,
		group = "smmansoor",
		imagePullPolicy = ImagePullPolicy.Never)

public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/hotdeals**").filters(f ->
						f.hystrix(c -> c.setName("hotdeals").setFallbackUri("forward:/fallback"))).uri("lb://hot-deals"))
				.route(p -> p.path("/fashion/**").filters(f ->
						f.hystrix(c -> c.setName("fashion").setFallbackUri("forward:/fallback"))).uri("lb://fashion-bestseller"))
				.route(p -> p.path("/toys/**").filters(f ->
						f.hystrix(c -> c.setName("toys").setFallbackUri("forward:/fallback"))).uri("lb://toys-bestseller"))
				.build();
	}

	@GetMapping("/fallback")
	public ResponseEntity<List<Product>> fallback() {
		System.out.println("fallback enabled");
		HttpHeaders headers = new HttpHeaders();
		headers.add("fallback", "true");
		return ResponseEntity.ok().headers(headers).body(Collections.emptyList());
	}

}
