package com.ctoader.learn;

import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @Value("${instance.name}")
    private String instanceName;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @GetMapping
    public String simpleRequest(@RequestHeader("x-custom-token") String requestToken) {
        log.info("Processing request for token {}.", requestToken);
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<String> response = Optional.of(eurekaClient.getNextServerFromEureka("simple-service", false))
                .map(instanceInfo -> instanceInfo.getHomePageUrl() + "/" + instanceName.replaceAll("\\s+",""))
                .map(url -> restTemplate.exchange(url, HttpMethod.GET, null, String.class))
                .orElseThrow(() -> new IllegalStateException());

        return response.getBody();
    }
}
