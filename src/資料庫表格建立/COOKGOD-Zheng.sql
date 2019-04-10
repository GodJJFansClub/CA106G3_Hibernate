菜色

DROP TABLE DISH;
DROP SEQUENCE DISH_SEQ;

CREATE TABLE DISH (
 DISH_ID                  VARCHAR2(6)    NOT NULL,
 DISH_NAME                VARCHAR2(30)   NOT NULL,
 DISH_STATUS              VARCHAR2(2)      NOT NULL,
 DISH_PIC                 BLOB,
 DISH_RESUME              CLOB ,
 DISH_PRICE               NUMBER(6),
 PRIMARY KEY (DISH_ID));
 

 
 CREATE SEQUENCE DISH_SEQ
   START WITH 1
   INCREMENT BY 1 
   NOMAXVALUE 
   NOCYCLE
   NOCACHE;
   
   INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'XO醬炒雙脆','D0',NULL,'使用顏色鮮明、口感爽脆的蘭花蚌和甜豆拌炒，口感更有層次、配色鮮艷喜氣，加上以辣提鮮的康寶海龍皇爆炒醬，為歲末年終不可或缺的必勝菜式。','1800');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'百年慶好合','D0',NULL,'吃湯圓喜樂人團圓，今晚家家戶戶皆有喜，湯圓讓人在歲末年終有了圓圓滿滿的好彩頭！','200');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'芝麻流沙包','D0',NULL,'如果你是港式早茶愛好者，那一定吃過美味的流沙包，如果你還是個廚藝愛好者，那就不能錯過親手製作美味的流沙包！','380');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'魚子蒸燒賣','D0',NULL,'燒賣為港式點心中絕對不能錯過的美味,魚子蒸燒賣不但配色鮮豔動人,師傅四十餘年的料理經驗,運用獨特的內餡處理順序,帶給顧客最純正道地港式好滋味。','480');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'彭家招牌熬雞鍋','D0',NULL,'嚴選花東純正上等土雞 佐以日本北海道干貝、高山芽白心、精選排翅、冬菇、冬筍等食材以古法秘製高湯 金華火腿 悶燉數日而成 湯濃醇，味鮮美 肉質甜 營養成分更勝雞精 ，口味上乘，團圓年菜絕對極品！','2900');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'富貴牛腩筋','D0',NULL,'採用頂級紐西蘭KIWI羊肩排，以紅酒及獨特香料搭配特調羊排醬汁醃製，文火香煎而成，入口豐腴，口感極緻。','1280');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'黑椒羊小排','D0',NULL,'精選美國帶骨牛小排，稻草香入菜，與紅酒共同熬煮5小時，軟嫩滑順好入口','1440');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'花雕貴妃蝦','D0',NULL,'採用肉質飽滿富彈性的新鮮大蝦，以特調佐料及精選十餘種中藥材浸泡，?佳風味，爽口嫩脆，清甜夠味。','1000');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'蠔油美鮑片','D0',NULL,'獨家特殊調味配方，並透過長時間醃製，讓貝肉充分吸收滷汁，釋放出鮑貝鮮美香甜的口感。鮑貝肉質Q彈有嚼勁，經過蠔油烹調後，更能襯托出鮑貝於大海中的絕美海味，一口咬下後的湯汁瞬間在口中爆發，令人回味無窮。','1280');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'金華元蹄','D0',NULL,'CAS認證3斤以上豬腳，以獨家配料醃製5天，經文火滷製，外皮緊實香Q，肉質鮮嫩多汁，入口彈牙,口齒留香。','1000');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'綜合沙拉','D0',NULL,'炎炎夏日，總是讓人食慾不振，想要吃點清爽無負擔的餐點真的很簡單，就讓我們鮮採直送特別挑選的荷蘭頂級SalanovaR品種萵苣。','480');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'刺身拼盤','D0',NULL,'刺身是日式料理中著名菜式，通常是選用新鮮的魚類、蝦類、貝類等食材，去掉外殼、骨頭、內臟，只保留最鮮美的肉質，再將處理好的肉質切割成片，蘸醬油、山葵、味噌等調味料食用。肉質鮮嫩爽口有咬勁，營養價值頗高，含有豐富的蛋白質、維生素及微量礦物質，脂肪含量低，且容易吸收，對身體非常有益，是難得的天然美味。','750');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'炸蝦天婦羅','D0',NULL,'把蝦腸跟尾巴前的外殼取下、一尾一尾的下鍋炸，新鮮的魚肉跟脆脆的麵衣口感正是它的魅力所在啊！','490');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'墨魚蒸飯','D0',NULL,'整隻新鮮墨魚下鍋，細火慢煎，再送進烤箱，烤出Q度，逼出的鮮甜醬 汁、墨魚汁，再遵循義大利古法，和著米飯小火慢炒，綿密香濃的墨魚飯，全部包進墨魚的肚子裡，Q彈有勁墨 魚，有著海洋的鮮，醬汁的甜，配上米飯，讓口感更上層樓。','490');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'土瓶蒸','D0',NULL,'食材有雞肉、蛤蠣、白果、魚片、竹輪等?因為蒸煮過後所有食材的精華都在湯裡面只能用一個「鮮」字來形容記得在湯裡面加點檸檬汁會有不一樣的風味喔??。','200');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'雞祥如意','D1',NULL,'精選8種頂級餡料，特別保留烘烤時滲出的精華原汁，加入白酒與洋蔥拌炒成烤雞肉汁，享用時佐以微酸帶甜的蔓越莓醬，襯托出雞肉的鮮甜美味。即日起至1月28日開放預購，售完為止，要買要快唷！','1280');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'砂鍋八寶雞燉翅','D1',NULL,'主廚特選上等魚翅與多種珍貴食材，以恰到好處的火候精燉4小時，味香四溢，是除夕圍爐不可或缺的一道功夫大菜','3600');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'古傳老鴨煲','D1',NULL,'承襲正統江浙菜烹調手法，特選2公斤肥鴨細火慢燉4小時，熬製成乳白色的湯底，味濃而不膩，再加入扁魚乾、邊尖筍、金針花、家鄉肉、蹄筋、海參及包心白菜，文火再燜煮2小時，將邊尖筍鹹香滋味、金針花的自然花香、鴨骨精華及海味膠質完全釋放在湯底中；鴨肉纖維則變得綿密軟嫩、包心白菜滑口回甘、吸滿湯汁。一口湯再加上一口料，讓饕客們口口生香、口口滿足。','1380');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'炙烤美國Prime牛肉','D1',NULL,'選用美國最高等級Prime牛肉，肉質與脂肪分佈比例堪稱最佳。調味堅持極簡精神，烹製工法卻極其細膩，以純天然酸性醃料醃漬並輕柔按摩一小時，讓肉香全然釋放、軟化肉質，表層油脂在燒烤過程中，漸成焦糖色澤並鎖住濃郁肉汁，最後僅以少量鹽巴與胡椒粉點出濃厚肉香，讓頂級牛肉的原始風味能盡其展現！','1880');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'酸菜白肉鍋','D1',NULL,'主廚特選上等魚翅與多種珍貴食材，以恰到好處的火候精燉4小時，味香四溢，是除夕圍爐不可或缺的一道功夫大菜','900');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'泰式酸辣貴妃腿','D1',NULL,'嚴選上等豬後腿(一隻約600g)，先川燙去除腥味，再以主廚特調香料及調味料醃製一天一夜，隔日取出後用當令蔬菜及香料滷1.5小時熟成入味後，再以200度高溫油炸瞬間鎖住肉並達到表皮酥香的效果，最後淋上主廚秘制以台灣本地蝦米、鹹蛋黃爆香再加上多種南洋辛香料調製而成的泰式風味酸辣醬，即完成一道外皮酥脆、肉嫩多汁有豐富層次口感的豬腳料理。','880');
INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),'紅酒稻草西施牛','D1',NULL,'精選美國帶骨牛小排，稻草香入菜，與紅酒共同熬煮5小時，軟嫩滑順好入口','1580');

   
--------------------------------------------------------------------------------------------------------------

主廚論壇文章

DROP TABLE FORUM_ART;
DROP SEQUENCE FORUM_ART_SEQ;
ALTER SESSION SET NLS_TIMESTAMP_FORMAT ='yyyy-mm-dd hh24:mi';


CREATE TABLE FORUM_ART(
 FORUM_ART_ID		        VARCHAR2(6)      NOT NULL,
 FORUM_ART_NAME		        VARCHAR2(90)   NOT NULL,
 FORUM_ART_CON		        CLOB ,  
 FORUM_ART_START		    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FORUM_ART_PIC		        BLOB,
 FORUM_ART_STATUS	        VARCHAR2(2) ,
 CHEF_ID			        VARCHAR2(6) ,
 
 CONSTRAINT FORUM_ART_FK FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 PRIMARY KEY (FORUM_ART_ID));
 
 CREATE SEQUENCE FORUM_ART_SEQ
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE;
 
 INSERT INTO FORUM_ART (FORUM_ART_ID,FORUM_ART_NAME,FORUM_ART_CON,FORUM_ART_PIC,FORUM_ART_STATUS,CHEF_ID) VALUES ('A'||LPAD((FORUM_ART_SEQ.NEXTVAL),5,'0'),'泰式打拋豬','泰式經典料理，酸酸辣辣的很開胃下飯，但其實做法很簡單，準備的食材也不會太多，適合在家自己試試看！',NULL,'D1','C02121');
 
