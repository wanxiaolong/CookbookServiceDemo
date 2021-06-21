insert into `ingredient`(id, name) values (1, 'egg');
insert into `ingredient`(id, name) values (2, 'milk');
insert into `ingredient`(id, name) values (3, 'potato');

insert into `recipe`(id, name) values (1, 'Hardboiled egg');
insert into `recipe`(id, name) values (2, 'Scrambled egg');
insert into `recipe`(id, name) values (3, 'Spanish omelette');
insert into `recipe`(id, name) values (4, 'Potato chips');
insert into `recipe`(id, name) values (5, 'Mashed potato');

insert into `recipe_ingredient`(recipe_id, ingredient_id) values (1, 1);
insert into `recipe_ingredient`(recipe_id, ingredient_id) values (2, 1);
insert into `recipe_ingredient`(recipe_id, ingredient_id) values (2, 2);
insert into `recipe_ingredient`(recipe_id, ingredient_id) values (3, 1);
insert into `recipe_ingredient`(recipe_id, ingredient_id) values (3, 3);
insert into `recipe_ingredient`(recipe_id, ingredient_id) values (4, 3);
insert into `recipe_ingredient`(recipe_id, ingredient_id) values (5, 2);
insert into `recipe_ingredient`(recipe_id, ingredient_id) values (5, 3);
