DROP TABLE BROADCAST;
DROP TABLE REPORT;
DROP TABLE FORUM_MSG;
DROP TABLE FORUM_ART;
DROP TABLE AUTH;
DROP TABLE FUN;
DROP TABLE EMP;
DROP TABLE MENU_DISH;
DROP TABLE MENU_ORDER;
DROP TABLE MENU;
DROP TABLE FEST_ORDER_DETAIL;
DROP TABLE FEST_ORDER;
DROP TABLE FEST_DISH;
DROP TABLE FEST_MENU;
DROP TABLE CHEF_OD_DETAIL;
DROP TABLE CHEF_ORDER;
DROP TABLE FOOD_OD_DETAIL;
DROP TABLE FOOD_ORDER;
DROP TABLE FOOD_MALL;
DROP TABLE FAV_FD_SUP;
DROP TABLE AD;
DROP TABLE FOOD_SUP;
DROP TABLE DISH_FOOD;
DROP TABLE FOOD;
DROP TABLE CHEF_DISH;
DROP TABLE DISH;
DROP TABLE CHEF_SCH;
DROP TABLE FAV_CHEF;
DROP TABLE CHEF;
DROP TABLE CUST;

------------------------------------------------------------------
--DATE_FORMAT--
ALTER SESSION SET NLS_TIMESTAMP_FORMAT= 'YYYY-MM-DD HH24:MI:SS';

------------------------------------------------------------------
--建立表格:顧客(移除CUST_BLOB的NOT NULL)--
CREATE TABLE CUST (
 CUST_ID                 VARCHAR2(6)   NOT NULL,
 CUST_ACC                VARCHAR2(15)  NOT NULL,
 CUST_PWD                VARCHAR2(15)  NOT NULL,
 CUST_NAME               VARCHAR2(30)  NOT NULL,
 CUST_SEX                VARCHAR2(2)   NOT NULL,
 CUST_TEL                VARCHAR2(20)    NOT NULL,
 CUST_ADDR               VARCHAR2(200) NOT NULL,
 CUST_PID                CHAR(10)      NOT NULL,
 CUST_MAIL               VARCHAR2(30)  NOT NULL,
 CUST_BRD                DATE,
 CUST_REG                DATE          NOT NULL,
 CUST_PIC                BLOB,
 CUST_STATUS             VARCHAR2(1)     NOT NULL,
 CUST_NINAME             VARCHAR2(30)  NOT NULL,
 PRIMARY KEY (CUST_ID)
);

------------------------------------------------------------------
--建立表格:主廚--
CREATE TABLE CHEF (
 CHEF_ID                  VARCHAR2(6)    NOT NULL,
 CHEF_AREA                CHAR(1)        NOT NULL,
 CHEF_STATUS              VARCHAR2(2)      NOT NULL,
 CHEF_CHANNEL             VARCHAR2(100),
 CHEF_RESUME              CLOB    NOT NULL,
 CONSTRAINT CHEF_ID_FK FOREIGN KEY (CHEF_ID) REFERENCES CUST (CUST_ID),
 PRIMARY KEY (CHEF_ID) 
);

------------------------------------------------------------------
--建立表格:喜愛主廚--
CREATE TABLE FAV_CHEF (
 CUST_ID                  VARCHAR2(6)    NOT NULL,
 CHEF_ID                  VARCHAR2(6)    NOT NULL,
 CONSTRAINT FAV_CHEF_FK1 FOREIGN KEY (CUST_ID) REFERENCES CUST (CUST_ID),
 CONSTRAINT FAV_CHEF_FK2 FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 CONSTRAINT FAV_CHEF_PK  PRIMARY KEY (CUST_ID,CHEF_ID)
);

------------------------------------------------------------------
--建立表格:主廚排程--
CREATE TABLE CHEF_SCH (
 CHEF_SCH_DATE            DATE           NOT NULL,
 CHEF_ID                  VARCHAR2(6)    NOT NULL,
 CHEF_SCH_STATUS          VARCHAR2(2)      NOT NULL,
 CONSTRAINT CHEF_SCH_FK FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 CONSTRAINT CHEF_SCH_PK PRIMARY KEY (CHEF_ID,CHEF_SCH_DATE)
);

