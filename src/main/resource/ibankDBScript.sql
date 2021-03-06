CREATE USER ibanknew
    IDENTIFIED BY ibank123;

grant all privileges to ibanknew;


CREATE TABLE Customer(
id VARCHAR2(50),
firstName VARCHAR2(50),
lastName VARCHAR2(50),
email VARCHAR2(50),
phone NUMBER(10),
address VARCHAR2(50),
dateOfBirth DATE ,
branchId VARCHAR2(50),
enqId VARCHAR2(50),
applicationStatus VARCHAR2(50),
customerId NUMBER(10),
userName VARCHAR2(50),
password VARCHAR2(50),
authorizedImageName VARCHAR2(50),
authorizedImageText VARCHAR2(50),
CONSTRAINT customers_pk PRIMARY KEY (id),
CONSTRAINT fk_branch
    FOREIGN KEY (branchId)
    REFERENCES Branch(id)

)

CREATE TABLE Branch(
id VARCHAR2(50),
ifscCode VARCHAR2(50),
branchName VARCHAR2(50),
address VARCHAR2(50),
contact NUMBER(10),
CONSTRAINT branch_pk PRIMARY KEY (id)
)

CREATE TABLE Account(
id VARCHAR2(50),
accountType VARCHAR2(50),
accountNumber NUMBER(20),
balance NUMBER(10,2),
interestRate NUMBER(10,2),
CustId VARCHAR2(50),
CONSTRAINT account_pk PRIMARY KEY (id),
CONSTRAINT fk_customer
    FOREIGN KEY (CustId)
    REFERENCES Customer(id)

)

CREATE TABLE BranchManager(
id VARCHAR2(50),
firstName VARCHAR2(50),
lastName VARCHAR2(50),
email VARCHAR2(50),
phone NUMBER(10),
address VARCHAR2(50),
dateOfBirth DATE ,
branchId VARCHAR2(50),
userName VARCHAR2(50),
password VARCHAR2(50),
CONSTRAINT branch_manager_pk PRIMARY KEY (id),
CONSTRAINT fk_branchmanager
    FOREIGN KEY (branchId)
    REFERENCES Branch(id)

)


CREATE TABLE Cust_Document(                
id       VARCHAR2(50),
CustId   VARCHAR2(50),
imageAge      BLOB,
imageAddress   BLOB,
CONSTRAINT cust_doc_pk PRIMARY KEY (id),
CONSTRAINT fk_customer_doc
    FOREIGN KEY (CustId)
    REFERENCES Customer(id)
)
