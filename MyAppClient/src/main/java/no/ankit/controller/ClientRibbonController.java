package no.ankit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by AB75448 on 24.08.2016.
 */
@RestController
public class ClientRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/ribbon/invokeServer")
    public ResponseEntity<?> invokeServer(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("myapp-server");
        if (serviceInstance != null) {
            String url = String.format("http://%s:%d/serviceToInvoke", serviceInstance.getHost(), serviceInstance.getPort());
            return restTemplate.getForEntity(url, String.class);
        }
        throw new IllegalStateException("Unable to locate a myapp-server service");

    }


}
