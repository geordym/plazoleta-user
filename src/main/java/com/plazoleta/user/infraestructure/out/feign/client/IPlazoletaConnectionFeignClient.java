package com.plazoleta.user.infraestructure.out.feign.client;


import com.plazoleta.user.domain.model.external.Restaurant;
import com.plazoleta.user.infraestructure.out.feign.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-API", url = "${external.plazoleta.api.base-url}", configuration = FeignClientConfig.class)
public interface IPlazoletaConnectionFeignClient {

    @GetMapping(value = "/api/restaurant/owner/{ownerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Restaurant findRestaurantByOwnerId(@PathVariable Long ownerId);

    @GetMapping(value = "/api/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Restaurant findRestaurantById(@PathVariable Long restaurantId);

}