------------------------------------------------------------------
--建立表格:菜色--
CREATE TABLE DISH (
 DISH_ID                  VARCHAR2(6)    NOT NULL,
 DISH_NAME                VARCHAR2(30)   NOT NULL,
 DISH_STATUS              VARCHAR2(2)      NOT NULL,
 DISH_RESUME              CLOB   NOT NULL,
 DISH_PRICE               NUMBER(6),
 PRIMARY KEY (DISH_ID)
);

------------------------------------------------------------------
--建立表格:主廚擅長菜色--
CREATE TABLE CHEF_DISH (
 CHEF_ID                  VARCHAR2(6)   NOT NULL,
 DISH_ID                  VARCHAR2(6)   NOT NULL,
 CHEF_DISH_STATUS         VARCHAR2(2)   NOT NULL,
 CONSTRAINT CHEF_DISH_FK_D FOREIGN KEY (DISH_ID) REFERENCES DISH (DISH_ID), 
 CONSTRAINT CHEF_DISH_FK_C FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 CONSTRAINT CHEF_DISH_PK  PRIMARY KEY (CHEF_ID,DISH_ID)
);

------------------------------------------------------------------
--建立表格:食材--
CREATE TABLE FOOD (
 FOOD_ID                  VARCHAR2(6)   NOT NULL,
 FOOD_NAME                VARCHAR2(30)  NOT NULL,
 PRIMARY KEY (FOOD_ID)
);

------------------------------------------------------------------
--建立表格:菜色使用食材--
CREATE TABLE DISH_FOOD (
 DISH_ID                  VARCHAR2(6)   NOT NULL,
 FOOD_ID                  VARCHAR2(6)   NOT NULL,
 DISH_F_QTY               NUMBER(4)     NOT NULL,
 DISH_F_UNIT              VARCHAR2(4),
 CONSTRAINT DISH_FOOD_FK1  FOREIGN KEY (DISH_ID) REFERENCES DISH (DISH_ID),
 CONSTRAINT DISH_FOOD_FK2  FOREIGN KEY (FOOD_ID) REFERENCES FOOD (FOOD_ID),
 CONSTRAINT DISH_FOOD_PK   PRIMARY KEY (DISH_ID,FOOD_ID)
);

------------------------------------------------------------------
--建立表格:食材供應商--
CREATE TABLE FOOD_SUP (
 FOOD_SUP_ID                VARCHAR2(6)   NOT NULL,
 FOOD_SUP_NAME              VARCHAR2(30)  NOT NULL,
 FOOD_SUP_TEL               VARCHAR2(20)    NOT NULL,
 FOOD_SUP_STATUS            VARCHAR2(3)     NOT NULL,
 FOOD_SUP_RESUME            CLOB NOT NULL,
 CONSTRAINT FOOD_SUP_FK FOREIGN KEY (FOOD_SUP_ID) REFERENCES CUST (CUST_ID),
 PRIMARY KEY (FOOD_SUP_ID) 
);

------------------------------------------------------------------
--建立表格:廣告--
CREATE TABLE AD (
 AD_ID                    VARCHAR2(6)      NOT NULL,
 AD_STATUS                VARCHAR2(2)        NOT NULL,
 AD_START                 DATE             NOT NULL,
 AD_END                   DATE             NOT NULL,
 AD_TYPE                  NUMBER(1)        NOT NULL,
 AD_TITLE                 VARCHAR2(30)     NOT NULL,
 AD_CON                   CLOB   NOT NULL,
 FOOD_SUP_ID              VARCHAR2(6),
 CONSTRAINT AD_FK FOREIGN KEY (FOOD_SUP_ID) REFERENCES FOOD_SUP (FOOD_SUP_ID),
 PRIMARY KEY (AD_ID) 
);

------------------------------------------------------------------
--建立表格:喜愛食材供應商--
CREATE TABLE FAV_FD_SUP (
 CHEF_ID                   VARCHAR2(6)    NOT NULL,
 FOOD_SUP_ID               VARCHAR2(6)    NOT NULL,
 FAV_FD_SUP_NOTE           VARCHAR2(300),
 FAV_FD_SUP_NUM            NUMBER(2),
 CONSTRAINT FAV_FD_SUP_FK_C FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 CONSTRAINT FAV_FD_SUP_FK_F FOREIGN KEY (FOOD_SUP_ID) REFERENCES FOOD_SUP (FOOD_SUP_ID),
 CONSTRAINT FAV_FD_SUP_PK  PRIMARY KEY (CHEF_ID,FOOD_SUP_ID)
);

