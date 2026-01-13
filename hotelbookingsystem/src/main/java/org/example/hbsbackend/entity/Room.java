package org.example.hbsbackend.entity;
import jakarta.persistence.*;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private RoomLevel level;

    @Column(nullable = false)
    private int beds;

    @Column(nullable = false)
    private boolean occupied;

    @Column(nullable = false)
    private double pricePerNight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(
            mappedBy = "room",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"room", "data"})
    private List<RoomPhoto> photos = new ArrayList<>();
}
