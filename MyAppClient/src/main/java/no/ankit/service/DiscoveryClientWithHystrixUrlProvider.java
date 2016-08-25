package no.ankit.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
public class DiscoveryClientWithHystrixUrlProvider implements ServerURLProvider {

    @Autowired(required = false)
    DiscoveryClient discoveryClient;

    @Override
    @HystrixCommand(fallbackMethod = "provideDefault")
    public String provide() {
        return provideByInstanceId("myapp-server");
    }

    public String provideDefault() {
        return provideByInstanceId("myapp-client");
    }

    private String provideByInstanceId(String instanceId){
        List<ServiceInstance> instances = this.discoveryClient.getInstances(instanceId);
        if(instances != null && !instances.isEmpty()) {
            ServiceInstance serviceInstance = instances.get(0);
            String url = String.format("http://%s:%d/serviceToInvoke", serviceInstance.getHost(), serviceInstance.getPort());
            return url;
        }else{
            throw new ServiceUnavailableException("Unable to locate a myapp-server service");
        }
    }

}
