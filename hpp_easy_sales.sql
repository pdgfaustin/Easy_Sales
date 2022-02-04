-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 04 Février 2022 à 14:46
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `hpp_easy_sales`
--

-- --------------------------------------------------------

--
-- Structure de la table `appro`
--

CREATE TABLE IF NOT EXISTS `appro` (
  `idAppro` smallint(6) NOT NULL AUTO_INCREMENT,
  `idType` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idArticles` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idSite` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qteAppro` decimal(6,0) DEFAULT NULL,
  `jrAppro` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `semaineApp` int(2) NOT NULL,
  `qlteArticle` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `dateAppro` date DEFAULT NULL,
  `users` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idAppro`),
  KEY `idType` (`idType`),
  KEY `idArticles` (`idArticles`),
  KEY `idSite` (`idSite`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=48 ;

--
-- Contenu de la table `appro`
--

INSERT INTO `appro` (`idAppro`, `idType`, `idArticles`, `idSite`, `qteAppro`, `jrAppro`, `semaineApp`, `qlteArticle`, `dateAppro`, `users`) VALUES
(29, 'PRODUCTION', 'CLO', 'HPP LEMBA', '1200', 'Mardi', 5, 'SQB', '2022-02-01', 'HNKONGOLO'),
(30, 'PRODUCTION', 'CLO', 'HPP LEMBA', '18000', 'Mardi', 5, 'TM/LM', '2022-02-01', 'HNKONGOLO'),
(31, 'PRODUCTION', 'CHA', 'HPP LEMBA', '27000', 'Mardi', 5, 'TM/LM', '2022-02-01', 'HNKONGOLO'),
(32, 'PRODUCTION', 'CHA', 'HPP LEMBA', '800', 'Mardi', 5, 'SQB', '2022-02-01', 'HNKONGOLO'),
(33, 'PRODUCTION', 'BAG', 'HPP LEMBA', '10', 'Mardi', 5, 'SQB', '2022-02-01', 'HNKONGOLO'),
(34, 'PRODUCTION', 'BAG', 'HPP LEMBA', '8000', 'Mardi', 5, 'TM/LM', '2022-02-01', 'HNKONGOLO'),
(35, 'PRODUCTION', 'MAT', 'HPP LEMBA', '2256', 'Mardi', 5, 'TM/LM', '2022-02-01', 'HNKONGOLO'),
(36, 'PRODUCTION', 'MAT', 'HPP LEMBA', '188', 'Mardi', 5, 'SQB', '2022-02-01', 'HNKONGOLO'),
(37, 'PRODUCTION', 'TNS', 'HPP LEMBA', '2232', 'Mardi', 5, 'SQB', '2022-02-01', 'HNKONGOLO'),
(38, 'PRODUCTION', 'TNS', 'HPP LEMBA', '2560', 'Mardi', 5, 'TM/LM', '2022-02-01', 'HNKONGOLO'),
(39, 'PRODUCTION', 'SHOE', 'HPP LEMBA', '10', 'Mardi', 5, 'SQB', '2022-02-01', 'HNKONGOLO'),
(40, 'PRODUCTION', 'SHOE', 'HPP LEMBA', '2590', 'Mardi', 5, 'TM/LM', '2022-02-01', 'HNKONGOLO'),
(41, 'TOP UP', 'CLO', 'HPP LEMBA', '1500', 'Mercredi', 5, 'SQB', '2022-02-02', 'MR.ERIC'),
(42, 'TOP UP', 'CHA', 'HPP LEMBA', '100', 'Mercredi', 5, 'SQB', '2022-02-02', 'MR.ERIC'),
(43, 'TOP UP', 'BAG', 'HPP LEMBA', '260', 'Mercredi', 5, 'SQB', '2022-02-02', 'MR.ERIC'),
(44, 'TOP UP', 'MAT', 'HPP LEMBA', '200', 'Mercredi', 5, 'TM/LM', '2022-02-02', 'MR.ERIC'),
(45, 'TOP UP', 'TNS', 'HPP LEMBA', '250', 'Mercredi', 5, 'TM/LM', '2022-02-02', 'MR.ERIC'),
(46, 'TOP UP', 'SHOE', 'HPP LEMBA', '20', 'Mercredi', 5, 'TM/LM', '2022-02-02', 'MR.ERIC'),
(47, 'TOP UP', 'SHOE', 'HPP LEMBA', '600', 'Mercredi', 5, 'TM/LM', '2022-02-02', 'MR.ERIC');

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE IF NOT EXISTS `articles` (
  `idArticles` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `desiArticle` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qteStock` decimal(6,0) DEFAULT '0',
  `prixUnitaire` decimal(6,0) DEFAULT '0',
  `prixVidageStock` decimal(6,0) DEFAULT '0',
  PRIMARY KEY (`idArticles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `articles`
--

INSERT INTO `articles` (`idArticles`, `desiArticle`, `qteStock`, `prixUnitaire`, `prixVidageStock`) VALUES
('BAG', 'Bags', '0', '0', '0'),
('CHA', 'Children', '0', '0', '0'),
('CLO', 'Clothes', '0', '0', '0'),
('MAT', 'Materials', '0', '0', '0'),
('SHOE', 'Chaussures', '0', '0', '0'),
('TNS', 'Tinies/Belts etc.', '0', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `articlesite`
--

CREATE TABLE IF NOT EXISTS `articlesite` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `idArticles` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `desiArticle` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qteStock` decimal(6,0) DEFAULT '0',
  `idSite` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pteauVente` decimal(6,0) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Contenu de la table `articlesite`
--

INSERT INTO `articlesite` (`id`, `idArticles`, `desiArticle`, `qteStock`, `idSite`, `pteauVente`) VALUES
(1, 'CLO', 'Clothes', '19200', 'HPP LEMBA', '1491'),
(2, 'CHA', 'Children', '27800', 'HPP LEMBA', '94'),
(3, 'BAG', 'Bags', '8010', 'HPP LEMBA', '250'),
(4, 'MAT', 'Materials', '2444', 'HPP LEMBA', '196'),
(5, 'TNS', 'Tinies / Belts etc.', '4792', 'HPP LEMBA', '243'),
(6, 'SHOE', 'Chaussures', '2600', 'HPP LEMBA', '616');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `idclie` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `nomClient` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idclie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`idclie`, `nomClient`) VALUES
('00243', ''),
('00243811869726', 'Faustin'),
('00243840610172', 'Flory');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `les_appros`
--
CREATE TABLE IF NOT EXISTS `les_appros` (
`idType` varchar(15)
,`idArticles` varchar(15)
,`idSIte` varchar(15)
,`qteAppro` decimal(6,0)
,`semaineApp` int(2)
,`qlteArticle` varchar(5)
,`jrAppro` varchar(10)
,`dateAppro` date
,`users` varchar(15)
,`desiArticle` varchar(100)
,`qteStock` decimal(6,0)
,`pteauVente` decimal(6,0)
,`libSite` varchar(100)
,`adresse` text
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `les_ventes`
--
CREATE TABLE IF NOT EXISTS `les_ventes` (
`idclie` varchar(15)
,`idArticles` varchar(15)
,`idSite` varchar(15)
,`idTVentes` varchar(15)
,`NumFact` varchar(15)
,`qteVente` decimal(6,0)
,`prixVente` decimal(6,0)
,`jrVente` varchar(10)
,`dateVente` date
,`users` varchar(15)
,`semaineVente` int(2)
,`desiArticle` varchar(100)
,`nomClient` varchar(30)
,`libsite` varchar(100)
,`adresse` text
);
-- --------------------------------------------------------

--
-- Structure de la table `parametreventes`
--

CREATE TABLE IF NOT EXISTS `parametreventes` (
  `idParam` smallint(6) NOT NULL AUTO_INCREMENT,
  `nomParam` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateOuverture` date DEFAULT NULL,
  `dateFermeture` date DEFAULT NULL,
  `etatParam` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `users` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idParam`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Contenu de la table `parametreventes`
--

INSERT INTO `parametreventes` (`idParam`, `nomParam`, `dateOuverture`, `dateFermeture`, `etatParam`, `users`) VALUES
(1, 'NORMAL', '2022-01-26', '2022-01-31', 'B', 'FF.PADINGANYI'),
(2, 'SOLDE', '2022-01-31', '2022-02-04', 'B', 'HNKONGOLO'),
(3, 'NORMAL', '2022-02-04', NULL, 'A', 'MR.ERIC');

-- --------------------------------------------------------

--
-- Structure de la table `sites`
--

CREATE TABLE IF NOT EXISTS `sites` (
  `idSite` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `libSite` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`idSite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `sites`
--

INSERT INTO `sites` (`idSite`, `libSite`, `adresse`) VALUES
('HPP KINTAMBO', 'BOUTISUE HPP CONGO DE KINTANBO', 'KINTAMBO'),
('HPP LEMBA', 'BOUTIQUE HPP CONGO DE LEMBA', 'LEMBA');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `totalventejournalier`
--
CREATE TABLE IF NOT EXISTS `totalventejournalier` (
`idArticles` varchar(15)
,`SommeQTEVente` decimal(28,0)
,`prixTotal` decimal(34,0)
,`dateVente` date
,`jrVente` varchar(10)
,`idSite` varchar(15)
,`semaineVente` int(2)
);
-- --------------------------------------------------------

--
-- Structure de la table `travailusers`
--

CREATE TABLE IF NOT EXISTS `travailusers` (
  `idTravail` smallint(6) NOT NULL AUTO_INCREMENT,
  `idUsers` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idSite` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `actif` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idTravail`),
  KEY `idUsers` (`idUsers`),
  KEY `idSite` (`idSite`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=12 ;

--
-- Contenu de la table `travailusers`
--

INSERT INTO `travailusers` (`idTravail`, `idUsers`, `idSite`, `dateDebut`, `dateFin`, `actif`) VALUES
(6, 'FF.PADINGANYI', 'HPP KINTAMBO', '2022-01-17', '2022-02-01', 'B'),
(7, 'FOLOLO2100', 'HPP KINTAMBO', '2022-01-24', '2022-02-01', 'B'),
(8, 'HNKONGOLO', 'HPP LEMBA', '2022-01-24', NULL, 'A'),
(9, 'MR.ERIC', 'HPP LEMBA', '2022-01-31', NULL, 'A'),
(10, 'FF.PADINGANYI', 'HPP LEMBA', '2022-02-01', NULL, 'A'),
(11, 'FOLOLO2100', 'HPP KINTAMBO', '2022-02-01', NULL, 'A');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `travailusersview`
--
CREATE TABLE IF NOT EXISTS `travailusersview` (
`idUsers` varchar(15)
,`idSIte` varchar(15)
,`dateDebut` date
,`dateFin` date
,`actif` varchar(1)
,`nomComplet` varchar(30)
,`statuts` varchar(15)
,`contact` text
,`pwd` varchar(100)
,`libSite` varchar(100)
,`adresse` text
);
-- --------------------------------------------------------

--
-- Structure de la table `typeappro`
--

CREATE TABLE IF NOT EXISTS `typeappro` (
  `idType` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `typeappro`
--

INSERT INTO `typeappro` (`idType`) VALUES
('IN STAND'),
('PRODUCTION'),
('TOP UP');

-- --------------------------------------------------------

--
-- Structure de la table `typeventes`
--

CREATE TABLE IF NOT EXISTS `typeventes` (
  `idTVentes` varchar(15) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`idTVentes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `typeventes`
--

INSERT INTO `typeventes` (`idTVentes`) VALUES
('NORMAL'),
('SOLDE');

-- --------------------------------------------------------

--
-- Structure de la table `usersbd`
--

CREATE TABLE IF NOT EXISTS `usersbd` (
  `idUsers` varchar(15) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `nomComplet` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `statuts` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contact` text COLLATE utf8_unicode_ci,
  `pwd` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idUsers`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `usersbd`
--

INSERT INTO `usersbd` (`idUsers`, `nomComplet`, `statuts`, `contact`, `pwd`) VALUES
('FF.PADINGANYI', 'FAUSTIN PADINGANYI', 'Administrateur', 'OK', '[1, 2, 3]'),
('FOLOLO2100', 'FLORY PADINGANYI', 'Caissier', 'OK', '[F, F, ., P, A, D, I, N, G, A, N, Y, I]'),
('HNKONGOLO', 'HÉLÈNE NKONGOLO', 'Gérant', 'OK', '[1, 2, 3]'),
('MR.ERIC', 'TAMPWO ERIC', 'Administrateur', 'OK', '[1, 2, 3]');

-- --------------------------------------------------------

--
-- Structure de la table `ventes`
--

CREATE TABLE IF NOT EXISTS `ventes` (
  `idventes` smallint(6) NOT NULL AUTO_INCREMENT,
  `idclie` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idArticles` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idSite` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idTVentes` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `numFact` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qteVente` decimal(6,0) DEFAULT NULL,
  `prixVente` decimal(6,0) DEFAULT NULL,
  `semaineVente` int(2) NOT NULL,
  `jrVente` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateVente` date DEFAULT NULL,
  `users` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idventes`),
  KEY `idclie` (`idclie`),
  KEY `idTVentes` (`idTVentes`),
  KEY `idArticles` (`idArticles`),
  KEY `idSite` (`idSite`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=48 ;

--
-- Contenu de la table `ventes`
--

INSERT INTO `ventes` (`idventes`, `idclie`, `idArticles`, `idSite`, `idTVentes`, `numFact`, `qteVente`, `prixVente`, `semaineVente`, `jrVente`, `dateVente`, `users`) VALUES
(33, '00243840610172', 'SHOE', 'HPP LEMBA', 'SOLDE', 'HPP1', '1', '12000', 0, 'Mercredi', '2022-02-02', 'MR.ERIC'),
(34, '00243840610172', 'SHOE', 'HPP LEMBA', 'SOLDE', 'HPP2', '1', '12000', 0, 'Mercredi', '2022-02-02', 'MR.ERIC'),
(35, '00243840610172', 'CLO', 'HPP LEMBA', 'SOLDE', 'HPP3', '1', '15000', 0, 'Mercredi', '2022-02-02', 'MR.ERIC'),
(36, '00243811869726', 'BAG', 'HPP LEMBA', 'SOLDE', 'HPP4', '2', '1500', 5, 'Mercredi', '2022-02-02', 'MR.ERIC'),
(37, '00243811869726', 'TNS', 'HPP LEMBA', 'SOLDE', 'HPP4', '3', '2500', 5, 'Mercredi', '2022-02-02', 'MR.ERIC'),
(38, '00243', 'CLO', 'HPP LEMBA', 'NORMAL', 'HPP5', '4', '17000', 5, 'Vendredi', '2022-02-04', 'MR.ERIC'),
(39, '00243811869726', 'CLO', 'HPP LEMBA', 'NORMAL', 'HPP6', '3', '3500', 5, 'Vendredi', '2022-02-04', 'MR.ERIC'),
(40, '00243811869726', 'CHA', 'HPP LEMBA', 'NORMAL', 'HPP6', '6', '8500', 5, 'Vendredi', '2022-02-04', 'MR.ERIC'),
(41, '00243811869726', 'BAG', 'HPP LEMBA', 'NORMAL', 'HPP6', '1', '15000', 5, 'Vendredi', '2022-02-04', 'MR.ERIC'),
(42, '00243811869726', 'MAT', 'HPP LEMBA', 'NORMAL', 'HPP6', '2', '1900', 5, 'Vendredi', '2022-02-04', 'MR.ERIC'),
(43, '00243811869726', 'TNS', 'HPP LEMBA', 'NORMAL', 'HPP6', '3', '800', 5, 'Vendredi', '2022-02-04', 'MR.ERIC'),
(44, '00243811869726', 'SHOE', 'HPP LEMBA', 'NORMAL', 'HPP6', '2', '12000', 5, 'Vendredi', '2022-02-04', 'MR.ERIC'),
(45, '00243840610172', 'BAG', 'HPP LEMBA', 'NORMAL', 'HPP7', '7', '3200', 5, 'Vendredi', '2022-02-04', 'MR.ERIC'),
(46, '00243811869726', 'TNS', 'HPP LEMBA', 'NORMAL', 'HPP8', '1', '5000', 5, 'Vendredi', '2022-02-04', 'MR.ERIC'),
(47, '00243811869726', 'MAT', 'HPP LEMBA', 'NORMAL', 'HPP9', '2', '4000', 5, 'Vendredi', '2022-02-04', 'MR.ERIC');

-- --------------------------------------------------------

--
-- Structure de la vue `les_appros`
--
DROP TABLE IF EXISTS `les_appros`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `les_appros` AS select `a`.`idType` AS `idType`,`a`.`idArticles` AS `idArticles`,`a`.`idSite` AS `idSIte`,`a`.`qteAppro` AS `qteAppro`,`a`.`semaineApp` AS `semaineApp`,`a`.`qlteArticle` AS `qlteArticle`,`a`.`jrAppro` AS `jrAppro`,`a`.`dateAppro` AS `dateAppro`,`a`.`users` AS `users`,`b`.`desiArticle` AS `desiArticle`,`b`.`qteStock` AS `qteStock`,`b`.`pteauVente` AS `pteauVente`,`c`.`libSite` AS `libSite`,`c`.`adresse` AS `adresse` from (((`appro` `a` join `articlesite` `b`) join `sites` `c`) join `typeappro` `d`) where ((`a`.`idType` = `d`.`idType`) and (`a`.`idArticles` = `b`.`idArticles`) and (`a`.`idSite` = `c`.`idSite`));

-- --------------------------------------------------------

--
-- Structure de la vue `les_ventes`
--
DROP TABLE IF EXISTS `les_ventes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `les_ventes` AS select `a`.`idclie` AS `idclie`,`a`.`idArticles` AS `idArticles`,`a`.`idSite` AS `idSite`,`a`.`idTVentes` AS `idTVentes`,`a`.`numFact` AS `NumFact`,`a`.`qteVente` AS `qteVente`,`a`.`prixVente` AS `prixVente`,`a`.`jrVente` AS `jrVente`,`a`.`dateVente` AS `dateVente`,`a`.`users` AS `users`,`a`.`semaineVente` AS `semaineVente`,`b`.`desiArticle` AS `desiArticle`,`c`.`nomClient` AS `nomClient`,`e`.`libSite` AS `libsite`,`e`.`adresse` AS `adresse` from ((((`ventes` `a` join `articlesite` `b`) join `clients` `c`) join `typeventes` `d`) join `sites` `e`) where ((`a`.`idclie` = `c`.`idclie`) and (`a`.`idArticles` = `b`.`idArticles`) and (`a`.`idSite` = `e`.`idSite`) and (`a`.`idTVentes` = `d`.`idTVentes`));

-- --------------------------------------------------------

--
-- Structure de la vue `totalventejournalier`
--
DROP TABLE IF EXISTS `totalventejournalier`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `totalventejournalier` AS select `a`.`idArticles` AS `idArticles`,sum(`a`.`qteVente`) AS `SommeQTEVente`,sum((`a`.`qteVente` * `a`.`prixVente`)) AS `prixTotal`,`a`.`dateVente` AS `dateVente`,`a`.`jrVente` AS `jrVente`,`a`.`idSite` AS `idSite`,`a`.`semaineVente` AS `semaineVente` from ((((`ventes` `a` join `articles` `b`) join `clients` `c`) join `sites` `d`) join `typeventes` `e`) where ((`a`.`idclie` = `c`.`idclie`) and (`a`.`idArticles` = `b`.`idArticles`) and (`a`.`idSite` = `d`.`idSite`) and (`a`.`idTVentes` = `e`.`idTVentes`)) group by `a`.`dateVente`,`a`.`jrVente`,`a`.`semaineVente`,`a`.`idSite`,`a`.`idArticles`;

-- --------------------------------------------------------

--
-- Structure de la vue `travailusersview`
--
DROP TABLE IF EXISTS `travailusersview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `travailusersview` AS select `a`.`idUsers` AS `idUsers`,`a`.`idSite` AS `idSIte`,`a`.`dateDebut` AS `dateDebut`,`a`.`dateFin` AS `dateFin`,`a`.`actif` AS `actif`,`b`.`nomComplet` AS `nomComplet`,`b`.`statuts` AS `statuts`,`b`.`contact` AS `contact`,`b`.`pwd` AS `pwd`,`c`.`libSite` AS `libSite`,`c`.`adresse` AS `adresse` from ((`travailusers` `a` join `usersbd` `b`) join `sites` `c`) where ((`a`.`idUsers` = `b`.`idUsers`) and (`a`.`idSite` = `c`.`idSite`));

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `appro`
--
ALTER TABLE `appro`
  ADD CONSTRAINT `appro_ibfk_1` FOREIGN KEY (`idType`) REFERENCES `typeappro` (`idType`),
  ADD CONSTRAINT `appro_ibfk_2` FOREIGN KEY (`idArticles`) REFERENCES `articles` (`idArticles`),
  ADD CONSTRAINT `appro_ibfk_3` FOREIGN KEY (`idSite`) REFERENCES `sites` (`idSite`);

--
-- Contraintes pour la table `travailusers`
--
ALTER TABLE `travailusers`
  ADD CONSTRAINT `travailusers_ibfk_1` FOREIGN KEY (`idUsers`) REFERENCES `usersbd` (`idUsers`),
  ADD CONSTRAINT `travailusers_ibfk_2` FOREIGN KEY (`idSite`) REFERENCES `sites` (`idSite`);

--
-- Contraintes pour la table `ventes`
--
ALTER TABLE `ventes`
  ADD CONSTRAINT `ventes_ibfk_1` FOREIGN KEY (`idclie`) REFERENCES `clients` (`idclie`),
  ADD CONSTRAINT `ventes_ibfk_2` FOREIGN KEY (`idTVentes`) REFERENCES `typeventes` (`idTVentes`),
  ADD CONSTRAINT `ventes_ibfk_3` FOREIGN KEY (`idArticles`) REFERENCES `articles` (`idArticles`),
  ADD CONSTRAINT `ventes_ibfk_4` FOREIGN KEY (`idSite`) REFERENCES `sites` (`idSite`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
