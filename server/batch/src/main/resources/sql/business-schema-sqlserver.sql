CREATE TABLE TABLEFLOW (
    CDICFILESTATUS SMALLINT,
    CUSTDATE DATETIME,
    STARTER VARCHAR(12),
    INITUSERID VARCHAR(12),
    INITDATETIME DATETIME,
    INITSTATUS CHAR(1)
);

CREATE TABLE CDICFILESTS (
    FILENO CHAR(10) PRIMARY KEY,
    FILEGROUP CHAR(1),
    SUBFILE CHAR(11),
    FILENAME NVARCHAR(15),
    STATUS CHAR(1),
    EXECUTOR VARCHAR(12),
    EXECUTEDATETIME DATETIME,
    CONFIRMER VARCHAR(12),
    CONFIRMDATETIME DATETIME,
    FILEDESC VARCHAR(256)
);

CREATE TABLE FILEDEPEND (
    FILENO CHAR(10),
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
    STATUS CHAR(1),
    FILEDESC VARCHAR(256)
);

CREATE TABLE LOCALFILESTS (
    NAME VARCHAR(15) PRIMARY KEY,
    UPLOADDATETIME DATETIME,
    PROCESSUSER VARCHAR(12),
    COLUMNCOUNT INT,
    STATUS CHAR(1),
    FILEDESC VARCHAR(256)
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

CREATE INDEX IDX_BUS_NATNID ON LOCAL_BUS (NATNID_REGNNUMB);
CREATE INDEX IDX_BUS_CUSTNUMB ON LOCAL_BUS (CUST_NUMB);

CREATE TABLE LOCAL_LUS (
    ACCT_NO VARCHAR(20),
    UNINO VARCHAR(11),
    PROD_NAME VARCHAR(4),
    BALANCE DECIMAL(13,2),
    INTEREST DECIMAL(7,5),
    STATUS VARCHAR(15),
    NAME NVARCHAR(100),
    NEW_B_CODE VARCHAR(6),
    PERSON_TYPE VARCHAR(1),
    COMPANY VARCHAR(3),
    COMM_ADR NVARCHAR(100),
    BRANCH_CODE VARCHAR(3),
    BRANCH_NAME VARCHAR(50),
    CURR_CODE VARCHAR(4),
    CURR_NAME VARCHAR(10),
    CLOSED_BY CHAR(3),
    B_CODE VARCHAR(6),
    LEGAL_ADR NVARCHAR(100),
    TEL_NO_1 VARCHAR(20),
    TEL_NO_2 VARCHAR(20),
    OBU_DBU VARCHAR(3)
);

CREATE TABLE A11 (
    CUSTUNIT CHAR(3),
    CUSTBRNO CHAR(4),
    CUSTID CHAR(11) PRIMARY KEY,
    CUSTIDNO CHAR(3),
    CUSTHEADID CHAR(11),
    CUSTCNAME CHAR(60),
    CUSTBIRDATE CHAR(8),
    CUSTCEOCODE CHAR(11),
    CUSTCEONAME CHAR(60),
    CUSTSTACODE CHAR(4),
    CUSTBUSCODE CHAR(6),
    CUSTCRTDATE CHAR(10),
    CUSTOADDRESS CHAR(80),
    CUSTADDRESS CHAR(80),
    CUSTTEL1 CHAR(17),
    CUSTTEL2 CHAR(17),
    CUSTEMAILADD CHAR(40)
);

CREATE TABLE A11A (
    CUSTUNIT CHAR(3),
    CUSTBRNO CHAR(4),
    CUSTID CHAR(11),
    CUSTIDNO CHAR(3),
    CUSTHEADID CHAR(11),
    CUSTCNAME CHAR(60),
    CUSTBIRDATE CHAR(8),
    CUSTCEOCODE CHAR(11),
    CUSTCEONAME CHAR(60),
    CUSTSTACODE CHAR(4),
    CUSTBUSCODE CHAR(6),
    CUSTCRTDATE CHAR(10),
    CUSTOADDRESS CHAR(80),
    CUSTADDRESS CHAR(80),
    CUSTTEL1 CHAR(17),
    CUSTTEL2 CHAR(17),
    CUSTEMAILADD CHAR(40),
    CUSTSRNO CHAR(30)
);

CREATE TABLE A21 (
    PBUNIT CHAR(3),
    PBBRNO CHAR(4),
    PBSRNO CHAR(30),
    PBAPNO CHAR(12),
    PBCHARCODE CHAR(8),
    PBSTATUS CHAR(4),
    PBCUSTID CHAR(11),
    CUSTIDNO CHAR(3),
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
    CUSTIDNO CHAR(3),
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
    CUSTIDNO CHAR(3),
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

CREATE INDEX IDX_C21 ON C21 (PBCUSTID, PBSRNO);

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

CREATE INDEX IDX_C22 ON C22 (TDCUSTID, TDSRNO);

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
    OTINTPAYMEMO CHAR(38),
    OTCOMPCODE CHAR(4),
    OTRCCODE CHAR(10),
    OTREFNO CHAR(8)
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
    OTINTPAYMEMO CHAR(38),
    OTCOMPCODE CHAR(4),
    OTRCCODE CHAR(10),
    OTREFNO CHAR(8)
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
    OTINTPAYMEMO CHAR(38),
    OTCOMPCODE CHAR(4),
    OTRCCODE CHAR(10),
    OTREFNO CHAR(8)
);

CREATE TABLE A26_FO (
    OTAPNO CHAR(12),
    OTCOMPCODE CHAR(4),
    OTRCCODE CHAR(10),
    OTREFNO CHAR(8)
);

CREATE TABLE B26_FO (
    OTAPNO CHAR(12),
    OTCOMPCODE CHAR(4),
    OTRCCODE CHAR(10),
    OTREFNO CHAR(8)
);

CREATE TABLE C26_FO (
    OTAPNO CHAR(12),
    OTCOMPCODE CHAR(4),
    OTRCCODE CHAR(10),
    OTREFNO CHAR(8)
);

CREATE TABLE A34 (
    UNIT CHAR(3),
    BRANCH_NO CHAR(4),
    SR_NO CHAR(30),
    AP_NO CHAR(12),
    APPLY_DATE CHAR(8),
    APPLY_KIND CHAR(1),
    BILL_NO CHAR(7),
    CURRENCY_CODE CHAR(3),
    BILL_AMT DECIMAL(15,2),
    BILL_DATE CHAR(8),
    ACC_NAME CHAR(60)
);

CREATE INDEX IDX_A34 ON A34 (SR_NO, BILL_NO, CURRENCY_CODE);

CREATE TABLE A41(
    UNIT CHAR(3),
    BRANCH_NO CHAR(4),
    SR_NO CHAR(30),
    SR_SUB_NO CHAR(16),
    AP_NO CHAR(12),
    COLA CHAR(1),
    CHAR_CODE CHAR(8),
    STATUS CHAR(2),
    CUST_ID CHAR(11),
    GOV_CODE CHAR(2),
    REPLY_NO CHAR(16),
    FIRST_LOAN_DATE CHAR(8),
    LOAN_BEGIN_DATE CHAR(8),
    DUE_DATE CHAR(8),
    CURRENCY_CODE CHAR(3),
    AMT DECIMAL(15,2),
    CURRENT_BALANCE DECIMAL(15,2),
    RATE_TYPE CHAR(16),
    RATE_ADJUST_SIGN CHAR(1),
    RATE_ADJUST DECIMAL(7,5),
    CURRENT_RATE DECIMAL(7,5),
    INT_KIND CHAR(1),
    INT_CYCLE CHAR(3),
    LAST_INT CHAR(8),
    NEXT_INT CHAR(8),
    PAY_INT_TYPE CHAR(2),
    PAT_START CHAR(8),
    PAY_ACCT_NO CHAR(30),
    INT_RECEIVABLE DECIMAL(15,2),
    OVER_CODE CHAR(1),
    OWE_INT DECIMAL(15,2),
    PENALTY DECIMAL(15,2),
    OWE_LAW_FEE DECIMAL(15,2),
    TEMP_AMT DECIMAL(15,2),
    EVL_RANK CHAR(1),
    OVERDUE_DATE CHAR(8),
    WRITEOFF_DATE CHAR(8),
    WRITEOFF_AMT DECIMAL(15,2)
);

CREATE INDEX IDX_A41 ON A41 (SR_NO, SR_SUB_NO, AP_NO, CURRENCY_CODE);

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
    MEMO CHAR(40),
    PKEY CHAR(256)
);

