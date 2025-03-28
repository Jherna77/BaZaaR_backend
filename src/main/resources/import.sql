-- INSERT INTO role (id, name, tag) VALUES (1, 'ADMIN', 'user.role.admin');
-- INSERT INTO role (id, name, tag) VALUES (2, 'SHOP', 'user.role.shop');
-- INSERT INTO role (id, name, tag) VALUES (3, 'CUSTOMER', 'user.role.customer');

-- Roles de la aplicación
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_SHOP');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_CUSTOMER');

-- Usuarios de prueba
INSERT INTO users (id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (1, 'masteradmin@bazaar.es', '123456','Administrador','Master','BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777, 1);
INSERT INTO users (id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (2, 'shop@bazaar.es', '123456','Tienda', 'ShopSurname', 'BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777, 1);
INSERT INTO users (id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (3, 'customer@bazaar.es', '123456','Cliente','CustomerSurname','BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777, 1);

-- Asignación de roles
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 3);

-- Categorías de productos
INSERT INTO categories (id, name) VALUES (1, 'Deportes');
INSERT INTO categories (id, name) VALUES (2, 'Electrónica');
INSERT INTO categories (id, name) VALUES (3, 'Hogar');
INSERT INTO categories (id, name) VALUES (4, 'Moda');
INSERT INTO categories (id, name) VALUES (5, 'Juguetes');
INSERT INTO categories (id, name) VALUES (6, 'Libros');
INSERT INTO categories (id, name) VALUES (7, 'Otros');

-- Productos de prueba
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (1, 'Balón de fútbol', 'Balón de fútbol de la marca Adidas', 20.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (2, 'Balón de baloncesto', 'Balón de baloncesto de la marca Spalding', 25.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (3, 'Balón de voleibol', 'Balón de voleibol de la marca Mikasa', 30.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (4, 'Smartphone', 'Smartphone de la marca Samsung', 300.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (5, 'Tablet', 'Tablet de la marca Apple', 400.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (6, 'Portátil', 'Portátil de la marca HP', 500.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (7, 'Sofá', 'Sofá de 3 plazas', 600.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (8, 'Mesa', 'Mesa de comedor', 200.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (9, 'Silla', 'Silla de oficina', 100.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (10, 'Camiseta', 'Camiseta de manga corta', 10.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (11, 'Pantalón', 'Pantalón vaquero', 20.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (12, 'Zapatos', 'Zapatos de vestir', 30.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (13, 'Muñeca', 'Muñeca de la marca Barbie', 15.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (14, 'Coche teledirigido', 'Coche teledirigido de la marca Nikko', 20.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (15, 'Pelota', 'Pelota de goma', 5.0, 0.0, 0.0);
INSERT INTO products (id, name, description, price, discount_price, discount_rate) VALUES (16, 'El Quijote', 'Libro de Miguel de Cervantes', 10.0, 0.0, 0.0);

-- Asignación de productos a categorías
INSERT INTO products_categories (product_id, category_id) VALUES (1, 1);
INSERT INTO products_categories (product_id, category_id) VALUES (2, 1);
INSERT INTO products_categories (product_id, category_id) VALUES (3, 1);
INSERT INTO products_categories (product_id, category_id) VALUES (4, 2);
INSERT INTO products_categories (product_id, category_id) VALUES (5, 2);
INSERT INTO products_categories (product_id, category_id) VALUES (6, 2);
INSERT INTO products_categories (product_id, category_id) VALUES (7, 3);
INSERT INTO products_categories (product_id, category_id) VALUES (8, 3);
INSERT INTO products_categories (product_id, category_id) VALUES (9, 3);
INSERT INTO products_categories (product_id, category_id) VALUES (10, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (11, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (12, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (13, 5);
INSERT INTO products_categories (product_id, category_id) VALUES (14, 5);
INSERT INTO products_categories (product_id, category_id) VALUES (15, 5);
INSERT INTO products_categories (product_id, category_id) VALUES (16, 6);