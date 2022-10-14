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

insert into banks (name) value ('Alfa Bank');
insert into banks (name) value ('MTBank');

insert into atms (bank_id, address_id) value (1, 1);
insert into atms (bank_id, address_id) value (2, 2);

insert into cash (atm_id, denomination, quantity, currency_type)
values (1, 5, 100, 'BYN'),
	   (1, 10, 100, 'BYN'),
       (1, 20, 100, 'BYN'),
       (1, 50, 100, 'BYN'),
       (1, 100, 100, 'BYN'),
       (2, 5, 50, 'BYN'),
	   (2, 10, 50, 'BYN'),
       (2, 20, 100, 'BYN'),
       (2, 50, 50, 'BYN'),
       (2, 100, 100, 'BYN');

insert into cash (atm_id, denomination, quantity, currency_type)
values (1, 50, 100, 'RUB'),
	   (1, 100, 50, 'RUB'),
       (1, 200, 10, 'RUB'),
       (1, 500, 10, 'RUB'),
       (1, 1000, 10, 'RUB'),
       (2, 50, 100, 'RUB'),
	   (2, 100, 50, 'RUB'),
       (2, 200, 100, 'RUB'),
       (2, 500, 50, 'RUB'),
       (2, 1000, 50, 'RUB');

insert into cash (atm_id, denomination, quantity, currency_type)
values (1, 5, 50, 'USD'),
	   (1, 10, 50, 'USD'),
	   (1, 20, 50, 'USD'),
	   (1, 50, 10, 'USD'),
	   (1, 100, 10, 'USD'),
	   (2, 5, 60, 'USD'),
	   (2, 10, 100, 'USD'),
	   (2, 20, 30, 'USD'),
	   (2, 50, 100, 'USD'),
	   (2, 100, 20, 'USD');

insert into cash (atm_id, denomination, quantity, currency_type)
values (1, 5, 100, 'EUR'),
	   (1, 10, 50, 'EUR'),
	   (1, 20, 100, 'EUR'),
	   (1, 50, 50, 'EUR'),
	   (1, 100, 50, 'EUR'),
	   (2, 5, 60, 'EUR'),
	   (2, 10, 100, 'EUR'),
	   (2, 120, 30, 'EUR'),
	   (2, 50, 100, 'EUR'),
	   (2, 100, 20, 'EUR');

insert into cash (atm_id, denomination, quantity, currency_type)
values (1, 5, 100, 'CNY'),
	   (1, 10, 50, 'CNY'),
	   (1, 20, 100, 'CNY'),
	   (1, 50, 100, 'CNY'),
	   (1, 100, 50, 'CNY'),
       (2, 5, 200, 'CNY'),
	   (2, 10, 150, 'CNY'),
	   (2, 20, 100, 'CNY'),
	   (2, 50, 100, 'CNY'),
	   (2, 100, 50, 'CNY');

insert into bank_clients (bank_id, client_id) value (1, 1);
insert into bank_clients (bank_id, client_id) value (2, 2);
