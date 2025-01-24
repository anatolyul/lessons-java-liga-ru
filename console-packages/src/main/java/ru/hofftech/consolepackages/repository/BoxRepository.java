package ru.hofftech.consolepackages.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import ru.hofftech.consolepackages.model.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Репозиторий для управления коробками.
 */
@Setter
@Getter
@Repository
public class BoxRepository {
    private List<Box> boxes = new ArrayList<>();

    /**
     * Инициализирует список коробок.
     */
    public BoxRepository() {
        boxes.addAll(List.of(
                new Box("Посылка Тип 1", "1", "1"),
                new Box("Посылка Тип 2", "22", "2"),
                new Box("Посылка Тип 3", "333", "3"),
                new Box("Посылка Тип 4", "4444", "4"),
                new Box("Посылка Тип 5", "55555", "5"),
                new Box("Посылка Тип 6", "666\n666", "6"),
                new Box("Посылка Тип 7", "777\n7777", "7"),
                new Box("Посылка Тип 8", "8888\n8888", "8"),
                new Box("Посылка Тип 9", "999\n999\n999", "9")));
    }

    /**
     * Возвращает все коробки.
     *
     * @return строка, содержащая информацию о всех коробках
     */
    public List<Box> findAll() {
        return boxes;
    }

    /**
     * Создает новую коробку.
     *
     * @param name   имя коробки
     * @param form   форма коробки
     * @param symbol символ коробки
     * @return строка, содержащая информацию о созданной коробке или сообщение об ошибке
     */
    public String createBox(String name, String form, String symbol) {
        if (name == null || form == null || symbol == null) {
            return "Ошибочный запрос на создание посылки";
        }

        if (findBoxByName(name) == null) {
            Box newBox = new Box(name, form, symbol);
            boxes.add(newBox);
            return String.format("Посылка:\nname: %s\nform:\n%s", newBox.getName(), newBox.getForm());
        } else {
            return String.format("Посылка с именем %s уже есть в системе, добавление новой запрещено!", name);
        }
    }

    /**
     * Находит коробку по имени.
     *
     * @param name имя коробки
     * @return найденная коробка или null, если коробка не найдена
     */
    public Box findBoxByName(String name) {
        return boxes.stream().filter(box -> box.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Находит коробку по идентификатору или имени.
     *
     * @param id   идентификатор коробки
     * @param name имя коробки
     * @return строка, содержащая информацию о найденной коробке или сообщение об ошибке
     */
    public String findBox(String id, String name) {
        Box boxResult = findBoxByName(id != null ? id : name);

        if (boxResult != null) {
            return String.format("Посылка:\nname: %s\nform:\n%s", boxResult.getName(), boxResult.getForm());
        } else {
            return String.format("Посылка с именем %s не найдена в системе!", name);
        }
    }

    /**
     * Обновляет информацию о коробке.
     *
     * @param id     идентификатор коробки
     * @param name   новое имя коробки
     * @param form   новая форма коробки
     * @param symbol новый символ коробки
     * @return строка, содержащая информацию об обновленной коробке или сообщение об ошибке
     */
    public String updateBox(String id, String name, String form, String symbol) {
        if (id == null || name == null || form == null || symbol == null) {
            return "Ошибочный запрос на редактирование посылки";
        }

        Box box = findBoxByName(id);

        if (box != null) {
            boxes.remove(box);
            box.setName(name);
            box.setSymbol(symbol);
            box.setForm(form);
            boxes.add(box);
            return String.format("Посылка:\nname: %s\nform:\n%s", box.getName(), box.getForm());
        } else {
            return String.format("Посылка с именем %s не найдена, редактирование запрещено!", name);
        }
    }

    /**
     * Удаляет коробку по имени.
     *
     * @param name имя коробки
     * @return строка, содержащая сообщение об удалении коробки или сообщение об ошибке
     */
    public String deleteBox(String name) {
        Box box = findBoxByName(name);

        if (box != null) {
            boxes.remove(box);
            return String.format("Посылка с именем %s удалена", box.getName());
        } else {
            return "Посылка не найдена, удаление запрещено!";
        }
    }

    /**
     * Возвращает случайный список коробок.
     *
     * @param count количество коробок для выбора
     * @return список случайных коробок
     */
    public List<Box> getBoxesRandom(int count) {
        return getBoxes().stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                    Random random = new Random();
                    return random.ints(count, 0, collected.size())
                            .mapToObj(collected::get)
                            .collect(Collectors.toList());
                }));
    }
}
