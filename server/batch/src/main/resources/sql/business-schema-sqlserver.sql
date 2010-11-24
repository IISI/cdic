CREATE TABLE TABLEFLOW (
    CDICFILESTATUS SMALLINT,
    CUSTDATE DATETIME,
    STARTER VARCHAR(12),
    INITUSERID VARCHAR(12),
    INITDATETIME DATETIME,
    INITSTATUS CHAR(1)
);

CREATE TABLE CDICFILESTS (
    FILENO CHAR(3) PRIMARY KEY,
    FILEGROUP CHAR(1),
    SUBFILE CHAR(11),
    FILENAME NVARCHAR(15),
    STATUS CHAR(1),
    CONFIRMER VARCHAR(12),
    CONFIRMDATETIME DATETIME
);

CREATE TABLE FILEDEPEND (
    FILENO CHAR(3),
    DEPENDENCY CHAR(15),
    DEPTYPE CHAR(8),
    CONSTRAINT PK_FILEDEPEND PRIMARY KEY (FILENO, DEPENDENCY, DEPTYPE)
);

CREATE TABLE HOSTFILESTS (
    NAME VARCHAR(15) PRIMARY KEY,
    RECLEN INT,
    HOSTDATETIME DATETIME,
    COPYDATETIME DATETIME,
    PROCESSUSER VARCHAR(12),
    STATUS CHAR(1)
);

CREATE TABLE LOCALFILESTS (
    NAME VARCHAR(15) PRIMARY KEY,
    UPLOADDATETIME DATETIME,
    PROCESSUSER VARCHAR(12),
    COLUMNCOUNT INT,
    STATUS CHAR(1)
);

CREATE TABLE LOCAL_BUS (
    CUST_NUMB CHAR(9),
    CUST_TYPE CHAR(1),
    NATNID_REGNNUMB CHAR(20),
    CUST_TITL_LINE1 CHAR(34),
    DSCT_DESC_L CHAR(27),
    BIRTHDAY CHAR(8),
    BRANCH CHAR(4),
    DATE_ESTB CHAR(10),
    CUST_STAT CHAR(3),
    BIZ_TYPE CHAR(6),
    CUST_ASSNNATID CHAR(10),
    CUST_ASSNNAME CHAR(34)
);

CREATE TABLE LOCAL_LUS (
    ACCT_NO VARCHAR(14),
    UNINO VARCHAR(11),
    PROD_NAME VARCHAR(4),
    BALANCE DECIMAL(13,2),
    INTEREST DECIMAL(7,5),
    STATUS VARCHAR(10),
    NAME NVARCHAR(100),
    NEW_B_CODE VARCHAR(6),
    PERSON_TYPE VARCHAR(1),
    COMPANY VARCHAR(3),
    COMM_ADR NVARCHAR(100)
);

CREATE TABLE A11 (
    CUSTUNIT CHAR(3),
    CUSTBRNO CHAR(4),
    CUSTID CHAR(11),
    CUSTIDNO CHAR(3),
    CUSTHEADID CHAR(11) PRIMARY KEY,
    CUSTCNAME CHAR(60),
    CUSTBIRDATE DATETIME,
    CUSTCEOCODE CHAR(11),
    CUSTCEONAME CHAR(60),
    CUSTSTACODE CHAR(4),
    CUSTBUSCODE CHAR(6),
    CUSTCRTDATE DATETIME,
    CUSTOADDRESS CHAR(80),
    CUSTADDRESS CHAR(80),
    CUSTTEL1 CHAR(17),
    CUSTTEL2 CHAR(17),
    CUSTEMAILADD CHAR(40)
);

CREATE TABLE A21 (
    PBUNIT CHAR(3),
    PBBRNO CHAR(4),
    PBSRNO CHAR(30),
    PBAPNO CHAR(12),
    PBCHARCODE CHAR(8),
    PBSTATUS CHAR(4),
    PBCUSTID CHAR(11),
    CUSTIDNO CHAR(4),
    PBCUSTTYPE CHAR(3),
    PBOPENDATE CHAR(8),
    PBCURCODE CHAR(3),
    PBACTBAL DECIMAL(14,2),
    PBBAL DECIMAL(14,2),
    PBSTOPPAYAMT DECIMAL(14,2),
    PBCARDAMT DECIMAL(14,2),
    PBGSACTCODE CHAR(1),
    PBJOINTCODE CHAR(1),
    PBRATETYPE CHAR(16),
    PBINTRATE DECIMAL(7,5),
    PBINTPAYABLE DECIMAL(14,2),
    PBOVRSTATUS CHAR(1),
    PBTAXCODE CHAR(1),
    PBGROSSINT DECIMAL(14,2),
    PBGROSSTAX DECIMAL(14,2),
    PBLASTTXDATE CHAR(8),
    CONSTRAINT PK_A21 PRIMARY KEY (PBSRNO, PBCURCODE)
);

