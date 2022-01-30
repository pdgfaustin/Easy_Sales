-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 26 Janvier 2022 à 16:38
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
  `dateAppro` date DEFAULT NULL,
  `users` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idAppro`),
  KEY `idType` (`idType`),
  KEY `idArticles` (`idArticles`),
  KEY `idSite` (`idSite`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Contenu de la table `appro`
--

INSERT INTO `appro` (`idAppro`, `idType`, `idArticles`, `idSite`, `qteAppro`, `jrAppro`, `dateAppro`, `users`) VALUES
(1, 'INJECTION', 'BAG', 'HPP KINTAMBO', '7000', 'Mercredi', '2022-01-26', 'FF.PADINGANYI'),
(2, 'INJECTION', 'CHI', 'HPP KINTAMBO', '560', 'Mercredi', '2022-01-26', 'FF.PADINGANYI'),
(3, 'INJECTION', 'CLO', 'HPP KINTAMBO', '17000', 'Mercredi', '2022-01-26', 'FF.PADINGANYI'),
(4, 'INJECTION', 'MAT', 'HPP KINTAMBO', '800', 'Mercredi', '2022-01-26', 'FF.PADINGANYI'),
(5, 'INJECTION', 'TIN', 'HPP KINTAMBO', '9000', 'Mercredi', '2022-01-26', 'FF.PADINGANYI');

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
('BAG', 'Bags', '6988', '7000', '0'),
('CHI', 'Children', '560', '6500', '0'),
('CLO', 'Clothes', '16993', '7000', '0'),
('MAT', 'Materials', '799', '1700', '0'),
('TIN', 'Tinies/Belts etc', '8966', '950', '0');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `idclie` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idclie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`idclie`) VALUES
('00243'),
('00243811869726'),
('00243840610172');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `les_appro`
--
CREATE TABLE IF NOT EXISTS `les_appro` (
`idTYpe` varchar(15)
,`idArticles` varchar(15)
,`idSite` varchar(15)
,`qteAppro` decimal(6,0)
,`jrAppro` varchar(10)
,`dateAppro` date
,`users` varchar(15)
,`desiArticle` varchar(100)
,`prixUnitaire` decimal(6,0)
,`libSite` varchar(100)
,`adresse` text
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `les_ventes`
--
CREATE TABLE IF NOT EXISTS `les_ventes` (
`idClie` varchar(15)
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

CREATE TABLE IF NOT EXISTS `parametreventes` (
  `idParam` smallint(6) NOT NULL AUTO_INCREMENT,
  `nomParam` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateOuverture` date DEFAULT NULL,
  `dateFermeture` date DEFAULT NULL,
  `etatParam` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `users` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idParam`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `parametreventes`
--

INSERT INTO `parametreventes` (`idParam`, `nomParam`, `dateOuverture`, `dateFermeture`, `etatParam`, `users`) VALUES
(1, 'NORMAL', '2022-01-26', NULL, 'A', 'FF.PADINGANYI');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `rapportdetailsemaine`
--
CREATE TABLE IF NOT EXISTS `rapportdetailsemaine` (
`idArticles` varchar(15)
,`desiArticle` varchar(100)
,`jrvente` varchar(10)
,`dateVente` date
,`venteTot` decimal(34,0)
,`Les_Appro` decimal(28,0)
,`qteVenduJr` decimal(28,0)
,`nbreClient` bigint(21)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `rapportglobalsemaine`
--
CREATE TABLE IF NOT EXISTS `rapportglobalsemaine` (
`jrvente` varchar(10)
,`dateVente` date
,`venteTot` decimal(34,0)
,`Les_Appro` decimal(28,0)
,`qteVenduJr` decimal(28,0)
,`nbreClient` bigint(21)
);
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Contenu de la table `travailusers`
--

INSERT INTO `travailusers` (`idTravail`, `idUsers`, `idSite`, `dateDebut`, `dateFin`, `actif`) VALUES
(6, 'FF.PADINGANYI', 'HPP KINTAMBO', '2022-01-17', NULL, 'A'),
(7, 'FOLOLO2100', 'HPP KINTAMBO', '2022-01-24', NULL, 'A'),
(8, 'HNKONGOLO', 'HPP LEMBA', '2022-01-24', NULL, 'A');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `travailusersview`
--
CREATE TABLE IF NOT EXISTS `travailusersview` (
`idUsers` varchar(15)
,`idsite` varchar(15)
,`datedebut` date
,`datefin` date
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
('BALANCE DEBUT'),
('INJECTION');

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
('NORMAL');

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
('HNKONGOLO', 'HÉLÈNE NKONGOLO', 'Gérant', 'OK', '[1, 2, 3]');

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
  `jrVente` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateVente` date DEFAULT NULL,
  `users` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idventes`),
  KEY `idclie` (`idclie`),
  KEY `idTVentes` (`idTVentes`),
  KEY `idArticles` (`idArticles`),
  KEY `idSite` (`idSite`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=17 ;

--
-- Contenu de la table `ventes`
--

INSERT INTO `ventes` (`idventes`, `idclie`, `idArticles`, `idSite`, `idTVentes`, `numFact`, `qteVente`, `prixVente`, `jrVente`, `dateVente`, `users`) VALUES
(3, '00243', 'TIN', 'HPP KINTAMBO', 'NORMAL', 'HPP1', '17', '950', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(4, '00243', 'BAG', 'HPP KINTAMBO', 'NORMAL', 'HPP1', '3', '7000', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(5, '00243', 'CLO', 'HPP KINTAMBO', 'NORMAL', 'HPP1', '6', '7000', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(6, '00243', 'BAG', 'HPP KINTAMBO', 'NORMAL', 'HPP2', '1', '7000', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(7, '00243', 'CHI', 'HPP KINTAMBO', 'NORMAL', 'HPP2', '3', '6500', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(8, '00243', 'TIN', 'HPP KINTAMBO', 'NORMAL', 'HPP2', '11', '950', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(9, '00243840610172', 'BAG', 'HPP KINTAMBO', 'NORMAL', 'HPP3', '5', '7000', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(10, '00243840610172', 'TIN', 'HPP KINTAMBO', 'NORMAL', 'HPP3', '19', '950', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(11, '00243840610172', 'MAT', 'HPP KINTAMBO', 'NORMAL', 'HPP3', '1', '1700', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(12, '00243811869726', 'BAG', 'HPP KINTAMBO', 'NORMAL', 'HPP4', '1', '7000', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(13, '00243811869726', 'CLO', 'HPP KINTAMBO', 'NORMAL', 'HPP4', '7', '7000', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(14, '00243811869726', 'TIN', 'HPP KINTAMBO', 'NORMAL', 'HPP4', '6', '950', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(15, '00243', 'BAG', 'HPP KINTAMBO', 'NORMAL', 'HPP5', '6', '7000', 'Mercredi', '2022-01-26', 'FOLOLO2100'),
(16, '00243', 'TIN', 'HPP KINTAMBO', 'NORMAL', 'HPP5', '9', '950', 'Mercredi', '2022-01-26', 'FOLOLO2100');

-- --------------------------------------------------------

--
-- Structure de la vue `les_appro`
--
DROP TABLE IF EXISTS `les_appro`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `les_appro` AS select `a`.`idType` AS `idTYpe`,`a`.`idArticles` AS `idArticles`,`a`.`idSite` AS `idSite`,`a`.`qteAppro` AS `qteAppro`,`a`.`jrAppro` AS `jrAppro`,`a`.`dateAppro` AS `dateAppro`,`a`.`users` AS `users`,`b`.`desiArticle` AS `desiArticle`,`b`.`prixUnitaire` AS `prixUnitaire`,`d`.`libSite` AS `libSite`,`d`.`adresse` AS `adresse` from (((`appro` `a` join `articles` `b`) join `typeappro` `c`) join `sites` `d`) where ((`a`.`idType` = `c`.`idType`) and (`a`.`idArticles` = `b`.`idArticles`) and (`a`.`idSite` = `d`.`idSite`));

-- --------------------------------------------------------

--
-- Structure de la vue `les_ventes`
--
DROP TABLE IF EXISTS `les_ventes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `les_ventes` AS select `a`.`idclie` AS `idClie`,`a`.`idArticles` AS `idArticles`,`a`.`idSite` AS `idSite`,`a`.`idTVentes` AS `idTVentes`,`a`.`numFact` AS `numFact`,`a`.`qteVente` AS `qteVente`,`a`.`prixVente` AS `prixVente`,`a`.`jrVente` AS `jrVente`,`a`.`dateVente` AS `dateVente`,`a`.`users` AS `users`,`d`.`desiArticle` AS `desiArticle`,`e`.`libSite` AS `libSite`,`e`.`adresse` AS `adresse` from ((((`ventes` `a` join `clients` `b`) join `typeventes` `c`) join `articles` `d`) join `sites` `e`) where ((`a`.`idclie` = `b`.`idclie`) and (`a`.`idArticles` = `d`.`idArticles`) and (`a`.`idSite` = `e`.`idSite`) and (`a`.`idTVentes` = `c`.`idTVentes`));

