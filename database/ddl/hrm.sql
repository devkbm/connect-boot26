create table if not exists COM.HRMTYPECODE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',    
    TYPE_CODE				VARCHAR(10) 	NOT NULL 	COMMENT '구분코드',    
	TYPE_CODE_NAME			VARCHAR(500) 	NOT NULL 	COMMENT '구분코드명칭',
	USE_YN					BOOLEAN			NOT NULL 	COMMENT '사용여부',		
	PRT_SEQ					INT				NULL		COMMENT '출력순서',
	APPOINT_TYPE_CODE		VARCHAR(5)		NULL		COMMENT '발령구분코드',	
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmtypecode primary key(TYPE_CODE)	
) COMMENT = '인사시스템구분코드정보';

create table if not exists COM.HRMTYPEDETAILCODE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
	TYPE_CODE				VARCHAR(10)		NOT NULL	COMMENT '구분코드',
    DETAIL_CODE				VARCHAR(20) 	NOT NULL 	COMMENT '상세코드',           
	DETAIL_CODE_NAME		VARCHAR(500) 	NOT NULL 	COMMENT '상세코드명칭',
	USE_YN					BOOLEAN			NOT NULL 	COMMENT '사용여부',		
	PRT_SEQ					INT				NULL		COMMENT '출력순서',	
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmtypedetailcode primary key(TYPE_CODE, DETAIL_CODE),
	constraint fk_hrmtypedetailcode foreign key(TYPE_CODE) references HRMTYPECODE(TYPE_CODE)
) COMMENT = '인사시스템상세코드정보';

create table if not exists COM.HRMRELATIONCODE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
	RELATION_ID				INT				NOT NULL	COMMENT '인사연관코드ID' AUTO_INCREMENT,    
    REL_CODE				VARCHAR(20)		NOT NULL	COMMENT '연관코드',
    PARENT_TYPE_ID			VARCHAR(20)		NOT NULL	COMMENT '부모구분ID',
	PARENT_DETAIL_ID		VARCHAR(20) 	NOT NULL 	COMMENT '부모구분상세ID',
	CHILD_TYPE_ID			VARCHAR(20) 	NOT NULL 	COMMENT '자식구분ID',	
	CHILD_DETAIL_ID			VARCHAR(20) 	NOT NULL 	COMMENT '자식구분상세ID',
	constraint pk_HRMRELATIONCODE primary key(RELATION_ID)
) COMMENT = '인사연관코드정보';

create table HRMSTAFF (
	SYS_DT				DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT				DATETIME		null		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		null		COMMENT '최종수정유저',
	STAFF_ID			VARCHAR(10) 	not null  	COMMENT '직원ID',
	STAFF_NAME			VARCHAR(500)	null		COMMENT '이름_한글',
	STAFF_NAME_LEGAL	VARCHAR(500)	null		COMMENT '이름_법적이름',
	STAFF_NAME_ENG		VARCHAR(500)	null		COMMENT '이름_영문',
	STAFF_NAME_CHI		VARCHAR(500)	null		COMMENT '이름_한문',	
	RREGNO				VARCHAR(20)		null		COMMENT '주민등록번호',
	GENDER				VARCHAR(1)		null		COMMENT '성별',
	BIRTHDAY			DATE			null		COMMENT '생일(생년월일)',
	WORK_STATE_CODE		VARCHAR(2)		null		COMMENT '근무상태코드',
	IMG_PATH			VARCHAR(2000)  	null 		COMMENT '이미지경로',
	BLNG_DEPT_CODE		VARCHAR(10)		null		COMMENT '소속부서',
	WORK_DEPT_CODE		VARCHAR(10)		null		COMMENT '근무부서',
	JOB_GROUP_CODE		VARCHAR(4)		null		COMMENT '직군코드',
	JOB_POSITION_CODE	VARCHAR(4)		null		COMMENT '직위코드',
	OCCUPATION_CODE		VARCHAR(4)		null		COMMENT '직종코드',
	JOB_GRADE_CODE		VARCHAR(4)		null		COMMENT '직급코드',
	PAY_STEP_CODE		VARCHAR(4)		null		COMMENT '호봉코드',
	JOB_CODE			VARCHAR(4)		null		COMMENT '직무코드',	
	constraint pk_hrmstaff primary key(STAFF_ID)
) COMMENT = '직원기본';

