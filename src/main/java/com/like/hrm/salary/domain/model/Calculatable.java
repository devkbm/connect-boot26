package com.like.hrm.salary.domain.model;

import java.math.BigDecimal;

/**
 * 계산식에 해당 하는 결과 값을 리턴한다.
 * @author cb457
 *
 */
public interface Calculatable {

	public BigDecimal calc(String expression);
}
