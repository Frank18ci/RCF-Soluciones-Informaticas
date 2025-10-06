package com.rcf.schedulesservice.client.configuration;

import com.rcf.schedulesservice.client.exception.RetreiveMessageErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public ErrorDecoder errorResponse() {
        return new RetreiveMessageErrorDecoder();
    }
}
