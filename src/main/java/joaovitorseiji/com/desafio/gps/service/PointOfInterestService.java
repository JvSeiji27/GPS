package joaovitorseiji.com.desafio.gps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import joaovitorseiji.com.desafio.gps.dto.PointOfInterestDto;
import joaovitorseiji.com.desafio.gps.enitity.PointOfInterest;
import joaovitorseiji.com.desafio.gps.repository.PointOfInterestRepository;

@Service
public class PointOfInterestService {
	
	@Autowired
	PointOfInterestRepository repository;
	
	public void createPoi(PointOfInterestDto poi) {
		PointOfInterest newPoi = new PointOfInterest(poi.name(),poi.x(),poi.y());
		repository.save(newPoi);
	}
	
	public Page<PointOfInterest> findAll(int page, int pageSize){
		return repository.findAll(PageRequest.of(page, pageSize));
	}
	
	public List<PointOfInterest> findNearMe(long xMin, long xMax, long yMin, long yMax){
		return repository.findNear(xMin, xMax, yMin, yMax);
	}
}
