-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 23 Lip 2017, 22:20
-- Wersja serwera: 10.1.19-MariaDB
-- Wersja PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `libary`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book`
--

CREATE TABLE `book` (
  `bookid` bigint(20) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year` bigint(20) NOT NULL,
  `ownerid` varchar(255) DEFAULT NULL,
  `current_borrow_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `book`
--

INSERT INTO `book` (`bookid`, `author`, `description`, `name`, `year`, `ownerid`, `current_borrow_id`) VALUES
(1, 'J.K.Rowling', 'W dniu jedenastych urodzin Harry dowiaduje si?, ?e jest czarodziejem. Czeka na niego szko?a magii pe?na tajemnic. \r\n', 'Harry Potter i Kamie? Filozoficzny ', 1997, NULL, NULL),
(2, 'Henryk Sienkiewicz', 'G?ównym w?tkiem powie?ci jest mi?o?? Winicjusza i Ligii. ', 'Quo vadis\r\n', 189, NULL, NULL),
(3, 'Adam Mickiewicz', 'L?cka to kurwa jedna', 'Lalka', 1890, NULL, NULL),
(4, 'Stanislaw Wyspianski', 'Wesele szlachcica z chlopka', 'Wesele', 1900, NULL, NULL),
(5, 'Niewiadomo', 'Stary i nowy testament.', 'Biblia', 0, NULL, NULL),
(6, 'Henryk Sienkiewicz', 'Jakis tam pierdolony romansik, dokad zmierasz', 'Quo Vadis', 1895, NULL, NULL),
(7, 'William Szekspir', 'Ojciec Hermii nie zgadza si? na jej ma??e?stwo z Lizandrem. ', 'Sen nocy letniej', 1600, NULL, NULL),
(8, 'Ernest Clyne', 'Zycie toczy sie w wirtualnej rzeczywistosci', 'Player One', 2012, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `borrow_history`
--

CREATE TABLE `borrow_history` (
  `id` bigint(20) NOT NULL,
  `borrow_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `userborrow` varchar(255) DEFAULT NULL,
  `bookborrow` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `borrow_history`
--

INSERT INTO `borrow_history` (`id`, `borrow_date`, `return_date`, `userborrow`, `bookborrow`) VALUES
(1, '2017-07-10', '2017-07-11', '1000', 1),
(2, '2017-07-11', '2017-07-12', '1001', 1),
(3, '2017-07-13', '2017-07-16', '1001', 1),
(4, '2017-07-18', NULL, '1001', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `role`
--

INSERT INTO `role` (`role_id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(11, 'ahha');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `cardnumber` varchar(255) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`cardnumber`, `active`, `last_name`, `name`, `password`) VALUES
('0', 0, 'Null', 'Wype?niacz', '579234085uj34ntfmsfdsfxvx'),
('1', 1, 'admin', 'admin', 'admin'),
('1000', 1, 'Jan', 'Nowak', 'dupa'),
('1001', 1, 'Jan', 'Kowalski', 'dupa'),
('1002', 1, 'Beata', 'Nowak', 'dupa');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_role`
--

CREATE TABLE `user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
('1', 1),
('1000', 1),
('1001', 2),
('1002', 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bookid`),
  ADD KEY `FKjmjtdlb872k6e5n2spokg9g0y` (`ownerid`);

--
-- Indexes for table `borrow_history`
--
ALTER TABLE `borrow_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKskpkovxo0x7gr48ytn82flncl` (`userborrow`),
  ADD KEY `FK6po8ew7dertlr1gte0w6895jq` (`bookborrow`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`cardnumber`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `book`
--
ALTER TABLE `book`
  MODIFY `bookid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT dla tabeli `borrow_history`
--
ALTER TABLE `borrow_history`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT dla tabeli `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FKjmjtdlb872k6e5n2spokg9g0y` FOREIGN KEY (`ownerid`) REFERENCES `user` (`cardnumber`);

--
-- Ograniczenia dla tabeli `borrow_history`
--
ALTER TABLE `borrow_history`
  ADD CONSTRAINT `FK6po8ew7dertlr1gte0w6895jq` FOREIGN KEY (`bookborrow`) REFERENCES `book` (`bookid`),
  ADD CONSTRAINT `FKskpkovxo0x7gr48ytn82flncl` FOREIGN KEY (`userborrow`) REFERENCES `user` (`cardnumber`);

--
-- Ograniczenia dla tabeli `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`cardnumber`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
