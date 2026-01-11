package org.example.hbsbackend.repository;

import org.example.hbsbackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
        SELECT b FROM Booking b
        JOIN b.rooms r
        WHERE r.id = :roomId
        AND b.startDate < :startDate
        AND b.endDate > :endDate
""")
    List<Booking> findOverlappingBookings(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