CREATE INDEX IDX_A21 ON A21 (PBCUSTID, PBSRNO);

CREATE TABLE B21 (
    PBUNIT CHAR(3),
    PBBRNO CHAR(4),
    PBSRNO CHAR(30),
    PBAPNO CHAR(12),
    PBCHARCODE CHAR(8),
    PBSTATUS CHAR(4),
    PBCUSTID CHAR(11),
    CUSTIDNO CHAR(4),
    PBCUSTTYPE CHAR(3),
    PBOPENDATE CHAR(8),
    PBCURCODE CHAR(3),
    PBACTBAL DECIMAL(14,2),
    PBBAL DECIMAL(14,2),
    PBSTOPPAYAMT DECIMAL(14,2),
    PBCARDAMT DECIMAL(14,2),
    PBGSACTCODE CHAR(1),
    PBJOINTCODE CHAR(1),
    PBRATETYPE CHAR(16),
    PBINTRATE DECIMAL(7,5),
    PBINTPAYABLE DECIMAL(14,2),
    PBOVRSTATUS CHAR(1),
    PBTAXCODE CHAR(1),
    PBGROSSINT DECIMAL(14,2),
    PBGROSSTAX DECIMAL(14,2),
    PBLASTTXDATE CHAR(8),
    CONSTRAINT PK_B21 PRIMARY KEY (PBSRNO, PBCURCODE)
);

CREATE INDEX IDX_B21 ON B21 (PBCUSTID, PBSRNO);

CREATE TABLE C21 (
    PBUNIT CHAR(3),
    PBBRNO CHAR(4),
    PBSRNO CHAR(30),
    PBAPNO CHAR(12),
    PBCHARCODE CHAR(8),
    PBSTATUS CHAR(4),
    PBCUSTID CHAR(11),
    CUSTIDNO CHAR(4),
    PBCUSTTYPE CHAR(3),
    PBOPENDATE CHAR(8),
    PBCURCODE CHAR(3),
    PBACTBAL DECIMAL(14,2),
    PBBAL DECIMAL(14,2),
    PBSTOPPAYAMT DECIMAL(14,2),
    PBCARDAMT DECIMAL(14,2),
    PBGSACTCODE CHAR(1),
    PBJOINTCODE CHAR(1),
    PBRATETYPE CHAR(16),
    PBINTRATE DECIMAL(7,5),
    PBINTPAYABLE DECIMAL(14,2),
    PBOVRSTATUS CHAR(1),
    PBTAXCODE CHAR(1),
    PBGROSSINT DECIMAL(14,2),
    PBGROSSTAX DECIMAL(14,2),
    PBLASTTXDATE CHAR(8),
    CONSTRAINT PK_C21 PRIMARY KEY (PBSRNO, PBCURCODE)
);

CREATE TABLE A22 (
    TDUNIT CHAR(3),
    TDBRNO CHAR(4),
    TDSRNO CHAR(30),
    TDAPNO CHAR(12),
    TDCHARCODE CHAR(8),
    TDSTATUS CHAR(4),
    TDCUSTID CHAR(11),
    TDCUSTIDNO CHAR(3),
    TDCUSTTYPE CHAR(3),
    TDSLIPNO CHAR(16),
    TDCURCODE CHAR(3),
    TDAMT DECIMAL(14,2),
    TDSTOPPAYAMT DECIMAL(14,2),
    TDBGNDATE CHAR(8),
    TDDUEDATE CHAR(8),
    TDRATETYPE CHAR(16),
    TDPERIOD CHAR(3),
    TDINTTYPE CHAR(1),
    TDNAMECODE CHAR(1),
    TDINTRATE DECIMAL(7,5),
    TDINTPAYCODE CHAR(1),
    TDAUTOPRIM CHAR(1),
    TDAUTOINTNO CHAR(30),
    TDISUEDATE CHAR(8),
    TDREISUEDATE CHAR(8),
    TDGSACTCODE CHAR(1),
    TDJOINTCODE CHAR(1),
    TDSDCASE CHAR(19),
    TDINTEDATE CHAR(8),
    TDINTPAY DECIMAL(14,2),
    TDINTPAYABLE DECIMAL(14,2),
    TDVIOLATEAMT DECIMAL(14,2),
    TDPGKIND CHAR(1),
    TDPGAMT DECIMAL(14,2),
    TDPGSETDATE CHAR(8),
    TDTAXCODE CHAR(1),
    TDGROSSINT DECIMAL(14,2),
    TDGROSSTAX DECIMAL(14,2),
    TDLASTTXDATE CHAR(8)
);

