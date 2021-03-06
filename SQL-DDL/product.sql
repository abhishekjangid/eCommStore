CREATE TABLE `ECOMFSD.PRODUCT` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `CATEGORY` varchar(256) NOT NULL,
  `MAKE_YEAR` int(4) NOT NULL,
  `MODEL_NUMBER` varchar(30) NOT NULL,
  `PRICE` DECIMAL(10,2) NOT NULL,
  `SELLER_ID` int(10) NOT NULL,
  `QUANTITY` int(4) NOT NULL,
  `SPECS` varchar(256) NOT NULL,
  `REMARKS` varchar(256) NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (SELLER_ID) REFERENCES ECOMFSD.USER(ID)
) AUTO_INCREMENT=1000000001;