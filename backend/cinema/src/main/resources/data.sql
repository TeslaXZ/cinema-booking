insert into Movies
values (1, true, 18, 'HORROR', 120, 'CHUCKY'),
       (2, true, 12, 'COMEDY', 120, 'MI POBRE ANGELITO'),
       (3, true, 18, 'ACTION', 120, 'RAMBO');

insert into Rooms
values (1, true, 'SALA 1', 1),
       (2, true, 'SALA 2', 2),
       (3, true, 'SALA 3', 3);

INSERT INTO Seats (id, status, number, row_number, room_id)
VALUES 
    (1, true, 1, 1, 1), -- Asigna esta butaca a 'SALA 1' (room_id = 1)
    (2, true, 2, 1, 1), -- Asigna esta butaca a 'SALA 1' (room_id = 1)
    (3, true, 1, 1, 2), -- Asigna esta butaca a 'SALA 2' (room_id = 2)
    (4, true, 2, 1, 2), -- Asigna esta butaca a 'SALA 2' (room_id = 2)
    (5, true, 1, 1, 3), -- Asigna esta butaca a 'SALA 3' (room_id = 3)
    (6, true, 2, 1, 3); -- Asigna esta butaca a 'SALA 3' (room_id = 3)

INSERT INTO Customers (id, status, document_number, name, lastname, age, phone_number, email)
VALUES 
    (1, true, '123456789', 'Juan', 'Perez', 30, '555-1234', 'juan.perez@example.com'),
    (2, true, '987654321', 'Maria', 'Lopez', 25, '555-5678', 'maria.lopez@example.com');

INSERT INTO Billboards (id, status, date, start_time, end_time, movie_id, room_id)
VALUES 
    (1, true, '2024-07-25', '15:00:00', '17:00:00', 1, 1), -- Película 'CHUCKY' en 'SALA 1'
    (2, true, '2024-07-25', '18:00:00', '20:00:00', 2, 2), -- Película 'MI POBRE ANGELITO' en 'SALA 2'
    (3, true, '2024-07-25', '20:30:00', '22:30:00', 3, 3); -- Película 'RAMBO' en 'SALA 3'

INSERT INTO Bookings (id, status, date, customer_id, seat_id, billboard_id)
VALUES 
    (1, true, '2024-07-25', 1, 1, 1), -- Reserva de Juan Perez para 'CHUCKY' en 'SALA 1'
    (2, true, '2024-07-25', 2, 3, 2); -- Reserva de Maria Lopez para 'MI POBRE ANGELITO' en 'SALA 2'


