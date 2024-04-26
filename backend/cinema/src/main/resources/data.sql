INSERT INTO Movies (id, status, allowed_age, genre, length_minutes, name)
VALUES (1, true, 18, 'HORROR', 120, 'CHUCKY'),
       (2, true, 12, 'COMEDY', 120, 'MI POBRE ANGELITO'),
       (3, true, 18, 'ACTION', 120, 'RAMBO'),
       (4, true, 18, 'HORROR', 110, 'EL RESPLANDOR'),
       (5, true, 18, 'HORROR', 105, 'IT'),
       (6, true, 16, 'HORROR', 95, 'SILENT HILL'),
       (7, true, 18, 'HORROR', 100, 'ANABELLE'),
       (8, true, 16, 'HORROR', 98, 'INSIDIOUS');


INSERT INTO Rooms (id, status, name, number)
VALUES
        (1, true, 'SALA 1', 1),
        (2, true, 'SALA 2', 2),
        (3, true, 'SALA 3', 3),
        (4, true, 'SALA 4', 4),
        (5, true, 'SALA 5', 5);


INSERT INTO Seats (id, status, number, hall_number, room_id)
VALUES 
    (1, true, 4, 3, 1),
    (2, true, 2, 1, 1), 
    (3, true, 7, 10, 2), 
    (4, true, 3, 4, 2),
    (5, true, 8, 13, 3), 
    (6, true, 5, 3, 3),
    (7, true, 3, 6, 1), 
    (8, true, 8, 15, 1), 
    (9, true, 3, 9, 2), 
    (10, true, 1, 3, 2), 
    (11, true, 3, 1, 3), 
    (12, true, 2, 4, 3), 
    (13, true, 2, 3, 4),
    (14, true, 5, 6, 4), 
    (15, true, 2, 9, 5), 
    (16, true, 3, 7, 5); 


INSERT INTO Customers (id, status, document_number, name, lastname, age, phone_number, email)
VALUES 
    (1, true, '123456789', 'Juan', 'Perez', 30, '555-1234', 'juan.perez@example.com'),
    (2, true, '987654321', 'Maria', 'Lopez', 25, '555-5678', 'maria.lopez@example.com'),
    (3, true, '654321987', 'Carlos', 'Gomez', 35, '555-4321', 'carlos.gomez@example.com'),
    (4, true, '112233445', 'Laura', 'Fernández', 28, '555-8765', 'laura.fernandez@example.com');


INSERT INTO Billboards (id, status, date, start_time, end_time, movie_id, room_id)
VALUES 
    (1, true, '2024-04-26', '15:00:00', '17:00:00', 1, 1),
    (2, true, '2024-07-25', '18:00:00', '20:00:00', 2, 2),
    (3, true, '2024-08-23', '20:30:00', '22:30:00', 3, 3),
    (4, true, '2024-04-30', '22:30:00', '00:30:00', 4, 4), 
    (5, true, '2024-05-10', '14:00:00', '16:00:00', 5, 1), 
    (6, true, '2024-07-27', '17:00:00', '19:00:00', 6, 2), 
    (7, true, '2024-09-27', '20:00:00', '22:00:00', 7, 3),
    (8, true, '2024-10-26', '22:30:00', '00:00:00', 8, 5);


INSERT INTO Bookings (id, status, date, customer_id, seat_id, billboard_id)
VALUES 
    (1, true, '2024-04-26', 1, 1, 1),
    (2, true, '2024-10-05', 2, 3, 2),
    (3, true, '2024-08-01', 3, 3, 4), 
    (4, true, '2024-06-16', 4, 6, 6), 
    (5, true, '2024-04-30', 1, 2, 5), 
    (6, true, '2024-05-11', 2, 4, 7), 
    (7, true, '2024-05-19', 3, 3, 8), 
    (8, true, '2024-08-30', 4, 5, 6),
    (9, true, '2024-03-27', 1, 4, 3),
    (10, true, '2024-09-01', 2, 2, 2),
    (11, true, '2024-07-10', 3, 1, 1),
    (12, true, '2024-08-04', 4, 6, 4);


