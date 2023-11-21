create table purchase(
    id int auto_increment primary key,

    customer_id int not null,
    foreign key (customer_id) references customer(id),

    nfe varchar(255),
    price decimal(10,2) not null,
    created_at DATETIME not null
);


create table purchase_book(
    purchase_id int not null,
    book_id int not null,

    foreign key (purchase_id) references purchase(id),
    foreign key (book_id) references book(id),

    PRIMARY KEY (purchase_id, book_id)
);