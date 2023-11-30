package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venue")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_id")
    private Long venueId;

    @Column(name = "place_name", nullable = false)
    private String placeName;

    @Column(name = "people_capacity", nullable = false)
    private int peopleCapacity;

    @ManyToOne
    @JoinColumn(name = "country_city_id", nullable = false)
    private CountryCity countryCity;

    @OneToMany(mappedBy = "venue")
    private List<Concert> concerts;

    public Venue(){
    }

    public Venue(String placeName, int peopleCapacity, CountryCity countryCity) {
        this.placeName = placeName;
        this.peopleCapacity = peopleCapacity;
        this.countryCity = countryCity;
    }

    @Override
    public String toString() {
        return "Venue[" +
                "venueId=" + venueId +
                ", placeName='" + placeName + '\'' +
                ", capacity=" + peopleCapacity +
                ']';
    }

    public Long getVenueId() {
        return venueId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public CountryCity getCountryCity() {
        return countryCity;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setPeopleCapacity(int peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }

    public List<Concert> getConcerts() {
        return concerts;
    }
}
