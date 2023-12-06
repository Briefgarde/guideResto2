package ch.hearc.ig.guideresto.business;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@PrimaryKeyJoinColumn(name = "fk_eval")
@Table(name = "complete_eval")
public class CompleteEvaluation extends Evaluation {

  @Column(name = "commentaire")
  private String comment;
  @Column(name = "username")
  private String username;
  @Transient
  private Set<Grade> grades;

  public CompleteEvaluation(Integer id, LocalDate visitDate, Restaurant restaurant, String comment,
      String username) {
    super(id, visitDate, restaurant);
    this.comment = comment;
    this.username = username;
    this.grades = new HashSet<>();
  }

  public CompleteEvaluation(){};

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setGrades(Set<Grade> grades) {
    this.grades = grades;
  }

  public String getComment() {
    return comment;
  }

  public String getUsername() {
    return username;
  }

  public Set<Grade> getGrades() {
    return grades;
  }
}