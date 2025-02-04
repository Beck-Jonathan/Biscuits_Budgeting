USE budget;


/******************
Create the sp_check_email_avail
 Created By Jonathan Beck3/3/2024

***************/
DROP PROCEDURE IF EXISTS sp_check_email_avail;
DELIMITER $$
CREATE PROCEDURE sp_check_email_avail(
in Email_param nvarchar(100)
)
begin 
select count(*)
 from user
 where Email=Email_Param;
 END $$
 DELIMITER ;
 
  /******************
Create the retreive by key script for the User table
 Created By Jonathan Beck 7/24/2024
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_pk_User;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_pk_User
(
User_ID_param int
)
 Begin 
 select 

User.User_ID as 'User_ID',
User.User_Name as 'User_Name',
User.User_PW as 'User_PW',
User.Email as 'Email'
 FROM User
where User_ID=User_ID_param
 ; END $$
 DELIMITER ;
 
  /******************
Create the sp_check_username_avail
 Created By Jonathan Beck3/3/2024

***************/
DROP PROCEDURE IF EXISTS sp_check_username_avail;
DELIMITER $$
CREATE PROCEDURE sp_check_username_avail(
in user_param nvarchar(100)
)
begin 
select COUNT(*)
 from user
 where User_Name=user_param;
 END $$
 DELIMITER ;
 
 
/******************
Create the retrive user id by email
 Created By Jonathan Beck3/3/2024

***************/
DROP PROCEDURE IF EXISTS sp_user_id_by_email;
DELIMITER $$
CREATE PROCEDURE sp_user_id_by_email(
in Email_param nvarchar(100)
)
begin 

 select max(user_Id)
 from user
 where Email=Email_Param;
 END $$
 DELIMITER ;
 
  
  /******************
Create the retrive user id by username
 Created By Jonathan Beck3/3/2024

***************/
DROP PROCEDURE IF EXISTS sp_user_id_by_username;
DELIMITER $$
CREATE PROCEDURE sp_user_id_by_username(
in user_name_param nvarchar(100)
)
begin 

 select user_Id
 from user
 where User_Name=user_name_param;
 END $$
 DELIMITER ;
 
  /******************
Create the insert script for the User table
 Created By Jonathan Beck 7/24/2024
***************/
DROP PROCEDURE IF EXISTS sp_insert_User;
DELIMITER $$
CREATE PROCEDURE sp_insert_User(
in User_Name_param nvarchar(100)
,in User_PW_param nvarchar(100)
,in Email_param nvarchar(100)
)
begin 
INSERT INTO  User
(User_Name,User_PW,Email)
 values 
(User_Name_param
,User_PW_param
,Email_param
)
 ; END $$
 DELIMITER ;
 
  /******************
Create the get_pw script for the user table
***************/

DROP PROCEDURE IF EXISTS sp_get_pw;
DELIMITER $$
CREATE PROCEDURE sp_get_pw(
User_Name_param nvarchar(100)

)
begin 
select User_PW 
from user

where User_Name = User_Name_param

;
 END $$
 DELIMITER ;
 
 /******************
Create the retreive by all script for the Category table
 Created By Jonathan Beck 7/24/2024
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_user_Category;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_user_Category(
in user_Id_param int
)
begin 
 SELECT 

Category.Category_ID as 'Category_Category_ID'
 FROM Category
where user_ID=user_Id_param
 ;
 END $$ 
 DELIMITER ;
 
 
 /******************
Create the insert script for the Category table
 Created By Jonathan Beck 7/24/2024
***************/
DROP PROCEDURE IF EXISTS sp_insert_Category;
DELIMITER $$
CREATE PROCEDURE sp_insert_Category(
in Category_ID_param nvarchar(100),
in user_ID_param int
)
begin 
INSERT INTO  Category
(Category_ID,user_ID)
 values 
