CREATE TABLE IF NOT EXISTS SPRING_SESSION (
	PRIMARY_ID CHAR(36) NOT NULL,
	SESSION_ID CHAR(36) NOT NULL,
	CREATION_TIME BIGINT NOT NULL,
	LAST_ACCESS_TIME BIGINT NOT NULL,
	MAX_INACTIVE_INTERVAL INT NOT NULL,
	EXPIRY_TIME BIGINT NOT NULL,
	PRINCIPAL_NAME VARCHAR(100),
	CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX IF NOT EXISTS  SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX IF NOT EXISTS  SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX IF NOT EXISTS  SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE IF NOT EXISTS SPRING_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID CHAR(36) NOT NULL,
	ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
	ATTRIBUTE_BYTES LONGVARBINARY NOT NULL,
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS COMCODE (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
    CODE_ID				VARCHAR(255) 	NOT NULL 	,--COMMENT '코드ID',    
	SYSTEM_TYPE_CODE	VARCHAR(3)		NOT NULL 	,--COMMENT '시스템구분코드',    
    P_CODE_ID			VARCHAR(242)	NULL 		,--COMMENT '상위코드ID',
   	CODE				VARCHAR(10) 	NOT NULL 	,--COMMENT '공통코드',
	CODE_NAME			VARCHAR(255) 	NOT NULL 	,--COMMENT '코드명칭',
	CODE_NAME_ABBR		VARCHAR(255) 	NULL 		,--COMMENT '코드명칭약어',	
	FROM_DT				DATETIME		NOT NULL	,--COMMENT '시작일시',
	TO_DT				DATETIME		NOT NULL	,--COMMENT '종료일시'	DEFAULT '9999-12-31',
	HIERARCHY_LEVEL		INT				NOT NULL	,--COMMENT '계층단계(1부터 시작)' DEFAULT 1,
	PRT_SEQ				INT				NULL		,--COMMENT '출력순서',	
	FIXED_LENGTH_YN		BOOLEAN			NOT NULL 	,--COMMENT '고정길이여부',
   	CODE_LENGTH			INT				NULL		,--COMMENT '코드길이' DEFAULT 0,
	CMT					VARCHAR(2000) 	NULL 		,--COMMENT '비고',
	constraint pk_comcode primary key(CODE_ID)	
); -- COMMENT = '공통코드관리';

CREATE TABLE IF NOT EXISTS COMDEPT (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	DEPT_ID				VARCHAR(10) 	NOT NULL 	,--COMMENT '부서ID',
	ORG_CD				VARCHAR(50)		NULL		,--COMMENT '조직코드',
    DEPT_CD				VARCHAR(10) 	NOT NULL 	,--COMMENT '부서코드',
    P_DEPT_ID			VARCHAR(255)	NULL 		,--COMMENT '상위부ID',
    DEPT_NM_KOR			VARCHAR(255) 	NOT NULL 	,--COMMENT '부서명(한글)',
    DEPT_ABBR_KOR		VARCHAR(255) 	NULL 		,--COMMENT '부서약어(한글)',
    DEPT_NM_ENG			VARCHAR(255) 	NULL 		,--COMMENT '부서명(영어)',
    DEPT_ABBR_ENG		VARCHAR(255) 	NULL 		,--COMMENT '부서약어(영어)',
	FROM_DT				DATE			NULL		,--COMMENT '시작일',
	TO_DT				DATE			NULL		,--COMMENT '종료일',	
	PRT_SEQ				INT				NULL		,--COMMENT '출력순서',		
	CMT					VARCHAR(2000) 	NULL 		,--COMMENT '비고',
	constraint pk_comdept 		primary key(DEPT_ID),
	constraint fk_comdept1	 	foreign key(P_DEPT_ID) references COMDEPT(DEPT_ID)
); -- COMMENT = '부서관리';

CREATE TABLE IF NOT EXISTS COMFILEINFO (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
    FILE_ID				BINARY(16)	 	NOT NULL 	,--COMMENT '키',
    APP_URL				VARCHAR(50)		NULL 		,--COMMENT '유저아이디',
    USER_ID				VARCHAR(20)		NULL		,--COMMENT '발령코드',
    CONTENT_TYPE		VARCHAR(50)		NULL 		,--COMMENT 'CONTENT-TYPE',
    UUID				VARCHAR(1000)	NOT NULL 	,--COMMENT 'UUID_서버에저장된파일명',
    FILE_PATH			VARCHAR(1000)	NULL 		,--COMMENT '파일경로',
    FILE_NM				VARCHAR(1000)	NULL 		,--COMMENT '파일명',
    FILE_SIZE			INT				NULL 		,--COMMENT '파일사이즈',
    DOWNLOAD_CNT		INT				NULL 		,--COMMENT '다운로드횟수',
	constraint pk_comfileinfo primary key(FILE_ID)
); -- COMMENT = '공통파일관리';

CREATE TABLE IF NOT EXISTS COMUSER (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
    USER_ID				VARCHAR(50)		NOT NULL	,--COMMENT '유저ID',
	ORG_CD				VARCHAR(50)		NULL		,--COMMENT '조직코드',
	STAFF_NO			VARCHAR(50)		NULL		,--COMMNET '직원번호',
    USER_NAME			VARCHAR(100)	NULL		,--COMMENT '유저명',	
    DEPT_CD				VARCHAR(10) 	NULL 		,--COMMENT '부서코드',
    MOBILE_NUM 			VARCHAR(20) 	NULL		,--COMMENT '핸드폰번호',
    EMAIL 				VARCHAR(320) 	NULL 		,--COMMENT '이메일주소',
    PWD 		   		VARCHAR(2000)	NULL		,--COMMENT '비밀번호',
    FK_FILE				VARCHAR(40)		NULL        ,--COMMENT '이미지파일',
    NON_EXPIRED_YN		BOOLEAN			NOT NULL    ,--COMMENT '계정만료여부',
    NON_LOCKED_YN		BOOLEAN			NOT NULL	,--COMMENT '계정잠금여부',
    PASS_NON_EXPIRED_YN	BOOLEAN			NOT NULL	,--COMMENT '비밀번호만료여부',
    ENABLED_YN			BOOLEAN			NOT NULL	,--COMMENT '사용여부',
	constraint pk_comuser primary key(USER_ID)
	--constraint fk_comuser1	foreign key(FK_FILE) references COMFILEINFO(PK_FILE)
); -- COMMENT = '사용자관리';

CREATE TABLE IF NOT EXISTS COMAUTHORITY (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	AUTH_ID				VARCHAR(50)		NOT NULL	,--COMMENT '권한ID',
	ORG_CD				VARCHAR(50)		NOT NULL	,--COMMENT '조직코드',
	AUTH_CD				VARCHAR(50)		NOT NULL	,--COMMENT '권한코드',    
    DESCRIPTION			VARCHAR(500)	NULL		,--COMMENT '권한설명',
	constraint pk_comauthority primary key(AUTH_ID)
); -- COMMENT = '권한관리';

CREATE TABLE IF NOT EXISTS COMUSERAUTHORITY (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
    USER_ID				VARCHAR(50)		NOT NULL	,--COMMENT '유저ID',	
    AUTH_ID				VARCHAR(50)		NOT NULL	,--COMMENT '권한명',
	constraint pk_comuserauthority 	primary key(USER_ID, AUTH_ID),
	constraint fk_comuserauthority1	foreign key(USER_ID) references COMUSER(USER_ID),
	constraint fk_comuserauthority2	foreign key(AUTH_ID) references COMAUTHORITY(AUTH_ID)
); -- COMMENT = '사용자권한매핑관리';

CREATE TABLE IF NOT EXISTS COMRESOURCE (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
    RESOURCE_CODE		VARCHAR(10)		NOT NULL	,--COMMENT '리소스코드',    
    RESOURCE_NAME		VARCHAR(50)		NOT NULL	,--COMMENT '리소스명',
    RESOURCE_TYPE		VARCHAR(10)		NOT NULL	,--COMMENT '리소스타입',
    URL					VARCHAR(500)	NULL		,--COMMENT 'URL',
    DESCRIPTION			VARCHAR(500)	NULL		,--COMMENT '설명',
	constraint pk_comresource	primary key(RESOURCE_CODE)
); -- COMMENT = '웹서버 리소스관리';

CREATE TABLE IF NOT EXISTS COMMENUGROUP (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	MENU_GROUP_ID		VARCHAR(50)		NOT NULL	,--COMMENT '메뉴그룹ID',
	ORG_CD				VARCHAR(10)		NULL		,--COMMENT '조직코드',
    MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	,--COMMENT '메뉴그룹코드',
    MENU_GROUP_NAME		VARCHAR(50)		NOT NULL	,--COMMENT '메뉴그룹명',
    DESCRIPTION			VARCHAR(500)	NULL		,--COMMENT '설명',
	constraint pk_commenugroup	primary key(MENU_GROUP_ID)
); -- COMMENT = '메뉴그룹관리';

CREATE TABLE IF NOT EXISTS COMMENU (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	MENU_ID				VARCHAR(50)		NOT NULL	,--COMMENT '메뉴코드',
	ORG_CD				VARCHAR(10)		NOT NULL	,--COMMENT '조직코드',
	MENU_CODE			VARCHAR(10)		NOT NULL	,--COMMENT '메뉴코드',
	MENU_NAME			VARCHAR(50)		NOT NULL	,--COMMENT '메뉴명',
    MENU_TYPE			VARCHAR(10)		NOT NULL	,--COMMENT '메뉴타입',
	P_MENU_ID			VARCHAR(50)		NULL		,--COMMENT '상위메뉴코드',
	MENU_GROUP_ID		VARCHAR(50)		NOT NULL	,--COMMENT '메뉴그룹코드',	        
    APP_URL				VARCHAR(50)		NULL		,--COMMENT 'APP_URL',
    SEQ					INT(11)			NULL		,--COMMENT '계층별 순번',
    LVL					INT(11)			NULL		,--COMMENT '계층레벨',    
	constraint pk_commenu		primary key(MENU_ID),
	constraint fk_commenu1	 	foreign key(P_MENU_ID) references COMMENU(MENU_ID),
	constraint fk_commenu2	 	foreign key(MENU_GROUP_ID) references COMMENUGROUP(MENU_GROUP_ID)
); -- COMMENT = '메뉴관리';

CREATE TABLE IF NOT EXISTS COMUSERMENUGROUP (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	USER_ID				VARCHAR(50)		NOT NULL	,--COMMENT '유저ID',
    MENU_GROUP_ID		VARCHAR(10)		NOT NULL	,--COMMENT '메뉴그룹코드',
	constraint pk_comusermenugroup 		primary key(USER_ID, MENU_GROUP_ID),
	constraint fk_comusermenugroup1 	foreign key(USER_ID) references COMUSER(USER_ID),
	constraint fk_comusermenugroup2 	foreign key(MENU_GROUP_ID) references COMMENUGROUP(MENU_GROUP_ID)
); -- COMMENT = '사용자메뉴그룹매핑관리';

CREATE TABLE IF NOT EXISTS COMLOGINHISTORY (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	ID					INT				NOT NULL	,--COMMENT 'ID' 		AUTO_INCREMENT,
	USER_ID				VARCHAR(50)		NOT NULL	,--COMMENT '유저ID',
	EVENT_TYPE			VARCHAR(500)	NOT NULL	,--COMMENT '로그인구분',
	EVENT_DT			DATETIME		NOT NULL	,--COMMENT '이벤트일시',	
	CLIENT_IP			VARCHAR(500)	NOT NULL	,--COMMENT '클라이언트IP',
	SUCCESS_YN			BOOLEAN			NOT NULL	,--COMMENT '성공여부',
	constraint pk_comloginhistory primary key(ID) 	
); -- COMMENT = '로그인이력';

CREATE TABLE IF NOT EXISTS COMHOLIDAY (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	HOLIDAY_DT			DATETIME		NOT NULL	,--COMMENT '휴일',
   	HOLIDAY_NM			VARCHAR(255) 	NOT NULL 	,--COMMENT '휴일명',    
	CMT					VARCHAR(2000) 	NULL 		,--COMMENT '비고',
	constraint pk_comholiday primary key(HOLIDAY_DT)	
); -- COMMENT = '공휴일관리';


CREATE TABLE IF NOT EXISTS COMTERMDOMAIN (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	DOMAIN_ID			VARCHAR(600)	NOT NULL	,--COMMENT '도메인ID'
	DB					VARCHAR(600)	NOT NULL	,--COMMENT 'DATABASE'
	DOMAIN_NAME			VARCHAR(600)	NOT NULL	,--COMMENT '도메인명'	
	DATA_TYPE			VARCHAR(600)	NOT NULL	,--COMMENT 'DATATYPE'	
	CMT					TEXT		 	NULL 		,--COMMENT '비고',
	constraint pk_comtermdomain primary key(DOMAIN_ID)	
); -- COMMENT = '데이터도메인';


CREATE TABLE IF NOT EXISTS COMTERMWORD (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	LOGICAL_NAME		VARCHAR(600)	NOT NULL	,--COMMENT '논리명'
	LOGICAL_NAME_ENG	VARCHAR(600)	NULL		,--COMMENT '논리명(영문)'
	PHYSICAL_NAME		VARCHAR(600)	NOT NULL	,--COMMENT '물리명'		
	CMT					TEXT		 	NULL 		,--COMMENT '비고',
	constraint pk_comtermword primary key(LOGICAL_NAME)	
); -- COMMENT = '단어사전';

CREATE TABLE IF NOT EXISTS COMTERM (
	CREATED_DT			DATETIME		NULL		,--COMMENT '최초등록일시',
	CREATED_USER_ID		VARCHAR(50)		NULL		,--COMMENT '최초등록유저',
	CREATED_HOST_IP		VARCHAR(50)		NULL		,--COMMENT '최초등록IP',
	CREATED_APP_URL		VARCHAR(500)	NULL		,--COMMENT '최초등록APPURL',
	MODIFIED_DT			DATETIME		NULL		,--COMMENT '최종수정일시',
	MODIFIED_USER_ID	VARCHAR(50)		NULL		,--COMMENT '최종수정유저',
	MODIFIED_HOST_IP	VARCHAR(50)		NULL		,--COMMENT '최종수정IP',
	MODIFIED_APP_URL	VARCHAR(500)	NULL		,--COMMENT '최종수정APPURL',
	TERM_ID				VARCHAR(600)	NOT NULL	,--COMMENT '용어ID'
	SYSTEM				VARCHAR(600)	NOT NULL	,--COMMENT '시스템'
	TERM				VARCHAR(600)	NOT NULL	,--COMMENT '용어'
	TERM_ENG			VARCHAR(600)	NULL		,--COMMENT '용어(영문)'
	COLUMN_NAME			VARCHAR(600)	NULL		,--COMMENT '컬럼명'
	DOMAIN_ID			VARCHAR(600)	NOT NULL	,--COMMENT '도메인ID'
	COMBI_YN			BOOLEAN			NOT NULL	,--COMMENT '복합여부'	
	TERM_DESCRIPTION	TEXT		 	NULL 		,--COMMENT '용어설명',
	CMT					TEXT		 	NULL 		,--COMMENT '비고',
	constraint pk_comterm primary key(TERM_ID),
	constraint fk_comterm1 	foreign key(DOMAIN_ID) references COMTERMDOMAIN(DOMAIN_ID)
); -- COMMENT = '용어사전';

