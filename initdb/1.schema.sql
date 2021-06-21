create database if not exists cookbook;
use cookbook;
create table `ingredient` (
    `id` int unsigned auto_increment,
    `name` varchar(20) not null,
    primary key (`id`)
) engine=InnoDB default charset=utf8mb4;

create table `recipe` (
    `id` int unsigned auto_increment,
    `name` varchar(20) not null,
    primary key (`id`)
) engine=InnoDB default charset=utf8mb4;

create table `recipe_ingredient` (
    `id` int unsigned auto_increment,
    `recipe_id` int unsigned not null,
    `ingredient_id` int unsigned not null,
    primary key (`id`),
    foreign key (`recipe_id`) references recipe(`id`),
    foreign key (`ingredient_id`) references ingredient(`id`)
) engine=InnoDB default charset=utf8mb4;