------------------------------------------------------------------
 
--建立表格:商城食材--
CREATE TABLE FOOD_MALL (
 FOOD_SUP_ID                VARCHAR2(6)   NOT NULL,
 FOOD_ID                    VARCHAR2(6)   NOT NULL,
 FOOD_M_NAME                VARCHAR2(30)  NOT NULL,
 FOOD_M_STATUS              VARCHAR2(3)     NOT NULL,
 FOOD_M_PRICE               NUMBER(6)     NOT NULL,
 FOOD_M_UNIT                VARCHAR2(8)   NOT NULL,
 FOOD_M_PLACE               VARCHAR2(30)  NOT NULL,
 FOOD_M_PIC                 BLOB,
 FOOD_M_RESUME              CLOB,
 FOOD_M_RATE                NUMBER(1),
 CONSTRAINT FOOD_MALL_FK1 FOREIGN KEY (FOOD_SUP_ID) REFERENCES FOOD_SUP (FOOD_SUP_ID),
 CONSTRAINT FOOD_MALL_FK2 FOREIGN KEY (FOOD_ID)     REFERENCES FOOD (FOOD_ID),
 CONSTRAINT FOOD_MALL_PK  PRIMARY KEY (FOOD_SUP_ID,FOOD_ID) 
);

------------------------------------------------------------------
--建立表格:食神嚴選食材訂單--
CREATE TABLE FOOD_ORDER (
 FOOD_OD_ID                VARCHAR2(6)    NOT NULL,
 FOOD_OD_STATUS            VARCHAR2(3)      NOT NULL, 
 FOOD_OD_START             DATE           NOT NULL,
 FOOD_OD_SEND              DATE           NOT NULL,
 FOOD_OD_RCV               DATE,
 FOOD_OD_END               DATE,
 FOOD_OD_NAME              VARCHAR2(30)   NOT NULL,
 FOOD_OD_ADDR              VARCHAR2(200)  NOT NULL,
 FOOD_OD_TEL               VARCHAR2(20)     NOT NULL,
 CUST_ID                   VARCHAR2(6)    NOT NULL,
 CONSTRAINT FOOD_ORDER_FK FOREIGN KEY (CUST_ID) REFERENCES CUST (CUST_ID),
 PRIMARY KEY (FOOD_OD_ID) 
);

------------------------------------------------------------------
--建立表格:食神嚴選食材訂單明細--
CREATE TABLE FOOD_OD_DETAIL (
 FOOD_OD_ID                VARCHAR2(6)   NOT NULL,
 FOOD_SUP_ID               VARCHAR2(6)   NOT NULL,
 FOOD_ID                   VARCHAR2(6)   NOT NULL,
 FOOD_OD_QTY               NUMBER(4)     NOT NULL,
 FOOD_OD_STOTAL            NUMBER(7)     NOT NULL,
 FOOD_OD_RATE              NUMBER(1),
 FOOD_OD_MSG               VARCHAR2(300),
 CONSTRAINT FOOD_OD_DETAIL_FK1 FOREIGN KEY (FOOD_OD_ID)          REFERENCES FOOD_ORDER (FOOD_OD_ID),
 CONSTRAINT FOOD_OD_DETAIL_FK2 FOREIGN KEY (FOOD_SUP_ID,FOOD_ID) REFERENCES FOOD_MALL (FOOD_SUP_ID,FOOD_ID),
 CONSTRAINT FOOD_OD_DETAIL_PK  PRIMARY KEY (FOOD_OD_ID,FOOD_SUP_ID,FOOD_ID) 
);

------------------------------------------------------------------
--建立表格:主廚食材訂單--
CREATE TABLE CHEF_ORDER (
 CHEF_OD_ID                 VARCHAR2(6)   NOT NULL,
 CHEF_OD_STATUS             NUMBER(1)     NOT NULL,
 CHEF_OD_START              DATE          NOT NULL,
 CHEF_OD_SEND               DATE          NOT NULL,
 CHEF_OD_RCV                DATE,
 CHEF_OD_END                DATE,
 CHEF_OD_NAME               VARCHAR2(30)  NOT NULL,
 CHEF_OD_ADDR               VARCHAR2(200) NOT NULL,
 CHEF_OD_TEL                VARCHAR2(20)    NOT NULL,
 CHEF_ID                    VARCHAR2(6)   NOT NULL,
 CONSTRAINT CHEF_ORDER_FK FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 PRIMARY KEY (CHEF_OD_ID)
);
 
