-- Users
INSERT INTO users (id, name, username, email, password, user_type) VALUES
                                                                       (1, 'Mihai Popa', 'MihaiAdmin', 'admin@hotel.com', 'admin_123', 'ADMIN'),

                                                                       (2, 'Denis Popa', 'DenisHost', 'denis@hotel.com', 'host111', 'HOST'),
                                                                       (3, 'Maria Magdalena', 'MariaHost', 'maria@hotel.com', 'host222', 'HOST'),

                                                                       (4, 'Alex Alexandru', 'AlexGuest', 'alex@gmail.com', 'guest111', 'CLIENT'),
                                                                       (5, 'Carmen Elena', 'CarmenGuest', 'carmen@gmail.com', 'guest222', 'CLIENT'),
                                                                       (6, 'Ion Ionescu', 'IonGuest', 'ion@gmail.com', 'guest333', 'CLIENT');

--Rooms
INSERT INTO rooms (id, room_number, host_id, room_type, price_per_night, capacity) VALUES
                                                                                       (1, '101A', 2, 'DOUBLE', 250.00, 2),
                                                                                       (2, '102B', 2, 'SUITE', 450.00, 4),

                                                                                       (3, '201A', 3, 'SINGLE', 150.00, 1),
                                                                                       (4, '202B', 3, 'DOUBLE', 220.00, 2);

-- Features
INSERT INTO room_features (room_id, feature) VALUES
                                                 (1, 'WiFi Gratuit'), (1, 'Balcon'),
                                                 (2, 'WiFi Gratuit'), (2, 'Jacuzzi'), (2, 'Vedere la mare'),
                                                 (3, 'WiFi Gratuit'),
                                                 (4, 'WiFi Gratuit'), (4, 'TV Smart'), (4, 'Minibar');

-- Bookings
INSERT INTO bookings (id, start_date, end_date, guest_id, room_id, status) VALUES
(1, '2024-06-01', '2024-06-05', 4, 1, 'ACCEPTED'),

(2, '2024-06-10', '2024-06-15', 5, 2, 'PENDING'),

(3, '2024-07-01', '2024-07-03', 6, 4, 'ACCEPTED'),

(4, '2024-08-15', '2024-08-20', 4, 3, 'REJECTED');

--Sequence update for postgresql
SELECT setval('user_sequence', (SELECT MAX(id) FROM users));
SELECT setval('room_sequence', (SELECT MAX(id) FROM rooms));
SELECT setval('booking_sequence', (SELECT MAX(id) FROM bookings));