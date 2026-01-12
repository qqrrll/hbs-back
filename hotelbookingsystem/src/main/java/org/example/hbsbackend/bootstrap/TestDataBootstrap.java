package org.example.hbsbackend.bootstrap;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.entity.*;
import org.example.hbsbackend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class TestDataBootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // ❗ НЕ трогаем admin
        if (userRepository.existsByEmail("user@test.com")) {
            return;
        }

        // 👤 USER
        User user = new User();
        user.setEmail("user@test.com");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("+70000000001");
        user.setRole(Role.USER);

        userRepository.save(user);

        // 🏨 HOTEL
        Hotel hotel = new Hotel();
        hotel.setName("Grand Hotel");
        hotel.setCity("Almaty");
        hotel.setAddress("Abay 10");
        hotel.setStars(5);
        hotel.setRating(4.8);
        hotel.setRoomCount(2);
        hotel.setOccupied(false);

        hotelRepository.save(hotel);

        // 🛏 ROOMS
        Room room1 = new Room();
        room1.setHotel(hotel);
        room1.setNumber("101");
        room1.setBeds(2);
        room1.setPricePerNight(12000);
        room1.setOccupied(false);
        room1.setLevel(RoomLevel.STANDARD);

        Room room2 = new Room();
        room2.setHotel(hotel);
        room2.setNumber("102");
        room2.setBeds(3);
        room2.setPricePerNight(18000);
        room2.setOccupied(false);
        room2.setLevel(RoomLevel.VIP);

        roomRepository.saveAll(List.of(room1, room2));

        // 📅 BOOKING
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setHotel(hotel);
        booking.setRooms(List.of(room1));
        booking.setStartDate(LocalDate.now());
        booking.setEndDate(LocalDate.now().plusDays(3));
        booking.setRoomCount(1);
        booking.setTotalPrice(36000);

        bookingRepository.save(booking);

        // ⭐ REVIEW
        Review review = new Review();
        review.setHotel(hotel);
        review.setUser(user);
        review.setRating(5);
        review.setText("Amazing hotel, highly recommend!");

        reviewRepository.save(review);

        System.out.println("✅ TEST DATA LOADED");
    }
}