-----------------------------------------------------------------------------------------------------------------

主廚論壇留言

DROP TABLE FORUM_MSG;
DROP SEQUENCE FORUM_MSG_SEQ;

ALTER SESSION SET NLS_TIMESTAMP_FORMAT ='yyyy-mm-dd hh24:mi';

CREATE TABLE FORUM_MSG(
 FORUM_MSG_ID		      VARCHAR(6)     NOT NULL,
 FORUM_MSG_CON		      CLOB   ,
 FORUM_MSG_START		  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FORUM_MSG_STATUS		  VARCHAR(2) ,
 FORUM_ART_ID		      VARCHAR(6) ,
 CUST_ID			      VARCHAR2(6) ,
 CONSTRAINT FORUM_MSG_FK1 FOREIGN KEY (FORUM_ART_ID) REFERENCES FORUM_ART (FORUM_ART_ID), 
 CONSTRAINT FORUM_MSG_FK2 FOREIGN KEY (CUST_ID)      REFERENCES CUST (CUST_ID),
 PRIMARY KEY (FORUM_MSG_ID));
 
 CREATE SEQUENCE FORM_MSG_SEQ
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE;
 
INSERT INTO FORUM_MSG (FORUM_MSG_ID,FORUM_MSG_CON,FORUM_MSG_STATUS,FORUM_ART_ID,CUST_ID) VALUES ('MS'||LPAD(FORM_MSG_SEQ.NEXTVAL,4,'0'),'下班後 馬上做給家人吃吃看','D1','A00001','C02121');
------------------------------------------------------------------------------------------------------------------
菜色使用食材

DROP TABLE DISH_FOOD;

CREATE TABLE DISH_FOOD (
 DISH_ID                  VARCHAR2(6)    ,
 FOOD_ID                  VARCHAR2(6)   ,
 DISH_F_QTY               NUMBER(4)      ,
 DISH_F_UNIT              VARCHAR(4),
 
 CONSTRAINT DISH_FOOD_FK1 FOREIGN KEY (DISH_ID) REFERENCES DISH (DISH_ID),
 CONSTRAINT DISH_FOOD_FK2 FOREIGN KEY (FOOD_ID) REFERENCES FOOD (FOOD_ID),
 PRIMARY KEY (DISH_ID,FOOD_ID));
 
