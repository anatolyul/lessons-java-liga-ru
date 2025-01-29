package ru.hofftech.logisticcliservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import org.springframework.shell.standard.ShellOption;
import ru.hofftech.logisticcliservice.dto.command.BillingCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxCreateCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxDeleteCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxEditCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxFindCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxListCommandDto;
import ru.hofftech.logisticcliservice.dto.command.HelpCommandDto;
import ru.hofftech.logisticcliservice.dto.command.ImportCommandDto;
import ru.hofftech.logisticcliservice.dto.command.LoadCommandDto;
import ru.hofftech.logisticcliservice.dto.command.UnloadCommandDto;
import ru.hofftech.logisticcliservice.enums.TypeAlgorithm;
import ru.hofftech.logisticcliservice.service.handler.CommandHandler;

/**
 * Контроллер для обработки команд, вводимых через консоль.
 */
@ShellComponent
@RequiredArgsConstructor
public class ShellController {
    private final CommandHandler commandHandler;

    /**
     * Отображает справочник команд.
     *
     * @return строка с описанием команд
     */
    @ShellMethod("Справочник команд")
    public String helpCommand() {
        return commandHandler.handle(HelpCommandDto.builder().build());
    }

    /**
     * Импортирует посылки из файла TXT или JSON.
     *
     * @param importFilename имя файла для импорта
     * @return результат выполнения команды
     */
    @ShellMethod(value = "Импорт посылок из файла txt/json", key = "import")
    public String importFile(@ShellOption(value = {"--import-filename", "--file"}, help = "Файл импорта данных")
                             String importFilename) {
        ImportCommandDto importCommandDto = ImportCommandDto.builder()
                .filename(importFilename)
                .build();

        return commandHandler.handle(importCommandDto);
    }

    /**
     * Создает новую посылку.
     *
     * @param name   имя посылки
     * @param form   форма посылки
     * @param symbol символ посылки
     * @return результат выполнения команды
     */
    @ShellMethod(value = "Создание посылок")
    public String create(
            @ShellOption(value = {"--name"}, help = "Имя посылки") String name,
            @ShellOption(value = {"--form"}, help = "Форма посылки") String form,
            @ShellOption(value = {"--symbol"}, help = "Символ посылки") String symbol) {

        BoxCreateCommandDto boxCreateCommandDto = BoxCreateCommandDto.builder()
                .name(name)
                .form(form)
                .symbol(symbol)
                .build();

        return commandHandler.handle(boxCreateCommandDto);
    }

    /**
     * Редактирует существующую посылку.
     *
     * @param id     идентификатор посылки
     * @param name   новое имя посылки
     * @param form   новая форма посылки
     * @param symbol новый символ посылки
     * @return результат выполнения команды
     */
    @ShellMethod("Редактирование посылок")
    public String edit(
            @ShellOption(value = {"--id"}, help = "Идентификатор посылки") String id,
            @ShellOption(value = {"--name"}, help = "Новое имя посылки") String name,
            @ShellOption(value = {"--form"}, help = "Новая форма посылки") String form,
            @ShellOption(value = {"--symbol"}, help = "Новый символ посылки") String symbol) {

        BoxEditCommandDto boxEditCommandDto = BoxEditCommandDto.builder()
                .oldName(id)
                .name(name)
                .form(form)
                .symbol(symbol).build();

        return commandHandler.handle(boxEditCommandDto);
    }

    /**
     * Находит и отображает информацию о посылке.
     *
     * @param name имя посылки
     * @return результат выполнения команды
     */
    @ShellMethod("Поиск и получение информации о посылке")
    public String find(@ShellOption(value = {"--name"}, help = "Имя посылки") String name) {

        BoxFindCommandDto boxFindCommandDto = BoxFindCommandDto.builder().boxName(name).build();

        return commandHandler.handle(boxFindCommandDto);
    }

    /**
     * Удаляет посылку.
     *
     * @param name имя посылки
     * @return результат выполнения команды
     */
    @ShellMethod("Удаление посылок")
    public String delete(@ShellOption(value = {"--name"}, help = "Имя посылки") String name) {

        BoxDeleteCommandDto boxDeleteCommandDto = BoxDeleteCommandDto.builder().boxName(name).build();

        return commandHandler.handle(boxDeleteCommandDto);
    }

    /**
     * Отображает список всех посылок.
     *
     * @return результат выполнения команды
     */
    @ShellMethod("Список посылок")
    public String list() {
        return commandHandler.handle(BoxListCommandDto.builder().build());
    }

    /**
     * Загружает посылки в грузовики.
     *
     * @param parcelsText текст с именами посылок
     * @param parcelsFile файл с именами посылок
     * @param trucks      размеры грузовиков
     * @param type        тип алгоритма погрузки
     * @param outFilename имя выходного файла
     * @return результат выполнения команды
     */
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

        LoadCommandDto loadCommandDto = LoadCommandDto.builder()
                .parcelsFile(parcelsFile)
                .parcelsText(parcelsText)
                .trucks(trucks)
                .type(TypeAlgorithm.convertStringToEnum(type))
                .outFilename(outFilename)
                .build();

        return commandHandler.handle(loadCommandDto);
    }

    /**
     * Разгружает грузовики и сохраняет результаты в файл.
     *
     * @param inFilename  имя входного файла
     * @param outFilename имя выходного файла
     * @param withCount   добавить колонку с количеством
     * @return результат выполнения команды
     */
    @ShellMethod("Загрузка данных по машинам из файла переданным в параметре -in-filename и выгрузка результатов")
    public String unload(
            @ShellOption(value = {"--in-filename"}, help = "Имя входного файла") String inFilename,
            @ShellOption(value = {"--out-filename"}, defaultValue = "",
                    help = "Имя выходного файла") String outFilename,
            @ShellOption(value = {"--withcount"}, defaultValue = "",
                    help = "Добавить колонку с количеством") boolean withCount) {

        UnloadCommandDto unloadCommandDto = UnloadCommandDto.builder()
                .inFilename(inFilename)
                .outFilename(outFilename)
                .withCount(withCount)
                .build();

        return commandHandler.handle(unloadCommandDto);
    }

    /**
     * Отображает список операций пользователя за период.
     *
     * @param user       пользователь системы
     * @param periodFrom начало периода
     * @param periodTo   конец периода
     * @return результат выполнения команды
     */
    @ShellMethod("Список операций пользователя за период")
    public String billing(
            @ShellOption(value = {"--user"}, help = "Пользователь системы") String user,
            @ShellOption(value = {"--period-from"}, help = "Период от") String periodFrom,
            @ShellOption(value = {"--period-to"}, help = "Период до") String periodTo) {

        BillingCommandDto billingCommandDto = BillingCommandDto.builder()
                .userName(user)
                .build();
        billingCommandDto.setStringStartDate(periodFrom);
        billingCommandDto.setStringEndDate(periodTo);

        return commandHandler.handle(billingCommandDto);
    }
}
