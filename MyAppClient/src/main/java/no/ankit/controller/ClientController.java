package no.ankit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by AB75448 on 24.08.2016.
 */
@RestController
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = false)
    DiscoveryClient discoveryClient;

    @RequestMapping("/invokeServer")
    public ResponseEntity<?> invokeServer(){
        List<ServiceInstance> instances = this.discoveryClient.getInstances("myapp-server");
        if(instances != null && !instances.isEmpty()) {
            ServiceInstance serviceInstance = instances.get(0);
            String url = String.format("http://%s:%d/serviceToInvoke", serviceInstance.getHost(), serviceInstance.getPort());
            return restTemplate.getForEntity(url, String.class);
        }
        throw new IllegalStateException("Unable to locate a leaderboard service");

    }


}