CREATE TABLE A74 (
    PKEY CHAR(50),
    UNIT CHAR(3),
    BRNO CHAR(4),
    CURCODE CHAR(3),
    RATETYPE CHAR(16), 
    RTYPE CHAR(1),
    PERIOD CHAR(3),
    LARGEMAX INT,
    EFFDATE CHAR(8),
    RATE DECIMAL(18,6),
    CONSTRAINT PK_A74 PRIMARY KEY (CURCODE, RATETYPE, RTYPE, PERIOD, LARGEMAX, EFFDATE)
);

CREATE TABLE CDICF20 (
    UNIT CHAR(3),
    BRANCH_NO CHAR(4),
    CURRENCY_CODE CHAR(3),
    RATE_DATE CHAR(8),
    TRANS_RATE DECIMAL(12,6)
);

CREATE TABLE A76 (
    UNIT CHAR(3),
    BRANCH_NO CHAR(4),
    SR_NO CHAR(30),
    AP_NO CHAR(12),
    CUST_ID CHAR(11),
    A76_TYPE CHAR(1),
    START_NO CHAR(7),
    END_NO CHAR(7),
    CURRENCY_CODE CHAR(3),
    AMT DECIMAL(15,2),
    DUE_DATE CHAR(8),
    CODE CHAR(3),
    DISHONORED_REASON CHAR(2),
    ENTRY_DATE CHAR(8),
    RESERVE_DATE CHAR(8)
);