(Category_ID_param, user_id_param
)
 ; END $$
 DELIMITER ;

 /******************
Create the check if contains script for the transaciton table
 Created By Jonathan Beck 7/24/2024
***************/
DROP PROCEDURE IF EXISTS sp_transaction_exists;
DELIMITER $$
CREATE PROCEDURE sp_transaction_exists(
in User_ID_param nvarchar(100),
in Bank_Account_ID_param nvarchar(100),
in Amount_param decimal,
in Description_param nvarchar(255),
in Post_date_param date
)
begin 
select count(*)
from  Transaction
where 
User_ID=User_ID_param and
Bank_Account_ID=Bank_Account_ID_param and
Amount=Amount_param and
Description=Description_param and
Post_Date=Post_date_param
 ; END $$
 DELIMITER ;
 
  /******************
Create the insert script for the Transaction table
 Created By Jonathan Beck 7/25/2024
***************/
DROP PROCEDURE IF EXISTS sp_insert_Transaction;
DELIMITER $$
CREATE PROCEDURE sp_insert_Transaction(

in User_ID_param int
,in Category_ID_param nvarchar(100)
,in Bank_Account_ID_param nvarchar(100)
,in Post_Date_param Date
,in Check_No_param int
,in Description_param nvarchar(255)
,in Amount_param decimal
,in Type_param nvarchar(20)
,in Status_param nvarchar(20)
)
begin 
INSERT INTO  Transaction
(User_ID,Category_ID,Bank_Account_ID,Post_Date,Check_No,Description,Amount,Type,Status)
 values 
(
User_ID_param
,Category_ID_param
,Bank_Account_ID_param
,Post_Date_param
,Check_No_param
,Description_param
,Amount_param
,Type_param
,Status_param
)
 ; END $$
 DELIMITER ;
 
 /******************
Create the retreive by user script for the Transaction table
 Created By Jonathan Beck 7/25/2024
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_user_Transaction;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_user_Transaction
(
User_ID_param int
)
 Begin 
 select 

Transaction.Transaction_ID as 'Transaction_Transaction_ID',
Transaction.User_ID as 'Transaction_User_ID',
Transaction.Category_ID as 'Transaction_Category_ID',
Transaction.Bank_Account_ID as 'Transaction_Bank_Account_ID',
Transaction.Post_Date as 'Transaction_Post_Date',
Transaction.Check_No as 'Transaction_Check_No',
Transaction.Description as 'Transaction_Description',
Transaction.Amount as 'Transaction_Amount',
Transaction.Type as 'Transaction_Type',
Transaction.Status as 'Transaction_Status',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',

User.Email as 'User_Email'
 FROM Transaction
join User on Transaction.User_ID = User.User_ID
join Category on Transaction.Category_ID = Category.Category_ID
where Transaction.User_ID=User_ID_param
 ; END $$
 DELIMITER ;
 
 /******************
Create the retreive by user script for the Uwer_Role_Line table
 Created By Jonathan Beck 7/26/2024
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_User_User_Role_Line;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_User_User_Role_Line
(
User_ID_param int
)
 Begin 
 select 

User_Role_Line.Role_ID as 'User_Role_Line_Role_ID'
from User_Role_Line
where User_ID=User_ID_param
 ; END $$
 DELIMITER ;
 
 /******************
Create the insert script for the User_Role_Line table
 Created By Jonathan Beck 7/26/2024
***************/
DROP PROCEDURE IF EXISTS sp_insert_User_Role_Line;
DELIMITER $$
CREATE PROCEDURE sp_insert_User_Role_Line(
in Role_ID_param nvarchar(100)
,in User_ID_param int
)
begin 
INSERT INTO  User_Role_Line
(Role_ID,User_ID)
 values 
(Role_ID_param
,User_ID_param
)
 ; END $$
 DELIMITER ;
 
  /******************
Create the retreive all script for the transaction table
 Created By Jonathan Beck 7/26/2024
***************/
 
 DROP PROCEDURE IF EXISTS sp_retreive_by_all_Transaction;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_all_Transaction(
limit_param int ,
 offset_param int 
)
begin 
 SELECT 

Transaction.Transaction_ID as 'Transaction_Transaction_ID',
Transaction.User_ID as 'Transaction_User_ID',
Transaction.Category_ID as 'Transaction_Category_ID',
Transaction.Bank_Account_ID as 'Transaction_Bank_Account_ID',
Transaction.Post_Date as 'Transaction_Post_Date',
Transaction.Check_No as 'Transaction_Check_No',
Transaction.Description as 'Transaction_Description',
Transaction.Amount as 'Transaction_Amount',
Transaction.Type as 'Transaction_Type',
Transaction.Status as 'Transaction_Status',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email',
Category.Category_ID as 'Category_Category_ID'
 FROM Transaction
join User on Transaction.User_ID = User.User_ID
join Category on Transaction.Category_ID = Category.Category_ID

