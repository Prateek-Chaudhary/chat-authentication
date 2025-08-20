package com.prateek.authentication.configs;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ConfigurationProperties(prefix = "jwt")
@Component
public class SecretKeyConfigs {

    private String activeKey;
    private Map<String, String> secretKeys;
}
