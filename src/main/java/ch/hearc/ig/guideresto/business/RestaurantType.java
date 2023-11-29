package ch.hearc.ig.guideresto.business;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TYPES_GASTRONOMIQUES")
public class RestaurantType {
    @Id
    @Column(name = "NUMERO", nullable = false)
    @GeneratedValue(  strategy= GenerationType.SEQUENCE,  generator="hibernate_seq_rest_type")
    @SequenceGenerator(name = "hibernate_seq_rest_type", sequenceName = "SEQ_TYPES_GASTRONOMIQUES", allocationSize = 1)
    private Integer id;
    @Column(name = "LIBELLE", nullable = false)
    private String label;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @OneToMany(mappedBy = "type")
    private Set<Restaurant> restaurants;

    public RestaurantType(Integer id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.restaurants = new HashSet<>();
    }
    public RestaurantType() {
    }
    
    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantType that = (RestaurantType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}