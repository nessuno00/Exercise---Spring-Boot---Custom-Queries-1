package Controller;


import com.example.demo.Flight;
import com.example.demo.FlightRepository;
import com.example.demo.FlightStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @GetMapping
    public List<Flight> flightList ()  {
        return flightRepository.findAll();
    }

    @PostMapping("/provision")
    public void provisionFlights() {
        for (int i = 0; i < 50; i++) {
            Flight flight = new Flight();

            flight.setDescription(generateRandomString());
            flight.setFromAirport(generateRandomString());
            flight.setStatus(FlightStatus.ONTIME);
            flight.setToAirport(generateRandomString());
            flightRepository.save(flight);

        }

    }
    private String generateRandomString() {
        int leftLimit = 115;
        int rightLimit = 122;
        int targetStringLength = 10;

        StringBuilder buffer = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {

                int randomLimitedInt = leftLimit + (int)
                        (Math.random() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            return buffer.toString();
        }
    }

