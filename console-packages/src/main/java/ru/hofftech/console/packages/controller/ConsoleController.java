package ru.hofftech.console.packages.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.service.handler.CommandHandler;

import java.util.EnumMap;
import java.util.Map;

/**
 * Контроллер для обработки команд, вводимых через консоль.
 */
@ShellComponent
@RequiredArgsConstructor
public class ConsoleController {
    private final CommandHandler commandHandler;

    @ShellMethod("Справочник команд")
    public String helpCommand() {
        return commandHandler.handle(new Command(ConsoleCommand.HELP, null));
    }

    @ShellMethod(value = "Импорт посылок из файла txt/json", key = "import")
    public String importFile(@ShellOption(value = {"--import-filename", "--file"}, help = "Файл импорта данных")
                             String importFilename) {
        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.IMPORT_FILENAME, importFilename);

        if (importFilename.contains(".json")) {
            return commandHandler.handle(new Command(ConsoleCommand.IMPORT_FILE_JSON, arguments));
        }
        return commandHandler.handle(new Command(ConsoleCommand.IMPORT_FILE_TXT, arguments));
    }

    @ShellMethod(value = "Создание посылок")
    public String create(
            @ShellOption(value = {"--name"}, help = "Имя посылки") String name,
            @ShellOption(value = {"--form"}, help = "Форма посылки") String form,
            @ShellOption(value = {"--symbol"}, help = "Символ посылки") String symbol) {

        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.NAME, name);
        arguments.put(Argument.FORM, form);
        arguments.put(Argument.SYMBOL, symbol);

        return commandHandler.handle(new Command(ConsoleCommand.BOX_CREATE, arguments));
    }

    @ShellMethod("Редактирование посылок")
    public String edit(
            @ShellOption(value = {"--id"}, help = "Идентификатор посылки") String id,
            @ShellOption(value = {"--name"}, help = "Новое имя посылки") String name,
            @ShellOption(value = {"--form"}, help = "Новая форма посылки") String form,
            @ShellOption(value = {"--symbol"}, help = "Новый символ посылки") String symbol) {

        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.ID, id);
        arguments.put(Argument.NAME, name);
        arguments.put(Argument.FORM, form);
        arguments.put(Argument.SYMBOL, symbol);

        return commandHandler.handle(new Command(ConsoleCommand.BOX_EDIT, arguments));
    }

    @ShellMethod("Поиск и получение информации о посылке")
    public String find(@ShellOption(value = {"--name"}, help = "Имя посылки") String name) {

        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.NAME, name);

        return commandHandler.handle(new Command(ConsoleCommand.BOX_FIND, arguments));
    }

    @ShellMethod("Удаление посылок")
    public String delete(@ShellOption(value = {"--name"}, help = "Имя посылки") String name) {

        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.NAME, name);

        return commandHandler.handle(new Command(ConsoleCommand.BOX_DELETE, arguments));
    }

    @ShellMethod("Список посылок")
    public String list() {
        return commandHandler.handle(new Command(ConsoleCommand.BOX_LIST, null));
    }

    @ShellMethod("Загрузка посылок по именам в машины")
    public String load(
            @ShellOption(value = {"--parcels-text"},
                    defaultValue = "", help = "Текст с именами посылок") String parcelsText,
            @ShellOption(value = {"--parcels-file"},
                    defaultValue = "", help = "Файл с именами посылок") String parcelsFile,
            @ShellOption(value = {"--trucks"}, help = "Размеры машин") String trucks,
            @ShellOption(value = {"--type"}, help = "Тип алгоритма погрузки") String type,
            @ShellOption(value = {"--out-filename"}, defaultValue = "",
                    help = "Имя выходного файла") String outFilename) {

        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.PARCELS_TEXT, parcelsText);
        arguments.put(Argument.PARCELS_FILE, parcelsFile);
        arguments.put(Argument.TRUCKS, trucks);
        arguments.put(Argument.TYPE, type);
        arguments.put(Argument.OUT_FILENAME, outFilename);

        return commandHandler.handle(new Command(ConsoleCommand.LOAD, arguments));
    }

    @ShellMethod("Загрузка данных по машинам из файла переданным в параметре -in-filename и выгрузка результатов")
    public String unload(
            @ShellOption(value = {"--in-filename"}, help = "Имя входного файла") String inFilename,
            @ShellOption(value = {"--out-filename"}, defaultValue = "",
                    help = "Имя выходного файла") String outFilename,
            @ShellOption(value = {"--withcount"}, defaultValue = "",
                    help = "Добавить колонку с количеством") String withCount) {

        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.IN_FILENAME, inFilename);
        arguments.put(Argument.OUT_FILENAME, outFilename);
        arguments.put(Argument.WITHCOUNT, withCount);

        return commandHandler.handle(new Command(ConsoleCommand.UNLOAD, arguments));
    }

    @ShellMethod("Список операций пользователя за период")
    public String billing(
            @ShellOption(value = {"--user"}, help = "Пользователь системы") String user,
            @ShellOption(value = {"--period-from"}, help = "Период от") String periodFrom,
            @ShellOption(value = {"--period-to"}, help = "Период до") String periodTo) {

        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.USER, user);
        arguments.put(Argument.PERIOD_FROM, periodFrom);
        arguments.put(Argument.PERIOD_TO, periodTo);

        return commandHandler.handle(new Command(ConsoleCommand.BILLING, arguments));
    }
}
