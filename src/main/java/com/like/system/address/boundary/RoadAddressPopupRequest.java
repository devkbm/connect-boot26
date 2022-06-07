package com.like.system.address.boundary;

import org.springframework.util.StringUtils;

public record RoadAddressPopupRequest(
		/**
		 * 신청시 발급받은 승인키 - 필수
		 */
		String confmKey,
		/**
		 * 주소 검색 결과를 리턴받을 URL (통합검색창을 호출한 페이지) - 필수
		 */
		String returnUrl,
		/**
		 * 도로명주소 검색결과 화면 출력유형 <br/>
		 * 1 : 도로명, 2 : 도로명+지번+상세보기(관련지번, 관할주민센터), 3 : 도로명+상세보기(상세건물명), 4 : 도로명+지번+상세보기(관련지번, 관할주민센터, 상세건물명)
		 */
		String resultType,
		/**
		 * 상세주소 동/층/호정보 제공여부		<br/>
		 * Y : 제공, N : 미제공(직접입력)		<br/>
		 * ※ 자세한 내용은 상세주소API 메뉴에서 '팝업API 적용방법'을 참고하시기 바랍니다.
		 */
		String useDetailAddr		
		) {
	
	public RoadAddressPopupRequest {		
		if (!StringUtils.hasText(confmKey)) throw new IllegalArgumentException("승인키는 필수입니다.");
		if (!StringUtils.hasText(returnUrl)) throw new IllegalArgumentException("승인키는 필수입니다.");
	}
	
}

