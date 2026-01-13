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

        if (userRepository.existsByEmail("ivan@test.com")) {
            System.out.println("ℹ️ Тестовые данные уже есть");
            return;
        }

        // ================= USERS =================
        User ivan = user("ivan@test.com", "Иван", "Иванов", "+77010000001");
        User maria = user("maria@test.com", "Мария", "Петрова", "+77010000002");
        User alexey = user("alex@test.com", "Алексей", "Сидоров", "+77010000003");
        User olga = user("olga@test.com", "Ольга", "Кузнецова", "+77010000004");
        User sergey = user("sergey@test.com", "Сергей", "Морозов", "+77010000005");
        User anna = user("anna@test.com", "Анна", "Смирнова", "+77010000006");
        User dmitry = user("dmitry@test.com", "Дмитрий", "Волков", "+77010000007");
        User elena = user("elena@test.com", "Елена", "Орлова", "+77010000008");

        userRepository.saveAll(List.of(
                ivan, maria, alexey, olga,
                sergey, anna, dmitry, elena
        ));

        // ================= HOTELS =================
        Hotel h1 = hotel("Гранд Отель Алматы", "Алматы", "пр. Абая 10", 5, 4.9);
        Hotel h2 = hotel("Отель Байтерек", "Астана", "ул. Достык 5", 4, 4.5);
        Hotel h3 = hotel("Северный Ветер", "Петропавловск", "ул. Ленина 15", 3, 4.2);
        Hotel h4 = hotel("Южный Берег", "Шымкент", "ул. Тауке Хана 22", 4, 4.6);
        Hotel h5 = hotel("Каспий Палас", "Актау", "мкр. 12, дом 7", 5, 4.8);
        Hotel h6 = hotel("Горный Приют", "Алматы", "ул. Медеу 1", 4, 4.7);

        hotelRepository.saveAll(List.of(h1, h2, h3, h4, h5, h6));

        // ================= ROOMS =================
        List<Room> rooms = List.of(
                room(h1,"101",2,15000,RoomLevel.STANDARD),
                room(h1,"102",3,25000,RoomLevel.VIP),
                room(h1,"103",1,10000,RoomLevel.ECONOMY),
                room(h1,"104",4,30000,RoomLevel.VIP),

                room(h2,"201",2,14000,RoomLevel.STANDARD),
                room(h2,"202",3,22000,RoomLevel.VIP),
                room(h2,"203",1,9000,RoomLevel.ECONOMY),
                room(h2,"204",4,28000,RoomLevel.VIP),

                room(h3,"301",2,12000,RoomLevel.STANDARD),
                room(h3,"302",1,8000,RoomLevel.ECONOMY),
                room(h3,"303",3,20000,RoomLevel.VIP),
                room(h3,"304",2,13000,RoomLevel.STANDARD),

                room(h4,"401",2,16000,RoomLevel.STANDARD),
                room(h4,"402",3,24000,RoomLevel.VIP),
                room(h4,"403",1,9500,RoomLevel.ECONOMY),
                room(h4,"404",4,29000,RoomLevel.VIP),

                room(h5,"501",2,18000,RoomLevel.STANDARD),
                room(h5,"502",3,26000,RoomLevel.VIP),
                room(h5,"503",1,11000,RoomLevel.ECONOMY),
                room(h5,"504",4,35000,RoomLevel.VIP),

                room(h6,"601",2,17000,RoomLevel.STANDARD),
                room(h6,"602",3,27000,RoomLevel.VIP),
                room(h6,"603",1,10500,RoomLevel.ECONOMY),
                room(h6,"604",4,32000,RoomLevel.VIP)
        );

        roomRepository.saveAll(rooms);

        // ================= BOOKINGS =================
        bookingRepository.saveAll(List.of(
                booking(ivan,h1,rooms.get(0),3),
                booking(maria,h2,rooms.get(5),2),
                booking(alexey,h3,rooms.get(10),4),
                booking(olga,h4,rooms.get(15),3),
                booking(sergey,h5,rooms.get(18),5),
                booking(anna,h6,rooms.get(21),2),
                booking(dmitry,h1,rooms.get(1),1),
                booking(elena,h2,rooms.get(6),4),
                booking(ivan,h5,rooms.get(19),3),
                booking(maria,h6,rooms.get(23),2)
        ));

        // ================= REVIEWS =================
        reviewRepository.saveAll(List.of(
                review(ivan,h1,5,"Шикарный отель, всё на высшем уровне."),
                review(maria,h2,4,"Хорошо, но хотелось бы лучше завтрак."),
                review(alexey,h3,5,"Очень уютно и тихо."),
                review(olga,h4,4,"Отличное расположение."),
                review(sergey,h5,5,"Лучший сервис, который я видел."),
                review(anna,h6,5,"Потрясающий вид на горы."),
                review(dmitry,h1,4,"Чисто, комфортно."),
                review(elena,h2,4,"Хороший отель за свои деньги."),
                review(ivan,h3,5,"Вернусь ещё раз."),
                review(maria,h4,4,"Приятный персонал."),
                review(alexey,h5,5,"Роскошно!"),
                review(olga,h6,5,"Очень понравилось."),
                review(sergey,h2,3,"Нормально, но без восторга."),
                review(anna,h1,5,"Супер!"),
                review(dmitry,h4,4,"Рекомендую.")
        ));

        System.out.println("✅ ОГРОМНЫЙ НАБОР ТЕСТОВЫХ ДАННЫХ СОЗДАН");
    }

    // ================= HELPERS =================

    private User user(String email, String first, String last, String phone) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode("password123"));
        u.setFirstName(first);
        u.setLastName(last);
        u.setPhone(phone);
        u.setRole(Role.USER);
        return u;
    }

    private Hotel hotel(String name, String city, String address, int stars, double rating) {
        Hotel h = new Hotel();
        h.setName(name);
        h.setCity(city);
        h.setAddress(address);
        h.setStars(stars);
        h.setRating(rating);
        h.setRoomCount(20);
        h.setOccupied(false);
        return h;
    }

    private Room room(Hotel h, String number, int beds, double price, RoomLevel level) {
        Room r = new Room();
        r.setHotel(h);
        r.setNumber(number);
        r.setBeds(beds);
        r.setPricePerNight(price);
        r.setOccupied(false);
        r.setLevel(level);
        return r;
    }

    private Booking booking(User u, Hotel h, Room r, int days) {
        Booking b = new Booking();
        b.setUser(u);
        b.setHotel(h);
        b.setRooms(List.of(r));
        b.setRoomCount(1);
        b.setStartDate(LocalDate.now().plusDays(1));
        b.setEndDate(LocalDate.now().plusDays(1 + days));
        b.setTotalPrice(r.getPricePerNight() * days);
        return b;
    }

    private Review review(User u, Hotel h, int rating, String text) {
        Review r = new Review();
        r.setUser(u);
        r.setHotel(h);
        r.setRating(rating);
        r.setText(text);
        return r;
    }
}
