create table box
(
    id     bigint  not null
        constraint box_pk
            primary key,
    name   text    not null,
    form   text    not null,
    symbol text    not null
);

comment on table box is 'Коробки-посылки';
comment on column box.id is 'Id коробки-посылки';
comment on column box.name is 'Наименование посылки';
comment on column box.form is 'Форма посылки';
comment on column box.symbol is 'Символ для прорисовки формы';

create unique index box_name_uindex
    on box (name);

