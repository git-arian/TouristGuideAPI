package com.example.touristguideapi.controller;
import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

     @GetMapping
     @ResponseBody
     public ResponseEntity<List<TouristAttraction>> getAllAttractions(){
         List<TouristAttraction> all = service.getAllAttractions();
         return ResponseEntity.ok(all);
     }

    @GetMapping("/name/{name}")
    @ResponseBody
    public ResponseEntity<List<TouristAttraction>> getAttractionsByName(@PathVariable String name) {
        List<TouristAttraction> attractions = service.getByName(name);
        if (attractions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(attractions, HttpStatus.OK);
        // returner vores data/header/body (attractions) og en statuskode OK (200)
    }


    // PathVariable = værdier som del af stien (identitet af ressourcen)
    // api/users{id} > api/users{43}
    // på samme måde attractions/{city} > attractions/cph, og ku på samme måde være attractions/berlin
    @GetMapping("/city/{city}") // endpoint
    @ResponseBody
    public ResponseEntity<List<TouristAttraction>> getAttractionsByCity(@PathVariable String city) {
        List<TouristAttraction> attractions = service.getByCity(city);
        if (attractions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attractions);
    }

    /*
    CRUD (create, read, update, delete)

    Flow for GET /attractions/{city} (fx /attractions/cph):
    User → kalder http://localhost:8080/attractions/cph
    Controller → "Service, find attraktioner i cph"
    Service → "Repository, slå 'cph' op"
    Repository → søger i ArrayList og returnerer en LISTE af matches
    Service → sender listen videre op
    Controller → hvis listen er tom: 404 Not Found ellers 200 OK med JSON
    */

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<TouristAttraction> createAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction saved = service.createAttraction(attraction);
        if (saved == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // PathVariable String name, (path, string name (?name="value") (bliver læst ind som en variabel, fx /update/tivoli
    // altså, name = tivoli (den attraktion vi vil opdatere.)
    // @RequestBody TouristAttraction > json laves om til TA objekt > så kan det specifikke {name }TouristAttraktion objekt opdateres
    @PostMapping("/update/{name}") // endpoint, attractions/update/{name}
    @ResponseBody
    public ResponseEntity<TouristAttraction> updateAttraction(@PathVariable String name, @RequestBody TouristAttraction updated) {
        TouristAttraction saved = service.updateAttraction(name, updated);
        if (saved == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saved);
    }
   // Update -> skal vide hvem (via {name}) (@PathVariable)
   // og hvad nyt indhold skal være (via @RequestBody)

   // Delete -> skal kun vide hvem (via {name}) (@PathVariable, ikke have nye felter!!!
    @PostMapping("/delete/{name}")
    @ResponseBody
    public ResponseEntity<Void> deleteAttraction(@PathVariable String name) {
        boolean deleted = service.deleteAttraction(name);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
