package com.controller;

import com.dto.FeaturedInDTO;
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
    public ResponseEntity<List<FeaturedInDTO>> getFeatured()
    {
        return new ResponseEntity<>(featuredInService.getFeatured(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeaturedInDTO> getFeaturedById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(featuredInService.getFeaturedById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeaturedInDTO> createFeatured(@RequestBody FeaturedInDTO featuredInDTO)
    {
        return new ResponseEntity<>(featuredInService.createFeatured(featuredInDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FeaturedInDTO> deleteFeatured(@PathVariable Integer id)
    {
        return new ResponseEntity<>(featuredInService.deleteFeatured(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeaturedInDTO> updateAnime(@PathVariable Integer id, @RequestBody FeaturedInDTO featuredInDTO)
    {
        return new ResponseEntity<>(featuredInService.updateFeatured(featuredInDTO, id), HttpStatus.OK);
    }
}
