use laba_atm;

insert into addresses (city, street, house) values ('Minsk', 'Kolasa', 15);
insert into addresses (city, street, house) values ('Minsk', 'Kosmonavtov', 32);

insert into accounts (number) value ('BY06ALFA3012888877776666');
insert into accounts (number) value ('BY04ALFA3014333555676789');

insert into cards (account_id, number, pin, balance, currency_type)
values (1, 5351402154561756, 1234, 350, 'BYN'),
	   (2, 5351402154234567, 5678, 9000, 'BYN'),
	   (1, 5351402154234888, 8901, 600, 'BYN'),
	   (2, 5351402154555567, 1267, 1200, 'USD'),
	   (1, 5351402144434567, 4289, 33000, 'RUB');

insert into clients (account_id, name, surname)
values (1, 'Olga', 'Orlova'),
	   (2, 'Ivan', 'Petrov');

insert into cash (denomination, quantity, currency_type)
values (5, 100, 'BYN'),
	   (10, 100, 'BYN'),
       (20, 100, 'BYN'),
       (50, 100, 'BYN'),
       (100, 100, 'BYN');

insert into cash (denomination, quantity, currency_type)
values (50, 100, 'RUB'),
	   (100, 50, 'RUB'),
       (200, 10, 'RUB'),
       (500, 10, 'RUB'),
       (1000, 10, 'RUB');
