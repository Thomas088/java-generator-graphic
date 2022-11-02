#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
#  1 )Table: drone
#------------------------------------------------------------

CREATE TABLE drone(
        idDrone   Int  Auto_increment  NOT NULL ,
        brand     Varchar (100) NOT NULL ,
        model     Varchar (100) NOT NULL ,
        refDrone  Varchar (100) NOT NULL ,
        state     Int NOT NULL ,
        speedMax  Int NOT NULL ,
        speedUnit Varchar (10) NOT NULL ,
        isDeleted Bool NOT NULL
	,CONSTRAINT drone_PK PRIMARY KEY (idDrone)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 2 ) Table: catalogfood
#------------------------------------------------------------

CREATE TABLE catalogfood(
        idCatalogfood Int  Auto_increment  NOT NULL ,
        name          Varchar (50) NOT NULL ,
        description   Text NOT NULL
	,CONSTRAINT catalogfood_PK PRIMARY KEY (idCatalogfood)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 3 ) Table: product
#------------------------------------------------------------

CREATE TABLE product(
        idProduct         Int  Auto_increment  NOT NULL ,
        name              Varchar (50) NOT NULL ,
        price             Float NOT NULL ,
        productType       Int NOT NULL ,
        pictureUrl        Varchar (255) ,
        quantity          Float NOT NULL ,
        measureUnit       Int NOT NULL ,
        littleDescription Text ,
        quantityStock     Int NOT NULL ,
        isDeleted         Bool NOT NULL ,
        idCatalogfood     Int
	,CONSTRAINT product_PK PRIMARY KEY (idProduct)

	,CONSTRAINT product_catalogfood_FK FOREIGN KEY (idCatalogfood) REFERENCES catalogfood(idCatalogfood)
)ENGINE=InnoDB;


#------------------------------------------------------------
#  4 ) Table: address
#------------------------------------------------------------

CREATE TABLE address(
        idAddress    Int  Auto_increment  NOT NULL ,
        street       Varchar (255) NOT NULL ,
        streetNumber Int NOT NULL ,
        mailbox      Int ,
        postalCode   Int NOT NULL ,
        city         Varchar (50) NOT NULL ,
        country      Varchar (100) NOT NULL
	,CONSTRAINT address_PK PRIMARY KEY (idAddress)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 5 ) Table: society
#------------------------------------------------------------

CREATE TABLE society(
        idSociety      Int  Auto_increment  NOT NULL ,
        name           Varchar (50) NOT NULL ,
        vatNumber      Int NOT NULL ,
        dateOfCreation Date NOT NULL ,
        pictureUrl     Varchar (255) ,
        label          Varchar (50) ,
        idCatalogfood  Int NOT NULL ,
        idAddress      Int NOT NULL
	,CONSTRAINT society_PK PRIMARY KEY (idSociety)

	,CONSTRAINT society_catalogfood_FK FOREIGN KEY (idCatalogfood) REFERENCES catalogfood(idCatalogfood)
	,CONSTRAINT society_address0_FK FOREIGN KEY (idAddress) REFERENCES address(idAddress)
	,CONSTRAINT society_address_AK UNIQUE (idAddress)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 6 ) Table: orderfood
#------------------------------------------------------------

CREATE TABLE orderfood(
        idOrderfood Int  Auto_increment  NOT NULL ,
        reference   Int NOT NULL ,
        date        Date NOT NULL ,
        amount      Float NOT NULL ,
        state       Int NOT NULL ,
        shipStart   Text NOT NULL ,
        shipEnd     Text NOT NULL ,
        isDeleted   Bool NOT NULL ,
        idDrone     Int NOT NULL ,
        idAddress   Int NOT NULL
	,CONSTRAINT orderfood_PK PRIMARY KEY (idOrderfood)

	,CONSTRAINT orderfood_drone_FK FOREIGN KEY (idDrone) REFERENCES drone(idDrone)
	,CONSTRAINT orderfood_address0_FK FOREIGN KEY (idAddress) REFERENCES address(idAddress)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 7 ) Table: user
#------------------------------------------------------------

