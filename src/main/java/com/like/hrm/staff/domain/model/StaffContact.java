package com.like.hrm.staff.domain.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.like.system.core.jpa.vo.Address;
import com.like.system.core.jpa.vo.PhoneNumber;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class StaffContact {

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "address_type", column = @Column(name = "HOME_ADDR_TYPE")),
        @AttributeOverride(name = "post_number", column = @Column(name = "HOME_POST_NO")),
        @AttributeOverride(name = "main_address", column = @Column(name = "HOME_MAIN_ADDR")),
        @AttributeOverride(name = "sub_address", column = @Column(name = "HOME_SUB_ADDR"))
	})
	Address home;
	
	//@Comment("내선전화번호")
	@Embedded
	@AttributeOverride(name = "number", column = @Column(name = "EXTENSION_PHONE_NO"))	
	PhoneNumber extensionNumber;
	
	//@Comment("모바일전화번호")	
	@Embedded
	@AttributeOverride(name = "number", column = @Column(name = "MOBILE_PHONE_NO"))
	PhoneNumber mobileNumber;
		
}