INSERT INTO DISH_FOOD(DISH_ID,FOOD_ID,DISH_F_QTY,DISH_F_UNIT) VALUES('D00003','F00001',30,'g');
INSERT INTO DISH_FOOD(DISH_ID,FOOD_ID,DISH_F_QTY,DISH_F_UNIT) VALUES('D00003','F00022',2,'kg');
INSERT INTO DISH_FOOD(DISH_ID,FOOD_ID,DISH_F_QTY,DISH_F_UNIT) VALUES('D00003','F00023',30,'g');
INSERT INTO DISH_FOOD(DISH_ID,FOOD_ID,DISH_F_QTY,DISH_F_UNIT) VALUES('D00003','F00024',30,'g');
INSERT INTO DISH_FOOD(DISH_ID,FOOD_ID,DISH_F_QTY,DISH_F_UNIT) VALUES('D00003','F00025',1,'Kg');
INSERT INTO DISH_FOOD(DISH_ID,FOOD_ID,DISH_F_QTY,DISH_F_UNIT) VALUES('D00003','F00026',150,'g');
INSERT INTO DISH_FOOD(DISH_ID,FOOD_ID,DISH_F_QTY,DISH_F_UNIT) VALUES('D00003','F00027',1,'kg');


 ---------------------------------------------------------------------------------------
套餐菜色

DROP TABLE MENU_DISH;


CREATE TABLE MENU_DISH (
 MENU_ID                  VARCHAR2(6) ,
 DISH_ID                  VARCHAR2(6) ,
 
 CONSTRAINT MENU_DISH_FK1 FOREIGN KEY (DISH_ID) REFERENCES DISH (DISH_ID),
 CONSTRAINT MENU_DISH_FK2 FOREIGN KEY (MENU_ID) REFERENCES MENU (MENU_ID),
 PRIMARY KEY (DISH_ID,MENU_ID));
 
 --CREATE SEQUENCE MENU_DISH_SEQ
    --START WITH 1
    --INCREMENT BY 1
    --NOMAXVALUE
    --NOCYCLE
    --NOCACHE;
 
 INSERT INTO MENU_DISH (DISH_ID,MENU_ID) VALUES('D00002','M00001');
 INSERT INTO MENU_DISH (DISH_ID,MENU_ID) VALUES('D00003','M00001');
 INSERT INTO MENU_DISH (DISH_ID,MENU_ID) VALUES('D00004','M00001');
 INSERT INTO MENU_DISH (DISH_ID,MENU_ID) VALUES('D00005','M00001');
 INSERT INTO MENU_DISH (DISH_ID,MENU_ID) VALUES('D00006','M00001');
 INSERT INTO MENU_DISH (DISH_ID,MENU_ID) VALUES('D00007','M00001');
-----------------------------------------------------------------------------------------

食材

DROP SEQUENCE FOOD_SEQ;
DROP TABLE FOOD;

CREATE TABLE FOOD (
 FOOD_ID                  VARCHAR2(6)   NOT NULL,
 FOOD_NAME                VARCHAR2(30)  NOT NULL,
 PRIMARY KEY (FOOD_ID));
 
 CREATE SEQUENCE FOOD_SEQ
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE;

INSERT INTO  FOOD (FOOD_ID,FOOD_NAME) VALUES('F'||LPAD(FOOD_SEQ.NEXTVAL,5,'0'),'扁魚乾');
INSERT INTO  FOOD (FOOD_ID,FOOD_NAME) VALUES('F'||LPAD(FOOD_SEQ.NEXTVAL,5,'0'),'肥鴨');
INSERT INTO  FOOD (FOOD_ID,FOOD_NAME) VALUES('F'||LPAD(FOOD_SEQ.NEXTVAL,5,'0'),'邊尖筍'); 
INSERT INTO  FOOD (FOOD_ID,FOOD_NAME) VALUES('F'||LPAD(FOOD_SEQ.NEXTVAL,5,'0'),'金針花'); 
INSERT INTO  FOOD (FOOD_ID,FOOD_NAME) VALUES('F'||LPAD(FOOD_SEQ.NEXTVAL,5,'0'),'家鄉肉'); 
INSERT INTO  FOOD (FOOD_ID,FOOD_NAME) VALUES('F'||LPAD(FOOD_SEQ.NEXTVAL,5,'0'),'蹄筋');
INSERT INTO  FOOD (FOOD_ID,FOOD_NAME) VALUES('F'||LPAD(FOOD_SEQ.NEXTVAL,5,'0'),'包心白菜');

-------------------------------------------------------------------------------------------




 