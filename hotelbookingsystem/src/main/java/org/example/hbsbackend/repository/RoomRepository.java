package org.example.hbsbackend.repository;

import org.example.hbsbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("""
select r from Room r
""")
    List<Room> findAllSafe();
    List<Room> findByHotelIdAndOccupiedFalse(Long hotelId);

    List<Room> findByHotelIdAndOccupiedTrue(Long hotelId);
}
