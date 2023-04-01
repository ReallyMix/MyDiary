--
-- Файл сгенерирован с помощью SQLiteStudio v3.3.3 в Вс янв 15 17:38:59 2023
--
-- Использованная кодировка текста: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Таблица: Дела
DROP TABLE IF EXISTS Дела;

CREATE TABLE Дела (
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    Name TEXT    NOT NULL
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