CREATE TABLE user(
        idUser    Int  Auto_increment  NOT NULL ,
        firstname Varchar (50) NOT NULL ,
        lastname  Varchar (50) NOT NULL ,
        birthdate Text NOT NULL ,
        gender    Varchar (50) NOT NULL ,
        usertype  Int NOT NULL ,
        avatarUrl Varchar (255) ,
        username  Varchar (100) NOT NULL ,
        email     Varchar (100) NOT NULL ,
        password  Varchar (100) NOT NULL ,
        authcode  Varchar (50) NOT NULL ,
        isDeleted Bool NOT NULL ,
        idSociety Int
	,CONSTRAINT user_PK PRIMARY KEY (idUser)

	,CONSTRAINT user_society_FK FOREIGN KEY (idSociety) REFERENCES society(idSociety)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 8 ) Table: menu
#------------------------------------------------------------

CREATE TABLE menu(
        idMenu            Int  Auto_increment  NOT NULL ,
        name              Varchar (100) NOT NULL ,
        price             Float NOT NULL ,
        pictureUrl        Varchar (255) ,
        littleDescription Text ,
        isDeleted         Bool NOT NULL ,
        idCatalogfood     Int NOT NULL
	,CONSTRAINT menu_PK PRIMARY KEY (idMenu)

	,CONSTRAINT menu_catalogfood_FK FOREIGN KEY (idCatalogfood) REFERENCES catalogfood(idCatalogfood)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 9 ) Table: orderfood_product
#------------------------------------------------------------

CREATE TABLE orderfood_product(
        idProduct   Int NOT NULL ,
        idOrderfood Int NOT NULL ,
        quantity    Int NOT NULL
	,CONSTRAINT orderfood_product_PK PRIMARY KEY (idProduct,idOrderfood)

	,CONSTRAINT orderfood_product_product_FK FOREIGN KEY (idProduct) REFERENCES product(idProduct)
	,CONSTRAINT orderfood_product_orderfood0_FK FOREIGN KEY (idOrderfood) REFERENCES orderfood(idOrderfood)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 10 ) Table: user_address
#------------------------------------------------------------

CREATE TABLE user_address(
        idUser    Int NOT NULL ,
        idAddress Int NOT NULL ,
        label     Varchar (100)
	,CONSTRAINT user_address_PK PRIMARY KEY (idUser,idAddress)

	,CONSTRAINT user_address_user_FK FOREIGN KEY (idUser) REFERENCES user(idUser)
	,CONSTRAINT user_address_address0_FK FOREIGN KEY (idAddress) REFERENCES address(idAddress)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 11) Table: product_menu
#------------------------------------------------------------

CREATE TABLE product_menu(
        idMenu    Int NOT NULL ,
        idProduct Int NOT NULL ,
        quantity  Int NOT NULL
	,CONSTRAINT product_menu_PK PRIMARY KEY (idMenu,idProduct)

	,CONSTRAINT product_menu_menu_FK FOREIGN KEY (idMenu) REFERENCES menu(idMenu)
	,CONSTRAINT product_menu_product0_FK FOREIGN KEY (idProduct) REFERENCES product(idProduct)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 12) Table: handle_order
#------------------------------------------------------------

CREATE TABLE handle_order(
        idOrderfood   Int NOT NULL ,
        idUser        Int NOT NULL ,
        idUserOrdered Int NOT NULL
	,CONSTRAINT handle_order_PK PRIMARY KEY (idOrderfood,idUser)

	,CONSTRAINT handle_order_orderfood_FK FOREIGN KEY (idOrderfood) REFERENCES orderfood(idOrderfood)
	,CONSTRAINT handle_order_user0_FK FOREIGN KEY (idUser) REFERENCES user(idUser)
)ENGINE=InnoDB;


#------------------------------------------------------------
# 13) Table: order_menu
#------------------------------------------------------------

CREATE TABLE order_menu(
        idMenu      Int NOT NULL ,
        idOrderfood Int NOT NULL ,
        quantity    Int NOT NULL
	,CONSTRAINT order_menu_PK PRIMARY KEY (idMenu,idOrderfood)

	,CONSTRAINT order_menu_menu_FK FOREIGN KEY (idMenu) REFERENCES menu(idMenu)
	,CONSTRAINT order_menu_orderfood0_FK FOREIGN KEY (idOrderfood) REFERENCES orderfood(idOrderfood)
)ENGINE=InnoDB;

