insert into shelters (id, name, address)
values (1, 'Find a home', 'Moscow region, village Vyazma, house 12'),
       (2, 'Take a friend', 'Moscow, Odintsovsky district, Gubkino village, house 25'),
       (3, 'Murkosha', 'Moscow, Ostashkovskaya house 14'),
       (4, 'Forest shelter', 'Moscow region, Istra district');

insert into pets (name, type, age, gender, shelters_id)
VALUES ('Murzik', 'kat', 2, 'boy', 3),
       ('Filya', 'dog', 1, 'boy', 1),
       ('Lusia', 'dog', 5, 'girl', 2),
       ('Aisha', 'dog', 1, 'girl', 4);