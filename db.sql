create table roles(
                      id serial primary key,
                      name varchar(64) not null
);
insert into roles (name) values ('ADMIN');
insert into roles (name) values ('USER');

select * from roles;

create table authorities(
                            id serial primary key,
                            name varchar(64) not null
);
insert into authorities (name) values ('read');
insert into authorities (name) values ('add');
insert into authorities (name) values ('edit');
insert into authorities (name) values ('delete');
select * from authorities;

create table role_authorities(
                                 role_id int,
                                 foreign key(role_id) references roles(id),
                                 authority_id int,
                                 foreign key(authority_id) references authorities(id)
);
insert into role_authorities (role_id, authority_id) values ('1','1');
insert into role_authorities (role_id, authority_id) values ('1','2');
insert into role_authorities (role_id, authority_id) values ('1','3');
insert into role_authorities (role_id, authority_id) values ('1','4');
insert into role_authorities (role_id, authority_id) values ('2','1');
select * from role_authorities;

create table users(
                      id serial primary key,
                      phone_number varchar(11) not null unique,
                      password varchar(64) not null,
                      role_id int not null,
                      foreign key(role_id) references roles(id)
);

create table wallets(
                        id serial primary key,
                        user_id int not null,
                        serial_number varchar(16) not null unique,
                        balance_kzt int,
                        balance_usd decimal,
                        balance_eur decimal,
                        foreign key(user_id) references users(id)
);

insert into wallets(user_id, serial_number, balance_kzt, balance_usd, balance_eur)
values (1, '1111222233334444', 25000, 0, 0);
select * from wallets;
update wallets set balance_kzt = 110000, balance_usd = 0 where id=2;

create table exchange_rate(
                              id serial primary key,
                              currency varchar(4) not null ,
                              value int not null
);
insert into exchange_rate(currency, value) values('USD', 420), ('EUR', 500);
select * from exchange_rate;

create table services(
                         id serial primary key,
                         name varchar(64) not null,
                         price int not null
);
insert into services(name, price) values ('Heating', 2000), ('Internet', 5000);
select * from services;

insert into users(phone_number, password, role_id) values ('87476530433', '123', 1);
select * from users;
delete from users where id = 11;


create table wallet_changes(
                               id serial primary key,
                               wallet_id int,
                               user_id int,
                               old_balance_kzt int,
                               old_balance_usd decimal,
                               old_balance_eur decimal,
                               new_balance_kzt int,
                               new_balance_usd decimal,
                               new_balance_eur decimal,
                               changed_date timestamp(6)
);
select * wallet_changes;

create or replace function log_wallet_changes()
    returns trigger
    language plpgsql
    as
    $$
begin
        if new.balance_kzt <> old.balance_kzt or new.balance_usd <> old.balance_usd or new.balance_eur <> old.balance_eur then
            insert into wallet_changes( wallet_id, user_id, old_balance_kzt, old_balance_usd, old_balance_eur,
                                       new_balance_kzt, new_balance_usd, new_balance_eur, changed_date)
                                       values (old.id, old.user_id, old.balance_kzt, old.balance_usd, old.balance_eur,
                                               new.balance_kzt, new.balance_usd, new.balance_eur, now());
end if;
return new;
end;
    $$;

create trigger wallet_changes_trigger
    before update
    on wallets
    for each row
    execute procedure log_wallet_changes();

