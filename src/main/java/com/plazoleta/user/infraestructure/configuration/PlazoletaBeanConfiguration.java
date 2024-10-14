package com.plazoleta.user.infraestructure.configuration;


import com.plazoleta.user.domain.spi.IPlazoletaConnectionPort;
import com.plazoleta.user.infraestructure.out.feign.adapter.PlazoletaConnectionFeignAdapter;
import com.plazoleta.user.infraestructure.out.feign.client.IPlazoletaConnectionFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PlazoletaBeanConfiguration {

    private final IPlazoletaConnectionFeignClient plazoletaConnectionFeignClient;

    @Bean
    IPlazoletaConnectionPort plazoletaConnectionPort(){
        return new PlazoletaConnectionFeignAdapter(plazoletaConnectionFeignClient);
    }
}