create table HRMSTAFFDUTYRESPONSIBILITY (
	SYS_DT				DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT				DATETIME		null		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		null		COMMENT '최종수정유저',
	STAFF_ID			VARCHAR(10) 	not null  	COMMENT '직원ID',
	DUTY_RESPONSIBILITY_CODE VARCHAR(4)		null	COMMENT '직책코드',
	FROM_DT				DATE			not null	COMMENT '시작일자',
	TO_DT				DATE			not null	COMMENT '종료일자',
	DEPUTY_YN			VARCHAR(1)		not null	COMMENT '대리여부',
	PAY_YN				VARCHAR(1)		not null	COMMENT '급여여부',
	constraint pk_hrmstaffdutyresponsibility primary key(STAFF_ID, DUTY_RESPONSIBILITY_CODE, FROM_DT),
	constraint fk_hrmstaffdutyresponsibility1 foreign key(STAFF_ID) references HRMSTAFF(STAFF_ID)  
) COMMENT = '직원직책';

create table HRMSTAFFAPPOINTMENTRECORD (
	SYS_DT						DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 					VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT						DATETIME		null		COMMENT '최종수정일시',
	UPD_USER					VARCHAR(50)		null		COMMENT '최종수정유저',
	ID							INT				not null	COMMENT '발령기록ID'	AUTO_INCREMENT,
	STAFF_ID					VARCHAR(10) 	not null  	COMMENT '직원ID',
	APPOINTMENT_DT				DATE			null 		COMMENT '발령일자',
	APPOINTMENT_END_DT			DATE			null 		COMMENT '발령종료일자',
	RECORD_NAME					VARCHAR(2000)	null		COMMENT '기록명',
	CMT							VARCHAR(2000) 	null 		COMMENT '비고',
	PROC_WAIT_YN				VARCHAR(1)		null		COMMENT '처리대기여부',
	BLNG_DEPT_CODE				VARCHAR(10)		null		COMMENT '소속부서',
	WORK_DEPT_CODE				VARCHAR(10)		null		COMMENT '근무부서',
	JOB_GROUP_CODE				VARCHAR(4)		null		COMMENT '직군코드',
	JOB_POSITION_CODE			VARCHAR(4)		null		COMMENT '직위코드',
	OCCUPATION_CODE				VARCHAR(4)		null		COMMENT '직종코드',
	JOB_GRADE_CODE				VARCHAR(4)		null		COMMENT '직급코드',
	PAY_STEP_CODE				VARCHAR(4)		null		COMMENT '호봉코드',
	JOB_CODE					VARCHAR(4)		null		COMMENT '직무코드',	
	DUTY_RESPONSIBILITY_CODE 	VARCHAR(4)		null		COMMENT '직책코드',	
	constraint pk_hrmstaffappointmentrecord primary key(ID),
	constraint fk_hrmstaffappointmentrecord1 foreign key(STAFF_ID) references HRMSTAFF(STAFF_ID)  
) COMMENT = '직원발령기록';

create table HRMSTAFFFAMILY (
	SYS_DT			DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT			DATETIME		null		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		null		COMMENT '최종수정유저',
	ID				INT				not null	COMMENT '직원가족ID'	AUTO_INCREMENT,
	STAFF_ID		VARCHAR(10) 	not null  	COMMENT '직원ID',
	FAMILY_NAME		VARCHAR(500)	not null	COMMENT '가족성명',
	RREGNO			VARCHAR(20)		not null	COMMENT '주민등록번호',
	FAMILY_REL_CODE	VARCHAR(3)		not null	COMMENT '가족관계코드',
	OCCUPATION_NAME	VARCHAR(500)	not null	COMMENT '직업명',
	SCHOOL_CAREER_CODE	VARCHAR(3)	not null	COMMENT '학력코드',
	CMT				VARCHAR(2000) 	null 		COMMENT '비고',
	constraint pk_hrmstafffamily primary key(ID),
	constraint fk_hrmstafffamily1 foreign key(STAFF_ID) references HRMSTAFF(STAFF_ID)  
) COMMENT = '직원가족정보';

create table HRMSTAFFSCHOOLCAREER (
	SYS_DT				DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT				DATETIME		null		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		null		COMMENT '최종수정유저',
	ID					INT				not null	COMMENT '직원학력ID'	AUTO_INCREMENT,
	STAFF_ID			VARCHAR(10) 	not null  	COMMENT '직원ID',
	SCHOOL_CAREER_CODE	VARCHAR(2)		not null	COMMENT '학력코드',	
	SCHOOL_CODE			VARCHAR(5)		not null	COMMENT '학교코드',
	FROM_DT				DATE			null 		COMMENT '시작일자',
	TO_DT				DATE			null 		COMMENT '종료일자',
	MAJOR_NAME			VARCHAR(500)	null 		COMMENT '전공학과명',
	PLURAL_MAJOR_NAME	VARCHAR(500)	null 		COMMENT '복수전공학과명',
	LOCATION_NAME		VARCHAR(500)	null 		COMMENT '소재지명',
	LESSON_YEAR			INT				null 		COMMENT '복수전공학과명',
	CMT					VARCHAR(2000) 	null 		COMMENT '비고',
	constraint pk_hrmstaffschoolcareer primary key(ID),
	constraint fk_hrmstaffschoolcareer foreign key(STAFF_ID) references HRMSTAFF(STAFF_ID)  
) COMMENT = '직원학력정보';

