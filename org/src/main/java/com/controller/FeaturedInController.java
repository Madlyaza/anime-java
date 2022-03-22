package com.controller;

import com.model.Anime;
import com.model.FeaturedIn;
import com.service.AnimeService;
import com.service.FeaturedInService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/featured")
public class FeaturedInController
{
    private final FeaturedInService featuredInService;

    private FeaturedInController(FeaturedInService featuredInService)
    {
        this.featuredInService = featuredInService;
    }

    @GetMapping()
    public ResponseEntity<List<FeaturedIn>> getFeatured()
    {
        return new ResponseEntity<>(featuredInService.getFeatured(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeaturedIn> getFeaturedById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(featuredInService.getFeaturedById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeaturedIn> createFeatured(@RequestBody FeaturedIn featuredIn)
    {
        return new ResponseEntity<>(featuredInService.createFeatured(featuredIn), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FeaturedIn> deleteFeatured(@PathVariable Integer id)
    {
        return new ResponseEntity<>(featuredInService.deleteFeatured(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeaturedIn> updateAnime(@PathVariable Integer id, @RequestBody FeaturedIn featuredIn)
    {
        return new ResponseEntity<>(featuredInService.updateFeatured(featuredIn, id), HttpStatus.OK);
    }
}
