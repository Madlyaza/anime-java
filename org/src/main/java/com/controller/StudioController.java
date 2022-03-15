package com.controller;

import com.model.Studio;
import com.service.StudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/studios")
public class StudioController
{
    private final StudioService studioService;

    private StudioController(StudioService studioService)
    {
        this.studioService = studioService;
    }

    @GetMapping()
    public ResponseEntity<List<Studio>> getStudio()
    {
        return new ResponseEntity<>(studioService.getStudios(), HttpStatus.OK);
    }
}
