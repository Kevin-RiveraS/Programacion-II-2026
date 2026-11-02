
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
('Kevin Rivera', '55515234', '2026-11-05', '14:00:00', 2, 'CONFIRMADA', 1),
('Carlos Luna', '55556478', '2026-11-05', '15:30:00', 4, 'CONFIRMADA', 2),
('Gloria Punay', '55590612', '2026-11-06', '20:00:00', 6, 'CONFIRMADA', 3),
('Lucía Fernández', '55583456', '2026-10-26', '13:00:00', 2, 'ATENDIDA', 4),
('Victor Yoc', '55578910', '2026-11-06', '19:00:00', 4, 'CANCELADA', 5),
('Ana Pérez', '55598765', '2026-11-07', '13:00:00', 4, 'CONFIRMADA', 6),
('Jorge Martínez', '55567890', '2026-11-08', '20:00:00', 4, 'PENDIENTE', 7),
('David López', '55533445', '2026-11-09', '21:00:00', 6, 'PENDIENTE', 8),
('Sofía Ramírez', '55555667', '2026-11-09', '14:30:00', 8, 'CONFIRMADA', 9),
('Luis Torres', '55577889', '2026-11-09', '18:00:00', 2, 'CONFIRMADA', 10);

SELECT * FROM mesa
SELECT * FROM reserva

TRUNCATE TABLE reserva, mesa RESTART IDENTITY CASCADE;

