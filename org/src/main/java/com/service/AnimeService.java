package com.service;

import com.dto.AnimeDTO;
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

    public List<AnimeDTO> getAnime()
    {
        return animeMapper.mapFromEntityList(animeRepository.getAnimes());
    }

    public AnimeDTO getAnimeById(Integer id)
    {
//        try
//        {
            return animeMapper.mapFromEntity(animeRepository.getAnimeById(id));
//        }
//        catch (Exception ex)
//        {
//            throw new DataNotFoundException("id: " + id);
//        }
    }

    public AnimeDTO createAnime(AnimeDTO animeDTO)
    {
//        try
//        {
            return animeMapper.mapFromEntity(animeRepository.uploadAnime(animeMapper.mapToEntity(animeDTO)));
//        }
//        catch (Exception ex)
//        {
//            throw new BadRequestException();
//        }
    }

    public AnimeDTO deleteAnime(Integer id)
    {
//        try
//        {
            return animeMapper.mapFromEntity(animeRepository.deleteAnime(id));
//        }
//        catch (Exception ex)
//        {
//            throw new NoContentException("id: " + id);
//        }
    }

    public AnimeDTO updateAnime(AnimeDTO animeDTO, Integer id)
    {
//        try
//        {
            return animeMapper.mapFromEntity(animeRepository.updateAnime(animeMapper.mapToEntity(animeDTO), id));
//        }
//        catch (Exception ex)
//        {
//            throw new BadRequestException();
//        }
    }
}