CREATE TABLE JOINT_ACCOUNT (
    CUST_NUMB CHAR(11),
    ACCT CHAR(30)
);

CREATE TABLE CIF (
    NATNID_REGNNUMB CHAR(20),
    CUST_NUMB CHAR(11),
    CIF CHAR(20),
    JOINT_FLAG CHAR(1),
    JOINT_ID CHAR(20)
);

CREATE TABLE PROVISION (
    CONTRACT_REFERENCE_NO CHAR(16),
    PROVISION2_SECURED DECIMAL(12,4),
    PROVISION3_1_SECURED DECIMAL(12,4),
    PROVISION3_2_SECURED DECIMAL(12,4),
    PROVISION4_SECURED DECIMAL(12,4),
    PROVISION5_SECURED DECIMAL(12,4),
    PROVISION2_CLEAN DECIMAL(12,4),
    PROVISION3_1_CLEAN DECIMAL(12,4),
    PROVISION3_2_CLEAN DECIMAL(12,4),
    PROVISION4_CLEAN DECIMAL(12,4),
    PROVISION5_CLEAN DECIMAL(12,4),
    CONSTRAINT PK_PROVISION PRIMARY KEY (CONTRACT_REFERENCE_NO)
);

CREATE TABLE A44 (
    GULNUNIT CHAR(3),
    GULNBRNO CHAR(4),
    GULNSRNO CHAR(30) NOT NULL,
    GULNSRSUBNO CHAR(16) NOT NULL,
    GULNCUSTID CHAR(11) NOT NULL,
    GUARANTID CHAR(11) NOT NULL,
    GUARANTNAME CHAR(60),
    GUCHARCODE CHAR(1) NOT NULL,
    GURELCODE CHAR(2),
    GUCURCODE CHAR(3) NOT NULL,
    GUAMT DECIMAL(12,2),
    GUEFFDATE CHAR(8),
    CONSTRAINT PK_A44 PRIMARY KEY (GULNSRNO, GULNSRSUBNO, GULNCUSTID, GUARANTID, GUCHARCODE, GUCURCODE)
);