ORDER BY Transaction_ID
limit limit_param
offset offset_param

 ;
 END $$ 
 DELIMITER ;
 
 /******************
Create the retreive by key script for the Transaction table
 Created By Jonathan Beck 1/16/2025
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_pk_Transaction;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_pk_Transaction
(
Transaction_ID_param int,
User_ID_Param int
)
 Begin 
 select 

Transaction.Transaction_ID as 'Transaction_Transaction_ID',
Transaction.User_ID as 'Transaction_User_ID',
Transaction.Category_ID as 'Transaction_Category_ID',
Transaction.Bank_Account_ID as 'Transaction_Bank_Account_ID',
Transaction.Post_Date as 'Transaction_Post_Date',
Transaction.Check_No as 'Transaction_Check_No',
Transaction.Description as 'Transaction_Description',
Transaction.Amount as 'Transaction_Amount',
Transaction.Type as 'Transaction_Type',
Transaction.Status as 'Transaction_Status',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email',
Category.Category_ID as 'Category_Category_ID',
Category.User_ID as 'Category_User_ID'
 FROM Transaction
join User on Transaction.User_ID = User.User_ID
join Category on Transaction.Category_ID = Category.Category_ID
where transaction.Transaction_ID=Transaction_ID_param
and transaction.user_ID = User_ID_param
 ; END $$
 DELIMITER ;
 
  /********
 
 A function that blah blah blah
 the input is blah, which is an integere
 it sends back a list of transctions defined as blah.
 
 *****/



drop procedure if exists sp_retreive_Transaction_by_User;
 DELIMITER $$
CREATE PROCEDURE sp_retreive_Transaction_by_User 
(
User_ID_param int,
category_param nvarchar(100),
year_param int,
limit_param int ,
 offset_param int ,
 order_param nvarchar(100),
 direction_param int
)
 Begin 
 if  (order_param="amount") then 
 
 select 

Transaction.Transaction_ID as 'Transaction_Transaction_ID',
Transaction.User_ID as 'Transaction_User_ID',
Transaction.Category_ID as 'Transaction_Category_ID',
Transaction.Bank_Account_ID as 'Transaction_Bank_Account_ID',
Transaction.Post_Date as 'Transaction_Post_Date',
Transaction.Check_No as 'Transaction_Check_No',
Transaction.Description as 'Transaction_Description',
Transaction.Amount as 'Transaction_Amount',
Transaction.Type as 'Transaction_Type',
Transaction.Status as 'Transaction_Status',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email'

 FROM Transaction
join User on Transaction.User_ID = User.User_ID

where Transaction.User_ID=User_ID_param
and (
		case
			when category_param ="" then
				1=1
			else
				category_id=category_param
			end
		)
and (
		case
			when year_param =0 then
				1=1
			else
				post_date > STR_TO_DATE(concat('01-01-',year_param), "%m-%d-%Y")
                and post_date < STR_TO_DATE(concat('01-01-',year_param+1), "%m-%d-%Y")
			end
		)



ORDER BY 

case when  direction_param=0 then Transaction_Amount   end desc,
	 case when  direction_param=1 then Transaction_Amount   end asc
        
       limit limit_param
       offset offset_param
       ;
   else
   select 

Transaction.Transaction_ID as 'Transaction_Transaction_ID',
Transaction.User_ID as 'Transaction_User_ID',
Transaction.Category_ID as 'Transaction_Category_ID',
Transaction.Bank_Account_ID as 'Transaction_Bank_Account_ID',
Transaction.Post_Date as 'Transaction_Post_Date',
Transaction.Check_No as 'Transaction_Check_No',
Transaction.Description as 'Transaction_Description',
Transaction.Amount as 'Transaction_Amount',
Transaction.Type as 'Transaction_Type',
Transaction.Status as 'Transaction_Status',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email'

 FROM Transaction
join User on Transaction.User_ID = User.User_ID

where Transaction.User_ID=User_ID_param
and (
		case
			when category_param ="" then
				1=1
			else
				category_id=category_param
			end
		)
and (
		case
			when year_param =0 then
				1=1
			else
				post_date > STR_TO_DATE(concat('01-01-',year_param), "%m-%d-%Y")
                and post_date < STR_TO_DATE(concat('01-01-',year_param+1), "%m-%d-%Y")
			end
		)


  
