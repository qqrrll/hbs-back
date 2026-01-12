-- =========================
-- CLEANUP (DEV ONLY)
-- =========================
DELETE FROM booking_rooms;
DELETE FROM bookings;
DELETE FROM reviews;
DELETE FROM refresh_tokens;
DELETE FROM room_photos;
DELETE FROM hotel_photos;
DELETE FROM rooms;
DELETE FROM hotels;
DELETE FROM users;

-- =========================
-- USERS
-- password = admin123
-- =========================
INSERT INTO users (id, first_name, last_name, email, phone, password, role)
VALUES
    (1, 'Admin', 'Root', 'admin@test.com', '+70000000001',
     '$2a$10$7xZyGx5YxZ6yE0M6o5Zx7uP7c5Zc5zJz2o7XkZ0wJZy3l8k4m', 'ADMIN'),

    (2, 'John', 'Doe', 'john@test.com', '+70000000002',
     '$2a$10$7xZyGx5YxZ6yE0M6o5Zx7uP7c5Zc5zJz2o7XkZ0wJZy3l8k4m', 'USER'),

    (3, 'Alice', 'Smith', 'alice@test.com', '+70000000003',
     '$2a$10$7xZyGx5YxZ6yE0M6o5Zx7uP7c5Zc5zJz2o7XkZ0wJZy3l8k4m', 'USER');

-- =========================
-- HOTELS
-- =========================
INSERT INTO hotels (id, name, city, address, room_count, is_occupied, stars, rating)
VALUES
    (1, 'Grand Hotel', 'Paris', '1 Champs-Élysées', 0, false, 5, 4.8),
    (2, 'Sea View Resort', 'Barcelona', '25 Ocean Drive', 0, false, 4, 4.4);

-- =========================
-- ROOMS
-- =========================
INSERT INTO rooms (id, number, level, beds, occupied, price_per_night, hotel_id)
VALUES
    (1, '101', 'STANDARD', 2, false, 120, 1),
    (2, '102', 'VIP', 3, false, 250, 1),
    (3, '201', 'ECONOMY', 1, false, 80, 2),
    (4, '202', 'STANDARD', 2, false, 150, 2);

-- =========================
-- UPDATE room_count
-- =========================
UPDATE hotels
SET room_count = (
    SELECT COUNT(*) FROM rooms WHERE rooms.hotel_id = hotels.id
);

-- =========================
-- HOTEL PHOTOS (fake blobs)
-- =========================
INSERT INTO hotel_photos (id, data, content_type, main_photo, hotel_id)
VALUES
    (1, decode('FFD8FFE0', 'hex'), 'image/jpeg', true, 1),
    (2, decode('FFD8FFE0', 'hex'), 'image/jpeg', true, 2);

-- =========================
-- ROOM PHOTOS
-- =========================
INSERT INTO room_photos (id, data, content_type, main_photo, room_id)
VALUES
    (1, decode('FFD8FFE0', 'hex'), 'image/jpeg', true, 1),
    (2, decode('FFD8FFE0', 'hex'), 'image/jpeg', true, 3);

-- =========================
-- BOOKINGS
-- =========================
INSERT INTO bookings (id, hotel_id, user_id, room_count, total_price, start_date, end_date)
VALUES
    (1, 1, 2, 1, 240, '2026-02-01', '2026-02-03');

-- =========================
-- BOOKING ↔ ROOMS
-- =========================
INSERT INTO booking_rooms (booking_id, room_id)
VALUES
    (1, 1);

-- =========================
-- REVIEWS
-- =========================
INSERT INTO reviews (id, user_id, hotel_id, rating, text)
VALUES
    (1, 2, 1, 5, 'Amazing hotel!'),
    (2, 3, 2, 4, 'Great view, nice staff.');

-- =========================
-- REFRESH TOKENS
-- =========================
INSERT INTO refresh_tokens (id, user_id, token, expiry_date)
VALUES
    (1, 1, 'admin-refresh-token', NOW() + INTERVAL '7 days'),
    (2, 2, 'user-refresh-token', NOW() + INTERVAL '7 days');
