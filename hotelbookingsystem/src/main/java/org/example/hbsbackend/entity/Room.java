package org.example.hbsbackend.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomLevel level; // VIP, STANDARD, ECONOMY

    @Column(nullable = false)
    private int beds;
    @Column(nullable = false)
    private boolean occupied;
    @Column(nullable = false)
    private double pricePerNight;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(
            mappedBy = "room",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RoomPhoto> photos = new ArrayList<>();

    // Getters and Setters
}