ORDER BY 
      
		case when order_param ="ID" and   direction_param=0 then Transaction_Transaction_ID  end desc,
		case when order_param ="date" and direction_param=0 then Transaction_Post_Date    end desc,    
         case when order_param ="check" and direction_param=0 then Transaction_Check_No   end desc,
         case when order_param ="ID" and   direction_param=1 then Transaction_Transaction_ID  end asc,
		case when order_param ="date" and direction_param=1 then Transaction_Post_Date    end asc,    
         case when order_param ="check" and direction_param=1 then Transaction_Check_No   end asc

       limit limit_param
       offset offset_param;
    end if

 ; END $$
 DELIMITER ;
 
  call sp_retreive_Transaction_by_User(1,'',0,5000,0,'check',0);
 
 
drop procedure if exists sp_retreive_Transaction_by_User_count;
 DELIMITER $$
 CREATE PROCEDURE sp_retreive_Transaction_by_User_count 
(
User_ID_param int,
category_param nvarchar(100),
year_param int
)
 Begin 
 select 

count(*)

from Transaction
join User on Transaction.User_ID = User.User_ID

where Transaction.User_ID=User_ID_param
and (
		case
			when category_param ="" then
				1=1
			else
				category_id=category_param
			end
		)
and (
		case
			when year_param =0 then
				1=1
			else
				post_date > STR_TO_DATE(concat('01-01-',year_param), "%m-%d-%Y")
                and post_date < STR_TO_DATE(concat('01-01-',year_param+1), "%m-%d-%Y")
			end
		)

 ; END $$
 DELIMITER ;
 
 
 
  DROP PROCEDURE IF EXISTS sp_update_Transaction_category;
 DELIMITER $$
CREATE PROCEDURE sp_update_Transaction_category
(oldTransaction_ID int
,oldUser_ID int

,newCategory_ID nvarchar(100)
)
begin 
UPDATE Transaction
 set 
Category_ID = newCategory_ID

WHERE Transaction_ID= oldTransaction_ID
AND User_ID= oldUser_ID



 ; end $$
 DELIMITER ;
 
 /******************
Create the update script for the Transaction table
 Created By Jonathan Beck 1/16/2025
***************/
DROP PROCEDURE IF EXISTS sp_update_Transaction;
 DELIMITER $$
CREATE PROCEDURE sp_update_Transaction
(oldTransaction_ID int
,oldUser_ID int
,newUser_ID int
,oldCategory_ID nvarchar(100)
,newCategory_ID nvarchar(100)
,oldBank_Account_ID nvarchar(100)
,newBank_Account_ID nvarchar(100)
,oldPost_Date Date
,newPost_Date Date
,oldCheck_No int
,newCheck_No int
,oldDescription nvarchar(255)
,newDescription nvarchar(255)
,oldAmount decimal
,newAmount decimal
,oldType nvarchar(20)
,newType nvarchar(20)
,oldStatus nvarchar(20)
,newStatus nvarchar(20)
)
begin 
UPDATE Transaction
 set User_ID = newUser_ID
,Category_ID = newCategory_ID
,Bank_Account_ID = newBank_Account_ID
,Post_Date = newPost_Date
,Check_No = newCheck_No
,Description = newDescription
,Amount = newAmount
,Type = newType
,Status = newStatus
WHERE Transaction_ID= oldTransaction_ID
AND User_ID= oldUser_ID
AND Category_ID= oldCategory_ID
AND Bank_Account_ID= oldBank_Account_ID
AND Post_Date= oldPost_Date
AND Check_No= oldCheck_No
AND Description= oldDescription
AND Amount= oldAmount
AND Type= oldType
AND Status= oldStatus

 ; end $$
 DELIMITER ;
 

 
 
  DROP PROCEDURE IF EXISTS sp_transaction_category_total;
 DELIMITER $$
CREATE PROCEDURE sp_transaction_category_total
(user_id_param int
,category_id_param nvarchar(100)
,sign_param nvarchar(100)
)
begin 
select 
sum(Amount)
from transaction

WHERE user_id=user_id_param
AND category_id= category_id_param

and (
		case when sign ="pos" then amount>0
			when sign_param ="neg" then amount<0
            else 1=1
			end
		)


 ; end $$
 DELIMITER ;
 
   DROP PROCEDURE IF EXISTS sp_find_transaction;
 DELIMITER $$
