package org.example.hbsbackend.repository;

import org.example.hbsbackend.entity.RoomPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPhotoRepository
        extends JpaRepository<RoomPhoto, Long> {
}