-- --------------------------------------------------------

--
-- Structure de la vue `rapportdetailsemaine`
--
DROP TABLE IF EXISTS `rapportdetailsemaine`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rapportdetailsemaine` AS select `a`.`idArticles` AS `idArticles`,`b`.`desiArticle` AS `desiArticle`,`a`.`jrVente` AS `jrvente`,`a`.`dateVente` AS `dateVente`,sum((`a`.`prixVente` * `a`.`qteVente`)) AS `venteTot`,(select sum(`appro`.`qteAppro`) from `appro` where ((`appro`.`jrAppro` = `a`.`jrVente`) and (`appro`.`dateAppro` = `a`.`dateVente`) and (`appro`.`idType` = 'INJECTION'))) AS `Les_Appro`,sum(`a`.`qteVente`) AS `qteVenduJr`,count(distinct `a`.`idclie`) AS `nbreClient` from (`ventes` `a` join `articles` `b`) where (`a`.`idArticles` = `b`.`idArticles`) group by `b`.`desiArticle`;

-- --------------------------------------------------------

--
-- Structure de la vue `rapportglobalsemaine`
--
DROP TABLE IF EXISTS `rapportglobalsemaine`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rapportglobalsemaine` AS select `a`.`jrVente` AS `jrvente`,`a`.`dateVente` AS `dateVente`,sum((`a`.`prixVente` * `a`.`qteVente`)) AS `venteTot`,(select sum(`appro`.`qteAppro`) from `appro` where ((`appro`.`jrAppro` = `a`.`jrVente`) and (`appro`.`dateAppro` = `a`.`dateVente`) and (`appro`.`idType` = 'INJECTION'))) AS `Les_Appro`,sum(`a`.`qteVente`) AS `qteVenduJr`,count(distinct `a`.`idclie`) AS `nbreClient` from `ventes` `a` group by `a`.`jrVente`;

-- --------------------------------------------------------

--
-- Structure de la vue `travailusersview`
--
DROP TABLE IF EXISTS `travailusersview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `travailusersview` AS select `a`.`idUsers` AS `idUsers`,`a`.`idSite` AS `idsite`,`a`.`dateDebut` AS `datedebut`,`a`.`dateFin` AS `datefin`,`a`.`actif` AS `actif`,`c`.`nomComplet` AS `nomComplet`,`c`.`statuts` AS `statuts`,`c`.`contact` AS `contact`,`c`.`pwd` AS `pwd`,`b`.`libSite` AS `libSite`,`b`.`adresse` AS `adresse` from ((`travailusers` `a` join `sites` `b`) join `usersbd` `c`) where ((`a`.`idUsers` = `c`.`idUsers`) and (`a`.`idSite` = `b`.`idSite`));

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