CREATE PROCEDURE sp_find_transaction
(user_id_param int
,category_desc_param nvarchar(100)
)
begin 
select 
Transaction.Transaction_ID as 'Transaction_Transaction_ID',
Transaction.User_ID as 'Transaction_User_ID',
Transaction.Category_ID as 'Transaction_Category_ID',
Transaction.Bank_Account_ID as 'Transaction_Bank_Account_ID',
Transaction.Post_Date as 'Transaction_Post_Date',
Transaction.Check_No as 'Transaction_Check_No',
Transaction.Description as 'Transaction_Description',
Transaction.Amount as 'Transaction_Amount',
Transaction.Type as 'Transaction_Type',
Transaction.Status as 'Transaction_Status',

User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email'

 FROM Transaction
join User on Transaction.User_ID = User.User_ID
WHERE Transaction.user_id=user_id_param
AND transaction.Description like concat('%',category_desc_param,'%')
 ; end $$
 DELIMITER ;
 

 
 
 
 DROP PROCEDURE IF EXISTS sp_assign_categories;
 DELIMITER $$
CREATE PROCEDURE sp_assign_categories
(user_id_param int
,category_desc_param nvarchar(100)
,category_search_param nvarchar(100)
)
begin 
update 

transaction
set Category_Id = category_desc_param

WHERE user_id=user_id_param
AND transaction.Description like concat('%',category_search_param,'%')



 ; end $$
 DELIMITER ;

 DROP PROCEDURE IF EXISTS sp_create_default_categories;
 DELIMITER $$
CREATE PROCEDURE sp_create_default_categories
(
user_id_param int

)
begin 
INSERT INTO Category (
Category_ID
,User_ID
)
VALUES
('Uncategorized',user_id_param),
('Food',user_id_param)
,('Gas',user_id_param)
,('Hotel',user_id_param)
,('Mortgage',user_id_param)
,('Amazon',user_id_param)
;
 end $$
 DELIMITER ;

DROP PROCEDURE IF EXISTS sp_update_Category;
 DELIMITER $$
CREATE PROCEDURE sp_update_Category
(oldCategory_ID nvarchar(100)
,newCategory_ID nvarchar(100)
,oldUser_ID int
)
begin 
UPDATE Category
 set  Category_ID=newCategory_ID
 WHERE Category_ID= oldCategory_ID
AND User_ID= oldUser_ID

 ; end $$
 DELIMITER ; 
 

 
 DROP PROCEDURE IF EXISTS sp_total_Category_by_time;
 DELIMITER $$
CREATE PROCEDURE sp_total_Category_by_time
(
user_id_param int
)
 begin
 drop temporary table if exists user_categories;
 create temporary table user_categories 
      select distinct(category_id) as 'category' from category
      where user_id = user_id_param;
      
       drop temporary table if exists user_categories2;
 create temporary table user_categories2 
      select distinct(category_id) as 'category' from category
      where user_id = user_id_param;

drop temporary table if exists user_min;
create temporary table user_min

    SELECT min( year(post_date)) as 'min' 
    FROM transaction
    where user_id = 1
;

drop temporary table if exists user_max;
create temporary table user_max

    SELECT max( year(post_date) ) as 'max'
    FROM transaction
    where user_id = 1
;

drop temporary table if exists user_years;
create temporary table user_years
WITH RECURSIVE numbers AS (
    select (SELECT * from user_min) AS number
    UNION ALL
    SELECT number + 1 FROM numbers WHERE number < (select * from user_max)+1 
)
select number from numbers;

drop temporary table if exists user_years2;
create temporary table user_years2
WITH RECURSIVE numbers AS (
    select (SELECT * from user_min) AS number
    UNION ALL
    SELECT number + 1 FROM numbers WHERE number < (select * from user_max)+1 
)
select number from numbers;

drop temporary table if exists pos_transactions;
create temporary table pos_transactions
select * from transaction where amount>0
and user_id=user_id_param;

drop temporary table if exists neg_transactions;
create temporary table neg_transactions
select * from transaction where amount<0
and user_id=user_id_param;

drop temporary table if exists cross_joiner;
create temporary table cross_joiner
select number as 'year',category from user_categories cross join user_years;

