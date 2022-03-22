package com.service;

import com.exception.BadRequestException;
import com.exception.DataNotFoundException;
import com.exception.NoContentException;
import com.model.Actor;
import com.model.Anime;
import com.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService
{
    private final AnimeRepository animeRepository;

    public AnimeService (AnimeRepository animeRepository)
    {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAnime()
    {
        return animeRepository.getAnimes();
    }

    public Anime getAnimeById(Integer id)
    {
        try
        {
            return animeRepository.getAnimeById(id);
        }
        catch (Exception ex)
        {
            throw new DataNotFoundException("id: " + id);
        }
    }

    public Anime createAnime(Anime anime)
    {
        try
        {
            return animeRepository.uploadAnime(anime);
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    public Anime deleteAnime(Integer id)
    {
        try
        {
            return animeRepository.deleteAnime(id);
        }
        catch (Exception ex)
        {
            throw new NoContentException("id: " + id);
        }
    }

    public Anime updateAnime(Anime anime, Integer id)
    {
        try
        {
            return animeRepository.updateAnime(anime, id);
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }
}
