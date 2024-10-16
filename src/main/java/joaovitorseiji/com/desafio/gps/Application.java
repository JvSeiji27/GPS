package joaovitorseiji.com.desafio.gps;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import joaovitorseiji.com.desafio.gps.enitity.PointOfInterest;
import joaovitorseiji.com.desafio.gps.repository.PointOfInterestRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	PointOfInterestRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<PointOfInterest> list = Arrays.asList(new PointOfInterest("Lanchonete",27L,12L), new PointOfInterest("Posto",31L,18L), new PointOfInterest("Joalheria",15L,12L),new PointOfInterest("Floricultura",19L,21L), new PointOfInterest("Pub",12L, 8L), new PointOfInterest("Supermercado",23L,6L), new PointOfInterest("Churrascaria",28L,2L));
		repository.saveAll(list);
		
	}

}
