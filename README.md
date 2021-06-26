### Проект - программа "Арендаторы".

Требования к окружению:
1. Версия Java - 11
1. База данных - PostgreSQL
1. Сборщик проекта - Maven
1. Контейнер сервлетов - Tomcat 7.0


Программа "Арендаторы" предназначена для ведения учета:
- площадей сдаваемых в аренду;
- арендаторов (юридических лиц);
- договоров аренды, действующих в настоящее время;
- платежей, поступивших в счёт оплаты аренды;
- израсходованной арендаторами электроэнергии.


Расшифровка сущностей, используемых в программе:  
***Renter*** - арендатор;  
***Contract*** - договор аренды (контракт);  
***Account*** - счёт по учёту поступивших платежей;  
***Payment*** (Account_data в БД) - поступившие платежи;  
***Place*** - помещение;  
***Meter*** - счётчик электроэнергии;  
***Reading*** (Meter_data в БД) - переданные показания счётчика электроэнергии.  
  
Сущности, с указанием параметров, отображаемых на экране пользователя приведены на схеме в файле `"Project scheme.jpg"`.  

Базовые предустановки бизнес-логики программы:
- для аренды любого помещения может быть заключен только один договор аренды;
- арендатор может иметь сколько угодно договоров аренды;
- в каждом помещении имеется счётчик электроэнергии, одно помещение - один счётчик;
- по каждому счётчику отражаются все показания переданные с данного счётчика;
- для каждого договора аренды открывается счёт для учёта поступлений денежных средств за аренду, один договор - один счёт;
- по каждому счёту отражаются все платежи поступившие только по данному договору аренды;
- у арендатора поле "Имя" должно быть уникальным.

В программе реализованы все базовые CRUD-операции.

Особенности CRUD-операций:
1. **добавить/create** - производится после нажатия кнопки "Add new ...";
1. **показать/read** - производится при загрузке соответствующей страницы;
1. **изменить/update** - производится после нажатия кнопки "Update", в той строке с данными, которые будут изменены;
1. **удалить/delete** - производится после нажатия кнопки "Delete", в той строке с данными, которые будут удалены.

Структура БД, с указанием связей приведена на схеме в файле `"Database scheme (with links).jpg"`.

В БД реализованы *bi-directional* связи: ***One-To-One*** и ***One-To-Many***.
В связи с чем, имеются следующие особенности бизнес-логики при выполнении CRUD-операций:
- добавить сущность, которая находится под отношением One-To-One возможно только при наличии свободной сущности "ответной" стороны (например, если нет свободных помещений, то новый контракт добавить не получится);
- удаление арендатора вызовет удаление всех имеющихся у арендатора контрактов, что в свою очередь повлечёт за собой удаление всех открытых по этим контрактам счетов и удаление всех данных о поступивших платежах по этим счетам;
- удаление контракта, вызовет удаление открытого по этому контракту счета и удаление всех данных о поступивших платежах по этому счету;
- удаление счета вызовет удаление всех данных о поступивших платежах по этому счету;
- удаление помещения вызовет удаление контракта заключенного на это помещение и т.д. (см. выше - удаление контракта), а также вызовет удаление счётчика электроэнергии и удаление всех показаний переданных этим счётчиком;
- удаление счётчика электроэнергии вызовет удаление всех показаний переданных этим счетчиком.


Подготовка БД для работы с программой(*):
- создать через БД с именем "my_db";
- создать таблицы в БД:
	- в БД "my_db" перейти во вкладку "Query editor";
	- скопировать содержимое файла "create_table_not_null_IDENTITY.sql";
	- вставить содержимое файла в "Query editor" и выполнить запрос;
- заполнить содержимое таблиц начальными данными:
	- в БД "my_db" перейти во вкладку "Query editor";
	- скопировать содержимое файла "insert_table.sql";
	- вставить содержимое файла в "Query editor" и выполнить запрос;
- в случае возникновения необходимости, скрипты для удаления созданной БД находятся в файле "delete_db.sql".

Примечание:  
(*) - для выполнения данных операций использовался PgAdmin.


Как собрать и запустить приложение:
1. открыть приложение "Командная строка" в Windows;
1. перейти в корневую директорию с проектом;
1. выполнить команду "mvn package -Dmaven.test.skip";
1. из папки target файл ROOT.war скопировать в папку webapps (у tomcat);
1. запускаем tomcat (в папке bin дважды кликаем на файл startup.bat);
1. в браузере ввести адрес: http://localhost:8080/;
1. загрузится главная страница приложения, приложение готово к работе.
