package joaovitorseiji.com.desafio.gps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import joaovitorseiji.com.desafio.gps.dto.PointOfInterestDto;
import joaovitorseiji.com.desafio.gps.enitity.PointOfInterest;
import joaovitorseiji.com.desafio.gps.service.PointOfInterestService;

@RestController
@RequestMapping("/points-of-interests")
public class PointOfInterestController {
	
	@Autowired
	PointOfInterestService service;
	
	@PostMapping
	public ResponseEntity<Void> createPointOfInterest(@RequestBody @Valid PointOfInterestDto dto){
		service.createPoi(dto);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<PointOfInterest>> listPoi(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
		var body = service.findAll(page, pageSize);
		return ResponseEntity.ok(body);
	}
	
	@GetMapping("/near-me")
	public ResponseEntity<List<PointOfInterest>> nearPoi(@RequestParam(name = "x") Long x, @RequestParam(name = "y") Long y, @RequestParam(name = "maxValue") Long dMax){
		Long xMin = x - dMax;
		Long xMax = x + dMax;
		Long yMin = y - dMax;
		Long yMax = y + dMax;
		
		var body = service.findNearMe( xMin, xMax, yMin,  yMax) 
		.stream().filter(p-> distanceBetweenPoints(x, y, p.getX(), p.getY()) <= dMax).toList();
		return ResponseEntity.ok(body);
	}
	
	private Double distanceBetweenPoints(Long x1, Long y1, Long x2, Long y2) {
		return Math.sqrt(Math.pow((x1-x2),2)+ Math.pow((y1-y2),2));
	}
}
