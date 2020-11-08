CREATE DATABASE chrdw_push_service CHARSET	 "utf8";

SHOW DATABASES;

USE chrdw_push_service;

CREATE TABLE hotel_base_info_en_us(
logic_id INT(8) AUTO_INCREMENT comment "the logic id of this hotel",
ecom_id INT(16) NOT NULL comment "the ecom id of this hotel",
hcom_id INT(16) NOT NULL comment "the hcom id of this hotel",
name varchar(20) NOT NULL comment "the name of this hotel",
property_type_id TINYINT(2) NOT NULL comment "the property type id of this hotel",
property_type_name CHAR(50) NOT NULL comment "the property type name of this hotel",
description_location_teaser varchar(255) NOT NULL comment "the location_teaser description of this hotel",
description_hotel_teaser varchar(255) NOT NULL comment "the hotel teaser description of this hotel",
description_room_teaser varchar(255) NOT NULL comment "the room teaser description of this hotel",
smoking_option TINYINT(1) NOT NULL comment "if can smoke in this hotel",
thumbnail_url varchar(255) NOT NULL comment "the url of this hotel's thumbnail",
address1 varchar(100) NOT NULL comment "the first address line of this hotel",
address2 varchar(100) NOT NULL comment "the second address line of this hotel",
address3 varchar(100) NOT NULL comment "the third address line of this hotel",
city char(10) NOT NULL comment "the hotel in which city",
province char(10) NOT NULL comment "the hotel in which province",
country char(10) NOT NULL comment "the hotel in which country",
is_active TINYINT(1) NOT NULL comment "identify the hotel is active. 1 is active and 0 is not active",
create_time datetime comment "the create time of this record",
update_time datetime comment "the update time of this record",
PRIMARY KEY pk_id(logic_id),
UNIQUE uk_ecom_id(ecom_id),
UNIQUE uk_hcom_id(hcom_id),
INDEX idx_name(name),
INDEX idx_address(address1(20), address2(20), address3(20))
) engine=innodb charset "utf8" comment "the base info of hotel"

CREATE TABLE hotel_location_info(
logic_id INT(8) AUTO_INCREMENT comment "the logic id of this hotel",
ecom_id INT(16) NOT NULL comment "the ecom id of this hotel",
hcom_id INT(16) NOT NULL comment "the hcom id of this hotel",
latitude decimal(9,6) NOT NULL comment "the latitude of this hotel",
longitude decimal(9,6) NOT NULL comment "the longitude of this hotel",
region_id INT(8) NOT NULL comment "the region id of this hotel",
zipcode INT(8) NOT NULL comment "the zipcode of the city",
create_time datetime comment "the create time of this record",
update_time datetime comment "the update time of this record",
PRIMARY KEY pk_id(logic_id),
UNIQUE uk_ecom_id(ecom_id),
UNIQUE uk_hcom_id(hcom_id),
UNIQUE uk_latitude_longitude(latitude, longitude)
) engine=innodb charset "utf8" comment "the location info of hotel"

CREATE TABLE hotel_amenity_info_en_us(
logic_id INT(8) AUTO_INCREMENT comment "the logic id of this hotel",
id INT(8) NOT NULL comment "the id of the hotel's amenity",
name varchar(20) NOT NULL comment "the name of the amenity",
ecom_id INT(16) NOT NULL comment "the ecom id of this hotel",
hcom_id INT(16) NOT NULL comment "the hcom id of this hotel",
create_time datetime comment "the create time of this record",
update_time datetime comment "the update time of this record",
PRIMARY KEY pk_id(logic_id),
UNIQUE uk_ecom_id(ecom_id),
UNIQUE uk_hcom_id(hcom_id)
) engine=innodb charset "utf8" comment "the amenity info of hotel"

CREATE TABLE hotel_media_info_en_us(
logic_id INT(8) AUTO_INCREMENT comment "the logic id of this media",
type_id TINYINT(1) NOT NULL comment "the type id of the media",
title char(20) NOT NULL comment "the title of the media",
media_size char(1) NOT NULL comment "the size of this media. t is small, m is medium, l is large",
url varchar(100) NOT NULL comment "the url of this media",
ecom_id INT(16) NOT NULL comment "the ecom id of the hotel which this media belongs to",
hcom_id INT(16) NOT NULL comment "the hcom id of the hotel which this media belongs to",
create_time datetime comment "the create time of the record",
update_time datetime comment "the update time of the record",
PRIMARY KEY pk_id(logic_id),
UNIQUE uk_ecom_id(ecom_id),
UNIQUE uk_hcom_id(hcom_id)
) engine=innodb charset "utf8" comment "the media info of hotel"

