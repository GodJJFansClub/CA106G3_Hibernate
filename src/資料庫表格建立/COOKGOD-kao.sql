------------------------------------------------------------------
--建立表格:喜愛主廚--
CREATE TABLE FAV_CHEF (
 CUST_ID                  VARCHAR2(6)    NOT NULL,
 CHEF_ID                  VARCHAR2(6)    NOT NULL,
 CONSTRAINT FAV_CHEF_FK1 FOREIGN KEY (CUST_ID) REFERENCES CUST (CUST_ID),
 CONSTRAINT FAV_CHEF_FK2 FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 CONSTRAINT FAV_CHEF_PK  PRIMARY KEY (CUST_ID,CHEF_ID)
);

INSERT INTO FAV_CHEF(CUST_ID,CHEF_ID)
VALUES('C00001','C00004');
INSERT INTO FAV_CHEF(CUST_ID,CHEF_ID)
VALUES('C00001','C00005');
INSERT INTO FAV_CHEF(CUST_ID,CHEF_ID)
VALUES('C00002','C00004');
INSERT INTO FAV_CHEF(CUST_ID,CHEF_ID)
VALUES('C00002','C00005');
INSERT INTO FAV_CHEF(CUST_ID,CHEF_ID)
VALUES('C00003','C00004');
INSERT INTO FAV_CHEF(CUST_ID,CHEF_ID)
VALUES('C00003','C00005');
-----------------------------------------------------------------------------------------------------------------------------------
--建立表格:主廚食材訂單--
CREATE TABLE CHEF_ORDER (
 CHEF_OR_ID                 VARCHAR2(17)  NOT NULL,
 CHEF_OR_STATUS             VARCHAR2(2)   NOT NULL,
 CHEF_OR_START              DATE          NOT NULL,
 CHEF_OR_SEND               DATE          NOT NULL,
 CHEF_OR_RCV                DATE,
 CHEF_OR_END                DATE,
 CHEF_OR_NAME               VARCHAR2(30)  NOT NULL,
 CHEF_OR_ADDR               VARCHAR2(200) NOT NULL,
 CHEF_OR_TEL                NUMBER(10)    NOT NULL,
 CHEF_ID                    VARCHAR2(6)   NOT NULL,
 CONSTRAINT CHEF_ORDER_FK FOREIGN KEY (CHEF_ID) REFERENCES CHEF (CHEF_ID),
 PRIMARY KEY (CHEF_OR_ID)
);

DROP SEQUENCE CHEF_ORDER_SEQ;

CREATE SEQUENCE CHEF_ORDER_SEQ
NOMAXVALUE 
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

