DROP DATABASE IF EXISTS budget;
CREATE DATABASE budget;
USE budget;

/******************
Create the User table
 Created By Jonathan Beck 7/24/2024
***************/


DROP TABLE IF EXISTS User;
CREATE TABLE User(

User_ID	int	AUTO_INCREMENT	not null	unique	comment '',
User_Name	nvarchar(100)	not null	unique	comment '',
User_PW	nvarchar(100)	not null	unique	comment '',
Email	nvarchar(100)	not null	unique	comment '',

CONSTRAINT User_PK PRIMARY KEY (User_ID),

 UNIQUE (User_ID),
 UNIQUE (User_Name),
 UNIQUE (Email)

);

/******************
Create the Category table
 Created By Jonathan Beck 7/31/2024
***************/


DROP TABLE IF EXISTS Category;
CREATE TABLE Category(

Category_ID	nvarchar(100)	not null	comment '',
User_ID	int	not null	comment '',

CONSTRAINT Category_PK PRIMARY KEY (Category_ID , User_ID),

CONSTRAINT fk_Category_User0 foreign key (User_ID) references User (User_ID)
);

/******************
Create the Bank_Account table
 Created By Jonathan Beck 1/22/2025
***************/


DROP TABLE IF EXISTS Bank_Account;
CREATE TABLE Bank_Account(

Bank_Account_ID	nvarchar(100)	not null	comment '',
User_ID	int	not null	comment '',
Account_Nickname	nvarchar(100)	not null	comment '',
Balance	decimal	not null	comment '',
Balance_Date	Date	not null	comment '',

CONSTRAINT Bank_Account_PK PRIMARY KEY (Bank_Account_ID, user_ID),

CONSTRAINT fk_Bank_Account_User0 foreign key (User_ID) references User (User_ID)
);



DROP TABLE IF EXISTS Transaction;
CREATE TABLE Transaction(

Transaction_ID	int auto_increment	not null	unique	comment '',
User_ID	int	not null	comment '',
Category_ID	nvarchar(100)	not null	comment '',
Bank_Account_ID	nvarchar(100)	not null	comment '',
Post_Date	Date	not null	comment '',
Check_No	int	null	comment '',
Description	nvarchar(255)	not null	comment '',
Amount	decimal(10,2)	not null	comment '',
Type	nvarchar(20)	not null	comment '',
Status	nvarchar(20)	not null	comment '',

CONSTRAINT Transaction_PK PRIMARY KEY (Transaction_ID),


CONSTRAINT fk_Transaction_User0 foreign key (User_ID) references User (User_ID),
CONSTRAINT fk_Transaction_Categofy1 foreign key (Category_ID,User_ID) references Category (Category_ID,User_ID) on update cascade,
CONSTRAINT fk_Transaction_Bank_Account2 foreign key (Bank_Account_ID, user_id) references Bank_Account (Bank_Account_ID,user_id) on update cascade,
CONSTRAINT UC_Transaction unique (user_id,Check_No, post_Date, description,amount)
);

select * from bank_account;





select * from bank_account;

Create index idx_trans
on Transaction (
 User_ID ,
 Account_Num ,
 Amount ,
 Description ,
 Post_Date 
);


alter table transaction add index trans_amount_asc  (amount) ;
alter table transaction add index trans_date_asc (Post_Date) ;
alter table transaction add index trans_check_asc (Check_No) ;

alter table transaction add index trans_amount_desc  (amount desc) ;
alter table transaction add index trans_date_desc (Post_Date desc) ;
alter table transaction add index trans_check_desc (Check_No desc) ;

SHOW INDEXES FROM transaction;

select amount from transaction;




 
 /******************
Create the Role table
 Created By Jonathan Beck 7/26/2024
***************/

DROP TABLE IF EXISTS Role;
CREATE TABLE Role(

Role_ID	nvarchar(100)	not null	unique	comment '',

CONSTRAINT Role_PK PRIMARY KEY (Role_ID),

 UNIQUE (Role_ID)

);

/******************
Create the Uwer_Role_Line table
 Created By Jonathan Beck 7/26/2024
***************/


DROP TABLE IF EXISTS User_Role_Line;
CREATE TABLE User_Role_Line(

Role_ID	nvarchar(100)	not null	comment '',
User_ID	int	not null	comment '',

CONSTRAINT Uwer_Role_Line_PK PRIMARY KEY (Role_ID , User_ID),

CONSTRAINT fk_Uwer_Role_Line_Roel0 foreign key (Role_ID) references Role (Role_ID),
CONSTRAINT fk_Uwer_Role_Line_User1 foreign key (User_ID) references User (User_ID)
);

/******************
Create the Mortgage table
 Created By Jonathan Beck 8/6/2024
***************/


DROP TABLE IF EXISTS Mortgage;
CREATE TABLE Mortgage(

Mortgage_ID	int	AUTO_INCREMENT	not null	unique	comment '',
User_ID	int	not null	comment '',
Present_Value	decimal	not null	comment '',
Future_Value	decimal	not null	comment '',
Interest_Rate	decimal	not null	comment '',
Monthly_Payment	decimal	not null	comment '',
Extra_Payment	decimal	not null	comment '',
Remaining_Term	int	not null	comment '',

CONSTRAINT Mortgage_PK PRIMARY KEY (Mortgage_ID),
CONSTRAINT fk_Mortgage_User0 foreign key (User_ID) references User (User_ID)
);

/******************
Create the Saved_Search_Order table
 Created By Jonathan Beck 2/4/2025
***************/


DROP TABLE IF EXISTS Saved_Search_Order;
CREATE TABLE Saved_Search_Order(

Saved_Search_Order_ID	int	AUTO_INCREMENT	not null	comment '',
Owned_User	int	not null	comment '',
Nickname	nvarchar(100)	not null	comment '',
Description	nvarchar(255)	not null	comment '',
Last_Used	datetime	not null	comment '',
Last_Updated	datetime	not null	comment '',
Times_Ran	int	not null	comment '',

CONSTRAINT Saved_Search_Order_PK PRIMARY KEY (Saved_Search_Order_ID),

CONSTRAINT fk_Saved_Search_Order_User0 foreign key (Owned_User) references User (User_ID)
);
