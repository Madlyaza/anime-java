package com.controller;

import com.model.Studio;
import com.service.StudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studios")
public class StudioController
{
    private final StudioService studioService;

    private StudioController(StudioService studioService)
    {
        this.studioService = studioService;
    }

    @GetMapping()
    public ResponseEntity<List<Studio>> getStudios()
    {
        return new ResponseEntity<>(studioService.getStudios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Studio> getStudioById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(studioService.getStudioById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Studio> createStudio(@RequestBody Studio studio)
    {
        return new ResponseEntity<>(studioService.createStudio(studio), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Studio> deleteStudio(@PathVariable Integer id)
    {
        return new ResponseEntity<>(studioService.deleteStudio(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Studio> updateStudio(@PathVariable Integer id, @RequestBody Studio studio)
    {
        return new ResponseEntity<>(studioService.updateStudio(studio, id), HttpStatus.OK);
    }
}
