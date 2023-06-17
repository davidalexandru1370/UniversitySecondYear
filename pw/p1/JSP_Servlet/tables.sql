drop table answers;
drop table users;

create table users
(
    id int not null primary key,
    username varchar(100) not null,
    password varchar(100) not null,
    all_time_best int not null
);

drop table questions;

create table questions
(
    id int not null primary key,
    question varchar(100) not null
);

create table answers
(
    id int not null primary key,
    questionid int not null,
    correctness boolean not null,
    answer varchar(100) not null,
    constraint fk_question foreign key(questionid) references questions(id)
);
