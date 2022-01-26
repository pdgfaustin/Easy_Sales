-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 25 jan. 2022 à 11:22
-- Version du serveur : 8.0.27
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hpp_easy_sales`
--
CREATE DATABASE IF NOT EXISTS `hpp_easy_sales` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `hpp_easy_sales`;

-- --------------------------------------------------------

--
-- Structure de la table `appro`
--

DROP TABLE IF EXISTS `appro`;
CREATE TABLE IF NOT EXISTS `appro` (
  `idAppro` smallint NOT NULL AUTO_INCREMENT,
  `idType` varchar(15) DEFAULT NULL,
  `idArticles` varchar(15) DEFAULT NULL,
  `idSite` varchar(15) DEFAULT NULL,
  `qteAppro` decimal(6,0) DEFAULT NULL,
  `jrAppro` varchar(10) DEFAULT NULL,
  `dateAppro` date DEFAULT NULL,
  `users` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idAppro`),
  KEY `idType` (`idType`),
  KEY `idArticles` (`idArticles`),
  KEY `idSite` (`idSite`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `appro`
--

INSERT INTO `appro` (`idAppro`, `idType`, `idArticles`, `idSite`, `qteAppro`, `jrAppro`, `dateAppro`, `users`) VALUES
(1, 'INJECTION', 'BAG', NULL, '520', 'Mardi', '2022-01-18', NULL),
(2, 'INJECTION', 'BAG', 'HPP KINTAMBO', '50', 'Mardi', '2022-01-18', NULL),
(3, 'INJECTION', 'CHA', 'HPP KINTAMBO', '6580', 'Mardi', '2022-01-18', NULL),
(4, 'INJECTION', 'BAG', 'HPP KINTAMBO', '250', 'Mardi', '2022-01-18', NULL),
(5, 'INJECTION', 'CHA', 'HPP KINTAMBO', '650', 'Mardi', '2022-01-18', NULL),
(6, 'INJECTION', 'TNS', 'HPP KINTAMBO', '890', 'Mardi', '2022-01-18', NULL),
(7, 'INJECTION', 'BAG', 'HPP KINTAMBO', '28', 'Mardi', '2022-01-18', NULL),
(8, 'INJECTION', 'BAG', 'HPP KINTAMBO', '2', 'Mardi', '2022-01-18', NULL),
(9, 'INJECTION', 'BAG', 'HPP KINTAMBO', '3', 'Mardi', '2022-01-18', NULL),
(10, 'INJECTION', 'BAG', 'HPP KINTAMBO', '7', 'Mardi', '2022-01-18', NULL),
(11, 'INJECTION', 'MAT', 'HPP KINTAMBO', '19', 'Mardi', '2022-01-18', NULL),
(12, 'INJECTION', 'TNS', 'HPP KINTAMBO', '11', 'Mardi', '2022-01-18', NULL),
(13, 'INJECTION', 'BAG', 'HPP KINTAMBO', '11', 'Mardi', '2022-01-18', NULL),
(14, 'INJECTION', 'CHA', 'HPP KINTAMBO', '19', 'Mardi', '2022-01-18', NULL),
(15, 'INJECTION', 'TNS', 'HPP KINTAMBO', '17', 'Mardi', '2022-01-18', NULL),
(16, 'INJECTION', 'SHOES', 'HPP KINTAMBO', '77', 'Mardi', '2022-01-18', NULL),
(17, 'INJECTION', 'MAT', 'HPP KINTAMBO', '89', 'Mardi', '2022-01-18', NULL),
(18, 'INJECTION', 'CHA', 'HPP KINTAMBO', '10', 'Mardi', '2022-01-18', 'FF.PADINGANYI');

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

DROP TABLE IF EXISTS `articles`;
CREATE TABLE IF NOT EXISTS `articles` (
  `idArticles` varchar(15) NOT NULL,
  `desiArticle` varchar(100) DEFAULT NULL,
  `qteStock` decimal(6,0) DEFAULT '0',
  `prixUnitaire` decimal(6,0) DEFAULT '0',
  `prixVidageStock` decimal(6,0) DEFAULT '0',
  PRIMARY KEY (`idArticles`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `articles`
--

INSERT INTO `articles` (`idArticles`, `desiArticle`, `qteStock`, `prixUnitaire`, `prixVidageStock`) VALUES
('BAG', 'Sacs', '301', '6000', '0'),
('CHA', 'Habit Enfant', '679', '0', '0'),
('CLO', 'Habit', '0', '6000', '0'),
('MAT', 'Matériels', '108', '6000', '0'),
('SHOES', 'Chaussures', '77', '1700', '0'),
('TNS', 'Petites Choses', '918', '0', '0');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `idclie` varchar(15) NOT NULL,
  PRIMARY KEY (`idclie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`idclie`) VALUES
('00243'),
('00243840610172');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `les_appro`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `les_appro`;
CREATE TABLE IF NOT EXISTS `les_appro` (
`idAppro` smallint
,`idType` varchar(15)
,`idArticles` varchar(15)
,`idSite` varchar(15)
,`qteAppro` decimal(6,0)
,`jrAppro` varchar(10)
,`dateAppro` date
,`users` varchar(15)
,`desiArticle` varchar(100)
,`libSite` varchar(100)
,`adresse` text
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `les_ventes`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `les_ventes`;
CREATE TABLE IF NOT EXISTS `les_ventes` (
`idventes` smallint
,`idclie` varchar(15)
,`idArticles` varchar(15)
,`idSite` varchar(15)
,`idTVentes` varchar(15)
,`numFact` varchar(15)
,`qteVente` decimal(6,0)
,`prixVente` decimal(6,0)
,`jrVente` varchar(10)
,`dateVente` date
,`users` varchar(15)
,`desiArticle` varchar(100)
,`libSite` varchar(100)
,`adresse` text
);

-- --------------------------------------------------------

--
-- Structure de la table `parametreventes`
--

DROP TABLE IF EXISTS `parametreventes`;
CREATE TABLE IF NOT EXISTS `parametreventes` (
  `idParam` smallint NOT NULL AUTO_INCREMENT,
  `nomParam` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateOuverture` date DEFAULT NULL,
  `dateFermeture` date DEFAULT NULL,
  `etatParam` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `users` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idParam`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `parametreventes`
--

INSERT INTO `parametreventes` (`idParam`, `nomParam`, `dateOuverture`, `dateFermeture`, `etatParam`, `users`) VALUES
(1, 'NORMAL', '2022-01-20', '2022-01-24', 'B', 'FF.PADINGANYI'),
(2, 'HEURE DE JOIE', '2022-01-24', '2022-01-24', 'B', 'FOLOLO2100'),
(3, 'HEURE DE JOIE', '2022-01-24', '2022-01-24', 'B', 'FOLOLO2100'),
(4, 'HEURE DE JOIE', '2022-01-24', '2022-01-24', 'B', 'FOLOLO2100'),
(5, 'SOLDE', '2022-01-24', '2022-01-24', 'B', 'FOLOLO2100'),
(6, 'NORMAL', '2022-01-24', NULL, 'A', 'FOLOLO2100');

-- --------------------------------------------------------

--
-- Structure de la table `sites`
--

DROP TABLE IF EXISTS `sites`;
CREATE TABLE IF NOT EXISTS `sites` (
  `idSite` varchar(15) NOT NULL,
  `libSite` varchar(100) DEFAULT NULL,
  `adresse` text,
  PRIMARY KEY (`idSite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `sites`
--

INSERT INTO `sites` (`idSite`, `libSite`, `adresse`) VALUES
('HPP KINTAMBO', 'BOUTISUE HPP CONGO DE KINTANBO', 'KINTAMBO'),
('HPP LEMBA', 'BOUTIQUE HPP CONGO DE LEMBA', 'LEMBA');

-- --------------------------------------------------------

--
-- Structure de la table `travailusers`
--

DROP TABLE IF EXISTS `travailusers`;
CREATE TABLE IF NOT EXISTS `travailusers` (
  `idTravail` smallint NOT NULL AUTO_INCREMENT,
  `idUsers` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idSite` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `actif` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idTravail`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `travailusers`
--

INSERT INTO `travailusers` (`idTravail`, `idUsers`, `idSite`, `dateDebut`, `dateFin`, `actif`) VALUES
(6, 'FF.PADINGANYI', 'HPP KINTAMBO', '2022-01-17', NULL, 'A'),
(7, 'FOLOLO2100', 'HPP KINTAMBO', '2022-01-24', NULL, 'A'),
(8, 'HNKONGOLO', 'HPP LEMBA', '2022-01-24', NULL, 'A');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `travailusersview`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `travailusersview`;
CREATE TABLE IF NOT EXISTS `travailusersview` (
`idUsers` varchar(15)
,`idSite` varchar(15)
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

DROP TABLE IF EXISTS `typeappro`;
CREATE TABLE IF NOT EXISTS `typeappro` (
  `idType` varchar(15) NOT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `typeappro`
--

INSERT INTO `typeappro` (`idType`) VALUES
('BALANCE DEBUT'),
('INJECTION');

-- --------------------------------------------------------

--
-- Structure de la table `typeventes`
--

DROP TABLE IF EXISTS `typeventes`;
CREATE TABLE IF NOT EXISTS `typeventes` (
  `idTVentes` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idTVentes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `typeventes`
--

INSERT INTO `typeventes` (`idTVentes`) VALUES
('HEURE DE JOIE'),
('NORMAL'),
('SOLDE');

-- --------------------------------------------------------

--
-- Structure de la table `usersbd`
--

DROP TABLE IF EXISTS `usersbd`;
CREATE TABLE IF NOT EXISTS `usersbd` (
  `idUsers` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `nomComplet` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `statuts` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `contact` text COLLATE utf8_unicode_ci,
  `pwd` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idUsers`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `usersbd`
--

INSERT INTO `usersbd` (`idUsers`, `nomComplet`, `statuts`, `contact`, `pwd`) VALUES
('FF.PADINGANYI', 'FAUSTIN PADINGANYI', 'Administrateur', 'OK', '[1, 2, 3]'),
('FOLOLO2100', 'FLORY PADINGANYI', 'Caissier', 'OK', '[1, 2, 3]'),
('HNKONGOLO', 'HÉLÈNE NKONGOLO', 'Gérant', 'OK', '[1, 2, 3]');

-- --------------------------------------------------------

--
-- Structure de la table `ventes`
--

DROP TABLE IF EXISTS `ventes`;
CREATE TABLE IF NOT EXISTS `ventes` (
  `idventes` smallint NOT NULL AUTO_INCREMENT,
  `idclie` varchar(15) DEFAULT NULL,
  `idArticles` varchar(15) DEFAULT NULL,
  `idSite` varchar(15) DEFAULT NULL,
  `idTVentes` varchar(15) NOT NULL,
  `numFact` varchar(15) DEFAULT NULL,
  `qteVente` decimal(6,0) DEFAULT NULL,
  `prixVente` decimal(6,0) DEFAULT NULL,
  `jrVente` varchar(10) DEFAULT NULL,
  `dateVente` date DEFAULT NULL,
  `users` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idventes`),
  KEY `idclie` (`idclie`),
  KEY `idArticles` (`idArticles`),
  KEY `idSite` (`idSite`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ventes`
--

INSERT INTO `ventes` (`idventes`, `idclie`, `idArticles`, `idSite`, `idTVentes`, `numFact`, `qteVente`, `prixVente`, `jrVente`, `dateVente`, `users`) VALUES
(2, '00243840610172', 'BAG', 'HPP KINTAMBO', 'NORMAL', 'HPP1', '1', '6000', 'Lundi', '2022-01-24', 'FOLOLO2100'),
(3, '00243840610172', 'SHOES', 'HPP KINTAMBO', 'NORMAL', 'HPP1', '5', '1700', 'Lundi', '2022-01-24', 'FOLOLO2100'),
(4, '00243', 'BAG', 'HPP KINTAMBO', 'NORMAL', 'HPP2', '2', '6000', 'Lundi', '2022-01-24', 'FOLOLO2100');

-- --------------------------------------------------------

--
-- Structure de la vue `les_appro`
--
DROP TABLE IF EXISTS `les_appro`;

DROP VIEW IF EXISTS `les_appro`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `les_appro`  AS SELECT `a`.`idAppro` AS `idAppro`, `a`.`idType` AS `idType`, `a`.`idArticles` AS `idArticles`, `a`.`idSite` AS `idSite`, `a`.`qteAppro` AS `qteAppro`, `a`.`jrAppro` AS `jrAppro`, `a`.`dateAppro` AS `dateAppro`, `a`.`users` AS `users`, `b`.`desiArticle` AS `desiArticle`, `c`.`libSite` AS `libSite`, `c`.`adresse` AS `adresse` FROM (((`appro` `a` join `articles` `b`) join `sites` `c`) join `typeappro` `d`) WHERE ((`a`.`idType` = `d`.`idType`) AND (`a`.`idArticles` = `b`.`idArticles`) AND (`a`.`idSite` = `c`.`idSite`)) ;

-- --------------------------------------------------------

--
-- Structure de la vue `les_ventes`
--
DROP TABLE IF EXISTS `les_ventes`;

DROP VIEW IF EXISTS `les_ventes`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `les_ventes`  AS SELECT `a`.`idventes` AS `idventes`, `a`.`idclie` AS `idclie`, `a`.`idArticles` AS `idArticles`, `a`.`idSite` AS `idSite`, `a`.`idTVentes` AS `idTVentes`, `a`.`numFact` AS `numFact`, `a`.`qteVente` AS `qteVente`, `a`.`prixVente` AS `prixVente`, `a`.`jrVente` AS `jrVente`, `a`.`dateVente` AS `dateVente`, `a`.`users` AS `users`, `b`.`desiArticle` AS `desiArticle`, `c`.`libSite` AS `libSite`, `c`.`adresse` AS `adresse` FROM (((`ventes` `a` join `articles` `b`) join `sites` `c`) join `clients` `d`) WHERE ((`a`.`idclie` = `d`.`idclie`) AND (`a`.`idArticles` = `b`.`idArticles`) AND (`a`.`idSite` = `c`.`idSite`)) ;

-- --------------------------------------------------------

--
-- Structure de la vue `travailusersview`
--
DROP TABLE IF EXISTS `travailusersview`;

DROP VIEW IF EXISTS `travailusersview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `travailusersview`  AS SELECT `a`.`idUsers` AS `idUsers`, `a`.`idSite` AS `idSite`, `a`.`dateDebut` AS `dateDebut`, `a`.`dateFin` AS `dateFin`, `a`.`actif` AS `actif`, `b`.`nomComplet` AS `nomComplet`, `b`.`statuts` AS `statuts`, `b`.`contact` AS `contact`, `b`.`pwd` AS `pwd`, `c`.`libSite` AS `libSite`, `c`.`adresse` AS `adresse` FROM ((`travailusers` `a` join `usersbd` `b`) join `sites` `c`) WHERE ((`a`.`idUsers` = `b`.`idUsers`) AND (`a`.`idSite` = `c`.`idSite`)) ;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `appro`
--
ALTER TABLE `appro`
  ADD CONSTRAINT `appro_ibfk_1` FOREIGN KEY (`idType`) REFERENCES `typeappro` (`idType`),
  ADD CONSTRAINT `appro_ibfk_2` FOREIGN KEY (`idArticles`) REFERENCES `articles` (`idArticles`),
  ADD CONSTRAINT `appro_ibfk_3` FOREIGN KEY (`idSite`) REFERENCES `sites` (`idSite`);

--
-- Contraintes pour la table `ventes`
--
ALTER TABLE `ventes`
  ADD CONSTRAINT `ventes_ibfk_1` FOREIGN KEY (`idclie`) REFERENCES `clients` (`idclie`),
  ADD CONSTRAINT `ventes_ibfk_2` FOREIGN KEY (`idArticles`) REFERENCES `articles` (`idArticles`),
  ADD CONSTRAINT `ventes_ibfk_3` FOREIGN KEY (`idSite`) REFERENCES `sites` (`idSite`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
