#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

#------------------------------------------------------------
# Table: product_menu
#------------------------------------------------------------

CREATE TABLE product_menu(
        idMenu    Int NOT NULL ,
        idProduct Int NOT NULL ,
        quantity  Int NOT NULL
	,CONSTRAINT product_menu_PK PRIMARY KEY (idMenu,idProduct)

	,CONSTRAINT product_menu_menu_FK FOREIGN KEY (idMenu) REFERENCES menu(idMenu)
	,CONSTRAINT product_menu_product0_FK FOREIGN KEY (idProduct) REFERENCES product(idProduct)
)ENGINE=InnoDB;