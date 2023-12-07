package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.Evaluation;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.persistence.hibernate.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
//    var scanner = new Scanner(System.in);
//    var fakeItems = new FakeItems();
//    var printStream = System.out;
//    var cli = new CLI(scanner, printStream, fakeItems);
//    cli.start();

    // BEFORE LAUNCHING THIS PROGRAM !
    // IT uses a NEW SCHEMA for the DB !
    // Please run the CREATE_TABLE and INSERT_DATA sql script to make it work !
    EntityManager em = JpaUtils.getEntityManager();
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    Restaurant restaurant = em.find(Restaurant.class, 1);
    List<Restaurant> restaurantList = em.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();



    System.out.println("Whatever");

    em.flush();
    transaction.commit();
    em.close();


  }
}
