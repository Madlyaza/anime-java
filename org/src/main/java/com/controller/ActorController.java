package com.controller;

import com.dto.ActorDTO;
import com.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/json/actors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorController
{
    private final ActorService actorService;

    private ActorController(ActorService actorService)
    {
        this.actorService = actorService;
    }

    @GetMapping()
    public ResponseEntity<List<ActorDTO>> getActors()
    {
        return new ResponseEntity<>(actorService.getActors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorByID(@PathVariable Integer id)
    {
        return new ResponseEntity<>(actorService.getActorsById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActorDTO> createActor(@RequestBody ActorDTO actorDTO)
    {
        return new ResponseEntity<>(actorService.createActor(actorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorDTO> deleteActor(@PathVariable Integer id)
    {
        return new ResponseEntity<>(actorService.deleteActor(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActorDTO> updateActor(@PathVariable Integer id, @RequestBody ActorDTO actorDTO)
    {
        return new ResponseEntity<>(actorService.updateActor(actorDTO, id), HttpStatus.OK);
    }
}
