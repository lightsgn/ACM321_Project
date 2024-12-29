BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "suppliers" (
	"id"	INTEGER NOT NULL UNIQUE,
	"name"	TEXT NOT NULL,
	"contact_number"	TEXT NOT NULL,
	"address"	TEXT NOT NULL,
	"is_active"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "sales" (
	"sales_id"	INTEGER NOT NULL UNIQUE,
	"date"	TEXT NOT NULL,
	"customer_id"	INTEGER NOT NULL,
	"product_id"	INTEGER NOT NULL,
	"quantity"	INTEGER NOT NULL,
	"unit_price"	INTEGER NOT NULL,
	PRIMARY KEY("sales_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "board_games" (
	"id"	INTEGER NOT NULL UNIQUE,
	"name"	TEXT,
	"maker"	TEXT,
	"type"	TEXT,
	"mechanic"	TEXT,
	"player_count"	INTEGER,
	"age_limit"	TEXT,
	"dice_usage"	INTEGER,
	"card_usage"	INTEGER,
	"average_play_time"	INTEGER,
	"price"	REAL,
	"quantity_available"	INTEGER,
	"quantity_sold"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "purchases" (
	"id"	INTEGER NOT NULL UNIQUE,
	"supplier_id"	INTEGER,
	"boardgame_id"	INTEGER,
	"date"	TEXT,
	"quantity"	INTEGER,
	"unit_price"	NUMERIC,
	"vat"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "suppliers" ("id","name","contact_number","address","is_active") VALUES (1,'Piazza D&R','02165155260','Tugay Yolu Cd. Piazza AVM No:69-C',1),
 (2,'Ritim İstanbul Nezih','02165145084','Cevizli Mh, Zuhal Cd. Ritim İstanbul AVM B1 Katı, No: 37, 34846 Maltepe/İstanbul',0),
 (3,'hmm','234567','mmhm',1);
INSERT INTO "sales" ("sales_id","date","customer_id","product_id","quantity","unit_price") VALUES (1,'2024-12-19',1,1,2,100),
 (2,'2024-12-19',1,2,2,150),
 (3,'2024-12-20',2,2,3,150);
INSERT INTO "board_games" ("id","name","maker","type","mechanic","player_count","age_limit","dice_usage","card_usage","average_play_time","price","quantity_available","quantity_sold") VALUES (1,'Catan','Klaus Teuber','Strategy','Resource management','3-4 players','10+','true','true','60-90 minutes',300.0,50,200),
 (2,'Dixit','Jean-Louis Roubira','Storytelling','Storytelling','3-6 players','8+','false','true','30 minutes',250.0,40,150),
 (3,'Risk','Albert Lamorisse','Strategy','Area control','2-6 players','10+','true','true','120+ minutes',350.0,30,180),
 (4,'Monopoly','Charles Darrow','Family Game','Money management','2-8 players','8+','true','true','60-180 minutes',200.0,100,400),
 (5,'Taboo','Brian Hersch','Party Game','Word game','4+ players','12+','false','20-30 minutes','20-30 minutes',150.0,60,300),
 (6,'Pandemic','Matt Leacock','Cooperative','Cooperative play','2-4 players','8+','false','true',' 45-60 minutes',350.0,25,120),
 (7,'Uno','Merle Robbins','Card Game','Shedding','2-10 players','7+','false','true','15-30 minutes',100.0,200,1000),
 (8,'Carcassonne','Klaus-Jürgen Wrede','Tile-Placement','Map-building','2-5 players','8+','false','false','30 minutes',200.0,70,250),
 (9,'Chess','Unknown','Logic and Strategy','Grid movement','2 players','6+','false','true','10 minutes to 1 hour',150.0,150,500),
 (10,'Exploding Kittens','Elan Lee, Matthew Inman, Shane Small','Party Game','Press your luck','2-5 players','7+','false','true','15-20 minutes',200.0,80,300);
INSERT INTO "purchases" ("id","supplier_id","boardgame_id","date","quantity","unit_price","vat") VALUES (1,5,8,'2005/20/03',28,100,89);
COMMIT;
