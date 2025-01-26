create table public."order"
(
    id            bigint not null
        constraint order_pk
            primary key,
    type          text,
    date          date,
    client_name   text,
    truck_count   bigint,
    box_count     bigint,
    segment_count bigint
);

comment on table public."order" is 'Заказы';
comment on column public."order".id is 'Номер операции';
comment on column public."order".type is 'Тип операции (Погрузка/Разгрузка)';
comment on column public."order".date is 'Дата операции';
comment on column public."order".client_name is 'Имя клиента';
comment on column public."order".truck_count is 'Кол-во машин';
comment on column public."order".box_count is 'Кол-во коробок-посылок';
comment on column public."order".segment_count is 'Кол-во расчетных единиц (сегментов)';