------------------------------------------------------------------
--建立表格:主廚食材訂單明細--
CREATE TABLE CHEF_OD_DETAIL (
 CHEF_OD_ID                  VARCHAR2(6)    NOT NULL,
 FOOD_SUP_ID                 VARCHAR2(6)    NOT NULL,
 FOOD_ID                     VARCHAR2(6)    NOT NULL,
 CHEF_OD_QTY                 NUMBER(4)      NOT NULL,
 CHEF_OD_STOTAL              NUMBER(7)      NOT NULL,
 CONSTRAINT CHEF_OD_DETAIL_FK1 FOREIGN KEY (CHEF_OD_ID)          REFERENCES CHEF_ORDER (CHEF_OD_ID),
 CONSTRAINT CHEF_OD_DETAIL_FK2 FOREIGN KEY (FOOD_SUP_ID,FOOD_ID) REFERENCES FOOD_MALL (FOOD_SUP_ID,FOOD_ID),
 CONSTRAINT CHEF_OD_DETAIL_PK  PRIMARY KEY (CHEF_OD_ID,FOOD_SUP_ID,FOOD_ID)
);

------------------------------------------------------------------
--建立表格:節慶主題料理--
CREATE TABLE FEST_MENU (
 FEST_M_ID               VARCHAR2(6)          NOT NULL,
 FEST_M_NAME             VARCHAR2(30)         NOT NULL,
 FEST_M_QTY              NUMBER(4)            NOT NULL,
 FEST_M_START            DATE                 NOT NULL,
 FEST_M_END              DATE                 NOT NULL,
 FEST_M_PIC              BLOB,
 FEST_M_RESUME           CLOB,
 FEST_M_SEND             DATE                 NOT NULL,
 FEST_M_STATUS           VARCHAR2(1)            NOT NULL,
 FEST_M_KIND             VARCHAR2(30)         NOT NULL,
 CHEF_ID                 VARCHAR2(6)          NOT NULL,
 CONSTRAINT FEST_MENU_FK FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 PRIMARY KEY (FEST_M_ID)
);

------------------------------------------------------------------
--建立表格:節慶主題料理菜色--
CREATE TABLE FEST_DISH(
 DISH_ID		         VARCHAR2(6)         NOT NULL,
 FEST_M_ID	             VARCHAR2(6)         NOT NULL,
 CONSTRAINT FEST_DISH_FK1 FOREIGN KEY(DISH_ID)   REFERENCES DISH (DISH_ID),
 CONSTRAINT FEST_DISH_FK2 FOREIGN KEY(FEST_M_ID) REFERENCES FEST_MENU (FEST_M_ID),
 CONSTRAINT FEST_DISH_PK  PRIMARY KEY (DISH_ID,FEST_M_ID)
);

------------------------------------------------------------------
--建立表格:節慶主題料理訂單--
CREATE TABLE FEST_ORDER(
 FEST_OD_ID		        VARCHAR2(6)     NOT NULL,	
 FEST_OD_STATUS		    VARCHAR2(2)       NOT NULL,
 FEST_OD_PRICE		    NUMBER(7)       NOT NULL,
 FEST_OD_START		    DATE            NOT NULL,
 FEST_OD_SEND		    DATE            NOT NULL,
 FEST_OD_END		   	DATE            NOT NULL,
 FEST_OD_DISC		    VARCHAR2(30),
 CUST_ID			    VARCHAR2(6)     NOT NULL,
 CONSTRAINT FEST_ORDER_FK FOREIGN KEY (CUST_ID) REFERENCES CUST (CUST_ID),
 PRIMARY KEY (FEST_OD_ID)
);

------------------------------------------------------------------
--建立表格:節慶主題料理訂單明細--
CREATE TABLE FEST_ORDER_DETAIL(
 FEST_OD_ID	            VARCHAR2(6)    NOT NULL,
 FEST_M_ID	            VARCHAR2(6)    NOT NULL,
 FEST_OD_RATE	   	    NUMBER(1),  
 FEST_OD_MSG	   	    VARCHAR2(300),
 FEST_OD_QTY		    NUMBER(4)      NOT NULL,
 FEST_OD_STOTAL	        NUMBER(8)      NOT NULL,
 CONSTRAINT FEST_ORDER_DETAIL_FK1 FOREIGN KEY (FEST_M_ID)  REFERENCES FEST_MENU (FEST_M_ID),
 CONSTRAINT FEST_ORDER_DETAIL_FK2 FOREIGN KEY (FEST_OD_ID) REFERENCES FEST_ORDER (FEST_OD_ID),
 CONSTRAINT FEST_ORDER_DETAIL_PK  PRIMARY KEY (FEST_OD_ID,FEST_M_ID)
);