CREATE TABLE GUARANTOR (
    V_CUST_NO CHAR(10),
    V_UDF_6 CHAR(2),
    V_UDF_7 CHAR(2),
    V_UDF_8 CHAR(8),
    V_UDF_9 CHAR(10),
    V_UDF_10 CHAR(3),
    V_UDF_11 DECIMAL(15,5),
    V_UDF_12 CHAR(8),
    V_UDF_13 CHAR(2),
    V_UDF_14 CHAR(2),
    V_UDF_15 CHAR(9),
    V_UDF_16 CHAR(10),
    V_UDF_17 CHAR(3),
    V_UDF_18 DECIMAL(15,5),
    V_UDF_19 CHAR(8),
    V_UDF_20 CHAR(2),
    V_UDF_21 CHAR(2),
    V_UDF_22 CHAR(9),
    V_UDF_23 CHAR(10),
    V_UDF_24 CHAR(3),
    V_UDF_25 DECIMAL(15,5),
    V_UDF_26 CHAR(8),
    V_UDF_27 CHAR(2),
    V_UDF_28 CHAR(2),
    V_UDF_29 CHAR(9),
    V_UDF_30 CHAR(10),
    V_UDF_31 CHAR(3),
    V_UDF_32 DECIMAL(15,5),
    V_UDF_33 CHAR(8),
    V_UDF_34 CHAR(2),
    V_UDF_35 CHAR(2),
    V_UDF_36 CHAR(9),
    V_UDF_37 CHAR(10),
    V_UDF_38 CHAR(3),
    V_UDF_39 DECIMAL(15,5),
    V_UDF_40 CHAR(8),
    V_UDF_41 CHAR(2),
    V_UDF_42 CHAR(2),
    V_UDF_43 CHAR(9),
    V_UDF_44 CHAR(10),
    V_UDF_45 CHAR(3),
    V_UDF_46 DECIMAL(15,5),
    V_UDF_47 CHAR(8),
    V_UDF_48 CHAR(2),
    V_UDF_49 CHAR(2),
    V_UDF_51 CHAR(9),
    V_UDF_52 CHAR(10),
    V_UDF_53 CHAR(3),
    V_UDF_54 DECIMAL(15,5),
    V_UDF_55 CHAR(8),
    V_UDF_56 CHAR(2),
    V_UDF_57 CHAR(2),
    V_UDF_58 CHAR(9),
    V_UDF_59 CHAR(10),
    V_UDF_60 CHAR(3),
    V_UDF_61 DECIMAL(15,5),
    V_UDF_62 CHAR(8),
    V_UDF_63 CHAR(2),
    V_UDF_64 CHAR(2),
    V_UDF_65 CHAR(9),
    V_UDF_66 CHAR(10),
    V_UDF_67 CHAR(3),
    V_UDF_68 DECIMAL(15,5),
    V_UDF_69 CHAR(8),
    V_UDF_70 CHAR(2),
    V_UDF_71 CHAR(2),
    V_UDF_72 CHAR(9),
    V_UDF_73 CHAR(10),
    V_UDF_74 CHAR(3),
    V_UDF_75 DECIMAL(15,5),
    V_UDF_76 CHAR(8),
    V_UDF_77 CHAR(2),
    V_UDF_78 CHAR(2),
    V_UDF_79 CHAR(9),
    V_UDF_80 CHAR(10),
    V_UDF_81 CHAR(3),
    V_UDF_82 DECIMAL(15,5),
    V_UDF_83 CHAR(8),
    V_UDF_84 CHAR(2),
    V_UDF_85 CHAR(2),
    V_UDF_86 CHAR(9),
    V_UDF_87 CHAR(10),
    V_UDF_88 CHAR(3),
    V_UDF_89 DECIMAL(15,5),
    V_UDF_90 CHAR(8),
    V_UDF_91 CHAR(2),
    V_UDF_92 CHAR(2),
    V_UDF_93 CHAR(9),
    V_UDF_94 CHAR(10),
    V_UDF_95 CHAR(3),
    V_UDF_96 DECIMAL(15,5),
    V_UDF_97 CHAR(8),
    V_UDF_98 CHAR(2),
    V_UDF_99 CHAR(2),
    V_UDF_100 CHAR(9),
    V_UDF_101 CHAR(10),
    V_UDF_102 CHAR(3),
    V_UDF_103 DECIMAL(15,5),
    V_UDF_104 CHAR(8),
    V_UDF_105 CHAR(2),
    V_UDF_106 CHAR(2),
    V_UDF_107 CHAR(9),
    V_UDF_108 CHAR(10),
    V_UDF_109 CHAR(3),
    V_UDF_110 DECIMAL(15,5),
    V_UDF_111 CHAR(8)
);

CREATE INDEX IDX_GUARANTOR_CUST_NO ON GUARANTOR (V_CUST_NO);
