package ch.hearc.ig.guideresto.business;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "VILLES")
public class City {
    @Id
    @Column(name = "NUMERO", nullable = false)
    @GeneratedValue(  strategy= GenerationType.SEQUENCE,  generator="hibernate_seq_ville")
    @SequenceGenerator(name = "hibernate_seq_ville", sequenceName = "SEQ_VILLES", allocationSize = 1)
    private Integer id;
    @Column(name = "CODE_POSTAL", nullable = false)
    private String zipCode;
    @Column(name = "NOM_VILLE", nullable = false)
    private String cityName;
    @OneToMany(mappedBy = "address.city", fetch = FetchType.LAZY)
    private Set<Restaurant> restaurants;

    public City(){}

    public City(Integer id, String zipCode, String cityName) {
        this.id = id;
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.restaurants = new HashSet<>();
    }

    public static City createCity(String zipCode, String name){
        City retour = new City();
        retour.setZipCode(zipCode);
        retour.setCityName(name);
        retour.setRestaurants(new HashSet<>());
        return retour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(zipCode, city.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zipCode);
    }
}