CREATE TABLE hotel_media_liink_info(
logic_id INT(8) AUTO_INCREMENT comment "the logic id of this media",
ecom_id INT(16) NOT NULL comment "the ecom id of this hotel",
hcom_id INT(16) NOT NULL comment "the hcom id of this hotel",
url varchar(100) NOT NULL comment "the url of this media",
create_time datetime comment "the create time of the record",
update_time datetime comment "the update time of the record",
PRIMARY KEY pk_id(logic_id),
UNIQUE uk_ecom_id(ecom_id),
UNIQUE uk_hcom_id(hcom_id)
) engine=innodb charset "utf8" comment "the media info of hotel"

CREATE TABLE room_base_info_en_us(
logic_id INT(8) AUTO_INCREMENT comment "the logic id of this room",
id INT(8) NOT NULL comment "the id of the room",
ecom_id INT(16) NOT NULL comment "the ecom id of this hotel",
hcom_id INT(16) NOT NULL comment "the hcom id of this hotel",
smoking_option TINYINT(1) NOT NULL comment "if can smoke in this room",
bed_type_option_id int(8) NOT NULL comment "the bed type id ot this room",
bed_type_option_description varchar(50) NOT NULL comment "the description of the bed type",
max_guest TINYINT(1) NOT NULL comment "the max guest in this room",
create_time datetime comment "the create time of this record",
update_time datetime comment "the update time of this record",
PRIMARY KEY pk_id(logic_id),
UNIQUE uk_room_id(id),
UNIQUE uk_ecom_id(ecom_id),
UNIQUE uk_hcom_id(hcom_id)
) engine=innodb charset "utf8" comment "the base info of room"

CREATE TABLE room_amenity_info_en_us(
logic_id INT(8) AUTO_INCREMENT comment "the logic id of this hotel",
id INT(8) NOT NULL comment "the id of the room's amenity",
name varchar(20) NOT NULL comment "the name of the amenity",
room_id INT(16) NOT NULL comment "the room id of this amenity",
ecom_id INT(16) NOT NULL comment "the ecom id of this hotel",
hcom_id INT(16) NOT NULL comment "the hcom id of this hotel",
create_time datetime comment "the create time of this record",
update_time datetime comment "the update time of this record",
PRIMARY KEY pk_id(logic_id),
UNIQUE uk_room_id(room_id),
UNIQUE uk_ecom_id(ecom_id),
UNIQUE uk_hcom_id(hcom_id)
) engine=innodb charset "utf8" comment "the amenity info of room"

CREATE TABLE room_media_info_en_us(
logic_id INT(8) AUTO_INCREMENT comment "the logic id of this media",
room_id INT(16) NOT NULL comment "the ecom id of the room which this media belongs to",
ecom_id INT(16) NOT NULL comment "the ecom id of this hotel",
hcom_id INT(16) NOT NULL comment "the hcom id of this hotel",
type_id TINYINT(1) NOT NULL comment "the type id of the media",
title char(20) NOT NULL comment "the title of the media",
media_size char(1) NOT NULL comment "the size of this media. t is small, m is medium, l is large",
url varchar(100) NOT NULL comment "the url of this media",
create_time datetime comment "the create time of the record",
update_time datetime comment "the update time of the record",
PRIMARY KEY pk_id(logic_id),
UNIQUE uk_room_id(room_id),
UNIQUE uk_ecom_id(ecom_id),
UNIQUE uk_hcom_id(hcom_id)
) engine=innodb charset "utf8" comment "the media info of room"

CREATE TABLE room_media_liink_infi(
logic_id INT(8) AUTO_INCREMENT comment "the logic id of this media",
room_id INT(16) NOT NULL comment "the ecom id of this room",
ecom_id INT(16) NOT NULL comment "the ecom id of this hotel",
hcom_id INT(16) NOT NULL comment "the hcom id of this hotel",
url varchar(100) NOT NULL comment "the url of this media",
create_time datetime comment "the create time of the record",
update_time datetime comment "the update time of the record",
PRIMARY KEY pk_id(logic_id),
UNIQUE uk_room_id(room_id),
UNIQUE uk_ecom_id(ecom_id),
UNIQUE uk_hcom_id(hcom_id)
) engine=innodb charset "utf8" comment "the media info of hotel"

show create TABLE hotel_base_info;

DROP TABLE hotel_location_info;

SHOW INDEX FROM hotel_base_info_en_us;

EXPLAIN SELECT ecom_id FROM hotel_base_info_en_us WHERE ecom_id=2570279;

EXPLAIN SELECT ecom_id FROM hotel_base_info_en_us WHERE smoking_option=0;

SHOW tables;

INSERT INTO hotel_base_info_en_us VALUES(
NULL, 
2570279, 
312278,
"The Bermondsey",
1, 
"Hotel",
"Located in London City Centre,",
"This smoke-free hotel features a restaurant",
"All 90 rooms provide conveniences like refrigerators and coffee makers",
0,
"https://images.trvl-media.com/hotels/3000000/2580000/2570300/2570279/83eddefb_t.jpg",
"111 Westminster Bridge Road",
"Westminster Bridge Road",
"Westminster Bridge Road",
"London",
"England",
"GBR",
1,
"2020-01-02 15:22:22",
"2020-01-02 15:22:22"
)


