package sof03.project.projectship;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sof03.project.projectship.domain.Captain;
import sof03.project.projectship.domain.CaptainRepository;
import sof03.project.projectship.domain.Fate;
import sof03.project.projectship.domain.FateRepository;
import sof03.project.projectship.domain.Owner;
import sof03.project.projectship.domain.OwnerRepository;
import sof03.project.projectship.domain.Port;
import sof03.project.projectship.domain.PortRepository;
import sof03.project.projectship.domain.Ship;
import sof03.project.projectship.domain.ShipCaptain;
import sof03.project.projectship.domain.ShipCaptainId;
import sof03.project.projectship.domain.ShipCaptainRepository;
import sof03.project.projectship.domain.ShipRepository;
import sof03.project.projectship.domain.ShipType;
import sof03.project.projectship.domain.ShipTypeRepository;
import sof03.project.projectship.domain.VoyageEvent;

@SpringBootApplication
public class ProjectshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectshipApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ShipRepository shipRepository, OwnerRepository ownerRepository,
			ShipCaptainRepository shipCaptainRepository,
			ShipTypeRepository shipTypeRepository, PortRepository portRepository, FateRepository fateRepository,
			CaptainRepository captainRepository) {
		return (args) -> {
			// Related Entities
			Owner whiteStarLine = new Owner("White Star Line", "Company", "United Kingdom");
			ownerRepository.save(whiteStarLine);

			ShipType passengerLiner = new ShipType("Passenger Liner");
			shipTypeRepository.save(passengerLiner);

			Port southampton = new Port("Southampton", "UK");
			portRepository.save(southampton);

			Fate sank = new Fate("Sank during maiden voyage");
			fateRepository.save(sank);

			// Captain
			Captain edwardSmith = captainRepository.save(new Captain("Edward Smith", 1850, "British"));

			// Ship
			Ship titanic = shipRepository.save(new Ship(
					"Titanic",
					52310,
					269.1,
					28.2,
					1912,
					passengerLiner,
					southampton,
					whiteStarLine,
					sank,
					new ArrayList<>(),
					new ArrayList<>()));

			// For my own Sanity
			System.out.println("\n \nBefore creating the ShipCaptain: \n Captain ID: " + edwardSmith.getCaptainId()
					+ ", Ship ID " + titanic.getShipId() + "\n \n ");

			// Ship-Captain
			ShipCaptain sc = new ShipCaptain(
					titanic,
					edwardSmith,
					LocalDate.of(1909, 7, 1),
					LocalDate.of(1912, 4, 14)
			);

			System.out.println("\n\n ShipCaptainId: " + sc.getId() + "\n \n");
			shipCaptainRepository.save(sc);

			titanic.getShipCaptain().add(sc);
			shipRepository.save(titanic);

			// Events
			VoyageEvent voyage1 = new VoyageEvent(titanic, "Maiden Voyage", "Atlantic Ocean", LocalDate.of(1912, 4, 10),
					"Departed from Southampton");
			VoyageEvent voyage2 = new VoyageEvent(titanic, "Sinking", "North Atlantic", LocalDate.of(1912, 4, 14),
					"Collided with iceberg and sank");

			titanic.getEvents().add(voyage1);
			titanic.getEvents().add(voyage2);

			shipRepository.save(titanic);
		};
	}

}
