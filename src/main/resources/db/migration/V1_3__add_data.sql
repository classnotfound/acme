-- Categories
insert into category(name) values('Cat');
insert into category(name) values('Dog');
insert into category(name) values('Mouse');
insert into category(name) values('Duck');
-- Tags
insert into tag(name) values('Walt Disney');
insert into tag(name) values('Tex Avery');
insert into tag(name) values('Pixart');
insert into tag(name) values('Cartoon');
insert into tag(name) values('Comics');
--Pets
insert into pet(name, status, category_id) values('Mickey', 'AVAILABLE',select id from category where name='Mouse'); --Mickey
insert into pet(name, status, category_id) values('Minnie', 'PENDING', select id from category where name='Mouse'); --Minnie
insert into pet(name, status, category_id) values('Goofy', 'SOLD', select id from category where name='Dog'); --Goofy
insert into pet(name, status, category_id) values('Donald', 'AVAILABLE', select id from category where name='Duck'); --Donald
insert into pet(name, status, category_id) values('Tom', 'AVAILABLE', select id from category where name='Cat'); -- Tom
insert into pet(name, status, category_id) values('Jerry', 'SOLD', select id from category where name='Mouse'); -- Jerry
--tags<->pets
insert into tag_pets(pets, tags) values(1,1);
insert into tag_pets(pets, tags) values(1,4);
insert into tag_pets(pets, tags) values(2,1);
insert into tag_pets(pets, tags) values(2,4);
insert into tag_pets(pets, tags) values(3,1);
insert into tag_pets(pets, tags) values(3,4);
insert into tag_pets(pets, tags) values(4,1);
insert into tag_pets(pets, tags) values(5,2);
insert into tag_pets(pets, tags) values(5,4);
insert into tag_pets(pets, tags) values(6,2);

--Photos
--Mickey
insert into photo(url, pet_id) values('img/pets/mickey-mouse-plush-disney-baby-photo-1800x1800-pr-0361.jpg', select id from pet where name='Mickey');
insert into photo(url, pet_id) values('img/pets/Large_Mickey_Mouse_Soft_Toy.jpg', select id from pet where name='Mickey');

--Minnie
insert into photo(url, pet_id) values('img/pets/pTRU1-9248293dt.jpg', select id from pet where name='Minnie');
insert into photo(url, pet_id) values('img/pets/31k8E0ZA9LL.jpg', select id from pet where name='Minnie');
insert into photo(url, pet_id) values('img/pets/1339629316_2.jpg', select id from pet where name='Minnie');

--Goofy
insert into photo(url, pet_id) values('img/pets/Disney-Character-Medium-Plush----pTRU1-17528798dt.jpg', select id from pet where name='Goofy');
insert into photo(url, pet_id) values('img/pets/41qLzb4-nzL._SY300_.jpg', select id from pet where name='Goofy');
insert into photo(url, pet_id) values('img/pets/k2-_cf9db624-ff60-44cd-8620-a93fac2969a9.v1.jpg', select id from pet where name='Goofy');

--Donald
insert into photo(url, pet_id) values('img/pets/1261000441862.jpg', select id from pet where name='Donald');
insert into photo(url, pet_id) values('img/pets/412010351704.jpg', select id from pet where name='Donald');
insert into photo(url, pet_id) values('img/pets/41Er0CjKgpL._SY300_.jpg', select id from pet where name='Donald');

--Tom
insert into photo(url, pet_id) values('img/pets/Tom-And-Jerry-plush-toy-Cat-and-mouse-staffed-soft-toy-factory-supply-freeshipping.jpg', select id from pet where name='Tom');
insert into photo(url, pet_id) values('img/pets/Warner-Bros-Tom-Stuffed-Toy-SDL502747510-1-f9969.jpg', select id from pet where name='Tom');
insert into photo(url, pet_id) values('img/pets/Large-Vintage-Plush-Tom-the-Cat-from-Tom.jpg', select id from pet where name='Tom');
insert into photo(url, pet_id) values('img/pets/Large-Vintage-Plush-Tom-the-Cat-from-Tom.jpg', select id from pet where name='Tom');

--Jerry
insert into photo(url, pet_id) values('img/pets/2109810-warner-brothers-jerry-plush-toy.jpg', select id from pet where name='Jerry');
insert into photo(url, pet_id) values('img/pets/jerry-plush-stuffed-toy-classic-tom-jerry-series-doll-20cm_TW03808_s.jpg', select id from pet where name='Jerry');
insert into photo(url, pet_id) values('img/pets/8260.jpg', select id from pet where name='Jerry');
insert into photo(url, pet_id) values('img/pets/tom-and-jerry-plush-9.8-25cm-jerry-mouse-character-doll-stuffed-animals-figure-soft-anime-collection-toy_7935196.jpeg', select id from pet where name='Jerry');
