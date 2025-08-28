package com.example.touristguideapi.service;
import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //"annotering @Service
public class TouristService {
    private final TouristRepository repository;


    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractions();
    }

    public List<TouristAttraction> getByCity(String city) {
        return repository.getByCity(city);
    }

    public TouristAttraction createAttraction(TouristAttraction attraction){
        return repository.createAttraction(attraction);
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction){
        return repository.updateAttraction(name, updatedAttraction);
    }

    public TouristAttraction deleteAttraction() {

    }





    //Klassen skal indeholde CRUD (create,update,read,delete) metoder svarende til repository klassen og
    // delegere kald til relevante metoder
    // dvs: fx , user går på http://mywebsite/cph
    // controller siger til service, hey find mig info om cph
    // og service kalder så videre til repository, og får sendt den specifikke information tilbage
    /*>>>
    1.	User → http://mywebsite/cph
	2.	Controller → siger: “Service, find mig attraktionen cph”
	3.	Service → siger: “Repository, slå lige cph op”
	4.	Repository → finder dataen i ArrayListen og sender tilbage.
	5.	Service → sender den videre op.
	6.	Controller → pakker den i ResponseEntity og returnerer JSON til brugeren.
     */
}
