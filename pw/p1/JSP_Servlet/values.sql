delete from users;
delete from answers;
delete from questions;

insert into users(id,username,password,all_time_best) values (1,'George','George',0), (2,'user2','pass',0);

insert into questions(id,question) values (1,'Who painted the Mona Lisa?'), (2,'Which Italian town is the setting for Romeo and Juliet?'),
                                          (3,'Which country produces the most tea?'), (4,'How many colors are in a rainbow?'),
                                          (5,'In what movie does Robert De Niro say, "You talkin to me?"');

insert into answers(id,questionid,correctness,answer) values (1,1,true,'Leonardo da Vinci'), (2,1,false,'Leonardo da Vinki'),
                                                             (3,1,false,'Mihai Eminescu'), (4,2,false,'Genoa'),
                                                             (5,2,false,'New Jersey'), (6,2,true,'Verona'),
                                                             (7,3,false,'Romania'), (8,3,true,'China'),
                                                             (9,3,false,'UK'),(10,4,false,'Eight'),
                                                             (11,4,false,'Six'),(12,4,true,'Seven'),
                                                             (13,5,false,'Titanic'),(14,5,true,'Taxi Driver'),
                                                             (15,5,false,'Scarface');