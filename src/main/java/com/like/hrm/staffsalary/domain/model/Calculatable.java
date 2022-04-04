package com.like.hrm.staffsalary.domain.model;

import java.math.BigDecimal;

/**
 * 계산식에 해당 하는 결과 값을 리턴한다. 
 */
public interface Calculatable {

	public BigDecimal calc(String expression);
}
