package ch.hearc.ig.guideresto.persistence.handlers.specializedHandlers;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.handlers.GenericHandler;

import javax.persistence.EntityManager;
import java.util.List;

public class RestaurantHandler extends GenericHandler<Restaurant> {
    public RestaurantHandler(EntityManager em) {
        super(em, Restaurant.class);
    }

    public List<Restaurant> findByName(String name) {
        try {
            return em.createQuery("SELECT r FROM Restaurant r WHERE r.name LIKE :name", Restaurant.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Restaurant> findByCity(String city) {
        try {
            return em.createQuery("SELECT r FROM Restaurant r WHERE r.address.city.cityName LIKE :city", Restaurant.class)
                    .setParameter("city", "%" + city + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Restaurant> findByType(RestaurantType type) {
        try {
            return em.createQuery("SELECT r FROM Restaurant r WHERE r.type.label LIKE :type", Restaurant.class)
                    .setParameter("type", "%" + type.getLabel() + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