---------------------------------------------------------------
--建立表格:嚴選套餐--
CREATE TABLE MENU(
 MENU_ID		          VARCHAR2(6)    NOT NULL,
 MENU_NAME	              VARCHAR2 (30)  NOT NULL,
 MENU_RESUME		      CLOB,
 MENU_PIC	              BLOB,
 MENU_STATUS	          VARCHAR2(1)      NOT NULL,
 MENU_PRICE		          NUMBER(6)      NOT NULL,
 PRIMARY KEY (MENU_ID)
);

----------------------------------------------------------------
--建立表格:嚴選套餐訂單--
CREATE TABLE MENU_ORDER(
 MENU_OD_ID		          VARCHAR2(17)     NOT NULL,
 MENU_OD_STATUS		      VARCHAR2(2)       NOT NULL,
 MENU_OD_START		      TIMESTAMP           NOT NULL,
 MENU_OD_BOOK		      TIMESTAMP           NOT NULL,
 MENU_OD_END			  DATE,
 MENU_OD_RATE		      NUMBER(1),
 MENU_OD_MSG		      VARCHAR2(300),
 CUST_ID			      VARCHAR2(6)     NOT NULL,
 CHEF_ID			      VARCHAR2(6)     NOT NULL,
 MENU_ID			      VARCHAR2(6)     NOT NULL,
 CONSTRAINT MENU_ORDER_FK_CUST FOREIGN KEY (CUST_ID) REFERENCES CUST (CUST_ID),
 CONSTRAINT MENU_ORDER_FK_CHEF FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 CONSTRAINT MENU_ORDER_FK_MENU FOREIGN KEY (MENU_ID) REFERENCES MENU (MENU_ID),
 PRIMARY KEY(MENU_OD_ID)
);

---------------------------------------------------------------
--建立表格:套餐菜色--
CREATE TABLE MENU_DISH(
 MENU_ID		           VARCHAR2(6)     NOT NULL,	
 DISH_ID		           VARCHAR2(6)     NOT NULL,
 CONSTRAINT MENU_DISH_FK1 FOREIGN KEY (MENU_ID) REFERENCES MENU (MENU_ID),
 CONSTRAINT MENU_DISH_FK2 FOREIGN KEY (DISH_ID) REFERENCES DISH (DISH_ID),
 CONSTRAINT MENU_DISH_PK  PRIMARY KEY(MENU_ID,DISH_ID)
);

-----------------------------------------------------------------
--建立表格:員工--
CREATE TABLE EMP(
 EMP_ID		               VARCHAR2(6)      NOT NULL,
 EMP_ACC		           VARCHAR2(15)     NOT NULL,
 EMP_PWD		           VARCHAR2(15)     NOT NULL,
 EMP_NAME	               VARCHAR2(30)     NOT NULL,
 EMP_PIC		           BLOB             NOT NULL,
 PRIMARY KEY (EMP_ID)
);

-------------------------------------------------------------------
--建立表格:功能--
CREATE TABLE FUN(
 FUN_ID		              VARCHAR2(6)      NOT NULL,		
 FUN_NAME	              VARCHAR2(30)     NOT NULL,	
 PRIMARY KEY (FUN_ID)
);

-------------------------------------------------------------------------
--建立表格:權限--
CREATE TABLE AUTH(
 EMP_ID		             VARCHAR2(6)      NOT NULL,
 FUN_ID		             VARCHAR2(6)      NOT NULL,
 CONSTRAINT AUTH_FK1 FOREIGN KEY (EMP_ID) REFERENCES EMP (EMP_ID),
 CONSTRAINT AUTH_FK2 FOREIGN KEY (FUN_ID) REFERENCES FUN (FUN_ID),
 CONSTRAINT AUTH_PK  PRIMARY KEY (EMP_ID,FUN_ID)
);