CREATE INDEX IDX_A22 ON A22 (TDCUSTID, TDSRNO);

CREATE TABLE B22 (
    TDUNIT CHAR(3),
    TDBRNO CHAR(4),
    TDSRNO CHAR(30),
    TDAPNO CHAR(12),
    TDCHARCODE CHAR(8),
    TDSTATUS CHAR(4),
    TDCUSTID CHAR(11),
    TDCUSTIDNO CHAR(3),
    TDCUSTTYPE CHAR(3),
    TDSLIPNO CHAR(16),
    TDCURCODE CHAR(3),
    TDAMT DECIMAL(14,2),
    TDSTOPPAYAMT DECIMAL(14,2),
    TDBGNDATE CHAR(8),
    TDDUEDATE CHAR(8),
    TDRATETYPE CHAR(16),
    TDPERIOD CHAR(3),
    TDINTTYPE CHAR(1),
    TDNAMECODE CHAR(1),
    TDINTRATE DECIMAL(7,5),
    TDINTPAYCODE CHAR(1),
    TDAUTOPRIM CHAR(1),
    TDAUTOINTNO CHAR(30),
    TDISUEDATE CHAR(8),
    TDREISUEDATE CHAR(8),
    TDGSACTCODE CHAR(1),
    TDJOINTCODE CHAR(1),
    TDSDCASE CHAR(19),
    TDINTEDATE CHAR(8),
    TDINTPAY DECIMAL(14,2),
    TDINTPAYABLE DECIMAL(14,2),
    TDVIOLATEAMT DECIMAL(14,2),
    TDPGKIND CHAR(1),
    TDPGAMT DECIMAL(14,2),
    TDPGSETDATE CHAR(8),
    TDTAXCODE CHAR(1),
    TDGROSSINT DECIMAL(14,2),
    TDGROSSTAX DECIMAL(14,2),
    TDLASTTXDATE CHAR(8)
);

CREATE INDEX IDX_B22 ON B22 (TDCUSTID, TDSRNO);

CREATE TABLE C22 (
    TDUNIT CHAR(3),
    TDBRNO CHAR(4),
    TDSRNO CHAR(30),
    TDAPNO CHAR(12),
    TDCHARCODE CHAR(8),
    TDSTATUS CHAR(4),
    TDCUSTID CHAR(11),
    TDCUSTIDNO CHAR(3),
    TDCUSTTYPE CHAR(3),
    TDSLIPNO CHAR(16),
    TDCURCODE CHAR(3),
    TDAMT DECIMAL(14,2),
    TDSTOPPAYAMT DECIMAL(14,2),
    TDBGNDATE CHAR(8),
    TDDUEDATE CHAR(8),
    TDRATETYPE CHAR(16),
    TDPERIOD CHAR(3),
    TDINTTYPE CHAR(1),
    TDNAMECODE CHAR(1),
    TDINTRATE DECIMAL(7,5),
    TDINTPAYCODE CHAR(1),
    TDAUTOPRIM CHAR(1),
    TDAUTOINTNO CHAR(30),
    TDISUEDATE CHAR(8),
    TDREISUEDATE CHAR(8),
    TDGSACTCODE CHAR(1),
    TDJOINTCODE CHAR(1),
    TDSDCASE CHAR(19),
    TDINTEDATE CHAR(8),
    TDINTPAY DECIMAL(14,2),
    TDINTPAYABLE DECIMAL(14,2),
    TDVIOLATEAMT DECIMAL(14,2),
    TDPGKIND CHAR(1),
    TDPGAMT DECIMAL(14,2),
    TDPGSETDATE CHAR(8),
    TDTAXCODE CHAR(1),
    TDGROSSINT DECIMAL(14,2),
    TDGROSSTAX DECIMAL(14,2),
    TDLASTTXDATE CHAR(8)
);

