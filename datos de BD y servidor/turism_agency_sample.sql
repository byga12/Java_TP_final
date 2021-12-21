-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-12-2021 a las 02:19:57
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `turism_agency`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer`
--

CREATE TABLE `customer` (
  `USERID` int(11) NOT NULL,
  `ADDRESS` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `BIRTHDATE` date DEFAULT NULL,
  `DNI` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `EMAIL` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NATIONALITY` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `PHONE` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `PURCHASESQUANTITY` int(11) DEFAULT NULL,
  `SURNAME` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `customer`
--

INSERT INTO `customer` (`USERID`, `ADDRESS`, `BIRTHDATE`, `DNI`, `EMAIL`, `NAME`, `NATIONALITY`, `PHONE`, `PURCHASESQUANTITY`, `SURNAME`) VALUES
(703, 'Av Siempre viva 9081', '1999-06-17', '47163809', 'ejemplo@ejemplo.com', 'Marcos', 'Uruguaya', '49815462', 0, 'Ape');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employee`
--

CREATE TABLE `employee` (
  `USERID` int(11) NOT NULL,
  `ADDRESS` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `BIRTHDATE` date DEFAULT NULL,
  `DNI` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `EMAIL` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `JOB` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NATIONALITY` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `PHONE` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `SURNAME` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `employee`
--

INSERT INTO `employee` (`USERID`, `ADDRESS`, `BIRTHDATE`, `DNI`, `EMAIL`, `JOB`, `NAME`, `NATIONALITY`, `PASSWORD`, `PHONE`, `SALARY`, `SURNAME`, `USERNAME`) VALUES
(702, 'Av Siempre viva 897', '1992-01-20', '34768173', 'ejemplo@ejemplo.com', 'Dev', 'JorgeModificado', 'Argentina', 'password', '48197381', 78000, 'Brito', 'user');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sale`
--

CREATE TABLE `sale` (
  `SALEID` int(11) NOT NULL,
  `PAYMENTMETHOD` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `SALEDATE` date DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `tourist_package_id` int(11) DEFAULT NULL,
  `tourist_service_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `sale`
--

INSERT INTO `sale` (`SALEID`, `PAYMENTMETHOD`, `PRICE`, `SALEDATE`, `customer_id`, `employee_id`, `tourist_package_id`, `tourist_service_id`) VALUES
(753, 'Tarjeta de Credito', 9000, '2021-12-19', NULL, 702, NULL, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '800');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `service_package`
--

CREATE TABLE `service_package` (
  `serviceId` int(11) NOT NULL,
  `packageId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `service_package`
--

INSERT INTO `service_package` (`serviceId`, `packageId`) VALUES
(752, 4),
(752, 5),
(752, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `touristpackage`
--

CREATE TABLE `touristpackage` (
  `PACKAGEID` int(11) NOT NULL,
  `PACKAGEPRICE` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `touristpackage`
--

INSERT INTO `touristpackage` (`PACKAGEID`, `PACKAGEPRICE`) VALUES
(752, 103771.35);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `touristservice`
--

CREATE TABLE `touristservice` (
  `SERVICEID` int(11) NOT NULL,
  `SERVICEDATE` date DEFAULT NULL,
  `SERVICEDESCRIPTION` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `SERVICEDESTINY` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `SERVICENAME` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `SERVICEPRICE` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `touristservice`
--

INSERT INTO `touristservice` (`SERVICEID`, `SERVICEDATE`, `SERVICEDESCRIPTION`, `SERVICEDESTINY`, `SERVICENAME`, `SERVICEPRICE`) VALUES
(2, '2021-12-17', 'Servicio de alquiler de auto para 4 personas durante una semana, para utilizar en la zona norte de CABA', 'CABA (zona norte)', 'Hotel por noche/s', 9000),
(3, '2021-12-08', 'Servicio de alquiler de auto para 4 personas durante una semana, para utilizar en la zona norte de CABA', 'CABA (zona norte)', 'Alquiler de auto', 30000),
(4, '2021-12-31', 'Servicio de pasajes de colectivo ilimitado por dia (para una persona)', 'Cualquier paÃ­s de LatinoamÃ©rica', 'Pasajes de colectivo', 150),
(5, '2021-12-10', 'Servicio de pasajes de tren ilimitados por dÃ­a (para una persona)', 'Cualquier paÃ­s de LatinoamÃ©rica', 'Pasajes de tren', 200),
(6, '2021-11-30', 'Servicio de pasajes de aviÃ³n para cualquier paÃ­s de LatinoamÃ©rica (para una persona)', 'Cualquier paÃ­s de LatinoamÃ©rica', 'Pasajes de aviÃ³n', 115000),
(7, '2021-12-09', '1 excursiÃ³n gratis por dia durante la estadÃ­a (mÃ¡ximo 30 dÃ­as gratis)', 'Cualquier paÃ­s de LatinoamÃ©rica', 'Excursiones', 7000),
(8, '2021-12-25', 'Pase gratis a eventos durante la estadÃ­a (mÃ¡ximo 30 eventos)', 'Cualquier paÃ­s de LatinoamÃ©rica', 'Entradas a eventos', 15000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`USERID`);

--
-- Indices de la tabla `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`USERID`);

--
-- Indices de la tabla `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`SALEID`),
  ADD KEY `FK_SALE_employee_id` (`employee_id`),
  ADD KEY `FK_SALE_tourist_service_id` (`tourist_service_id`),
  ADD KEY `FK_SALE_tourist_package_id` (`tourist_package_id`),
  ADD KEY `FK_SALE_customer_id` (`customer_id`);

--
-- Indices de la tabla `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Indices de la tabla `service_package`
--
ALTER TABLE `service_package`
  ADD PRIMARY KEY (`serviceId`,`packageId`),
  ADD KEY `FK_service_package_packageId` (`packageId`);

--
-- Indices de la tabla `touristpackage`
--
ALTER TABLE `touristpackage`
  ADD PRIMARY KEY (`PACKAGEID`);

--
-- Indices de la tabla `touristservice`
--
ALTER TABLE `touristservice`
  ADD PRIMARY KEY (`SERVICEID`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `FK_SALE_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`USERID`),
  ADD CONSTRAINT `FK_SALE_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`USERID`),
  ADD CONSTRAINT `FK_SALE_tourist_package_id` FOREIGN KEY (`tourist_package_id`) REFERENCES `touristpackage` (`PACKAGEID`),
  ADD CONSTRAINT `FK_SALE_tourist_service_id` FOREIGN KEY (`tourist_service_id`) REFERENCES `touristservice` (`SERVICEID`);

--
-- Filtros para la tabla `service_package`
--
ALTER TABLE `service_package`
  ADD CONSTRAINT `FK_service_package_packageId` FOREIGN KEY (`packageId`) REFERENCES `touristservice` (`SERVICEID`),
  ADD CONSTRAINT `FK_service_package_serviceId` FOREIGN KEY (`serviceId`) REFERENCES `touristpackage` (`PACKAGEID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
