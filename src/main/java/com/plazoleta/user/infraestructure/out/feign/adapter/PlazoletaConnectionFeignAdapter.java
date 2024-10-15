package com.plazoleta.user.infraestructure.out.feign.adapter;

import com.plazoleta.user.domain.exception.ExternalConnectionException;
import com.plazoleta.user.domain.model.external.Restaurant;
import com.plazoleta.user.domain.spi.IPlazoletaConnectionPort;
import com.plazoleta.user.infraestructure.out.feign.client.IPlazoletaConnectionFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PlazoletaConnectionFeignAdapter implements IPlazoletaConnectionPort {

   private final IPlazoletaConnectionFeignClient plazoletaConnectionFeignClient;

    @Override
    public Optional<Restaurant> findRestaurantByOwnerId(Long ownerId) {
        try {
           Restaurant restaurant = plazoletaConnectionFeignClient.findRestaurantByOwnerId(ownerId);
            return Optional.ofNullable(restaurant);
        } catch (FeignException.NotFound ex) {
            return Optional.empty();
        } catch (Exception ex) {
            throw new ExternalConnectionException();
        }
    }

    @Override
    public Optional<Restaurant> findRestaurantById(Long restaurantId) {
        try {
            Restaurant restaurant = plazoletaConnectionFeignClient.findRestaurantById(restaurantId);
            return Optional.ofNullable(restaurant);
        } catch (FeignException.NotFound ex) {
            return Optional.empty();
        } catch (Exception ex) {
            throw new ExternalConnectionException();
        }
    }
}
