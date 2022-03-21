package com.controller;

import com.model.Actor;
import com.model.Anime;
import com.service.ActorService;
import com.service.AnimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin()
@RequestMapping("/animes")
public class AnimeController
{
    private final AnimeService animeService;

    private AnimeController(AnimeService animeService)
    {
        this.animeService = animeService;
    }

    @GetMapping()
    public ResponseEntity<List<Anime>> getAnime()
    {
        return new ResponseEntity<>(animeService.getAnime(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeByID(@PathVariable Integer id)
    {
        return new ResponseEntity<>(animeService.getAnimeById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Anime> createAnime(@RequestBody Anime anime)
    {
        return new ResponseEntity<>(animeService.createAnime(anime), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Anime> deleteAnime(@PathVariable Integer id)
    {
        return new ResponseEntity<>(animeService.deleteAnime(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Anime> updateAnime(@PathVariable Integer id, @RequestBody Anime anime)
    {
        return new ResponseEntity<>(animeService.updateAnime(anime, id), HttpStatus.OK);
    }
}
