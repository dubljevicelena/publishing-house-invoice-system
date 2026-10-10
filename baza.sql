/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - baza
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`baza` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `baza`;

/*Table structure for table `faktura` */

DROP TABLE IF EXISTS `faktura`;

CREATE TABLE `faktura` (
  `idFaktura` int(11) NOT NULL,
  `datumKreiranja` date NOT NULL,
  `rokIsplate` date NOT NULL,
  `ukupanIznos` bigint(20) NOT NULL CHECK (`ukupanIznos` > 0),
  `idRacunovodja` int(11) NOT NULL,
  `idKnjizara` int(11) NOT NULL,
  PRIMARY KEY (`idFaktura`),
  KEY `idRacunovodja` (`idRacunovodja`),
  KEY `idKnjizara` (`idKnjizara`),
  CONSTRAINT `faktura_ibfk_1` FOREIGN KEY (`idRacunovodja`) REFERENCES `racunovodja` (`idRacunovodja`),
  CONSTRAINT `faktura_ibfk_2` FOREIGN KEY (`idKnjizara`) REFERENCES `knjizara` (`idKnjizara`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `faktura` */

/*Table structure for table `grad` */

DROP TABLE IF EXISTS `grad`;

CREATE TABLE `grad` (
  `idGrad` int(11) NOT NULL,
  `nazivGrada` varchar(50) NOT NULL,
  PRIMARY KEY (`idGrad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `grad` */

/*Table structure for table `knjiga` */

DROP TABLE IF EXISTS `knjiga`;

CREATE TABLE `knjiga` (
  `idKnjiga` int(11) NOT NULL,
  `nazivKnjige` varchar(50) NOT NULL,
  `godinaIzdanja` bigint(20) NOT NULL CHECK (`godinaIzdanja` > 0),
  `tiraz` bigint(20) NOT NULL CHECK (`tiraz` > 0),
  `cena` double NOT NULL CHECK (`cena` > 0),
  PRIMARY KEY (`idKnjiga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `knjiga` */

/*Table structure for table `knjizara` */

DROP TABLE IF EXISTS `knjizara`;

CREATE TABLE `knjizara` (
  `idKnjizara` int(11) NOT NULL,
  `nazivKnjizare` varchar(50) NOT NULL,
  `adresaKnjizare` varchar(50) NOT NULL,
  `emailKnjizare` varchar(50) NOT NULL,
  `idGrad` int(11) NOT NULL,
  PRIMARY KEY (`idKnjizara`),
  KEY `idGrad` (`idGrad`),
  CONSTRAINT `knjizara_ibfk_1` FOREIGN KEY (`idGrad`) REFERENCES `grad` (`idGrad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `knjizara` */

/*Table structure for table `racunovodja` */

DROP TABLE IF EXISTS `racunovodja`;

CREATE TABLE `racunovodja` (
  `idRacunovodja` int(11) NOT NULL AUTO_INCREMENT,
  `imeRacunovodje` varchar(30) NOT NULL,
  `prezimeRacunovodje` varchar(30) NOT NULL,
  `korisnickoIme` varchar(50) NOT NULL,
  `lozinka` varchar(50) NOT NULL,
  `brutoPlata` double NOT NULL CHECK (`brutoPlata` > 0),
  `koeficijentRadnogStaza` double NOT NULL CHECK (`koeficijentRadnogStaza` > 0),
  `pocetnaPlata` double NOT NULL CHECK (`pocetnaPlata` > 0),
  PRIMARY KEY (`idRacunovodja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `racunovodja` */

/*Table structure for table `racunovodjasmena` */

DROP TABLE IF EXISTS `racunovodjasmena`;

CREATE TABLE `racunovodjasmena` (
  `idRacunovodja` int(11) NOT NULL,
  `idSmena` int(11) NOT NULL,
  `datumRada` date NOT NULL,
  PRIMARY KEY (`idRacunovodja`,`idSmena`,`datumRada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `racunovodjasmena` */

/*Table structure for table `smena` */

DROP TABLE IF EXISTS `smena`;

CREATE TABLE `smena` (
  `idSmena` int(11) NOT NULL,
  `brojRadnihSati` bigint(20) NOT NULL CHECK (`brojRadnihSati` > 0),
  PRIMARY KEY (`idSmena`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `smena` */

/*Table structure for table `stavkafakture` */

DROP TABLE IF EXISTS `stavkafakture`;

CREATE TABLE `stavkafakture` (
  `idFaktura` int(11) NOT NULL,
  `rbStavkeFakture` int(11) NOT NULL,
  `kolicina` bigint(20) NOT NULL CHECK (`kolicina` > 0),
  `iznosStavke` double NOT NULL CHECK (`iznosStavke` > 0),
  `cena` double NOT NULL CHECK (`cena` > 0),
  `idKnjiga` int(11) NOT NULL,
  PRIMARY KEY (`idFaktura`,`rbStavkeFakture`),
  KEY `idKnjiga` (`idKnjiga`),
  CONSTRAINT `stavkafakture_ibfk_1` FOREIGN KEY (`idKnjiga`) REFERENCES `knjiga` (`idKnjiga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkafakture` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