-------------------------------------------------------------------------
--建立表格:主廚論壇文章--
CREATE TABLE FORUM_ART(
 FORUM_ART_ID		        VARCHAR2(6)      NOT NULL,
 FORUM_ART_NAME		        VARCHAR2(90)   NOT NULL,
 FORUM_ART_CON		        CLOB           NOT NULL,  
 FORUM_ART_START		    TIMESTAMP      NOT NULL,
 FORUM_ART_STATUS	        VARCHAR2(1)      NOT NULL,
 CHEF_ID			        VARCHAR2(6)    NOT NULL,
 CONSTRAINT FORUM_ART_FK FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 PRIMARY KEY (FORUM_ART_ID)
);

---------------------------------------------------------------------------
--建立表格:主廚論壇留言--
CREATE TABLE FORUM_MSG(
 FORUM_MSG_ID		      VARCHAR2(6)     NOT NULL,
 FORUM_MSG_CON		      CLOB          NOT NULL,
 FORUM_MSG_START		  TIMESTAMP     NOT NULL,
 FORUM_MSG_STATUS		  VARCHAR2(1)     NOT NULL,
 FORUM_ART_ID		      VARCHAR2(6)     NOT NULL,
 CUST_ID			      VARCHAR2(6)   NOT NULL,
 CONSTRAINT FORUM_MSG_FK1 FOREIGN KEY (FORUM_ART_ID) REFERENCES FORUM_ART (FORUM_ART_ID), 
 CONSTRAINT FORUM_MSG_FK2 FOREIGN KEY (CUST_ID)      REFERENCES CUST (CUST_ID),
 PRIMARY KEY (FORUM_MSG_ID)
);

------------------------------------------------------------------------------------
--建立表格:檢舉--
CREATE TABLE REPORT(
 REPORT_ID		          VARCHAR2(6)      NOT NULL,
 REPORT_TITLE  	          VARCHAR2(30)   NOT NULL,
 REPORT_SORT		      VARCHAR2(30)   NOT NULL,
 REPORT_START		      TIMESTAMP      NOT NULL,
 REPORT_ID_STATUS	      VARCHAR2(1)      NOT NULL,
 REPORT_CON		          VARCHAR2(100)  NOT NULL,
 CUST_ID		          VARCHAR2(6)    NOT NULL,
 FORUM_ART_ID		      VARCHAR2(6)      NOT NULL,
 CONSTRAINT REPORT_FK1 FOREIGN KEY (CUST_ID)      REFERENCES CUST (CUST_ID),
 CONSTRAINT REPORT_FK2 FOREIGN KEY (FORUM_ART_ID) REFERENCES FORUM_ART (FORUM_ART_ID),
 PRIMARY KEY(REPORT_ID)
);

-----------------------------------------------------------------
--建立表格:推播通知--
CREATE TABLE BROADCAST(
 BROADCAST_ID		          VARCHAR2(6)    NOT NULL,	
 BROADCAST_START		      TIMESTAMP,
 BROADCAST_CON		          VARCHAR2(100),
 BROADCAST_STATUS		      VARCHAR2(1)      NOT NULL,  
 CUST_ID		              VARCHAR2(6)    NOT NULL,
 CONSTRAINT BROADCAST_FK FOREIGN KEY (CUST_ID) REFERENCES CUST (CUST_ID),
 PRIMARY KEY(BROADCAST_ID)
);

-----------------------------------------------------------------

commit;

-----------------------------------------------------------------
DROP SEQUENCE CUST_ID_SEQ;
DROP SEQUENCE MENU_ID_SEQ;
DROP SEQUENCE DISH_ID_SEQ;
-----------------------------------------------------------------
--CREATE SEQUENCE--
CREATE SEQUENCE CUST_ID_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCYCLE
NOCACHE;

CREATE SEQUENCE MENU_ID_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCYCLE
NOCACHE;

CREATE SEQUENCE MENU_OD_ID_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 999999
NOCYCLE
NOCACHE;

CREATE SEQUENCE DISH_ID_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCYCLE
NOCACHE;

-----------------------------------------------------------------
--CUST--
INSERT INTO CUST (CUST_ID,CUST_ACC,CUST_PWD,CUST_NAME,CUST_SEX,CUST_TEL,CUST_ADDR,CUST_PID,CUST_MAIL,CUST_BRD,CUST_REG,CUST_STATUS,CUST_NINAME) 
VALUES ('C'||LPAD(to_char(CUST_ID_SEQ.NEXTVAL), 5, '0'),'CUST','123456','AAA','S1','0987654321','桃園市中壢區1號','A123456789','TEST_01@GMAIL.COM',TO_DATE('2000-01-01','YYYY-MM-DD'),TO_DATE('2019-02-11','YYYY-MM-DD'),0,'DAVID_01');