drop temporary table if exists year_break_down;
create temporary table year_break_down
select year(post_date) as 'post_year', sum(amount) as 'year_total',count(*) as 'count', category_id as 'category'
from transaction
where user_id=user_id_param
group by year(post_date) , category_id 

order by category_id;



select cross_joiner.year, cross_joiner.category, year_break_down.count,year_break_down.year_total from cross_joiner
left join year_break_down on cross_joiner.category = year_break_down.category and
cross_joiner.year = year_break_down.post_year

union

select number, 'total in', count(*), sum(amount) from user_years 
left outer join pos_transactions on number = year(post_date)

group by year(post_date)

union 
select number, 'total out', count(*), sum(amount) from user_years2 
left join neg_transactions on number = year(post_date)
/*where transaction.user_id = user_id_param*/

group by year(post_date)

union 
select '9999', category, count(*), sum(amount) from user_categories2
left join transaction on category = category_id
and transaction.user_id=user_id_param
group by category



union 
select '9999', 'total in', count(*), sum(amount) from transaction
where amount>0
and user_id=user_id_param

union 
select '9999', 'total out', count(*), sum(amount) from transaction
where amount<0
and user_id=user_id_param

order by  1 asc ,2
;

 end $$
 DELIMITER ;
 

 

 
  DROP PROCEDURE IF EXISTS sp_user_transaction_time_range;
 DELIMITER $$
CREATE PROCEDURE sp_user_transaction_time_range
(
user_id_param int
)
 begin
 select

 timestampdiff(year,min(post_date),max(post_date))
 from transaction

where
 user_id=user_id_param


  ; end $$
 DELIMITER ; 


drop procedure if exists sp_retreive_Transaction_by_User_Year_Category;
 DELIMITER $$
CREATE PROCEDURE sp_retreive_Transaction_by_User_Year_Category
(
User_ID_param int,
date_param date,
category_param nvarchar(100),
limit_param int ,
 offset_param int 
)
 Begin 
 select 

Transaction.Transaction_ID as 'Transaction_Transaction_ID',
Transaction.User_ID as 'Transaction_User_ID',
Transaction.Category_ID as 'Transaction_Category_ID',
Transaction.Bank_Account_ID as 'Transaction_Bank_Account_ID',
Transaction.Post_Date as 'Transaction_Post_Date',
Transaction.Check_No as 'Transaction_Check_No',
Transaction.Description as 'Transaction_Description',
Transaction.Amount as 'Transaction_Amount',
Transaction.Type as 'Transaction_Type',
Transaction.Status as 'Transaction_Status',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email'

 FROM Transaction
join User on Transaction.User_ID = User.User_ID

where Transaction.User_ID=User_ID_param
and Transaction.User_ID=User_ID_param
and Transaction.Category_ID=category_param
and post_date > STR_TO_DATE(concat('01-01-',year(date_param)), "%m-%d-%Y")
and post_date < STR_TO_DATE(concat('01-01-',year(date_param)+1), "%m-%d-%Y")   
ORDER BY Transaction_ID
limit limit_param
offset offset_param
 ; END $$
 DELIMITER ;
 
   DROP PROCEDURE IF EXISTS sp_transaction_for_export;
 DELIMITER $$
CREATE PROCEDURE sp_transaction_for_export
(user_id_param int

)
begin 
select 
Transaction.Transaction_ID as 'Transaction_Transaction_ID',
Transaction.User_ID as 'Transaction_User_ID',
Transaction.Category_ID as 'Transaction_Category_ID',
Transaction.Bank_Account_ID as 'Transaction_Bank_Account_ID',
Transaction.Post_Date as 'Transaction_Post_Date',
Transaction.Check_No as 'Transaction_Check_No',
Transaction.Description as 'Transaction_Description',
Transaction.Amount as 'Transaction_Amount',
Transaction.Type as 'Transaction_Type',
Transaction.Status as 'Transaction_Status'


 FROM Transaction



WHERE Transaction.user_id=user_id_param;

update transaction
set category_id = 'Uncategorized'
where category_id='Uncategorized'


 ; end $$
 DELIMITER ;
 
 

/******************
Create the delete script for the Category table
 Created By Jonathan Beck 7/31/2024
 updated 1/27/2025 to not allow deleteion of 'uncategorized'
***************/
DROP PROCEDURE IF EXISTS sp_delete_Category;
DELIMITER $$
CREATE PROCEDURE sp_delete_Category
(Category_ID_param nvarchar(100)
,User_ID_param int
)
BEGIN
case
when Category_ID_param='Uncategorized' then select 0;
 when Category_ID_param != 'Uncategorized' then
