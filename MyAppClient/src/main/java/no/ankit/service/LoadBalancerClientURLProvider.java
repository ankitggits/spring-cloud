package no.ankit.service;

import no.ankit.exception.ServiceUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

/**
 * Created by AB75448 on 25.08.2016.
 */
@Component
public class LoadBalancerClientURLProvider implements ServerURLProvider {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Override
    public String provide() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("myapp-server");
        if (serviceInstance != null) {
            String url = String.format("http://%s:%d/serviceToInvoke", serviceInstance.getHost(), serviceInstance.getPort());
            return url;
        }else{
            throw new ServiceUnavailableException("Unable to locate a myapp-server service");
        }
    }
}
