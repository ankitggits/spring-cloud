package no.ankit.controller;

import no.ankit.service.DiscoveryClientURLProvider;
import no.ankit.service.DiscoveryClientWithHystrixUrlProvider;
import no.ankit.service.LoadBalancerClientURLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by AB75448 on 24.08.2016.
 */
@RestController
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = false)
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    DiscoveryClientURLProvider discoveryClientURLProvider;

    @Autowired
    DiscoveryClientWithHystrixUrlProvider discoveryClientWithHystrixUrlProvider;

    @Autowired
    LoadBalancerClientURLProvider loadBalancerClientURLProvider;

    @RequestMapping("/invokeServer")
    public ResponseEntity<?> invokeServer(){
        return restTemplate.getForEntity(discoveryClientURLProvider.provide(), String.class);
    }

    @RequestMapping("/invokeServer/hystrix")
    public ResponseEntity<?> invokeServerWithHystrix(){
        return restTemplate.getForEntity(discoveryClientWithHystrixUrlProvider.provide(), String.class);
    }

    @RequestMapping("/invokeServer/ribbon")
    public ResponseEntity<?> invokeServerWithRibbon(){
        return restTemplate.getForEntity(loadBalancerClientURLProvider.provide(), String.class);
    }

    @RequestMapping(path="/serviceToInvoke")
    public ResponseEntity<?> getResult(){
        return new ResponseEntity<String>("Value from client", HttpStatus.OK);
    }


}
