-- Seed default roles
INSERT IGNORE INTO roles (id, name, description) VALUES 
(1, 'ADMIN', 'Administrator role with full access'),
(2, 'USER', 'Regular user role'),
(3, 'MODERATOR', 'Moderator role for content management');