create table HRMSTAFFLICENSE (
	SYS_DT					DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT					DATETIME		null		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(50)		null		COMMENT '최종수정유저',
	ID						INT				not null	COMMENT '직원자격면허ID'	AUTO_INCREMENT,
	STAFF_ID				VARCHAR(10) 	not null  	COMMENT '직원ID',
	LICENSE_TYPE			VARCHAR(2)		not null	COMMENT '자격면허유형',
	LICENSE_CODE			VARCHAR(5)		not null	COMMENT '자격면허코드',
	DATE_OF_ACQUISITION		DATE			null		COMMENT '취득일자',
	CERTIFICATION_AUTHORITY	VARCHAR(500)	null		COMMENT '인증기관',
	MANDATORY_YN			BOOLEAN			not null	COMMENT '필수여부',
	CMT						VARCHAR(2000) 	null 		COMMENT '비고',
	constraint pk_hrmstafflicense primary key(ID),
	constraint fk_hrmstafflicense foreign key(STAFF_ID) references HRMSTAFF(STAFF_ID)  
) COMMENT = '직원자격면허';

create table HRMDUTYCODE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',	   
    DUTY_CODE				VARCHAR(20)		NOT NULL	COMMENT '근무코드',
	DUTY_NAME				VARCHAR(50) 	NOT NULL 	COMMENT '근무명',
	DUTY_GROUP				VARCHAR(20) 	NOT NULL 	COMMENT '근무그룹',
	ENABLE_YN				BOOLEAN 		NOT NULL 	COMMENT '사용여부',	
	FAMILY_EVENT_YN			BOOLEAN 		NOT NULL 	COMMENT '사용여부',
	FAMILY_EVENT_AMT		INT				null		COMMENT '경조사지급금액',
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmdutycode primary key(DUTY_CODE)
) COMMENT = '근무코드정보';

create table HRMDUTYAPPLICATION (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(50)		NULL		COMMENT '최종수정유저',	   
    DUTY_ID					INT				NOT NULL	COMMENT '근태신청ID' AUTO_INCREMENT,
	EMP_ID					VARCHAR(50) 	NOT NULL 	COMMENT '사원번호',
	DUTY_CODE				VARCHAR(20)		NOT NULL	COMMENT '근무코드',
	DUTY_REASON				VARCHAR(2000) 	NOT NULL 	COMMENT '근태사유',
	DUTY_START_DT			DATETIME		NOT NULL 	COMMENT '근태시작일',
	DUTY_END_DT				DATETIME		NOT NULL 	COMMENT '근태종료일',
	FAMILY_EVENT_DT			DATETIME		NULL		COMMENT '경조사발생일자',
	FAMILY_EVENT_AMT		INT				NULL		COMMENT '경조사지급금액',
	constraint pk_hrmdutyapplication primary key(DUTY_ID),
	constraint fk_hrmdutyapplication1 foreign key(DUTY_CODE) references HRMDUTYCODE(DUTY_CODE)
) COMMENT = '근태신청정보';

create table HRMDUTYAPPLICATIONDATE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(50)		NULL		COMMENT '최종수정유저',	   
    ID						INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,
    FK_DUTY_ID				INT				NOT NULL	COMMENT '근태신청ID',	
	DUTY_DT					DATETIME		NOT NULL 	COMMENT '근태일시',	
	constraint pk_hrmdutyapplicationdate primary key(ID),
	constraint fk_hrmdutyapplicationdate foreign key(FK_DUTY_ID) references HRMDUTYAPPLICATION(DUTY_ID)
) COMMENT = '근태신청일시정보';

