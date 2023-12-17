package ch.hearc.ig.guideresto.persistence.handlers.specializedHandlers;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.persistence.handlers.GenericHandler;

import javax.persistence.EntityManager;
import java.util.List;

public class CityHandler extends GenericHandler<City> {
    public CityHandler(EntityManager em) {
        super(em, City.class);
    }

    public List<City> findCityByZipCode(String zipcode){
        try {
            return em.createQuery("SELECT c FROM City c WHERE c.zipCode LIKE :name", City.class)
                    .setParameter("name", "%" + zipcode + "%")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Failed to find City by zipCode : " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
