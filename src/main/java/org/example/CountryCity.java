package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country_city")
public class CountryCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_city_id")
    private Long countryCityId;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(mappedBy = "countryCity", fetch = FetchType.EAGER)
    private List<Venue> venues;

    public CountryCity(){
    }

    public CountryCity(String city, String country) {
        this.city = city;
        this.country = country;
        this.venues = new ArrayList<>(); // Инициализация списка venues
    }

    @Override
    public String toString() {
        return "CountryCity[" +
                "countryCityId=" + countryCityId +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ']';
    }

    public Long getCountryCityId() {
        return countryCityId;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountryCityId(Long countryCityId) {
        this.countryCityId = countryCityId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Venue> getVenues() {
        return venues;
    }
}
