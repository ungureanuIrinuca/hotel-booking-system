-- Users
INSERT INTO users (id, name, username, email, password, user_type) VALUES
                                                                       (1, 'Mihai Popa', 'MihaiAdmin', 'admin@hotel.com', '$2a$10$2DVXKVMSd.RYHBt.Zcb9g.gPnGup59dg0NM9Fbf.Waly57GyzBS4a', 'ADMIN'),

                                                                       (2, 'Denis Popa', 'DenisHost', 'denis@hotel.com', '$2a$10$2DVXKVMSd.RYHBt.Zcb9g.gPnGup59dg0NM9Fbf.Waly57GyzBS4a', 'HOST'),
                                                                       (3, 'Maria Magdalena', 'MariaHost', 'maria@hotel.com', '$2a$10$2DVXKVMSd.RYHBt.Zcb9g.gPnGup59dg0NM9Fbf.Waly57GyzBS4a', 'HOST'),

                                                                       (4, 'Alex Alexandru', 'AlexGuest', 'alex@gmail.com', '$2a$10$2DVXKVMSd.RYHBt.Zcb9g.gPnGup59dg0NM9Fbf.Waly57GyzBS4a', 'CLIENT'),
                                                                       (5, 'Carmen Elena', 'CarmenGuest', 'carmen@gmail.com', '$2a$10$2DVXKVMSd.RYHBt.Zcb9g.gPnGup59dg0NM9Fbf.Waly57GyzBS4a', 'CLIENT'),
                                                                       (6, 'Ion Ionescu', 'IonGuest', 'ion@gmail.com', '$2a$10$2DVXKVMSd.RYHBt.Zcb9g.gPnGup59dg0NM9Fbf.Waly57GyzBS4a', 'CLIENT');

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
(1, '2027-06-01', '2027-06-05', 4, 1, 'ACTIVE'),

(2, '2028-06-10', '2028-06-15', 5, 2, 'ACTIVE'),

(3, '2029-07-01', '2029-07-03', 6, 4, 'ACTIVE'),

(4, '2030-08-15', '2030-08-20', 4, 3, 'ACTIVE');

--Sequence update for postgresql
SELECT setval('user_sequence', (SELECT MAX(id) FROM users));
SELECT setval('room_sequence', (SELECT MAX(id) FROM rooms));
SELECT setval('booking_sequence', (SELECT MAX(id) FROM bookings));