package com.like.system.address.boundary;

import java.util.Objects;

import org.springframework.util.StringUtils;

/**
 * 도로명주소 API 신청 URL - https://www.juso.go.kr/addrlink/devAddrLinkRequestWrite.do?returnFn=write&cntcMenu=URL <br>
 * 도로명주소 검색 API URL - https://www.juso.go.kr/addrlink/addrLinkApi.do 
 */
public record RoadAddressApiRequest(
		/**
		 * 신청시 발급받은 승인키 <br>
		 * 필수여부 - Y 
		 */
		//String confmKey,
		/**
		 * 현재 페이지 번호 <br>
		 * 필수여부 - Y <br> 
		 * 기본값 1
		 */
		Integer currentPage,
		/**
		 * 페이지당 출력할 결과 Row 수 <br>
		 * 필수여부 - Y <br>
		 * 기본값 10  
		 */
		Integer countPerPage,
		/**
		 * 주소 검색어 <br>
		 * 필수여부 - Y
		 */
		String keyword,
		/**
		 * 검색결과형식 설정(xml, json) <br>
		 * 필수여부 - N <br>
		 * 기본값 xml
		 */
		String resultType,
		/**
		 * 	* 2020년12월8일 추가된 항목 <br>
		 * 	변동된 주소정보 포함 여부 <br>
		 * 	필수여부 - N <br>
		 * 	기본값 N
		 */
		String hstryYn,
		/**
		 *	* 2020년12월8일 추가된 항목 <br>
		 *  정확도순 정렬(none), 우선정렬(road: 도로명 포함, location: 지번 포함) <br>
		 *	※ keyword(검색어)가 우선정렬 항목에 포함된 결과 우선 표출 <br>
		 *	필수여부 - N <br>
		 *  기본값 none
		 */
		String firstSort,
		/**
		 * 	* 2020년12월8일 추가된 항목 <br>
		 * 	출력결과에 추가된 항목(hstryYn, relJibun, hemdNm) 제공여부 <br>
		 * 	※ 해당 옵션으로 추가제공되는 항목의 경우, 추후 특정항목이 제거되거나 추가될 수 있으니 적용 시 고려해주시기 바랍니다. <br>
		 *  필수여부 - N <br>
		 *  기본값 N
		 */
		String addInfoYn
		) {
	
	public RoadAddressApiRequest {		
		//if (!StringUtils.hasText(confmKey)) throw new IllegalArgumentException("승인키는 필수입니다.");
		//if (!StringUtils.hasText(keyword)) throw new IllegalArgumentException("검색어는 필수입니다.");
		if (Objects.isNull(currentPage)) currentPage = 1;
		if (Objects.isNull(countPerPage)) countPerPage = 10;
	}
	
}

