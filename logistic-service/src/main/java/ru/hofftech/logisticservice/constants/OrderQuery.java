package ru.hofftech.logisticservice.constants;

public class OrderQuery {

    public static final String FIND_ALL = """
            select
                id,
                type,
                date,
                client_name,
                truck_count,
                box_count,
                segment_count
                from public.order;
            """;

    public static final String FIND_BY_NAME_PERIOD = """
            select
                id,
                type,
                date,
                client_name,
                truck_count,
                box_count,
                segment_count
                from public.order
                where client_name = ?
                and date >= ?
                and date <= ?
                ;
            """;
}
