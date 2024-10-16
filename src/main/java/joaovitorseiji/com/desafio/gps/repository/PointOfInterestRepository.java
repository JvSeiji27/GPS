package joaovitorseiji.com.desafio.gps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import joaovitorseiji.com.desafio.gps.enitity.PointOfInterest;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {
	
	@Query("SELECT p FROM PointOfInterest p WHERE (p.x >= :xMin AND p.x <= :xMax AND p.y >= :yMin AND p.y <= :yMax)")
	List<PointOfInterest> findNear(
	    @Param("xMin") long xMin, 
	    @Param("xMax") long xMax, 
	    @Param("yMin") long yMin, 
	    @Param("yMax") long yMax
	);
}
