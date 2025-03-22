-- INSERT INTO role (id, name, tag) VALUES (1, 'ADMIN', 'user.role.admin');
-- INSERT INTO role (id, name, tag) VALUES (2, 'SHOP', 'user.role.shop');
-- INSERT INTO role (id, name, tag) VALUES (3, 'CUSTOMER', 'user.role.customer');

-- Roles de la aplicación
INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'SHOP');
INSERT INTO roles (id, name) VALUES (3, 'CUSTOMER');

-- Usuarios de prueba
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code) VALUES (1, 1, 'masteradmin@bazaar.es', '123456','Administrador','Master','BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code) VALUES (2, 2, 'shop1@bazaar.es', '123456','Shop1', 'Shop1Surname', 'BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code) VALUES (3, 2, 'shop2@bazaar.es', '123456','Shop2', 'Shop2Surname', 'BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code) VALUES (4, 2, 'shop3@bazaar.es', '123456','Shop3', 'Shop3Surname', 'BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code) VALUES (5, 3, 'customer1@bazaar.es', '123456','Customer1','Customer1Surname','BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code) VALUES (6, 3, 'customer2@bazaar.es', '123456','Customer2','Customer2Surname','BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code) VALUES (7, 3, 'customer3@bazaar.es', '123456','Customer3','Customer3Surname','BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777);


INSERT INTO categories (id, name) VALUES (1, 'Deportes');
INSERT INTO categories (id, name) VALUES (2, 'Electrónica');
INSERT INTO categories (id, name) VALUES (3, 'Hogar');
INSERT INTO categories (id, name) VALUES (4, 'Moda');
INSERT INTO categories (id, name) VALUES (5, 'Juguetes');
INSERT INTO categories (id, name) VALUES (6, 'Libros');
INSERT INTO categories (id, name) VALUES (7, 'Otros');

INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (1, 1, 'Balón de fútbol', 'Balón de fútbol de la marca Adidas', 20.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (2, 1, 'Balón de baloncesto', 'Balón de baloncesto de la marca Spalding', 25.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (3, 1, 'Balón de voleibol', 'Balón de voleibol de la marca Mikasa', 30.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (4, 2, 'Smartphone', 'Smartphone de la marca Samsung', 300.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (5, 2, 'Tablet', 'Tablet de la marca Apple', 400.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (6, 2, 'Portátil', 'Portátil de la marca HP', 500.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (7, 3, 'Sofá', 'Sofá de 3 plazas', 600.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (8, 3, 'Mesa', 'Mesa de comedor', 200.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (9, 3, 'Silla', 'Silla de oficina', 100.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (10, 4, 'Camiseta', 'Camiseta de manga corta', 10.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (11, 4, 'Pantalón', 'Pantalón vaquero', 20.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (12, 4, 'Zapatos', 'Zapatos de vestir', 30.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (13, 5, 'Muñeca', 'Muñeca de la marca Barbie', 15.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (14, 5, 'Coche teledirigido', 'Coche teledirigido de la marca Nikko', 20.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (15, 5, 'Pelota', 'Pelota de goma', 5.0, 0.0, 0.0);
INSERT INTO products (id, category_id, name, description, price, discount_price, discount_rate) VALUES (16, 6, 'El Quijote', 'Libro de Miguel de Cervantes', 10.0, 0.0, 0.0);
