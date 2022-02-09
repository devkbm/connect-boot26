package com.like.hrm.staff.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ResidentRegistrationNumber {

	@Column(name="RREGNO")
	String number;
	
	private ResidentRegistrationNumber(String number) {
		this.number = number;
	}
	
	public static ResidentRegistrationNumber of(String number) {
		return new ResidentRegistrationNumber(number);
	}
	
	/**
	 * 주민번호의 7번째 숫자로 성별을 구분한다.
	 * @param residentRegistrationNumber
	 * @return M[남자], F[여자], X[오류]
	 */
	public String getGender() {						
					
		return switch (this.number.substring(6, 7)) {
			case "1","3","5","7","9" -> "M";
			case "0","2","4","6","8" -> "F";
			default -> "X";
		};		
	}
	
	public LocalDate getBirthDay() {
		
		return LocalDate.now();
	}
}
