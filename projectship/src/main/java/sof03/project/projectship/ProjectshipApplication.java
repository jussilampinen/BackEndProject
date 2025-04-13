package sof03.project.projectship;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
import sof03.project.projectship.domain.User;
import sof03.project.projectship.domain.UserRepository;
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
			CaptainRepository captainRepository, UserRepository userRepository) {
		return (args) -> {
			User admin = new User("admin", new BCryptPasswordEncoder().encode("admin"), "ADMIN");
			User user = new User("user", new BCryptPasswordEncoder().encode("user"), "USER");

			userRepository.save(admin);
			userRepository.save(user);
    
			// Add owner
			Owner whiteStarLine = new Owner("White Star Line", "Company", "United Kingdom");
			ownerRepository.save(whiteStarLine);

			Owner cunardLine = new Owner("Cunard Line", "Company", "United Kingdom");
			ownerRepository.save(cunardLine);

			Owner royalNavy = new Owner("Royal Navy", "Military", "United Kingdom");
			ownerRepository.save(royalNavy);

			// Add Ship Type
			ShipType passengerLiner = new ShipType("Passenger Liner");
			shipTypeRepository.save(passengerLiner);

			ShipType oceanLiner = new ShipType("Ocean Liner");
			shipTypeRepository.save(oceanLiner);

			ShipType transport = new ShipType("Armed Transport");
			shipTypeRepository.save(transport);

			// Add Port
			Port southampton = new Port("Southampton", "UK");
			portRepository.save(southampton);

			Port liverpool = new Port("Liverpool", "UK");
			portRepository.save(liverpool);

			Port portsmouth = new Port("Portsmouth", "UK");
			portRepository.save(portsmouth);

			// Add Fate
			Fate sank = new Fate("Sank during maiden voyage");
			fateRepository.save(sank);

			Fate torpedoed = new Fate("Sunk by German U-boat");
			fateRepository.save(torpedoed);

			Fate mutinied = new Fate("Crew mutinied and took control");
			fateRepository.save(mutinied);

			// Add Captain
			Captain edwardSmith = captainRepository.save(new Captain("Edward Smith", 1850, "British"));

			Captain williamTurner = captainRepository.save(new Captain("William Turner", 1856, "British"));

			Captain williamBligh = captainRepository.save(new Captain("William Bligh", 1754, "British"));

			// Add Ship
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
					new ArrayList<>()));
					//new ArrayList<>()));

			Ship lusitania = shipRepository.save(new Ship(
					"Lusitania",
					44060,
					240.2,
					26.8,
					1906,
					oceanLiner,
					liverpool,
					cunardLine,
					torpedoed,
					new ArrayList<>()));
					// new ArrayList<>()));

			Ship bounty = shipRepository.save(new Ship(
					"HMS Bounty",
					215,
					27.7,
					7.4,
					1784,
					transport,
					portsmouth,
					royalNavy,
					mutinied,
					new ArrayList<>()));
					// new ArrayList<>()));
/* 
			// For my own Sanity
			System.out.println("\n \nBefore creating the ShipCaptain: \n Captain ID: " + edwardSmith.getCaptainId()
					+ ", Ship ID " + titanic.getShipId() + "\n \n ");

			// Add Ship-Captain
			ShipCaptain scTitanic = new ShipCaptain(
					titanic,
					edwardSmith,
					LocalDate.of(1909, 7, 1),
					LocalDate.of(1912, 4, 14));

			shipCaptainRepository.save(scTitanic);
			titanic.getShipCaptain().add(scTitanic);
			shipRepository.save(titanic);

			ShipCaptain scLusitania = new ShipCaptain(
					lusitania,
					williamTurner,
					LocalDate.of(1913, 1, 1),
					LocalDate.of(1915, 5, 7));

			shipCaptainRepository.save(scLusitania);
			lusitania.getShipCaptain().add(scLusitania);
			shipRepository.save(lusitania);

			ShipCaptain scBounty = new ShipCaptain(
					bounty,
					williamBligh,
					LocalDate.of(1787, 12, 23),
					LocalDate.of(1789, 4, 28));

			shipCaptainRepository.save(scBounty);
			bounty.getShipCaptain().add(scBounty);
			shipRepository.save(bounty);

			System.out.println("\n\n ShipCaptainId: " + scTitanic.getId() + "\n \n");
*/
			// Add Events
			VoyageEvent voyage1 = new VoyageEvent(titanic, "Maiden Voyage", "Atlantic Ocean", LocalDate.of(1912, 4, 10),
					"Departed from Southampton");
			VoyageEvent voyage2 = new VoyageEvent(titanic, "Sinking", "North Atlantic", LocalDate.of(1912, 4, 14),
					"Collided with iceberg and sank");

			titanic.getEvents().add(voyage1);
			titanic.getEvents().add(voyage2);

			VoyageEvent lusitaniaEvent = new VoyageEvent(lusitania, "Sinking", "Celtic Sea", LocalDate.of(1915, 5, 7),
					"Torpedoed by German U-boat U-20");

			lusitania.getEvents().add(lusitaniaEvent);

			VoyageEvent bountyEvent = new VoyageEvent(bounty, "Mutiny", "South Pacific", LocalDate.of(1789, 4, 28),"Famous mutiny led by Fletcher Christian");
			bounty.getEvents().add(bountyEvent);

			shipRepository.save(titanic);
			shipRepository.save(lusitania);
			shipRepository.save(bounty);
		};
	}
}


