**Домашнее задание 1. Консольные посылки**

На вход идет файл с посылками разного размера и формы.\
Нужно показать на схеме как их грузить в кузовы машин.\
Для простоты кузовы двухмерные, только ширина и высота.\
Может потребоваться больше одной машины для погрузки если в одну уже не влезает.

* Посылки нельзя вращать.
* Посылки не могут висеть в воздухе.
* У посылки должна быть опора больше половины её основания.
* Каждый кузов это 6x6 мест.
---
Пример невалидного размещения двух посылок:

+333   +
+1     +
++++++++
---
Предусмотреть 2 варианта алгоритмов погрузки, как самый простой вариант: одна посылка - одна машина.

**НО желательно полностью утилизировать вместимость машин\
ГПТ: Для реализации алгоритма можно пользоваться нейросетями**

---
Возможные посылки\
1\
\
22\
\
333\
\
4444\
\
55555\
\
666\
666\
\
777\
7777\
\
8888\
8888\
\
999\
999\
999

---

Пример работы программы\
Вход из файла\
999\
999\
999\
\
666\
666\
\
55555\
\
1\
\
1\
\
333

Выход на экран

\+      \+\
+333   +\
+55555 +\
+99911 +\
+999666+\
+999666+\
\++++++++

---

\+\+ Плюсиками отображается граница кузова.\
Написать консольную программу (main)\
На вход — путь до файлика\
На выход — на консоль упакованные посылки

Нефункциональные требования:
* Подробное логирование
* Написать тесты (как угодно по вашему вкусу)
* проект на гитхабе (не забудьте про гитигнор)
* Создать ветку hw1, выполнить задание, сделать пулреквест в мейн (в мейн сами не пуште, это делает ревьюер при апруве пулреквеста)

Задача со *\
Валидация входных данных

DoD Прислать в чат телеграмма и тегнуть Пашу и Борю
* ссылку на пулреквест
* скринкаст правильной работы приложении

---

**Домашнее задание 2. Новые функции, тесты**
* Результат работы погрузки можно также сохранять на диск - это погруженные машины в формате json.
  * Придумать свою структуру хранения файла с машинами в формате json.
* Обратная функция - теперь на вход может идти загруженные машины в формате .json - на выход файл со списком посылок.
* На вход функции погрузки передается количество машин, если не удаётся никак погрузить выдаётся ошибка.
* Передавать на вход имя алгоритма, по которому будет осуществляться упаковка. Реализовать ещё 2 алгоритма:
  * Равномерная погрузка по машинам
  * Максимально плотная погрузка

**Нефункциональные требования:**
* Классы с логикой должны быть тестируемы
* Написать хорошие тесты на все классы с логикой
* Придерживайтесь хорошей декомпозиции

[DoD] Прислать в чат телеграмма тегнуть Пашу, Борю и вашего нового ревьюера по ДЗ2
* ссылку на гитхаб
* скринкаст правильной работы приложении
