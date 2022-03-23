package com.controller;

import com.dto.AnimeDTO;
import com.service.AnimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/json/animes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnimeController
{
    private final AnimeService animeService;

    private AnimeController(AnimeService animeService)
    {
        this.animeService = animeService;
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<AnimeDTO>> getAnime()
    {
        return new ResponseEntity<>(animeService.getAnime(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AnimeDTO> getAnimeByID(@PathVariable Integer id)
    {
        return new ResponseEntity<>(animeService.getAnimeById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<AnimeDTO> createAnime(@RequestBody AnimeDTO animeDTO)
    {
        return new ResponseEntity<>(animeService.createAnime(animeDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnimeDTO> deleteAnime(@PathVariable Integer id)
    {
        return new ResponseEntity<>(animeService.deleteAnime(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimeDTO> updateAnime(@PathVariable Integer id, @RequestBody AnimeDTO animeDTO)
    {
        return new ResponseEntity<>(animeService.updateAnime(animeDTO, id), HttpStatus.OK);
    }
}
