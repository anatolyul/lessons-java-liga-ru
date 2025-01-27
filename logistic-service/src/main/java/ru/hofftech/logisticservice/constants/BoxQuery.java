package ru.hofftech.logisticservice.constants;

public class BoxQuery {

    public static final String FIND_ALL = """
            select
                id,
                name,
                form,
                symbol
                from public.box;
            """;

    public static final String FIND_BY_ID = """
            select
                id,
                name,
                form,
                symbol
                from public.box
                where id = ?;
            """;

    public static final String FIND_BY_NAME = """
            select
                id,
                name,
                form,
                symbol
                from public.box
                where name = ?;
            """;

    public static final String CREATE = """
                insert into public.box
                (id, name, form, symbol)
                values (?, ?, ?, ?)
                returning id, name, form, symbol;
            """;

    public static final String UPDATE = """
            update public.box
                    set name = ?, form = ?, symbol = ?
                    where name = ?
                    returning id, name, form, symbol;
            """;

    public static final String DELETE = """
            delete from public.box
                    where name = ?;
            """;

    public static final String MAX_ID = """
            select
                max(id) id
                from public.box;
            """;
}
