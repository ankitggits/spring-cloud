package no.ankit.service;

import no.ankit.exception.ServiceUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by AB75448 on 25.08.2016.
 */
@Component
public class DiscoveryClientURLProvider implements ServerURLProvider {

    @Autowired(required = false)
    DiscoveryClient discoveryClient;

    @Override
    public String provide() {
        List<ServiceInstance> instances = this.discoveryClient.getInstances("myapp-server");
        if(instances != null && !instances.isEmpty()) {
            ServiceInstance serviceInstance = instances.get(0);
            String url = String.format("http://%s:%d/serviceToInvoke", serviceInstance.getHost(), serviceInstance.getPort());
            return url;
        }else {
            throw new ServiceUnavailableException("Unable to locate a myapp-server service");
        }
    }
}
