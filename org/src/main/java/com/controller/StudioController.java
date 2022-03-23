package com.controller;

import com.dto.StudioDTO;
import com.model.Studio;
import com.service.StudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/json/studios", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudioController
{
    private final StudioService studioService;

    private StudioController(StudioService studioService)
    {
        this.studioService = studioService;
    }

    @GetMapping()
    public ResponseEntity<List<StudioDTO>> getStudios()
    {
        return new ResponseEntity<>(studioService.getStudios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudioDTO> getStudioById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(studioService.getStudioById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudioDTO> createStudio(@RequestBody StudioDTO studioDTO)
    {
        return new ResponseEntity<>(studioService.createStudio(studioDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudioDTO> deleteStudio(@PathVariable Integer id)
    {
        return new ResponseEntity<>(studioService.deleteStudio(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudioDTO> updateStudio(@PathVariable Integer id, @RequestBody StudioDTO studioDTO)
    {
        return new ResponseEntity<>(studioService.updateStudio(studioDTO, id), HttpStatus.OK);
    }
}
