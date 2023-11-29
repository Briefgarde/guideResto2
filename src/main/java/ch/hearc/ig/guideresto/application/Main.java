package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.persistence.hibernate.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {

  public static void main(String[] args) {
//    var scanner = new Scanner(System.in);
//    var fakeItems = new FakeItems();
//    var printStream = System.out;
//    var cli = new CLI(scanner, printStream, fakeItems);
//
//    cli.start();
    EntityManager em = JpaUtils.getEntityManager();
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    Restaurant restaurant = em.find(Restaurant.class, 1);
    Restaurant restaurant1 = em.find(Restaurant.class, 3);
    // why does it loads the Restaurant eagerly in City ? I've only loaded two restaurant here,
    // but if you go to their city (they have the same city), it alredy has all three loaded (all of them currently)




    System.out.println("Whatever");

    em.flush();
    transaction.commit();
    em.close();


  }
}
