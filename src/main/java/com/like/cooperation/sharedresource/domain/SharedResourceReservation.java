package com.like.cooperation.sharedresource.domain;

import java.time.LocalDateTime;

public class SharedResourceReservation {

	Long id;
	
	LocalDateTime from;
	
	LocalDateTime to;
	
	Boolean isApproval;
	
	SharedResource resource;
}
