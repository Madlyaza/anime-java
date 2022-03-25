package com.service;

import com.dto.AnimeDTO;
import com.exception.BadRequestException;
import com.exception.DataNotFoundException;
import com.exception.NoContentException;
import com.mapper.AnimeMapper;
import com.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService
{
    private final AnimeRepository animeRepository;
    private final AnimeMapper animeMapper;

    public AnimeService (AnimeRepository animeRepository, AnimeMapper animeMapper)
    {
        this.animeRepository = animeRepository;
        this.animeMapper = animeMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all animes.
     *
     * @return response entity with list of all animes
     */
    public List<AnimeDTO> getAnime()
    {
        return animeMapper.mapFromEntityList(animeRepository.getAnimes());
    }

    /**
     * Maps Entity to DTO and returns a single anime.
     *
     * @param id of the anime to find
     * @return response entity with single anime
     */
    public AnimeDTO getAnimeById(Integer id)
    {
        try
        {
            return animeMapper.mapFromEntity(animeRepository.getAnimeById(id));
        }
        catch (Exception ex)
        {
            throw new DataNotFoundException("id: " + id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single anime.
     *
     * @param animeDTO anime to post
     * @return response entity with posted anime
     */
    public AnimeDTO createAnime(AnimeDTO animeDTO)
    {
        try
        {
            return animeMapper.mapFromEntity(animeRepository.uploadAnime(animeMapper.mapToEntity(animeDTO)));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single anime.
     *
     * @param id of the anime to delete
     * @return response entity with deleted anime
     */
    public AnimeDTO deleteAnime(Integer id)
    {
        try
        {
            return animeMapper.mapFromEntity(animeRepository.deleteAnime(id));
        }
        catch (Exception ex)
        {
            throw new NoContentException("id: " + id);
        }
    }

    /**
     * Maps Entity to DTO and puts a single anime.
     *
     * @param id of the anime to put
     * @param animeDTO anime to put
     * @return response entity with put anime
     */
    public AnimeDTO updateAnime(AnimeDTO animeDTO, Integer id)
    {
        try
        {
            return animeMapper.mapFromEntity(animeRepository.updateAnime(animeMapper.mapToEntity(animeDTO), id));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }
}
