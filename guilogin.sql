-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Май 03 2020 г., 18:23
-- Версия сервера: 10.4.11-MariaDB
-- Версия PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `guilogin`
--

-- --------------------------------------------------------

--
-- Структура таблицы `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `sec_q` varchar(50) NOT NULL,
  `answer` varchar(25) NOT NULL,
  `cash` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `account`
--

INSERT INTO `account` (`id`, `login`, `password`, `sec_q`, `answer`, `cash`) VALUES
(1, 'Almas', '153759', 'Your lovely country', 'Kz', 505),
(2, 'Ardaq', '147', 'Your lovely country', 'Roblox', 100),
(3, 'Azamat', '123', 'Your lovely football team', 'Barselona', 80),
(4, 'Temp', 'qwert', 'Your lovely country', 'Kz', 0),
(5, 'root', '1', 'Your lovely country', 'Kz', 0),
(6, 'Alma', '123', 'Your lovely country', 'Kz', 0),
(7, 'temp1', '1', 'Your lovely country', 'Kz', 30),
(8, 'temp2', '2', 'Your lovely country', 'Kz', 0),
(9, '1', '1', 'Your lovely country', 'Kz', 0),
(10, 'admin', '153759456alm', 'Your lovely country', 'Qz', 999999),
(11, 'temp3', '3', 'Your lovely country', 'Kz', 0),
(12, 'Alabama', '1', 'Your lovely country', '1', 0),
(13, 'Labada', '1', 'Your lovely country', 'Kz', 0),
(14, 'Olzhas', '1', 'Your lovely country', 'Kz', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `amount` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `product`
--

INSERT INTO `product` (`id`, `name`, `amount`, `price`) VALUES
(1, 'Magichat', 2, 25),
(4, 'potionOfHealth', 10, 5),
(5, 'magicWind', 1, 30),
(7, 'MagicAmulet', 3, 60),
(8, 'EnchantedTalisman', 6, 20),
(9, 'InvisibilityCloak', 1, 500),
(10, 'potionOfLife', 19, 5);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT для таблицы `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
