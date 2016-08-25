package no.ankit.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by AB75448 on 24.08.2016.
*/
@Getter
@Setter
@Component
@ConfigurationProperties("myapp.client")
public class MyAppConfigProperties {

    private String test;
}