create table HRMDUTYAPPLICATIONLIMIT (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(50)		NULL		COMMENT '최종수정유저',	   
	LIMIT_ID				INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,
    FROM_YEAR_TYPE			VARCHAR(10)		NOT NULL	COMMENT '시작년도구분',
    FROM_MMDD				VARCHAR(4)		NOT NULL	COMMENT '시작월일',
    TO_YEAR_TYPE			VARCHAR(10)		NOT NULL	COMMENT '종료년도구분',
    TO_MMDD					VARCHAR(4)		NOT NULL	COMMENT '종료월일',
    CNT						INT				NOT NULL	COMMENT '제한갯수',
    INVALID_MSG				VARCHAR(2000)	NULL		COMMENT '초과시메시지',
	CMT						VARCHAR(2000)	NULL		COMMENT '비고',	
	constraint pk_hrmdutyapplicationlimit primary key(LIMIT_ID)	
) COMMENT = '근태신청제한정보';

create table HRMDUTYCODERULE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',	   
    RULE_ID					INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,
    DUTY_CODE				VARCHAR(20)		NOT NULL	COMMENT '근무코드',	
	FK_LIMIT_ID				INT				NOT NULL	COMMENT 'FK_제한정보ID',
	constraint pk_hrmdutycoderule primary key(RULE_ID),
	constraint fk_hrmdutycoderule1 foreign key(DUTY_CODE) references HRMDUTYCODE(DUTY_CODE),
	constraint fk_hrmdutycoderule2 foreign key(FK_LIMIT_ID) references HRMDUTYAPPLICATIONLIMIT(LIMIT_ID)
) COMMENT = '근무코드정책정보';

create table if not exists COM.HRMANUALLEAVE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    YYYY					INT				NOT NULL	COMMENT '귀속년도',
    EMP_ID					VARCHAR(20)		NOT NULL	COMMENT '사원번호',
    BASE_DT					DATETIME		NOT NULL   	COMMENT '기준일',
    FROM_DT					DATETIME	 	NOT NULL 	COMMENT '연차시작일',
	TO_DT					DATETIME 		NOT NULL 	COMMENT '연차종료일',
	TOTAL_WORK_DAYS			INT				NULL 		COMMENT '총근무일수',
	EXCEPT_DAYS				INT				NULL 		COMMENT '총근무일수',
	CNT						DECIMAL(16,5)	NULL 		COMMENT '발생갯수',		
	ADD_CNT					DECIMAL(16,5)	NULL 		COMMENT '가산갯수',
	USE_CNT					DECIMAL(16,5)	NULL 		COMMENT '사용갯수',
	INTRA_ANUAL				BOOLEAN			NULL 		COMMENT '총근무일수',	
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmanualleave primary key(YYYY,EMP_ID)	
) COMMENT = '직원연차정보';


create table if not exists COM.HRMPAYITEM (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    CODE					VARCHAR(10)		NOT NULL	COMMENT '급여항목코드',	
    CODE_NM					VARCHAR(50)		NOT NULL	COMMENT '급여항목명',
    ITEM_TYPE				VARCHAR(10)		NOT NULL	COMMENT '구분코드',    
	PAY_TABLE_YN			BOOLEAN			NOT NULL	COMMENT '급여테이블사용여부',
	SEQ						INT				NULL		COMMENT '순번',
    CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',	
	constraint pk_hrmpayitem primary key(CODE)	
) COMMENT = '급여항목코드정보';

create table if not exists COM.HRMPAYTABLE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
	ID						INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,    
    TABLE_NM				VARCHAR(50)		NOT NULL	COMMENT '급여테이블명',
    ENABLE_YN				BOOLEAN			NOT NULL	COMMENT '사용여부',
    TYPE_CODE1				VARCHAR(10)		NULL 		COMMENT '급여구분코드1',    
    TYPE_CODE2				VARCHAR(10)		NULL 		COMMENT '급여구분코드2',
    TYPE_CODE3				VARCHAR(10)		NULL 		COMMENT '급여구분코드3',	
	SEQ						INT				NULL		COMMENT '순번',
    CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',	
	constraint pk_hrmpaytable primary key(ID)	
) COMMENT = '급여테이블정보';

create table if not exists COM.HRMPAYTABLEITEM (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
	ID						INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,           
	PAY_TABBLE_ID			INT				NOT NULL	COMMENT 'FK_PAYTABLE',
    CODE1					VARCHAR(10)		NULL 		COMMENT '급여테이블항목코드1',    
    CODE2					VARCHAR(10)		NULL 		COMMENT '급여테이블항목코드2',
    CODE3					VARCHAR(10)		NULL 		COMMENT '급여테이블항목코드3',	
	AMT						DECIMAL			null		COMMENT '금액',
    CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',	
	constraint pk_hrmpaytableitem primary key(ID),
	constraint fk_hrmpaytableitem1 foreign key(PAY_TABBLE_ID) references HRMPAYTABLE(ID)
) COMMENT = '급여테이블항목정보';