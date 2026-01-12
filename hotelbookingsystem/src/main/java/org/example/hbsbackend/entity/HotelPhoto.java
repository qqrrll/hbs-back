package org.example.hbsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_photos")
@Data
@NoArgsConstructor
public class HotelPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    @Column(nullable = false)
    private byte[] data;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private boolean mainPhoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
}