UPDATE Transaction
 set  Category_ID="Uncategorized"
 WHERE Category_ID= Category_ID_param
AND User_ID= User_ID_param
 ;
Delete from Category 
WHERE Category_ID=Category_ID_param
AND User_ID=User_ID_param ;
end case
 
 ; END $$
 DELIMITER ;

DROP PROCEDURE IF EXISTS sp_insert_Mortgage;
DELIMITER $$
CREATE PROCEDURE sp_insert_Mortgage(
in User_ID_param int
,in Present_Value_param decimal
,in Future_Value_param decimal
,in Interest_Rate_param decimal
,in Monthly_Payment_param decimal
,in Extra_Payment_param decimal
,in Remaining_Term_param int
)
begin 
INSERT INTO  Mortgage
(User_ID,Present_Value,Future_Value,Interest_Rate,Monthly_Payment,Extra_Payment,Remaining_Term)
 values 
(User_ID_param
,Present_Value_param
,Future_Value_param
,Interest_Rate_param
,Monthly_Payment_param
,Extra_Payment_param
,Remaining_Term_param
)
 ; END $$
 DELIMITER ;
 
 /******************
 
 Created By Jonathan Beck 8/6/2024
***************/
 
 DROP PROCEDURE IF EXISTS sp_retreive_Mortgage_by_User;
DELIMITER $$
CREATE PROCEDURE sp_retreive_Mortgage_by_User 
(
User_ID_param int,
limit_param int ,
 offset_param int 
)
 Begin 
 select 

Mortgage.Mortgage_ID as 'Mortgage_Mortgage_ID',
Mortgage.User_ID as 'Mortgage_User_ID',
Mortgage.Present_Value as 'Mortgage_Present_Value',
Mortgage.Future_Value as 'Mortgage_Future_Value',
Mortgage.Interest_Rate as 'Mortgage_Interest_Rate',
Mortgage.Monthly_Payment as 'Mortgage_Monthly_Payment',
Mortgage.Extra_Payment as 'Mortgage_Extra_Payment',
Mortgage.Remaining_Term as 'Mortgage_Remaining_Term',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email'
 FROM Mortgage
join User on Mortgage.User_ID = User.User_ID
where Mortgage.User_ID=User_ID_param

ORDER BY Mortgage_ID
limit limit_param
offset offset_param
 ; END $$
 DELIMITER ;

/******************
Create the retreive by key script for the Transaction table
 Created By Jonathan Beck 1/16/2025
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_pk_Transaction;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_pk_Transaction
(
Transaction_ID_param int,
User_Id_Param int
)
 Begin 
 select 

Transaction.Transaction_ID as 'Transaction_Transaction_ID',
Transaction.User_ID as 'Transaction_User_ID',
Transaction.Category_ID as 'Transaction_Category_ID',
Transaction.Bank_Account_ID as 'Transaction_Bank_Account_ID',
Transaction.Post_Date as 'Transaction_Post_Date',
Transaction.Check_No as 'Transaction_Check_No',
Transaction.Description as 'Transaction_Description',
Transaction.Amount as 'Transaction_Amount',
Transaction.Type as 'Transaction_Type',
Transaction.Status as 'Transaction_Status',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email',
Category.Category_ID as 'Category_Category_ID',
Category.User_ID as 'Category_User_ID'
 FROM Transaction
join User on Transaction.User_ID = User.User_ID
join Category on Transaction.Category_ID = Category.Category_ID
where Transaction.Transaction_ID=Transaction_ID_param
and Transaction.User_ID=User_Id_Param
 ; END $$
 DELIMITER ;

/******************
Create the update script for the Bank_Account table
 Created By Jonathan Beck 1/22/2025
***************/
DROP PROCEDURE IF EXISTS sp_update_Bank_Account;
 DELIMITER $$
CREATE PROCEDURE sp_update_Bank_Account
(oldBank_Account_ID nvarchar(100)
,oldUser_ID int
,newUser_ID int
,oldAccount_Nickname nvarchar(100)
,newAccount_Nickname nvarchar(100)
,oldBalance decimal
,newBalance decimal
,oldBalance_Date Date
,newBalance_Date Date
)
begin 
UPDATE Bank_Account
 set User_ID = newUser_ID
