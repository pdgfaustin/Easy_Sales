-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 14 Janvier 2022 à 14:02
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE IF NOT EXISTS `articles` (
  `idArticles` varchar(15) NOT NULL,
  `desiArticle` varchar(100) DEFAULT NULL,
  `qteStock` decimal(6,0) DEFAULT NULL,
  `prixUnitaire` decimal(6,0) DEFAULT NULL,
  `prixVidageStock` decimal(6,0) DEFAULT NULL,
  PRIMARY KEY (`idArticles`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `idclie` varchar(15) NOT NULL,
  PRIMARY KEY (`idclie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sites`
--

CREATE TABLE IF NOT EXISTS `sites` (
  `idSite` varchar(15) NOT NULL,
  `libSite` varchar(100) DEFAULT NULL,
  `adresse` text,
  PRIMARY KEY (`idSite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `typeappro`
--

CREATE TABLE IF NOT EXISTS `typeappro` (
  `idType` varchar(15) NOT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ventes`
--

CREATE TABLE IF NOT EXISTS `ventes` (
  `idventes` smallint(6) NOT NULL AUTO_INCREMENT,
  `idclie` varchar(15) DEFAULT NULL,
  `idArticles` varchar(15) DEFAULT NULL,
  `idSite` varchar(15) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
-- Contraintes pour la table `ventes`
--
ALTER TABLE `ventes`
  ADD CONSTRAINT `ventes_ibfk_1` FOREIGN KEY (`idclie`) REFERENCES `clients` (`idclie`),
  ADD CONSTRAINT `ventes_ibfk_2` FOREIGN KEY (`idArticles`) REFERENCES `articles` (`idArticles`),
  ADD CONSTRAINT `ventes_ibfk_3` FOREIGN KEY (`idSite`) REFERENCES `sites` (`idSite`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
