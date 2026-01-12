package org.example.hbsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_photos")
@Data
@NoArgsConstructor
public class RoomPhoto {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(nullable = false)
    private boolean mainPhoto;
}

