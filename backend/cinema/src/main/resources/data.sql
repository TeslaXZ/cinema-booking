INSERT INTO Movies ( status, allowed_age, genre, length_minutes, name)
VALUES ( true, 18, 'HORROR', 120, 'CHUCKY'),
       ( true, 12, 'COMEDY', 120, 'MI POBRE ANGELITO'),
       ( true, 18, 'ACTION', 120, 'RAMBO'),
       ( true, 18, 'HORROR', 110, 'EL RESPLANDOR'),
       ( true, 18, 'HORROR', 105, 'IT'),
       ( true, 16, 'HORROR', 95, 'SILENT HILL'),
       ( true, 18, 'HORROR', 100, 'ANABELLE'),
       ( true, 16, 'HORROR', 98, 'INSIDIOUS');


INSERT INTO Rooms (status, name, number)
VALUES
        ( true, 'SALA 1', 1),
        ( true, 'SALA 2', 2),
        ( true, 'SALA 3', 3),
        ( true, 'SALA 4', 4),
        ( true, 'SALA 5', 5);


INSERT INTO Seats (status, number, hall_number, room_id)
VALUES 
    ( true, 4, 3, 1),
    ( true, 2, 1, 1), 
    ( true, 7, 10, 2), 
    (true, 3, 4, 2),
    (true, 8, 13, 3), 
    ( true, 5, 3, 3),
    ( true, 3, 6, 1), 
    (true, 8, 15, 1), 
    ( true, 3, 9, 2), 
    (true, 1, 3, 2), 
    ( true, 3, 1, 3), 
    ( true, 2, 4, 3), 
    ( true, 2, 3, 4),
    ( true, 5, 6, 4), 
    ( true, 2, 9, 5), 
    ( true, 3, 7, 5); 


INSERT INTO Customers ( status, document_number, name, lastname, age, phone_number, email)
VALUES 
    (true, '123456789', 'Juan', 'Perez', 30, '555-1234', 'juan.perez@example.com'),
    (true, '987654321', 'Maria', 'Lopez', 25, '555-5678', 'maria.lopez@example.com'),
    (true, '654321987', 'Carlos', 'Gomez', 35, '555-4321', 'carlos.gomez@example.com'),
    (true, '112233445', 'Laura', 'Fernández', 28, '555-8765', 'laura.fernandez@example.com');


INSERT INTO Billboards ( status, date, start_time, end_time, movie_id, room_id)
VALUES 
    ( true, '2024-04-26', '15:00:00', '17:00:00', 1, 1),
    ( true, '2024-07-25', '18:00:00', '20:00:00', 2, 2),
    ( true, '2024-08-23', '20:30:00', '22:30:00', 3, 3),
    ( true, '2024-04-30', '22:30:00', '00:30:00', 4, 4), 
    (true, '2024-05-10', '14:00:00', '16:00:00', 5, 1), 
    ( true, '2024-07-27', '17:00:00', '19:00:00', 6, 2), 
    ( true, '2024-09-27', '20:00:00', '22:00:00', 7, 3),
    (true, '2024-10-26', '22:30:00', '00:00:00', 8, 5);


INSERT INTO Bookings ( status, date, customer_id, seat_id, billboard_id)
VALUES 
    ( true, '2024-04-26', 1, 1, 1),
    ( true, '2024-10-05', 2, 3, 2),
    ( true, '2024-08-01', 3, 3, 4), 
    ( true, '2024-06-16', 4, 6, 6), 
    ( true, '2024-04-30', 1, 2, 5), 
    (true, '2024-05-11', 2, 4, 7), 
    ( true, '2024-05-19', 3, 3, 8), 
    ( true, '2024-08-30', 4, 5, 6),
    ( true, '2024-03-27', 1, 4, 3),
    (true, '2024-09-01', 2, 2, 2),
    (true, '2024-07-10', 3, 1, 1),
    ( true, '2024-08-04', 4, 6, 4);
