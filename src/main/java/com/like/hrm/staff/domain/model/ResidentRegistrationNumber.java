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
	
	/**
	 * 13자리 주민번호정보 ex) 1234567890123
	 * @param number 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static ResidentRegistrationNumber of(String number) throws IllegalArgumentException{		
		if (!valid(number)) throw new IllegalArgumentException("주민등록번호 형식에 맞지 않습니다. ->" + number);							
		
		return new ResidentRegistrationNumber(number);
	}
	
	/**
	 * 주민번호의 7번째 숫자로 성별을 구분한다.
	 * @param residentRegistrationNumber
	 * @return M[남자], F[여자], X[오류]
	 */
	public String getGender() {						
					
		return switch (this.getSexCode()) {
			case "1","3","5","7","9" -> "M";
			case "0","2","4","6","8" -> "F";
			default -> "X";
		};		
	}
	
	/**
	 * 주민등록번호 앞6자리로 생일을 추출한다.
	 * @return 생일 
	 */
	public LocalDate getBirthDay() {														
		return LocalDate.of(getYear(), getMonth(), getDayOfMonth());
	}
	
	/**
	 * 주민등록번호 7번째 숫자를 추출한다.
	 * ex) 1234567890123 -> 7
	 * @return
	 */
	private String getSexCode() {
		return this.number.substring(6, 7);
	}
	
	private int getYear() {
		String yearYY = this.number.substring(0, 2);
		String year = switch (this.getSexCode()) {
			case "9","0" -> "18" + yearYY;
			case "1","2","5","6" -> "19" + yearYY;
			case "3","4","7","8" -> "18" + yearYY;
			default -> "d";
		};
		
		return Integer.parseInt(year);
	}
	
	private int getMonth() {
		return Integer.parseInt(this.number.substring(2, 4));
	}
	
	private int getDayOfMonth() {
		return Integer.parseInt(this.number.substring(4, 6));
	}
	
	private static boolean valid(String number) {
		if (number.length() != 13) return false;
		
		return isNumber(number);
	}
	
	private static boolean isNumber(String numberStr){
		return numberStr.matches("^[0-9]*$");
	}		
}
