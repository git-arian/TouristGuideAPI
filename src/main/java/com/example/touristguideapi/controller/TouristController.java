package com.example.touristguideapi.controller;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.repository.TouristRepository;
import com.example.touristguideapi.service.TouristService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.nio.file.Path;
import java.util.ArrayList;
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

    // PathVariable = værdier som del af stien (identitet af ressourcen)
    // api/users{id} > api/users{43}
    // på samme måde attractions/{city} > attractions/cph, og ku på samme måde være attractions/berlin
    @GetMapping("/{city}")
    public ResponseEntity<List<TouristAttraction>> getAttractionsByCity(@PathVariable String city) {
        List<TouristAttraction> attractions = service.getByCity(city);

        if(attractions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attractions);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<TouristAttraction> createAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction saved = service.createAttraction(attraction);
        if (saved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(attraction);
    }

    /*
    @PostMapping("/update/{name}")
    @ResponseBody
    public ResponseEntity<TouristAttraction> updateAttraction(PathVariable String name, @RequestBody TouristAttraction updated) {

    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteAttraction(@RequestBody TouristAttraction attraction) {

    }
     */
}