INSERT INTO CHEF_ORDER(CHEF_OR_ID,CHEF_OR_STATUS,CHEF_OR_START,CHEF_OR_SEND,CHEF_OR_NAME,CHEF_OR_ADDR,CHEF_OR_TEL,CHEF_ID)
VALUES('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(CHEF_ORDER_SEQ.NEXTVAL),6,'0'),'o1',SYSDATE,TO_DATE('2019-03-29'),'Tom','桃園市中壢區中大路305號',0910889868,'C00004');
INSERT INTO CHEF_ORDER(CHEF_OR_ID,CHEF_OR_STATUS,CHEF_OR_START,CHEF_OR_SEND,CHEF_OR_NAME,CHEF_OR_ADDR,CHEF_OR_TEL,CHEF_ID)
VALUES('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(CHEF_ORDER_SEQ.NEXTVAL),6,'0'),'o1',SYSDATE,TO_DATE('2019-03-29'),'Sara','桃園市中壢區中大路307號',0910889869,'C00005');
-----------------------------------------------------------------------------------------------------------------------------------
--建立表格:主廚食材訂單明細--
CREATE TABLE CHEF_OD_DETAIL (
 CHEF_OR_ID                  VARCHAR2(17)   NOT NULL,
 FOOD_SUP_ID                 VARCHAR2(6)    NOT NULL,
 FOOD_ID                     VARCHAR2(6)    NOT NULL,
 CHEF_OD_QTY                 NUMBER(4)      NOT NULL,
 CHEF_OD_STOTAL              NUMBER(7)      NOT NULL,
 CONSTRAINT CHEF_OD_DETAIL_FK1 FOREIGN KEY (CHEF_OR_ID)          REFERENCES CHEF_ORDER (CHEF_OR_ID),
 CONSTRAINT CHEF_OD_DETAIL_FK2 FOREIGN KEY (FOOD_SUP_ID,FOOD_ID) REFERENCES FOOD_MALL (FOOD_SUP_ID,FOOD_ID),
 CONSTRAINT CHEF_OD_DETAIL_PK  PRIMARY KEY (CHEF_OR_ID,FOOD_SUP_ID,FOOD_ID)
);

DROP SEQUENCE CHEF_OD_DETAIL_SEQ;

CREATE SEQUENCE CHEF_OD_DETAIL_SEQ
NOMAXVALUE 
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

INSERT INTO CHEF_OD_DETAIL(CHEF_OR_ID,FOOD_SUP_ID,FOOD_ID,CHEF_OD_QTY,CHEF_OD_STOTAL)
VALUES('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(CHEF_ORDER_SEQ.CURRVAL),6,'0'),'C00006','F00015',1,350);
INSERT INTO CHEF_OD_DETAIL(CHEF_OR_ID,FOOD_SUP_ID,FOOD_ID,CHEF_OD_QTY,CHEF_OD_STOTAL)
VALUES('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(CHEF_ORDER_SEQ.CURRVAL),6,'0'),'C00006','F00016',1,369);
INSERT INTO CHEF_OD_DETAIL(CHEF_OR_ID,FOOD_SUP_ID,FOOD_ID,CHEF_OD_QTY,CHEF_OD_STOTAL)
VALUES('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(CHEF_ORDER_SEQ.CURRVAL),6,'0'),'C00006','F00024',1,349);
INSERT INTO CHEF_OD_DETAIL(CHEF_OR_ID,FOOD_SUP_ID,FOOD_ID,CHEF_OD_QTY,CHEF_OD_STOTAL)
VALUES('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(CHEF_ORDER_SEQ.CURRVAL),6,'0'),'C00007','F00016',1,520);
INSERT INTO CHEF_OD_DETAIL(CHEF_OR_ID,FOOD_SUP_ID,FOOD_ID,CHEF_OD_QTY,CHEF_OD_STOTAL)
VALUES('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(CHEF_ORDER_SEQ.CURRVAL),6,'0'),'C00007','F00024',1,310);
INSERT INTO CHEF_OD_DETAIL(CHEF_OR_ID,FOOD_SUP_ID,FOOD_ID,CHEF_OD_QTY,CHEF_OD_STOTAL)
VALUES('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(CHEF_ORDER_SEQ.CURRVAL),6,'0'),'C00007','F00026',1,320);
-----------------------------------------------------------------------------------------------------------------------------------
--建立表格:嚴選套餐--
CREATE TABLE MENU(
 MENU_ID		          VARCHAR2(6)    NOT NULL,
 MENU_NAME	              VARCHAR2 (30)  NOT NULL,
 MENU_RESUME		      CLOB,
 MENU_PIC	              BLOB,
 MENU_STATUS	          VARCHAR2(2)    NOT NULL,
 MENU_PRICE		          NUMBER(6)      NOT NULL,
 PRIMARY KEY (MENU_ID)
);

DROP SEQUENCE MENU_SEQ;

CREATE SEQUENCE MENU_SEQ
NOMAXVALUE 
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'大富豪盆菜','全新年菜佳餚「大富豪盆菜」。傳統的盆菜據傳源自南宋末年，已有數百年的歷史，是廣東、廣州、深圳和香港新界的漢族飲食習俗，代表「闔家團圓」之意。主廚特別選用八頭鮑魚、金蠔、烏參、瑤柱、蘿蔔、白菜、花菇、鴨、雞、蓮藕和油浸筍殼魚，先將所有食材分開滷好備用，再以白菜為底，層層將食材擺上，最後淋上勾芡的雞高湯，味道馥郁而香濃。','M1',16800);
INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'故宮晶華圍爐宴','「故宮晶華圍爐宴」，結合6道精緻開胃前菜、2道主菜和1盅故宮御品佛跳牆，相當澎湃美味。另有推出以5款珍稀食材入饌的「五福臨門金元寶」手工水餃，還有「白玉鳳眼」和「乾坤燒蹄」2款阿舍宴的經典菜色。','M1',6888);
INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'開富貴團圓宴','「花開富貴團圓宴」外帶年菜。不僅有集結8道佳餚的「花開富貴團圓宴－圍爐外帶年菜套餐」（6人份），每套售價$8,888元；更獨家推出限量的「土雞瑤柱燉排翅」和古法秘製的「老雞上湯佛跳牆」。','M1',8888);
INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'耶誕跨年饗宴套餐','耶誕跨年饗宴套餐有「義式低溫爐烤香料無骨春雞」、「海鮮總匯盤」及「經典羅西尼鴨肝菲力牛排」等精彩主菜可供選擇，配上「炙燒干貝佐松葉蟹黃瓜」、「雪梨牛尾湯」及「酥炸杜蘭麥粉鮮章魚沙拉」、「藍莓珍珠塔 焦糖冰淇淋」等美味配餐！','M1',7900);
INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'超值雙人聖誕跨年套餐','＜開胃菜＞ 檸香起司泡芙搭烏魚子、旨味鮪魚塔塔配醃漬檸檬、帝戎豬肉搭檸檬薄片＜冷前菜＞ 日本生食干貝沙拉襯蘭嶼飛魚卵＜熱前菜＞ 酥炸白酒風味小雞翅、佐辣巧克力和彩色胡椒＜湯品＞ 雞肉奶油濃湯、佐水波蛋和米蒙雷特起司＜主餐＞ 香煎10盎司美國牛排 佐菠菜酸奶、油封蒜和季節時蔬 干邑風味燻鮭魚奶油義大利麵＜甜品＞ 莫瑞爾櫻桃醬奶酪、錫蘭紅茶風味磅蛋糕，任選一種。','M1',5000);
INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'原木燒烤情人甜蜜套餐','在2月14日西洋情人節當天晚上，推出『原木燒烤情人甜蜜套餐』，師傅手工現烤裸麥麵包，主廚精選整隻波士頓龍蝦、8oz牛肩胛菲力，鮮嫩多汁又肉質Q彈的波士頓龍蝦，以及選用來自加州頂級的自然牛Brant Beef，無任何人工飼養。這次選用的牛肩胛菲力部位，肉質油脂分佈均勻，牛肉風味純淨，天然原味的頂級口感，加以主廚精心烹調的五分熟鎖住肉汁精華，以及原木燒烤的原始煙燻香氣，佐以海鹽調味，即可嚐到原味的完美享受!','M1',5200);
INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'豪華海陸雙重奏雙人餐','想吃牛排又想啖海鮮的情侶們可以選擇「豪華海陸雙重奏雙人套餐」，特別選用鮮嫩多汁的澳洲9+頂級和牛紐約客，搭配波士頓活龍蝦、南非鮑魚、溫生蠔等頂級海鮮，搭配「香煎北海道干貝」、「鮑魚白花椰菜濃湯」、「千層甜甜圈」等豐富配餐，讓喜歡吃牛排的您也能啖海鮮。','M1',7000);
INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'太平洋中秋燒烤套餐','最新推出以優質海鮮為主題的燒烤套餐，包括白珍珠生蠔、波士頓龍蝦、太平洋蟹及各式海鮮燒烤串，配上香醇葡萄酒，為您營造一個完美又難忘的餐饗體驗。','M1',7777);
INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'愛的烤肉套餐組合','由醃製無骨牛排、醃製小牛排、原味無骨牛排和原味牛肉片共同組成。客人可以同時享用醃製和原味兩種完全不同風味的肉品，豆腐煲可根據個人喜好任選。','M1',4520);
INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD(TO_CHAR(MENU_SEQ.NEXTVAL),5,'0'),'京都懷石套餐','美饌之都 演繹佳餚，宮廷園藝造景的前菜作開場，色、味、感多重享受，『崇尚初心』的懷石料理，簡單調味，完美呈現，彷彿置身於京都舊址，感受歷史人文的風土軼事與美饌佳餚的演繹感動。','M1',6800);

