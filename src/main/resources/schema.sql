create table shelters
(
    id      identity primary key,
    name    varchar not null unique,
    address varchar not null unique
);

create table pets
(
    id          identity primary key,
    name        varchar not null,
    type        varchar not null,
    age         int     not null,
    gender      varchar not null,
    shelters_id int     not null,
    foreign key (shelters_id) references shelters (id)
);


