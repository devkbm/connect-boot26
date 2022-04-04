package com.like.hrm.anualleave.boundary;

import java.time.LocalDate;
import java.util.Objects;

import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;

public class AnualLeaveDTO {

	public record SaveAnualLeave(
			Integer yyyy,
			String staffId,
			LocalDate base,
			LocalDate from,
			LocalDate to,
			Double cnt,
			Double use_cnt,
			Long total_work_days,
			Long except_days,
			Boolean isIntraAnual,
			String comment
			) {
		
		public SaveAnualLeave {		
			Objects.requireNonNull(yyyy);		        
			Objects.requireNonNull(staffId);			
		}
		
		public static SaveAnualLeave convertDTO(AnualLeave e) {
			if (e == null) return null;
			
			AnualLeaveId id = e.getId();			
			return new SaveAnualLeave(id.getYyyy()
									 ,id.getStaffId() 
									 ,e.getBase()
									 ,e.getFrom()
									 ,e.getTo()
									 ,e.getCnt()
									 ,e.getUse_cnt()
									 ,e.getTotal_work_days()
									 ,e.getExcept_days()
									 ,e.getIsIntraAnual()
									 ,e.getComment());
		}
		
		public AnualLeave newAnualLeave() {
			return new AnualLeave(new AnualLeaveId(yyyy, staffId)
								 ,base
								 ,from
								 ,to);
		}
		
		public void modifyEntity(AnualLeave entity) {
			entity.modify(base, from, to);
		}
		
		
	}
		
}
