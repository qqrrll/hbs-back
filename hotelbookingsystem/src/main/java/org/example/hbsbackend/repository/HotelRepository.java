package org.example.hbsbackend.repository;

import org.example.hbsbackend.entity.Hotel;
import org.example.hbsbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
