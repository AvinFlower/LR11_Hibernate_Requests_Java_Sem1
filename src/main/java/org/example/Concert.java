package org.example;

import javax.persistence.*;
import java.time.Instant;
import java.sql.Date;

@Entity
@Table(name = "concert")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_id")
    private Long concertId;

    @Column(name = "concert_name", nullable = false)
    private String concertName;

    @Column(name = "concert_date", nullable = false)
    private Date concertDate;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    public Concert() {
    }

    public Concert(String concertName, Date concertDate, Venue venue) {
        this.concertName = concertName;
        this.concertDate = concertDate;
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "Concert[" +
                "concertName='" + concertName + '\'' +
                ", date=" + concertDate +
                ']';
    }

    public Long getConcertId() {
        return concertId;
    }

    public String getConcertName() {
        return concertName;
    }

    public Date getConcertDate() {
        return concertDate;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public void setConcertDate(Date concertDate) {
        this.concertDate = concertDate;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