,Account_Nickname = newAccount_Nickname
,Balance = newBalance
,Balance_Date = newBalance_Date
WHERE Bank_Account_ID= oldBank_Account_ID
AND User_ID= oldUser_ID
AND Account_Nickname= oldAccount_Nickname
AND Balance= oldBalance
AND Balance_Date= oldBalance_Date

 ; end $$
 DELIMITER ;
 
 /******************
Create the retreive by key script for the Bank_Account table
 Created By Jonathan Beck 1/22/2025
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_pk_Bank_Account;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_pk_Bank_Account
(
Bank_Account_ID_param nvarchar(100)
)
 Begin 
 select 

Bank_Account.Bank_Account_ID as 'Bank_Account_Bank_Account_ID',
Bank_Account.User_ID as 'Bank_Account_User_ID',
Bank_Account.Account_Nickname as 'Bank_Account_Account_Nickname',
Bank_Account.Balance as 'Bank_Account_Balance',
Bank_Account.Balance_Date as 'Bank_Account_Balance_Date',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email'
 FROM Bank_Account
join User on Bank_Account.User_ID = User.User_ID
where Bank_Account_ID=Bank_Account_ID_param
 ; END $$
 DELIMITER ;
 
 /******************
 
 Created By Jonathan Beck 1/22/2025
***************/
DROP PROCEDURE IF EXISTS sp_retreive_Bank_Account_by_User;
DELIMITER $$
CREATE PROCEDURE sp_retreive_Bank_Account_by_User 
(
User_ID_param int,
limit_param int ,
 offset_param int 
)
 Begin 
 select 

Bank_Account.Bank_Account_ID as 'Bank_Account_Bank_Account_ID',
Bank_Account.User_ID as 'Bank_Account_User_ID',
Bank_Account.Account_Nickname as 'Bank_Account_Account_Nickname',
Bank_Account.Balance as 'Bank_Account_Balance',
Bank_Account.Balance_Date as 'Bank_Account_Balance_Date',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email'
 FROM Bank_Account
join User on Bank_Account.User_ID = User.User_ID
where Bank_Account.User_ID=User_ID_param

ORDER BY Bank_Account_ID
limit limit_param
offset offset_param
 ; END $$
 DELIMITER ;
 

 
 DROP PROCEDURE IF EXISTS sp_insert_Bank_Account;
DELIMITER $$
CREATE PROCEDURE sp_insert_Bank_Account(
in Bank_Account_ID_param nvarchar(100)
,in User_ID_param int
,in Account_Nickname_param nvarchar(100)
,in Balance_param decimal
,in Balance_Date_param Date
)
begin 
INSERT INTO  Bank_Account
(Bank_Account_ID,User_ID,Account_Nickname,Balance,Balance_Date)
 values 
(Bank_Account_ID_param
,User_ID_param
,Account_Nickname_param
,Balance_param
,Balance_Date_param
)
 ; END $$
 DELIMITER ;
 
 
/******************
 
 Created By Jonathan Beck 2/4/2025
***************/
DROP PROCEDURE IF EXISTS sp_retreive_Saved_Search_Order_by_User;
DELIMITER $$
CREATE PROCEDURE sp_retreive_Saved_Search_Order_by_User 
(
User_ID_param int,
limit_param int ,
 offset_param int 
)
 Begin 
 select 

Saved_Search_Order.Saved_Search_Order_ID as 'Saved_Search_Order_Saved_Search_Order_ID',
Saved_Search_Order.Owned_User as 'Saved_Search_Order_Owned_User',
Saved_Search_Order.Nickname as 'Saved_Search_Order_Nickname',
Saved_Search_Order.Description as 'Saved_Search_Order_Description',
Saved_Search_Order.Last_Used as 'Saved_Search_Order_Last_Used',
Saved_Search_Order.Last_Updated as 'Saved_Search_Order_Last_Updated',
Saved_Search_Order.Times_Ran as 'Saved_Search_Order_Times_Ran',
User.User_Name as 'User_User_Name'

 FROM Saved_Search_Order
join User on Saved_Search_Order.Owned_User = User.Owned_User
where Saved_Search_Order.User_ID=User_ID_param

ORDER BY Saved_Search_Order_ID
limit limit_param
offset offset_param
 ; END $$
 DELIMITER ;