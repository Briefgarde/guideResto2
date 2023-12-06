package ch.hearc.ig.guideresto.business;

import javax.persistence.*;
import java.time.LocalDate;

@Inheritance(strategy= InheritanceType.JOINED)
@Entity
@Table(name="EVALUATIONS")
public abstract class Evaluation {

  @Id
  @Column(name = "NUMERO", nullable = false)
  @GeneratedValue(  strategy= GenerationType.SEQUENCE,  generator="hibernate_seq_eval_general")
  @SequenceGenerator(name = "hibernate_seq_eval_general", sequenceName = "SEQ_EVAL", allocationSize = 1)
  private Integer id;

  @Column(name = "date_eval", nullable = false)
  private LocalDate visitDate;
  @ManyToOne
  @JoinColumn(name="FK_rest")
  private Restaurant restaurant;

  public Evaluation(Integer id, LocalDate visitDate, Restaurant restaurant) {
    this.id = id;
    this.visitDate = visitDate;
    this.restaurant = restaurant;
  }

  public Evaluation(){};

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getVisitDate() {
    return visitDate;
  }

  public void setVisitDate(LocalDate visitDate) {
    this.visitDate = visitDate;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }
}