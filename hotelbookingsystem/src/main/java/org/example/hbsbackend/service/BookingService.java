package org.example.hbsbackend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.BookingCreateDTO;
import org.example.hbsbackend.dto.BookingDTO;
import org.example.hbsbackend.entity.*;
import org.example.hbsbackend.repository.BookingRepository;
import org.example.hbsbackend.repository.RoomRepository;
import org.example.hbsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public boolean isRoomAvailable(Long roomId, LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findOverlappingBookings(roomId, startDate, endDate).isEmpty();
    }
    public List<BookingDTO> getAllBookings(){
        List<BookingDTO> bookingDTO = new ArrayList<>();
        for(Booking booking : bookingRepository.findAll()){
            bookingDTO.add(BookingDTO.convertToDTO(booking));
        }
        return bookingDTO;
    }
    public BookingDTO getBookingById(Long id){
        return BookingDTO.convertToDTO(bookingRepository.findById(id).get());
    }

    @Transactional
    public BookingDTO createBooking(BookingCreateDTO bookingDTO){
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Room> rooms = roomRepository.findAllById(bookingDTO.getRoomIds());
        if(rooms.isEmpty()){
            throw new RuntimeException("Rooms not found");
        }
        for(Room room : rooms){
            if(!isRoomAvailable(room.getId(), bookingDTO.getStartDate(), bookingDTO.getEndDate())){
                throw new RuntimeException("Room " + room.getId() + " is not available");
            }
        }
        Long days = ChronoUnit.DAYS.between(bookingDTO.getStartDate(), bookingDTO.getEndDate());
        if (days <= 0){
            throw new RuntimeException("Days must be greater than 0");
        }
        double totalPrice = 0;
        for(Room room : rooms){
            totalPrice += room.getPricePerNight();
        }
        totalPrice *= days;
        for (Room room : rooms) {
            Hotel hotel = room.getHotel();

        }
        Hotel hotel = rooms.get(0).getHotel();
        for(Room room : rooms){
            if(!room.getHotel().getId().equals(hotel.getId())){
                throw new RuntimeException("All rooms must be from the same hotel");
            }
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setHotel(hotel);
        booking.setRooms(rooms);
        booking.setRoomCount(rooms.size());
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setTotalPrice(totalPrice);
        return BookingDTO.convertToDTO(bookingRepository.save(booking));
    }
    @Transactional
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO){
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setStartDate(bookingDTO.getStartDate());
        return BookingDTO.convertToDTO(bookingRepository.save(booking));
    }
    public void deleteBooking(Long id){
        bookingRepository.deleteById(id);
    }
}
