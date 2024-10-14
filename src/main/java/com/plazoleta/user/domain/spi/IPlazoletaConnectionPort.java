package com.plazoleta.user.domain.spi;

import com.plazoleta.user.domain.model.external.Restaurant;

import java.util.Optional;

public interface IPlazoletaConnectionPort {

    Optional<Restaurant> findRestaurantByOwnerId(Long ownerId);
    Optional<Restaurant> findRestaurantById(Long ownerId);

}
