package com.mapper;

import com.dto.AnimeDTO;
import com.model.Anime;
import com.model.Studio;
import com.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("animeMapperComponent")
public class AnimeMapper implements EntityMapper<Anime, AnimeDTO>
{
    @Override
    public AnimeDTO mapFromEntity(Anime anime) {
        return new AnimeDTO(
            anime.getId(),
            anime.getStudio(),
            anime.getName(),
            anime.getCritic_score(),
            anime.getRelease_date()
        );
    }

    @Override
    public Anime mapToEntity(AnimeDTO animeDTO) {
        return new Anime(
                animeDTO.getId(),
                animeDTO.getStudio(),
                animeDTO.getName(),
                animeDTO.getCritic_score(),
                animeDTO.getRelease_date()
        );
    }

    public List<AnimeDTO> mapFromEntityList(List<Anime> entities) {
        List<AnimeDTO> animeDTOList = new ArrayList<>();
        for (Anime entity : entities) {
            animeDTOList.add(mapFromEntity(entity));
        }

        return animeDTOList;
    }
}
