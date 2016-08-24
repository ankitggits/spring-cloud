package no.ankit.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by AB75448 on 24.08.2016.
*/
@Getter
@Setter
@Component
@ConfigurationProperties("myapp.server")
public class MyAppConfig {

    private String test;
}
