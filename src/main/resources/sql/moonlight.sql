CREATE TABLE account(
	account_id INT AUTO_INCREMENT NOT NULL,
	passport_id VARCHAR(255) NULL,
	email VARCHAR(255) NULL,
	mobile VARCHAR(15) NOT NULL,
	reg_time TIMESTAMP NOT NULL,
	reg_ip VARCHAR(15) NOT NULL,
	uniq_name VARCHAR(127) NULL,
	gender TINYINT(1) NULL,
	province_id INT NULL,
	city_id INT NULL,
	area_id INT NULL,
	address VARCHAR(511) NULL,
	flag TINYINT(4),
	PRIMARY KEY (account_id),
	INDEX idx_name(uniq_name)
)ENGINE = INNODB DEFAULT CHARSET=utf8 COMMENT='用户表'

