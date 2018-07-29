-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Июл 23 2018 г., 17:14
-- Версия сервера: 10.1.30-MariaDB
-- Версия PHP: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `goto`
--

-- --------------------------------------------------------

--
-- Структура таблицы `chat`
--

CREATE TABLE `chat` (
  `_id` int(11) NOT NULL,
  `author` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `client` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `data` bigint(20) NOT NULL,
  `text` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `chat`
--

INSERT INTO `chat` (`_id`, `author`, `client`, `data`, `text`) VALUES
(1, 'pokroy', 'client', 1532358086838, 'ÐŸÑ€Ð¸Ð²ÐµÑ‚, Ð¢Ð¸Ð¼Ð¾Ñ„ÐµÐ¹!'),
(2, 'pokroy', 'client', 1532358117046, 'ÐšÐ°Ðº Ð¿Ñ€Ð¾Ð´Ð²ÐµÐ³Ð°ÐµÑ‚ÑÑ Ñ€Ð°Ð±Ð¾Ñ‚Ð° Ð½Ð°Ð´ ÑÐµÑ€Ð²ÐµÑ€Ð¾Ð¼?'),
(3, 'korzebin', 'client', 1532358149561, 'ÐŸÐ¾Ñ‚Ð¸Ñ…Ð¾Ð½ÑŒÐºÑƒ, Ð½Ð¾ Ð´Ð²Ð¸Ð³Ð°ÑŽÑ‚ÑÑ.'),
(4, 'korzebin', 'client', 1532358237904, 'Ð£Ð±Ñ€Ð°Ð» Ð»Ð¸ÑˆÐ½ÐµÐµ Ð¸Ð· ÐºÐ¾Ð´Ð°'),
(5, 'pokroy', 'client', 1532358266744, 'Ð˜ Ð¾Ð½Ð¾  Ð¿Ð¾ Ð¿Ñ€ÐµÐ¶Ð½ÐµÐ¼Ñƒ Ñ€Ð°Ð±Ð¾Ñ‚Ð°ÐµÑ‚?'),
(6, 'korzebin', 'client', 1532358280423, 'Ð”Ð°'),
(7, 'pokroy', 'client', 1532358311555, 'Ð­Ñ‚Ð¾ Ð²ÑÑ‘ Ñ…Ð¾Ñ€Ð¾ÑˆÐ¾, Ð½Ð¾ Ð¿Ð¾Ð¼Ð½Ð¸ Ð³Ð»Ð°Ð²Ð½Ð¾Ðµ Ð¿Ñ€Ð°Ð²Ð¸Ð»Ð¾. ÐÐµ Ñ‚Ñ€Ð¾Ð³Ð°Ð¹, Ð° Ñ‚Ð¾ ÑÐ»Ð¾Ð¼Ð°ÐµÑˆÑŒ!');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `chat`
--
ALTER TABLE `chat`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
