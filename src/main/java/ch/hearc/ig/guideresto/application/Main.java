package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.persistence.FakeItems;
import ch.hearc.ig.guideresto.persistence.hibernate.JpaUtils;
import ch.hearc.ig.guideresto.presentation.CLI;
import ch.hearc.ig.guideresto.presentation.CLI2;


import javax.persistence.*;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    var scanner = new Scanner(System.in);
    var fakeItems = new FakeItems();
    var printStream = System.out;
    System.out.println("I have changed the structure of the DB.");
    System.out.println("Please run the CREATE_TABLE and INSERT_DATA sql script first to make it work !");
    System.out.println("Choose your type : Base=1 or Updated=2 by my careful work : ");
    //This is just a quick way for me to check how a functionalities work in the "correct" way, without rewriting the main
    String input = scanner.nextLine();
    if (input.equals("1")){
      var cli = new CLI(scanner, printStream, fakeItems);
      cli.start();
    } else {
      EntityManager em = JpaUtils.getEntityManager();
      var cli2 = new CLI2(scanner, printStream, em);
      cli2.start();
      // this ^^ will take the "spotlight" for as long as it run
      // then vv it'll close to let the program end
      em.close();
    }





//    EntityManager em = JpaUtils.getEntityManager();
//
//    EntityTransaction transaction = em.getTransaction();
//    transaction.begin();







//    CompleteEvaluation completeEvaluation = new CompleteEvaluation();
//    completeEvaluation.setComment("I HATE MY JOB");
//    completeEvaluation.setRestaurant(restaurant);
//    completeEvaluation.setUsername("TITITITITIT");
//    completeEvaluation.setVisitDate(LocalDate.now());
//    completeEvaluation.setGrades(new HashSet<Grade>());
//    Grade grade = new Grade();
//    grade.setGrade(1);
//    EvaluationCriteria crit1 = em.find(EvaluationCriteria.class, 1);
//    grade.setCriteria(crit1);
//    grade.setEvaluation(completeEvaluation);
//    completeEvaluation.getGrades().add(grade);
//    em.persist(completeEvaluation);
//    em.persist(grade);






//    em.flush();
//    transaction.commit();
//    em.close();


  }
}
