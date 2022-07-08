package com.like.hrm.payitem.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.payitem.boundary.PayItemDTO;
import com.like.hrm.payitem.domain.model.PayItem;
import com.like.hrm.payitem.service.PayItemService;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class PayItemController {

	private PayItemService payItemService;
	
	public PayItemController(PayItemService payItemService) {
		this.payItemService = payItemService;		
	}
	
	@GetMapping("/api/hrm/payitem")
	public ResponseEntity<?> getHrmTypeList(PayItemDTO.SearchPayItem dto) {
		
		List<PayItemDTO.SavePayItem> list = payItemService.getPayItem(dto)
										   .stream()
										   .map(e -> PayItemDTO.SavePayItem.convert(e))
										   .collect(Collectors.toList());															
					
		return ResponseEntityUtil.toList(list											
										,String.format("%d 건 조회되었습니다.", list.size()));
	}
	
	@GetMapping("/api/hrm/payitem/{code}")
	public ResponseEntity<?> getPayItem(@PathVariable String code) {
		
		PayItem entity = payItemService.getPayItem(code);
						
		PayItemDTO.SavePayItem dto = PayItemDTO.SavePayItem.convert(entity);			
				
		return ResponseEntityUtil.toOne(dto											
									   ,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/payitem")
	public ResponseEntity<?> savePayItem(@RequestBody @Valid PayItemDTO.SavePayItem dto) {						
							
		payItemService.save(dto);						
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 저장되었습니다.", 1));
	}
	
		
	@DeleteMapping("/api/hrm/payitem/{code}")
	public ResponseEntity<?> deleteDutyCode(@PathVariable String code) {				
																		
		payItemService.delete(code);						
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 삭제되었습니다.", 1));
	}
}
