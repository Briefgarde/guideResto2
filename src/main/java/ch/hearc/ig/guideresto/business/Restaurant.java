package ch.hearc.ig.guideresto.business;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "RESTAURANTS")
public class Restaurant {

    @Id
    @Column(name = "NUMERO", nullable = false)
    @GeneratedValue(  strategy= GenerationType.SEQUENCE,  generator="hibernate_seq_restaurant")
    @SequenceGenerator(name = "hibernate_seq_restaurant", sequenceName = "SEQ_RESTAURANTS", allocationSize = 1)
    private Integer id;
    @Column(name = "NOM", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "SITE_WEB", nullable = false)
    private String website;
    @OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
    private Set<Evaluation> evaluations;
    @Embedded
    private Localisation address;

    @ManyToOne
    @JoinColumn(name = "FK_TYPE", nullable = false)
    private RestaurantType type;

    public Restaurant(Integer id, String name, String description, String website, String street, City city, RestaurantType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.website = website;
        this.evaluations = new HashSet<>();
        this.address = new Localisation(street, city);
        this.type = type;
    }

    public Restaurant() {
    }

    public Integer getId() {
        return id;
    }

    public static Restaurant createRestaurant(String name, String description, String website, String street, City city, RestaurantType type) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setWebsite(website);
        Localisation localisation = new Localisation();
        localisation.setStreet(street);
        localisation.setCity(city);
        restaurant.setAddress(localisation);
        restaurant.setType(type);
        restaurant.setEvaluations(new HashSet<>());
        return restaurant;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public void setAddress(Localisation address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getZipCode() {
        return address.getCity().getZipCode();
    }

    public String getStreet() {
        return address.getStreet();
    }

    public String getCityName() {
        return address.getCity().getCityName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public Localisation getAddress() {
        return address;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}