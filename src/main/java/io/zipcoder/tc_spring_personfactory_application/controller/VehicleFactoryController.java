package io.zipcoder.tc_spring_personfactory_application.controller;

import io.zipcoder.tc_spring_personfactory_application.domain.AbstractRandomVehicleFactory;
import io.zipcoder.tc_spring_personfactory_application.domain.Vehicle;
import io.zipcoder.tc_spring_personfactory_application.repository.VehicleRepository;
import io.zipcoder.tc_spring_personfactory_application.utilities.RandomVehicleFactoryLookUp;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;

/**
 * Created by leon on 8/14/17.
 */
public class VehicleFactoryController {
}
