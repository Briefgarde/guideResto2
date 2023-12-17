package ch.hearc.ig.guideresto.persistence.handlers.specializedHandlers;

import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.handlers.GenericHandler;

import javax.persistence.EntityManager;

public class RestaurantTypeHandler extends GenericHandler<RestaurantType> {
    public RestaurantTypeHandler(EntityManager em) {
        super(em, RestaurantType.class);
    }
}
