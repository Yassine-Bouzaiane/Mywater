-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 18 avr. 2019 à 12:42
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mywater`
--

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

DROP TABLE IF EXISTS `carte`;
CREATE TABLE IF NOT EXISTS `carte` (
  `id_user` int(11) NOT NULL,
  `id_prod` int(11) NOT NULL,
  `quantiteCart` int(11) NOT NULL,
  `etat` varchar(50) NOT NULL,
  KEY `fk_id_produit` (`id_prod`),
  KEY `fk_id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `carte`
--

INSERT INTO `carte` (`id_user`, `id_prod`, `quantiteCart`, `etat`) VALUES
(1, 1, 2, ''),
(86, 3, 666, ''),
(7, 4, 666, ''),
(7, 6, 666, 'panier'),
(7, 1, 666, 'commandee'),
(7, 5, 666, 'livree');

-- --------------------------------------------------------

--
-- Structure de la table `chantiers`
--

DROP TABLE IF EXISTS `chantiers`;
CREATE TABLE IF NOT EXISTS `chantiers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idElect` int(11) NOT NULL,
  `imageChantier` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_elect` (`idElect`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `chantiers`
--

INSERT INTO `chantiers` (`id`, `idElect`, `imageChantier`) VALUES
(1, 1, 'a.png');

-- --------------------------------------------------------

--
-- Structure de la table `electricite`
--

DROP TABLE IF EXISTS `electricite`;
CREATE TABLE IF NOT EXISTS `electricite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `imageElect` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `electricite`
--

INSERT INTO `electricite` (`id`, `titre`, `description`, `imageElect`) VALUES
(1, 'Chantier Hammamat', 'Chantier Hammamat où on a fait blablabla', 'ch_Hammamat.jpg'),
(2, 'Chantier La Soukra', 'Installation électrique pour le salon de coiffure \"Place des Dames\".', 'acceuil2.jpg'),
(3, 'Chantier Djerba', 'Installation électrique et construction d\'une piscine (9m x 4m) pour une Villa à Djerba', 'ch_Djerba.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `piscine`
--

DROP TABLE IF EXISTS `piscine`;
CREATE TABLE IF NOT EXISTS `piscine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hauteur` float NOT NULL,
  `largeur` float NOT NULL,
  `profondeur` float NOT NULL,
  `style` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `idProd` int(11) NOT NULL AUTO_INCREMENT,
  `imageProd` varchar(100) NOT NULL,
  `reference` varchar(20) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prix` float NOT NULL,
  `description` varchar(200) NOT NULL,
  `categorie` varchar(20) NOT NULL,
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`idProd`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`idProd`, `imageProd`, `reference`, `nom`, `prix`, `description`, `categorie`, `quantite`) VALUES
(1, 'osmoseur.png', '45784', 'Osmoseur Domestique', 420, 'Osmoseur domestique pour usage familiale et pour bureaux', 'eau', 500),
(2, 'sika.jpg', 'br66558', 'Sika ciment-colle', 18, 'Sika Mortier-colle amélioré, polyvalent en neuf et en rénovation, à faible émission de poussière pour pose de carreaux céramiques en couche fine.', 'electricite', 30),
(3, 'Chlore_pastille.jpg', 'CH0325', 'Chlore en Pastilles', 45, 'Chlore en pastille pour que la piscine reste claire et désinféctée pour une longue durée', 'eau', 450),
(4, 'Chlore_granule.jpg', 'CH0326', 'Chlore en Granulé', 60, 'Le chlore en granulé (autrement connue comme \"Chlore Choc\") est utiliser pour faire un traitement de choc en cas la piscine est devenu verte.', 'piscine', 300),
(5, 'epuisette-de-surface.jpg', 'CH01835', 'Epuisette de surface', 26, 'L\'épuisette de surface est utilisée pour nettoyer la surface de la piscine.', 'piscine', 13),
(6, 'tds.jpg', 'TE2662', 'TDS/pHmètre', 18, 'Testeur de taux de sels minéraux dissous dans l\'eau. Il fonctionne aussi comme un pHmétre', 'eau', 35);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `imageUser` varchar(200) DEFAULT NULL,
  `typeUser` varchar(20) DEFAULT NULL,
  `phone` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `name`, `Password`, `email`, `address`, `imageUser`, `typeUser`, `phone`) VALUES
(1, 'Super Administrateur', 'admin', 'admin@mywater.tn', '86 Avenue 13 Aoùt Choutrana 2', 'b.png', 'admin', 0),
(6, 'Waref Tounsi', 'waref', 'waref@tounsi.com', '68 Rue EL Moutanabi, Ariana, 2035', 'c.png', 'user', 0),
(7, 'Simple user', 'user', 'user@esprit.tn', '82, avenue UMA', 'a.png', 'user', 0),
(52, 'Yassine', 'yass', 'yassine@esprit.tn', '20 Rue des Mini Projets', 'a.png', 'admin', 0),
(86, 'Pain', 'pain', 'pain@pain.com', '31, avenue 13 Aoùt Choutrana 2', 'a.png', 'user', 0),
(102, 'WarefMan', 'azerty', 'test@testing.tn', 'LocalHost', 'a.png', 'user', 41672097);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `carte`
--
ALTER TABLE `carte`
  ADD CONSTRAINT `fk_id_produit` FOREIGN KEY (`id_prod`) REFERENCES `produit` (`idProd`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `chantiers`
--
ALTER TABLE `chantiers`
  ADD CONSTRAINT `fk_elect` FOREIGN KEY (`idElect`) REFERENCES `electricite` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
