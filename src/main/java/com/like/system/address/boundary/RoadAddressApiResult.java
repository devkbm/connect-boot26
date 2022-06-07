package com.like.system.address.boundary;

/**
 * 도로명주소 API 명세 URL - https://www.juso.go.kr/addrlink/devAddrLinkRequestGuide.do?menu=roadApi
 * <p> 이력 </p> <br>
 * API 서비스 제공항목 확대 (2017.02) <br>
 * API 서비스 제공항목 확대 (2020.12)
 */
public class RoadAddressApiResult {

	/**
	 * 총 검색 데이터수 <br>
	 * 필수여부 - Y
	 */
	String totalCount;
	
	/**
	 * 페이지 번호 <br>
	 * 필수여부 - Y
	 */
	Integer currentPage;
	
	/**
	 * 페이지당 출력할 결과 Row 수 <br>
	 * 필수여부 - Y
	 */
	Integer countPerPage;
	
	/**
	 * 에러 코드 <br>
	 * 필수여부 - Y
	 */
	String errorCode;
	
	/**
	 * 에러 메시지 <br>
	 * 필수여부 - Y 
	 */
	String errorMessage;
	
	/**
	 * 전체 도로명주소 <br>
	 * 필수여부 - Y 
	 */
	String roadAddr;
	
	/**
	 * 도로명주소(참고항목 제외) <br>
	 * 필수여부 - Y
	 */
	String roadAddrPart1;

	/**
	 * 도로명주소 참고항목 <br>
	 * 필수여부 - N 
	 */
	String roadAddrPart2;
	
	/**
	 * 지번주소 <br>
	 * 필수여부 - Y
	 */
	String jibunAddr;
	
	/**
	 * 도로명주소(영문) <br>
	 * 필수여부 - Y 
	 */
	String engAddr;
	
	/**
	 * 우편번호 <br>
	 * 필수여부 - Y
	 */
	String zipNo;
		
	/**
	 * 행정구역코드 <br>
	 * 필수여부 - Y
	 */
	String admCd;
	
	/**
	 * 도로명코드 <br>
	 * 필수여부 - Y
	 */
	String rnMgtSn;
	
	/**
	 * 건물관리번호 <br>
	 * 필수여부 - Y
	 */
	String bdMgtSn;
	
	/**
	 * 상세건물명 <br>
	 * 필수여부 - N
	 */
	String detBdNmList;
	
	// 아래는 - API 서비스 제공항목 확대 (2017.02)
	
	/**
	 * 건물명 <br>
	 * 필수여부 - N
	 */
	String bdNm;
	
	/**
	 * 공동주택여부(1 : 공동주택, 0 : 비공동주택) <br>
	 * 필수여부 - Y
	 */
	String bdKdcd;
	
	/**
	 * 시도명 <br>
	 * 필수여부 - Y
	 */
	String siNm;
	
	/**
	 * 시군구명 <br>
	 * 필수여부 - Y
	 */
	String sggNm;
	
	/**
	 * 읍면동명 <br>
	 * 필수여부 - Y
	 */
	String emdNm;
	
	/**
	 * 법정리명 <br>
	 * 필수여부 - N
	 */
	String liNm;
	
	/**
	 * 도로명 <br>
	 * 필수여부 - Y
	 */
	String rn;
	
	/**
	 * 지하여부(0 : 지상, 1 : 지하) <br>
	 * 필수여부 - Y
	 */
	String udrtYn;
	
	/**
	 * 건물본번 <br>
	 * 필수여부 - Y
	 */
	Integer buldMnnm;
	
	/**
	 * 건물부번 <br>
	 * 필수여부 - Y
	 */
	Integer buldSlno; 
	
	/**
	 * 산여부(0 : 대지, 1 : 산) <br>
	 * 필수여부 - Y
	 */
	String mtYn;
	
	/**
	 * 지번본번(번지) <br>
	 * 필수여부 - Y
	 */
	Integer lnbrMnnm;
	
	/**
	 * 지번부번(호) <br>
	 * 필수여부 - Y
	 */
	Integer lnbrSlno;
	
	/**
	 * 읍면동일련번호 <br>
	 * 필수여부 - Y
	 */
	String emdNo;
	
	// 아래는 - API 서비스 제공항목 확대 (2020.12)
	
	/**
	 * * 2020년12월8일 추가된 항목 <br>
     * 변동이력여부(0: 현행 주소정보, 1: 요청변수의 keyword(검색어)가 변동된 주소정보에서 검색된 정보) <br>
     * 필수여부 - Y
	 */
	String hstryYn;

	/**
	 * * 2020년12월8일 추가된 항목 <br>
     * 관련지번 <br>
     * 필수여부 - N
	 */
	String relJibun;
	
	/**
	 * * 2020년12월8일 추가된 항목 <br>
	 * 관할주민센터 <br>
     * ※ 참고정보이며, 실제와 다를 수 있습니다. <br>
     * 필수여부 - N
	 */
	String hemdNm;
}
