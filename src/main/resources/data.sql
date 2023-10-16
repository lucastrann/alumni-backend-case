INSERT INTO Users (id, name, picture, status, bio, fun_fact)
VALUES ('c28668b9-57b1-4779-b5ce-fbc1cb7878a8', 'Eivind',
        'https://ychef.files.bbci.co.uk/976x549/p07ryyyj.jpg',
        'Smol criminal',
        'Introducing Eivind, a curious and adventurous cat who enjoys exploring new places and playing with toys that make crinkly sounds.',
        'Did you know that Oslo is home to the world''s longest art gallery, which is located in the city''s subway system and features over 100 unique works of art?');
INSERT INTO Users (id, name, picture, status, bio, fun_fact)
VALUES ('1e97e97c-83e1-453f-8f87-87a659e84153',  'Hashir Raja',
        'https://i.ytimg.com/vi/fOd16PT1S7A/maxresdefault.jpg',
        'The Great Catsby',
        'Meet Martin, a playful and cuddly feline who loves nothing more than chasing laser pointers and napping in sunny spots around the house.',
        'Why is it so easy to weigh tuna? - They have their own scales');
INSERT INTO Users (id, name, picture, status, bio, fun_fact)
VALUES ('21bc1535-4a49-483f-92ef-c305f91dc1ef', 'Henning Sletner',
        'https://www.rd.com/wp-content/uploads/2021/01/GettyImages-1175550351.jpg',
        'Cat me some slack, will you?',
        'A cat above the rest.',
        'Purr-haps we can cuddle later.');
INSERT INTO Users (id, name, picture, status, bio, fun_fact)
VALUES ('18b14274-4bb2-461f-a293-2941a1b9dea1', 'Ozan Kara',
        'https://i.pinimg.com/564x/32/f2/43/32f24381b05fcf53d8088c98963fe326.jpg',
        'It''s a cat-astrophy!',
        'Born fluffy, still fluffy.',
        'What do you call a tuna with a tie? - Sofishticated');
INSERT INTO Users (id, name, picture, status, bio, fun_fact)
VALUES ('9e8ae4c6-7901-4ce3-b562-395fc411e006',  'Lucas Tran',
        'https://cdn.drawception.com/images/avatars/647493-B9E.png',
        'ARBEIDSLEDIG',
        'LOLLOLOLOLLOLOLOLOLLOL',
        'FUN FACT ABOUT ME, I HATE THIS FUCKING SHIT');

INSERT INTO Groups ( name, description, color, is_private)
VALUES ('Experis Academy Autumn 2022',
        'The Experis Academy alumni are like a squad of superhero programmers, armed with coding skills and ready to conquer any tech challenge that comes their way!',
        '#FF1493', true);
INSERT INTO Groups (name, description, color, is_private)
VALUES ( 'Experis Academy Spring 2023',
        'Joining the Experis Academy alumni is like unlocking a secret society of coding wizards, where the only rule is to have fun while building amazing tech solutions!',
        '#008B8B', true);

INSERT INTO group_user ("groups_id", "users_id")
VALUES (1, '9e8ae4c6-7901-4ce3-b562-395fc411e006');
VALUES (2, 'c28668b9-57b1-4779-b5ce-fbc1cb7878a8');
INSERT INTO group_user ("groups_id", "users_id")
VALUES (2, '1e97e97c-83e1-453f-8f87-87a659e84153');
INSERT INTO group_user ("groups_id", "users_id")
VALUES (2, '21bc1535-4a49-483f-92ef-c305f91dc1ef');
INSERT INTO group_user ("groups_id", "users_id")
VALUES (2, '18b14274-4bb2-461f-a293-2941a1b9dea1');
INSERT INTO group_user ("groups_id", "users_id")
VALUES (2, '9e8ae4c6-7901-4ce3-b562-395fc411e006');

INSERT INTO Post (owner_id, title, content)
VALUES ('9e8ae4c6-7901-4ce3-b562-395fc411e006', 'ChatGPT''s take on UI/UX',
        'Creating a great UI/UX is like baking the **perfect cake** - you need to mix the right ingredients, layer them with care, and add a touch of flair to make it truly delicious!' ||
        '![cake](https://del.h-cdn.co/assets/18/06/2560x1280/landscape-1518115142-delish-red-velvet-cake.jpg)');

INSERT INTO Post (owner_id, title, content)
VALUES ('9e8ae4c6-7901-4ce3-b562-395fc411e006', 'Inspiration',
        'UI/UX design is like a dance between art and science, where **creativity and logic** work in harmony to create beautiful and functional digital products.' ||
        '![dancers](https://live.staticflickr.com/4008/4494092068_4db041aa4a_b.jpg)');

INSERT INTO Post (owner_id, title, content)
VALUES ('9e8ae4c6-7901-4ce3-b562-395fc411e006', 'Roller coaster',
        'Designing digital products is like creating a **fun adventure park** for users, complete with exciting rides and easy-to-follow maps.' ||
        '![rollercoaster](https://img.freepik.com/free-vector/scene-with-roller-coaster-circus_1308-33643.jpg)');

INSERT INTO Post (owner_id, title, content)
VALUES ('9e8ae4c6-7901-4ce3-b562-395fc411e006', 'Let''s make Hogwarts great again!',
        'Creating a user-friendly digital product is like building a **house with secret passages** and hidden treasures - it should be easy to navigate, but also full of delightful surprises.' ||
        '![hogwarts staircase](https://blockwarts.files.wordpress.com/2022/07/hogwarts-staircase.jpg)');
