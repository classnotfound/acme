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
insert into photo(url, pet_id) values('http://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=26594018', select id from pet where name='Mickey');
insert into photo(url, pet_id) values('http://a.dilcdn.com/bl/wp-content/uploads/sites/13/2011/10/mickey-mouse-plush-disney-baby-photo-1800x1800-pr-0361.jpg', select id from pet where name='Mickey');
insert into photo(url, pet_id) values('http://image.ec21.com/image/sbjlhj/oimg_GC07077919_CA07077920/Large_Mickey_Mouse_Soft_Toy.jpg', select id from pet where name='Mickey');
--Minnie
insert into photo(url, pet_id) values('http://www.toysrus.com/graphics/product_images/pTRU1-9248293dt.jpg', select id from pet where name='Minnie');
insert into photo(url, pet_id) values('http://ecx.images-amazon.com/images/I/31k8E0ZA9LL.jpg', select id from pet where name='Minnie');
insert into photo(url, pet_id) values('https://www.purseboutique.com/commercesuite_products/1339629316_2.jpg', select id from pet where name='Minnie');
--Goofy
insert into photo(url, pet_id) values('http://www.toysrus.com/graphics/tru_prod_images/Disney-Character-Medium-Plush----pTRU1-17528798dt.jpg', select id from pet where name='Goofy');
insert into photo(url, pet_id) values('http://ecx.images-amazon.com/images/I/41qLzb4-nzL._SY300_.jpg', select id from pet where name='Goofy');
insert into photo(url, pet_id) values('http://i5.walmartimages.com/dfw/dce07b8c-8e62/k2-_cf9db624-ff60-44cd-8620-a93fac2969a9.v1.jpg', select id from pet where name='Goofy');
--Donald
insert into photo(url, pet_id) values('http://cdn.s7.disneystore.com/is/image/DisneyShopping/1261000441862?$yetidetail$', select id from pet where name='Donald');
insert into photo(url, pet_id) values('http://cdn.s7.disneystore.co.uk/is/image/DisneyStoreUK/412010351704?$yetidetail$', select id from pet where name='Donald');
insert into photo(url, pet_id) values('http://ecx.images-amazon.com/images/I/41Er0CjKgpL._SY300_.jpg', select id from pet where name='Donald');
--Tom
insert into photo(url, pet_id) values('http://g01.a.alicdn.com/kf/HTB1JGHFHVXXXXXxXFXXq6xXFXXXI/Tom-And-Jerry-plush-toy-Cat-and-mouse-staffed-soft-toy-factory-supply-freeshipping.jpg', select id from pet where name='Tom');
insert into photo(url, pet_id) values('http://n2.sdlcdn.com/imgs/a/p/j/large/Warner-Bros-Tom-Stuffed-Toy-SDL502747510-1-f9969.jpg', select id from pet where name='Tom');
insert into photo(url, pet_id) values('http://thumbs4.picclick.com/d/l400/pict/371521015327_/Large-Vintage-Plush-Tom-the-Cat-from-Tom.jpg', select id from pet where name='Tom');
insert into photo(url, pet_id) values('http://n2.sdlcdn.com/imgs/a/p/j/large/Warner-Bros-Tom-Stuffed-Toy-SDL502747510-1-f9969.jpg', select id from pet where name='Tom');
--Jerry
insert into photo(url, pet_id) values('http://ak0.scstatic.net/1/cdn2-cont2.sweetcouch.com/2109810-warner-brothers-jerry-plush-toy.jpg', select id from pet where name='Jerry');
insert into photo(url, pet_id) values('http://www.toyswill.com/products/TW03808/jerry-plush-stuffed-toy-classic-tom-jerry-series-doll-20cm_TW03808_s.jpg', select id from pet where name='Jerry');
insert into photo(url, pet_id) values('http://i1169.photobucket.com/albums/r513/cengwx00/cwx13/8260.jpg', select id from pet where name='Jerry');
insert into photo(url, pet_id) values('http://guideimg.alibaba.com/images/shop/2015/08/18/96/tom-and-jerry-plush-9.8-25cm-jerry-mouse-character-doll-stuffed-animals-figure-soft-anime-collection-toy_7935196.jpeg', select id from pet where name='Jerry');

