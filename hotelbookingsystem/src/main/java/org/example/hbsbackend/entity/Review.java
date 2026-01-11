package org.example.hbsbackend.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*    @Column(nullable = false)*/
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

/*    @Column(nullable = false)*/
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(nullable = false)
    private int rating; // 1-5

    @Column(nullable = false)
    private String text;

//    @ElementCollection
//    private List<String> photos;

    // Getters and Setters
}