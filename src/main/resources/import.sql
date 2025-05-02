-- INSERT INTO role (id, name, tag) VALUES (1, 'ADMIN', 'user.role.admin');
-- INSERT INTO role (id, name, tag) VALUES (2, 'SHOP', 'user.role.shop');
-- INSERT INTO role (id, name, tag) VALUES (3, 'CUSTOMER', 'user.role.customer');

-- Roles de la aplicación
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_SHOP');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_CUSTOMER');

-- Usuarios de prueba con password = Pa$$W0rd
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (1, 1, 'masteradmin@bazaar.es', '$2a$10$PybyrPSemLzx0TvKdNc06uULBocWPsIRhya2uXzMyugFhpI99SeQS','Administrador','Master','BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (2, 2, 'shop@bazaar.es', '$2a$10$qiEytms5DnNnkDHx/xWtXO8G7X4QBCb/TIO/a1k66oloFEdHiUGoW','Tienda', 'ShopSurname', 'BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77770, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (3, 2, 'shop1@bazaar.es', '$2a$10$qiEytms5DnNnkDHx/xWtXO8G7X4QBCb/TIO/a1k66oloFEdHiUGoW','Tienda de deportes', 'ShopSurname1', 'BaZaaR Street, 1','BaZaaR City1','BaZaaR Province1', 77771, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (4, 2, 'shop2@bazaar.es', '$2a$10$qiEytms5DnNnkDHx/xWtXO8G7X4QBCb/TIO/a1k66oloFEdHiUGoW','Tienda de electrónica', 'ShopSurname2', 'BaZaaR Street, 2','BaZaaR City2','BaZaaR Province2', 77772, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (5, 2, 'shop3@bazaar.es', '$2a$10$qiEytms5DnNnkDHx/xWtXO8G7X4QBCb/TIO/a1k66oloFEdHiUGoW','Tienda de hogar', 'ShopSurname3', 'BaZaaR Street, 3','BaZaaR City3','BaZaaR Province3', 77773, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (6, 2, 'shop4@bazaar.es', '$2a$10$qiEytms5DnNnkDHx/xWtXO8G7X4QBCb/TIO/a1k66oloFEdHiUGoW','Tienda de moda', 'ShopSurname4', 'BaZaaR Street, 4','BaZaaR City4','BaZaaR Province4', 77774, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (7, 2, 'shop5@bazaar.es', '$2a$10$qiEytms5DnNnkDHx/xWtXO8G7X4QBCb/TIO/a1k66oloFEdHiUGoW','Tienda de juguetes', 'ShopSurname5', 'BaZaaR Street, 5','BaZaaR City5','BaZaaR Province5', 77775, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (8, 2, 'shop6@bazaar.es', '$2a$10$qiEytms5DnNnkDHx/xWtXO8G7X4QBCb/TIO/a1k66oloFEdHiUGoW','Tienda de libros', 'ShopSurname6', 'BaZaaR Street, 6','BaZaaR City6','BaZaaR Province6', 77776, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (9, 2, 'shop7@bazaar.es', '$2a$10$qiEytms5DnNnkDHx/xWtXO8G7X4QBCb/TIO/a1k66oloFEdHiUGoW','Tienda de artículos varios', 'ShopSurname7', 'BaZaaR Street, 7','BaZaaR City7','BaZaaR Province7', 77777, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (10, 3, 'customer1@bazaar.es', '$2a$10$Fu/hQVx.yKmqRHhNN21S7Ozw8OF3lWTzCtf/ifoXWcdQRiJ.XqEyS','Cliente1','CustomerSurname1','BaZaaR Street, 1','BaZaaR City1','BaZaaR Province1', 77771, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (11, 3, 'customer2@bazaar.es', '$2a$10$Fu/hQVx.yKmqRHhNN21S7Ozw8OF3lWTzCtf/ifoXWcdQRiJ.XqEyS','Cliente2','CustomerSurname2','BaZaaR Street, 2','BaZaaR City2','BaZaaR Province2', 77772, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (12, 3, 'customer3@bazaar.es', '$2a$10$Fu/hQVx.yKmqRHhNN21S7Ozw8OF3lWTzCtf/ifoXWcdQRiJ.XqEyS','Cliente3','CustomerSurname3','BaZaaR Street, 3','BaZaaR City3','BaZaaR Province3', 77773, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (13, 3, 'customer4@bazaar.es', '$2a$10$Fu/hQVx.yKmqRHhNN21S7Ozw8OF3lWTzCtf/ifoXWcdQRiJ.XqEyS','Cliente4','CustomerSurname4','BaZaaR Street, 4','BaZaaR City4','BaZaaR Province4', 77774, 1);
INSERT INTO users (id, role_id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (14, 3, 'customer5@bazaar.es', '$2a$10$Fu/hQVx.yKmqRHhNN21S7Ozw8OF3lWTzCtf/ifoXWcdQRiJ.XqEyS','Cliente5','CustomerSurname5','BaZaaR Street, 5','BaZaaR City5','BaZaaR Province5', 77775, 1);

-- -- Usuarios de prueba con password = Pa$$W0rd
-- INSERT INTO users (id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (1, 'masteradmin@bazaar.es', '$2a$10$PybyrPSemLzx0TvKdNc06uULBocWPsIRhya2uXzMyugFhpI99SeQS','Administrador','Master','BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777, 1);
-- INSERT INTO users (id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (2, 'shop@bazaar.es', '$2a$10$qiEytms5DnNnkDHx/xWtXO8G7X4QBCb/TIO/a1k66oloFEdHiUGoW','Tienda', 'ShopSurname', 'BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777, 1);
-- INSERT INTO users (id, email, password, name, surnames, address, city, province, zip_code, enabled) VALUES (3, 'customer@bazaar.es', '$2a$10$Fu/hQVx.yKmqRHhNN21S7Ozw8OF3lWTzCtf/ifoXWcdQRiJ.XqEyS','Cliente','CustomerSurname','BaZaaR Street, SN','BaZaaR City','BaZaaR Province', 77777, 1);

-- -- Asignación de roles
-- INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
-- INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
-- INSERT INTO users_roles (user_id, role_id) VALUES (1, 3);
-- INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
-- INSERT INTO users_roles (user_id, role_id) VALUES (2, 3);
-- INSERT INTO users_roles (user_id, role_id) VALUES (3, 3);

-- Categorías de productos
INSERT INTO categories (id, name, image_url, enabled) VALUES (1, 'Otros', '/api/images/00category7.jpg', 1);
INSERT INTO categories (id, name, image_url, enabled) VALUES (2, 'Deportes', '/api/images/00category1.jpg', 1);
INSERT INTO categories (id, name, image_url, enabled) VALUES (3, 'Electrónica', '/api/images/00category2.jpg', 1);
INSERT INTO categories (id, name, image_url, enabled) VALUES (4, 'Hogar', '/api/images/00category3.jpg', 1);
INSERT INTO categories (id, name, image_url, enabled) VALUES (5, 'Moda', '/api/images/00category4.jpg', 1);
INSERT INTO categories (id, name, image_url, enabled) VALUES (6, 'Juguetes', '/api/images/00category5.jpg', 1);
INSERT INTO categories (id, name, image_url, enabled) VALUES (7, 'Libros', '/api/images/00category6.jpg', 1);

-- Productos de prueba
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (1, 3, 'Balón de fútbol', 'Balón de fútbol de la marca Adidas', 20.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (2, 3, 'Balón de baloncesto', 'Balón de baloncesto de la marca Spalding', 25.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (3, 3, 'Balón de voleibol', 'Balón de voleibol de la marca Mikasa', 30.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (4, 4, 'Smartphone', 'Smartphone de la marca Samsung', 300.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (5, 4, 'Tablet', 'Tablet de la marca Apple', 400.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (6, 4, 'Portátil', 'Portátil de la marca HP', 500.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (7, 5, 'Sofá', 'Sofá de 3 plazas', 600.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (8, 5, 'Mesa', 'Mesa de comedor', 200.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (9, 5, 'Silla', 'Silla de oficina', 100.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (10, 6, 'Camiseta', 'Camiseta de manga corta', 10.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (11, 6, 'Pantalón', 'Pantalón vaquero', 20.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (12, 6, 'Zapatos', 'Zapatos de vestir', 30.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (13, 7, 'Muñeca', 'Muñeca de la marca Barbie', 15.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (14, 7, 'Coche teledirigido', 'Coche teledirigido de la marca Nikko', 20.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (15, 7, 'Pelota', 'Pelota de goma', 5.0, 1);
INSERT INTO products (id, user_id, name, description, price, enabled) VALUES (16, 8, 'El Quijote', 'Libro de Miguel de Cervantes', 10.0, 1);

-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (1, 'Balón de fútbol', 'Balón de fútbol de la marca Adidas', 20.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (2, 'Balón de baloncesto', 'Balón de baloncesto de la marca Spalding', 25.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (3, 'Balón de voleibol', 'Balón de voleibol de la marca Mikasa', 30.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (4, 'Smartphone', 'Smartphone de la marca Samsung', 300.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (5, 'Tablet', 'Tablet de la marca Apple', 400.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (6, 'Portátil', 'Portátil de la marca HP', 500.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (7, 'Sofá', 'Sofá de 3 plazas', 600.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (8, 'Mesa', 'Mesa de comedor', 200.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (9, 'Silla', 'Silla de oficina', 100.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (10, 'Camiseta', 'Camiseta de manga corta', 10.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (11, 'Pantalón', 'Pantalón vaquero', 20.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (12, 'Zapatos', 'Zapatos de vestir', 30.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (13, 'Muñeca', 'Muñeca de la marca Barbie', 15.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (14, 'Coche teledirigido', 'Coche teledirigido de la marca Nikko', 20.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (15, 'Pelota', 'Pelota de goma', 5.0, 0.0, 0.0, 1);
-- INSERT INTO products (id, name, description, price, discount_price, discount_rate, enabled) VALUES (16, 'El Quijote', 'Libro de Miguel de Cervantes', 10.0, 0.0, 0.0, 1);

-- Asignación de productos a categorías
INSERT INTO products_categories (product_id, category_id) VALUES (1, 2);
INSERT INTO products_categories (product_id, category_id) VALUES (2, 2);
INSERT INTO products_categories (product_id, category_id) VALUES (3, 2);
INSERT INTO products_categories (product_id, category_id) VALUES (4, 3);
INSERT INTO products_categories (product_id, category_id) VALUES (5, 3);
INSERT INTO products_categories (product_id, category_id) VALUES (6, 3);
INSERT INTO products_categories (product_id, category_id) VALUES (7, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (8, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (9, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (10, 5);
INSERT INTO products_categories (product_id, category_id) VALUES (11, 5);
INSERT INTO products_categories (product_id, category_id) VALUES (12, 5);
INSERT INTO products_categories (product_id, category_id) VALUES (13, 6);
INSERT INTO products_categories (product_id, category_id) VALUES (14, 6);
INSERT INTO products_categories (product_id, category_id) VALUES (15, 6);
INSERT INTO products_categories (product_id, category_id) VALUES (16, 7);

-- Asignación de imágenes a productos
INSERT INTO product_images (product_id, image_url) VALUES (1, '/api/images/00product11.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (1, '/api/images/00product12.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (1, '/api/images/00product13.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (1, '/api/images/00product14.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (2, '/api/images/00product21.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (2, '/api/images/00product22.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (2, '/api/images/00product23.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (2, '/api/images/00product24.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (3, '/api/images/00product31.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (3, '/api/images/00product32.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (3, '/api/images/00product33.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (4, '/api/images/00product41.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (4, '/api/images/00product42.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (4, '/api/images/00product43.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (4, '/api/images/00product44.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (5, '/api/images/00product51.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (5, '/api/images/00product52.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (5, '/api/images/00product53.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (5, '/api/images/00product54.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (6, '/api/images/00product61.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (6, '/api/images/00product62.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (6, '/api/images/00product63.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (6, '/api/images/00product64.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (7, '/api/images/00product71.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (7, '/api/images/00product72.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (7, '/api/images/00product73.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (7, '/api/images/00product74.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (8, '/api/images/00product81.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (8, '/api/images/00product82.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (8, '/api/images/00product83.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (8, '/api/images/00product84.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (9, '/api/images/00product91.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (9, '/api/images/00product92.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (9, '/api/images/00product93.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (9, '/api/images/00product94.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (10, '/api/images/00product101.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (10, '/api/images/00product102.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (11, '/api/images/00product111.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (11, '/api/images/00product112.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (12, '/api/images/00product121.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (12, '/api/images/00product122.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (12, '/api/images/00product123.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (13, '/api/images/00product131.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (13, '/api/images/00product132.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (13, '/api/images/00product133.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (14, '/api/images/00product141.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (14, '/api/images/00product142.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (14, '/api/images/00product143.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (14, '/api/images/00product144.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (15, '/api/images/00product151.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (15, '/api/images/00product152.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (15, '/api/images/00product153.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (15, '/api/images/00product154.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (16, '/api/images/00product161.jpg');
INSERT INTO product_images (product_id, image_url) VALUES (16, '/api/images/00product162.jpg');

-- INSERT INTO user_products (user_id, product_id) VALUES (3, 1);
-- INSERT INTO user_products (user_id, product_id) VALUES (3, 2);
-- INSERT INTO user_products (user_id, product_id) VALUES (3, 3);
-- INSERT INTO user_products (user_id, product_id) VALUES (4, 4);
-- INSERT INTO user_products (user_id, product_id) VALUES (4, 5);
-- INSERT INTO user_products (user_id, product_id) VALUES (4, 6);
-- INSERT INTO user_products (user_id, product_id) VALUES (5, 7);
-- INSERT INTO user_products (user_id, product_id) VALUES (5, 8);
-- INSERT INTO user_products (user_id, product_id) VALUES (5, 9);
-- INSERT INTO user_products (user_id, product_id) VALUES (6, 10);
-- INSERT INTO user_products (user_id, product_id) VALUES (6, 11);
-- INSERT INTO user_products (user_id, product_id) VALUES (6, 12);
-- INSERT INTO user_products (user_id, product_id) VALUES (7, 13);
-- INSERT INTO user_products (user_id, product_id) VALUES (7, 14);
-- INSERT INTO user_products (user_id, product_id) VALUES (7, 15);
-- INSERT INTO user_products (user_id, product_id) VALUES (8, 16);
