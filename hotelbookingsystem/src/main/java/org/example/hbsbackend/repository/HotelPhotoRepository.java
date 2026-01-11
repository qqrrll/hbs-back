package org.example.hbsbackend.repository;

import org.example.hbsbackend.entity.HotelPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelPhotoRepository
        extends JpaRepository<HotelPhoto, Long> {
}
