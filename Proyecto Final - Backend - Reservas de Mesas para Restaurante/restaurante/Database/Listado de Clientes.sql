INSERT INTO mesa (numero_mesa, capacidad, ubicacion, activa) VALUES
(1, 2, 'Ventana', true),
(2, 2, 'Ventana', true),
(3, 4, 'Interior', true),
(4, 4, 'Interior', true),
(5, 6, 'Terraza', true),
(6, 6, 'Terraza', true),
(7, 8, 'VIP', true),
(8, 2, 'Bar', true),
(9, 4, 'Jardín', true),
(10, 10, 'Salón Principal', true);

INSERT INTO reserva (nombre_cliente, telefono_cliente, fecha, hora, cantidad_personas, estado, mesa_id) VALUES
('Kevin Rivera', '5551234', '2026-10-22', '14:00:00', 2, 'CONFIRMADA', 1),
('Carlos Luna', '5555678', '2026-10-23', '15:30:00', 4, 'CONFIRMADA', 3),
('Gloria Punay', '5559012', '2026-10-25', '20:00:00', 6, 'CONFIRMADA', 5),
('Lucía Fernández', '5553456', '2026-10-26', '13:00:00', 2, 'ATENDIDA', 2),
('Victor Yoc', '5557890', '2026-10-27', '19:00:00', 4, 'CANCELADA', 4);

SELECT * FROM reserva