CREATE TABLE A23 (
    CKUNIT CHAR(3),
    CKBRNO CHAR(4),
    CKSRNO CHAR(30),
    CKAPNO CHAR(12),
    CKCHARCODE CHAR(8),
    CKSTATUS CHAR(4),
    CKCUSTID CHAR(11),
    CKCUSTIDNO CHAR(3),
    CKCUSTTYPE CHAR(3),
    CKOPENDATE CHAR(8),
    CKCURCODE CHAR(3),
    CKACTBAL DECIMAL(14,2),
    CKSTOPPAYAMT DECIMAL(14,2),
    CKJOINTCODE CHAR(1),
    CKOVRSTATUS CHAR(1),
    CKLASTTXDATE CHAR(8)
);

CREATE INDEX IDX_A23 ON A23 (CKCUSTID, CKSRNO);

CREATE TABLE A24 (
    STUNIT CHAR(3),
    STBRNO CHAR(4),
    STCTLSRNO CHAR(30),
    STSRNO CHAR(30),
    STAPNO CHAR(12),
    STCHARCODE CHAR(8),
    STCUSTID CHAR(11),
    CKCUSTIDNO CHAR(3),
    STCUSTNAME CHAR(60),
    STCUSTBUSCODE CHAR(6),
    STCUSTTYPE CHAR(3),
    STCURCODE CHAR(3),
    STBAL DECIMAL(14,2),
    STRATETYPE CHAR(16),
    STINTRATE DECIMAL(7,5),
    STINTPAYABLE DECIMAL(14,2),
    STTAXCODE CHAR(1),
    STGROSSINT DECIMAL(14,2),
    STGROSSTAX DECIMAL(14,2),
    STOADDRESS CHAR(80),
    STADDRESS CHAR(80),
    STTEL1 CHAR(16),
    STTEL2 CHAR(16)
);

CREATE INDEX IDX_A24 ON A24 (STCUSTID, STSRNO);

CREATE TABLE A26 (
    OTUNIT CHAR(3),
    OTBRNO CHAR(4),
    OTAPNO CHAR(12),
    OTSRNO CHAR(30),
    OTCUSTID CHAR(11),
    OTCUSTIDNO CHAR(3),
    OTCHECKNO CHAR(7),
    OTCURCODE CHAR(3),
    OTPAYSAV DECIMAL(14,2),
    OTINTPAYABLE CHAR(8),
    OTINTPAYMEMO CHAR(60)
);

CREATE TABLE B26 (
    OTUNIT CHAR(3),
    OTBRNO CHAR(4),
    OTAPNO CHAR(12),
    OTSRNO CHAR(30),
    OTCUSTID CHAR(11),
    OTCUSTIDNO CHAR(3),
    OTCHECKNO CHAR(7),
    OTCURCODE CHAR(3),
    OTPAYSAV DECIMAL(14,2),
    OTINTPAYABLE CHAR(8),
    OTINTPAYMEMO CHAR(60)
);

CREATE TABLE C26 (
    OTUNIT CHAR(3),
    OTBRNO CHAR(4),
    OTAPNO CHAR(12),
    OTSRNO CHAR(30),
    OTCUSTID CHAR(11),
    OTCUSTIDNO CHAR(3),
    OTCHECKNO CHAR(7),
    OTCURCODE CHAR(3),
    OTPAYSAV DECIMAL(14,2),
    OTINTPAYABLE CHAR(8),
    OTINTPAYMEMO CHAR(60)
);

CREATE TABLE T02 (
    ACCT CHAR(12),
    IBCODE CHAR(5),
    DESCRIPTION CHAR(40)
);

CREATE INDEX IDX_T02 ON T02 (ACCT, IBCODE);

CREATE TABLE T06 (
    CODE CHAR(16),
    DESCRIPTION CHAR(40)
);

CREATE TABLE A73 (
    UNIT CHAR(3),
    BRANCH_NO CHAR(4),
    SR_NO CHAR(30),
    DEPOSIT_RECEIPT_NO CHAR(16),
    TXN_DATE CHAR(8),
    SER_NO CHAR(2),
    TXN_REASON CHAR(2),
    CURRENCY_CODE CHAR(3),
    HOLD_AMT DECIMAL(14,2),
    MEMO CHAR(40)
);

CREATE TABLE A74 (
    UNIT CHAR(3),
    BRNO CHAR(4),
    CURCODE CHAR(3),
    RATETYPE CHAR(16), 
    RTYPE CHAR(1),
    PERIOD CHAR(3),
    LARGEMAX DECIMAL(18,2),
    EFFDATE CHAR(8),
    RATE DECIMAL(18,6)
);

CREATE TABLE CDICF20 (
    UNIT CHAR(3),
    BRANCH_NO CHAR(4),
    CURRENCY_CODE CHAR(3),
    RATE_DATE CHAR(8),
    TRANS_RATE DECIMAL(12,6)
);
