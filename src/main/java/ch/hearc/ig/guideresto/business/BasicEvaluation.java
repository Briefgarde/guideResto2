package ch.hearc.ig.guideresto.business;

import ch.hearc.ig.guideresto.persistence.hibernate.BooleanConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "fk_eval")
@Table(name = "basiceval")
public class BasicEvaluation extends Evaluation {

  @Column(name = "isLiked", nullable = false)
  @Convert(converter = BooleanConverter.class)
  private boolean likeRestaurant;
  @Column(name = "address_ip", nullable = false)
  private String ipAddress;

  public BasicEvaluation(Integer id, LocalDate visitDate, Restaurant restaurant, boolean likeRestaurant,
      String ipAddress) {
    super(id, visitDate, restaurant);
    this.likeRestaurant = likeRestaurant;
    this.ipAddress = ipAddress;
  }

  public BasicEvaluation(){};

  public void setLikeRestaurant(boolean likeRestaurant) {
    this.likeRestaurant = likeRestaurant;
  }



  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public Boolean isLikeRestaurant() {
    return likeRestaurant;
  }

}