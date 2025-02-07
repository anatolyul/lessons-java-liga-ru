package ru.hofftech.logistictelegrambotservice.service.command;

import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;

/**
 * Сервис для выполнения команды отображения справочной информации.
 */
@Service
public class HelpCommandService implements CommandExecutor {

    /**
     * Выполняет команду отображения справочной информации.
     *
     * @param command команда для выполнения
     * @return строка, содержащая справочную информацию
     */
    @Override
    public String execute(CommandDto command) {
        return """
                
                Справочник команд:
                exit - завершение работы
                
                Примеры команд для работы с посылкой:
                Создание
                create --name "Квадратное колесо" --form "xxx\\nx x\\nxxx" --symbol "o"
                Поиск и получение информации
                find --name "Квадратное колесо"
                Редактирование
                edit --id "Квадратное колесо" --name "КУБ" --form "xxx\\nxxx\\nxxx" --symbol "%"
                Удаление
                delete --name "Посылка Тип 4"
                
                Примеры команд для погрузки в машины:
                Загрузка посылок по имени из параметра -parcels-text в машины с размерами параметра -trucks
                load --user "Petrov" --date "11.01.2025" --parcels-text "Посылка Тип 1\\nПосылка Тип 4\\nКУБ" --trucks "3x3\\n3x3\\n6x2" --type "one2one"
                Аналогично с сохранением результатов в файл
                load --user "Petrov" --date "11.01.2025" --parcels-text "Посылка Тип 1\\nПосылка Тип 4\\nКУБ" --trucks "3x3\\n3x3\\n6x2" --type "one2one" --out-filename "trucks.json"
                Аналогично, только имена посылок берем из файла указанного в параметре -parcels-file
                load --user "Petrov" --date "11.01.2025" --parcels-file "parcels.csv" --trucks "3x3\\n3x3\\n6x2" --type "one2one"
                load --user "Petrov" --date "11.01.2025" --parcels-file "parcels.csv" --trucks "3x3\\n3x3\\n6x2" --type "one2one" --out-filename "trucks.json"
                
                Примеры команд для разгрузки машин:
                Загрузка данных по машинам из файла переданным в параметре -in-filename и выгрузка результатов
                unload --user "Petrov" --date "11.01.2025" --in-filename "trucks.json"
                Аналогично результат выгружаем в файл указанный в параметре -out-filename
                unload --user "Petrov" --date "11.01.2025" --in-filename "trucks.json" --out-filename "parcels.csv"
                Аналогично, но ещё добавляем колонку с кол-вом
                unload --user "Petrov" --date "11.01.2025" --in-filename "trucks.json" --out-filename "parcels-with-count.csv" --withcount "true"
                
                Алгоритмы погрузки определяются параметром -type:
                one2one - простой (одна посылка = одна машина)
                max - сложный (максимальное размещение нескольких посылок по машинам)
                uniform - равномерная погрузка по машинам
                
                import --import-filename "boxes.txt"
                import --import-filename "trucks.json"
                
                billing --user "Petrov" --period-from "11.01.2025" --period-to "12.01.2025"
                """;
    }
}