INSERT INTO CUST (CUST_ID,CUST_ACC,CUST_PWD,CUST_NAME,CUST_SEX,CUST_TEL,CUST_ADDR,CUST_PID,CUST_MAIL,CUST_BRD,CUST_REG,CUST_STATUS,CUST_NINAME) 
VALUES ('C'||LPAD(to_char(CUST_ID_SEQ.NEXTVAL), 5, '0'),'CHEF','123456','BBB','S1','0987654322','桃園市中壢區2號','B223456789','TEST_02@GMAIL.COM',TO_DATE('2000-02-02','YYYY-MM-DD'),TO_DATE('2019-02-11','YYYY-MM-DD'),0,'DAVID_02');

INSERT INTO CUST (CUST_ID,CUST_ACC,CUST_PWD,CUST_NAME,CUST_SEX,CUST_TEL,CUST_ADDR,CUST_PID,CUST_MAIL,CUST_BRD,CUST_REG,CUST_STATUS,CUST_NINAME) 
VALUES ('C'||LPAD(to_char(CUST_ID_SEQ.NEXTVAL), 5, '0'),'FOOD_SUP','123456','CCC','S1','0987654323','桃園市中壢區3號','C323456789','TEST_03@GMAIL.COM',TO_DATE('2000-03-03','YYYY-MM-DD'),TO_DATE('2019-02-11','YYYY-MM-DD'),0,'DAVID_03');

-----------------------------------------------------------------
--MENU--
INSERT INTO MENU (MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE) 
VALUES ('M'||LPAD(to_char(MENU_ID_SEQ.NEXTVAL), 5, '0'),'RED_FOOD','HAPPY NEW YEAR','1','8888');

-----------------------------------------------------------------
--DISH--
INSERT INTO DISH (DISH_ID,DISH_NAME,DISH_STATUS,DISH_RESUME,DISH_PRICE)
VALUES ('D'||LPAD(to_char(DISH_ID_SEQ.NEXTVAL), 5, '0'),'BIG_SEAFOOD',1,'VERY BIG',88);

-----------------------------------------------------------------
--FOOD_SUP--
INSERT INTO FOOD_SUP (FOOD_SUP_ID,FOOD_SUP_NAME,FOOD_SUP_TEL,FOOD_SUP_STATUS,FOOD_SUP_RESUME) 
VALUES ('C00003','DAVID_SEAFOOD','02-2222-3333','1','SEAFOOD_SUP');

-----------------------------------------------------------------
--CHEF--
INSERT INTO CHEF (CHEF_ID,CHEF_AREA,CHEF_STATUS,CHEF_CHANNEL,CHEF_RESUME)
VALUES ('C00002','5','1','WWW.YOUTUBE.COM','BIG_MASTER');

-----------------------------------------------------------------
--CHEF_SCH--
INSERT INTO CHEF_SCH (CHEF_ID,CHEF_SCH_DATE,CHEF_SCH_STATUS)
VALUES ('C00002',TO_DATE('2019-02-11','YYYY-MM-DD'),'0');

-----------------------------------------------------------------
--CHEF_DISH--
INSERT INTO CHEF_DISH (CHEF_ID,DISH_ID,CHEF_DISH_STATUS)
VALUES ('C00002','D00001','1');

-----------------------------------------------------------------
--FAV_FD_SUP--
INSERT INTO FAV_FD_SUP (CHEF_ID,FOOD_SUP_ID,FAV_FD_SUP_NOTE,FAV_FD_SUP_NUM)
VALUES ('C00002','C00003','THE BEST',1);

-----------------------------------------------------------------
--MENU_ORDER--
INSERT INTO MENU_ORDER (MENU_OD_ID,MENU_OD_STATUS,MENU_OD_START,MENU_OD_BOOK,CUST_ID,CHEF_ID,MENU_ID)
VALUES ('MU20121011-000001','1',SYSDATE,TO_DATE('2019-02-25 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),'C00001','C00002','M00001');

-----------------------------------------------------------------
commit;