INSERT INTO MENU (MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE) 
VALUES ('M'||LPAD((MENU_SEQ.NEXTVAL),5,'0'),'粵式套餐','大家在家中即可享用豐盛美食。行政總主廚保留傳統粵菜風味，精巧烹調頂級食材，推出「粵式套餐」。','M1',12000);

INSERT INTO MENU (MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES ('M'||LPAD((MENU_SEQ.NEXTVAL),5,'0'),'六六大順','包含五福錦繡拼盤、洋蔥桂花龍蝦、紅燒無錫大肋排、清蒸野生年年餘、浦燒鰻櫻花蝦米糕、蠔皇鮑魚烏參虎掌和頂級黑鑽養生雞湯組。','M1',8888);

INSERT INTO MENU(MENU_ID,MENU_NAME,MENU_RESUME,MENU_STATUS,MENU_PRICE)
VALUES('M'||LPAD((MENU_SEQ.NEXTVAL),5,'0'),'鴻運呈祥','內含「烏魚子大拼盤」、「寧式東坡肉」、「櫻花蝦珍珠」、「清蒸石斑魚」和「乾隆佛跳牆」5道菜色；還有9道膾炙人口的單點菜色，其中最受歡迎的「寧式東坡肉」（另附餅皮），醬濃肉嫩入味、似肥而不膩。','M0',8888);




---------------------------------------------------------------------------------------------------------------------------------
--建立表格:推播通知--
CREATE TABLE BROADCAST(
 BROADCAST_ID		          VARCHAR2(6)    NOT NULL,	
 BROADCAST_START		      TIMESTAMP,
 BROADCAST_CON		          VARCHAR2(100),
 BROADCAST_STATUS		      VARCHAR2(2)    NOT NULL,  
 CUST_ID		              VARCHAR2(6)    NOT NULL,
 CONSTRAINT BROADCAST_FK FOREIGN KEY (CUST_ID) REFERENCES CUST (CUST_ID),
 PRIMARY KEY(BROADCAST_ID)
);

--DROP SEQUENCE BROADCAST_SEQ;

CREATE SEQUENCE BROADCAST_SEQ
NOMAXVALUE 
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

INSERT INTO BROADCAST(BROADCAST_ID,BROADCAST_START,BROADCAST_CON,BROADCAST_STATUS,CUST_ID) 
VALUES('B'||LPAD(TO_CHAR(BROADCAST_SEQ.NEXTVAL),5,'0'),SYSDATE,'NO1:訂單推播通知；您所訂購的嚴選套餐訂單已通過審核','B0','C00001');
INSERT INTO BROADCAST(BROADCAST_ID,BROADCAST_START,BROADCAST_CON,BROADCAST_STATUS,CUST_ID) 
VALUES('B'||LPAD(TO_CHAR(BROADCAST_SEQ.NEXTVAL),5,'0'),SYSDATE,'NO1:訂單推播通知；您所訂購的嚴選套餐訂單未通過審核','B0','C00001');
INSERT INTO BROADCAST(BROADCAST_ID,BROADCAST_START,BROADCAST_CON,BROADCAST_STATUS,CUST_ID) 
VALUES('B'||LPAD(TO_CHAR(BROADCAST_SEQ.NEXTVAL),5,'0'),SYSDATE,'NO2:訂單推播通知；您所訂購的節慶主題套餐訂單中套餐已出貨','B0','C00002');
INSERT INTO BROADCAST(BROADCAST_ID,BROADCAST_START,BROADCAST_CON,BROADCAST_STATUS,CUST_ID) 
VALUES('B'||LPAD(TO_CHAR(BROADCAST_SEQ.NEXTVAL),5,'0'),SYSDATE,'NO3:訂單推播通知；您所訂購的嚴選食材訂單中食材已出貨','B0','C00003');
----------------------------------------------------------------------------------------------------------------------------------


