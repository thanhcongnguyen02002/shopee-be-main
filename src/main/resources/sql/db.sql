DROP DATABASE IF EXISTS shopee;
CREATE  DATABASE shopee;
use shopee;

CREATE TABLE `Account`(
                          id 					INT AUTO_INCREMENT PRIMARY KEY,
                          username 			    VARCHAR(50) NOT NULL UNIQUE,
                          `role` 				ENUM('CUSTOMER', 'SELLER', 'ADMIN'),
                          `password` 			VARCHAR(255) NOT NULL,
                          date_of_birth 		DATE,
                          address 			    VARCHAR(255),
                          fullname 			    VARCHAR(100),
                          phone_number 		    VARCHAR(12),
                          email 				VARCHAR(50),
                          facebook 			    VARCHAR(100),
                          information 		    VARCHAR(500)
);

CREATE TABLE Product (
                         id 					INT AUTO_INCREMENT PRIMARY KEY,
                         `name` 				VARCHAR(255) NOT NULL UNIQUE,
                         image 				    VARCHAR(255) NOT NULL,
                         price 				    INT UNSIGNED,
                         `status` 			    ENUM('NEW', 'OLD') NOT NULL,
                         shipping_unit 		    ENUM('EXPRESS', 'FAST', 'SAVE') NOT NULL,
                         `type` 				ENUM('PHONE', 'COMPUTER', 'CLOTHES', 'FOOTWEAR') NOT NULL,
                         create_date 		    DATE
);

CREATE TABLE `Order` (
                         id 					INT AUTO_INCREMENT PRIMARY KEY,
                         create_date 		    DATE,
                         order_by 			    INT NOT NULL,
                         product_id 			INT NOT NULL,
                         quantity 			    INT,
                         `status` 			    ENUM('PENDING', 'DONE', 'CANCEL') DEFAULT('PENDING'),
                         FOREIGN KEY(order_by) REFERENCES `Account` (id) ON DELETE CASCADE,
                         FOREIGN KEY(product_id) REFERENCES `Product` (id) ON DELETE CASCADE
);

CREATE TABLE `token` (
                         id 					INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         token 				    VARCHAR(500) NOT NULL,
                         user_agent 			VARCHAR(500) NOT NULL,
                         is_black_list 		    TINYINT(1) DEFAULT NULL,
                         refresh_time 		    DATETIME DEFAULT NULL,
                         expiration 			DATETIME DEFAULT NULL,
                         UNIQUE KEY token (token)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `shopee`.`account` (`id`, `username`, `role`, `password`, `date_of_birth`, `address`, `fullname`, `phone_number`, `email`, `facebook`, `information`) VALUES ('1', 'admin', 'ADMIN', '$2a$12$xzDN6H..Id/hgoxCUwu5BesDr0nj5gcpylWJzF80ILZp7me0jCDkS', '2023-06-07 15:46:16', 'HN', 'ADMIN', '0123456', 'admin@gmail.com', 'admin', 'admin');
INSERT INTO `shopee`.`account` (`id`, `username`, `role`, `password`, `date_of_birth`, `address`, `fullname`, `phone_number`, `email`, `facebook`, `information`) VALUES ('2', 'seller', 'SELLER', '$2a$12$xzDN6H..Id/hgoxCUwu5BesDr0nj5gcpylWJzF80ILZp7me0jCDkS', '2023-06-07 15:46:16', 'HN', 'SELLER', '0123456', 'seller@gmail.com', 'seller', 'seller');
INSERT INTO `shopee`.`account` (`id`, `username`, `role`, `password`, `date_of_birth`, `address`, `fullname`, `phone_number`, `email`, `facebook`, `information`) VALUES ('3', 'customer', 'CUSTOMER', '$2a$12$xzDN6H..Id/hgoxCUwu5BesDr0nj5gcpylWJzF80ILZp7me0jCDkS', '2023-06-07 15:46:16', 'HN', 'CUSTOMER', '0123456', 'customer@gmail.com', 'customer', 'customer');

INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('1', 'Ip 13', 'https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/t/_/t_m_12.png', '10000000', 'NEW', 'FAST', 'PHONE', '2023-06-07 20:33:13');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('2', 'JEAN', 'https://pos.nvncdn.net/be3159-662/ps/20221205_V6G2YfE4zRO6weBXl9b2Ogby.jpg', '300000', 'NEW', 'EXPRESS', 'CLOTHES', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('3', 'Ip 14', 'https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/t/_/t_m_12.png', '12000000', 'OLD', 'SAVE', 'PHONE', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('4', 'DELL', 'https://cdn.tgdd.vn/Products/Images/44/296847/dell-inspiron-15-3520-i5-n5i5122w1-191222-091429-600x600.jpg', '20000000', 'NEW', 'EXPRESS', 'COMPUTER', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('5', 'Điện thoại Iphone 14', 'https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/t/_/t_m_12.png', '20000000', 'NEW', 'EXPRESS', 'PHONE', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('6', 'Áo Polo', 'https://file.hstatic.net/1000114455/file/tieu-chuan-chon-ao-thun-polo-mat-me-cho-ngay-he-1_bfa03aaf20f84eaabb181c237d9ebfbf.jpg', '200000', 'NEW', 'EXPRESS', 'CLOTHES', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('7', 'Áo khoáccccc', 'https://aokhoacnam.vn/upload/product/akn-062/thumb/ao-khoac-kaki-den-nam-tinh_x450.jpg', '400000', 'NEW', 'EXPRESS', 'CLOTHES', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('8', 'Máy tính', 'https://nhatminhcomputer.vn/wp-content/uploads/2022/11/bo-may-tinh-gaming-gom-nhung-gi.jpg', '50000000', 'NEW', 'EXPRESS', 'COMPUTER', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('9', 'Giày Nike AF1', 'https://pos.nvncdn.net/62a70c-142650/ps/20230201_943b3Gh5Rc9REiCl.jpg', '1000000', 'NEW', 'SAVE', 'FOOTWEAR', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('10', 'Giày Derby', 'https://madshoes.vn/wp-content/uploads/2022/08/giay-derby-nam-la-gi-2.jpg', '1500000', 'NEW', 'EXPRESS', 'FOOTWEAR', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('11', 'Crocs', 'https://cdn.shopify.com/s/files/1/0456/5070/6581/files/bang-size-giay-crocs-cap-nhat-chinh-xac_600x600.jpg?v=1671502942', '100000', 'NEW', 'FAST', 'FOOTWEAR', '2023-06-07');
INSERT INTO `shopee`.`product` (`id`, `name`, `image`, `price`, `status`, `shipping_unit`, `type`, `create_date`) VALUES ('12', 'Macbook', 'https://media.cnn.com/api/v1/images/stellar/prod/201116214842-13-macbook-air-review-silicon-underscoredjpg.jpg?q=w_2615,h_1556,x_0,y_0,c_fill', '30000000', 'NEW', 'EXPRESS', 'COMPUTER', '2023-06-07');

