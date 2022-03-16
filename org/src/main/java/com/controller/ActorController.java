package com.controller;

import com.model.Actor;
import com.model.Studio;
import com.service.ActorService;
import com.service.StudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/actors")
public class ActorController
{
    private final ActorService actorService;

    private ActorController(ActorService actorService)
    {
        this.actorService = actorService;
    }

    @GetMapping()
    public ResponseEntity<List<Actor>> getActors()
    {
        return new ResponseEntity<>(actorService.getActors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorByID(@PathVariable Integer id)
    {
        return new ResponseEntity<>(actorService.getActorsById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor)
    {
        return new ResponseEntity<>(actorService.createActor(actor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Actor> deleteActor(@PathVariable Integer id)
    {
        return new ResponseEntity<>(actorService.deleteActor(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Actor> updateActor(@PathVariable Integer id, @RequestBody Actor actor)
    {
        return new ResponseEntity<>(actorService.updateActor(actor, id), HttpStatus.OK);
    }
}
