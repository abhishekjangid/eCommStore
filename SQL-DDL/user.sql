CREATE TABLE `ECOMFSD.USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(10) NOT NULL,
  `PASSWD` varchar(16) NOT NULL,
  `USERTYPE` varchar(1) NOT NULL,
  `CONTNUM` varchar(10) DEFAULT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `DOJ` date NOT NULL,
  PRIMARY KEY (`ID`)
) AUTO_INCREMENT=1000000001;