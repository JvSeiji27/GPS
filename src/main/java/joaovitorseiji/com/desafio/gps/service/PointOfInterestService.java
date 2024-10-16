package joaovitorseiji.com.desafio.gps.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public List<PointOfInterest> findAll(int page, int pageSize){
		List<PointOfInterest> allPoints = new ArrayList<>();
		
		Page <PointOfInterest> pagina;
		
		do {
			Pageable pageable = PageRequest.of(page, pageSize);
			pagina = repository.findAll(pageable);
			allPoints.addAll(pagina.getContent());
			System.out.println("Pagina == " + page);
			page++;
		}while(pagina.hasNext());
		
		return allPoints;
	}
	
	public List<PointOfInterest> findNearMe(long xMin, long xMax, long yMin, long yMax){
		return repository.findNear(xMin, xMax, yMin, yMax);
	}
}
