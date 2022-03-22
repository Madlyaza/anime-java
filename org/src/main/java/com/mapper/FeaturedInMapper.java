package com.mapper;

import com.dto.FeaturedInDTO;
import com.model.FeaturedIn;
import com.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("featuredMapperComponent")
public class FeaturedInMapper implements EntityMapper<FeaturedIn, FeaturedInDTO>
{
    @Override
    public FeaturedInDTO mapFromEntity(FeaturedIn featuredIn) {
        return new FeaturedInDTO(
                featuredIn.getId(),
                featuredIn.getAnime(),
                featuredIn.getActor()
        );
    }

    @Override
    public FeaturedIn mapToEntity(FeaturedInDTO featuredInDTO) {
        return new FeaturedIn(
                featuredInDTO.getId(),
                featuredInDTO.getAnime(),
                featuredInDTO.getActor()
        );
    }

    public List<FeaturedInDTO> mapFromEntityList(List<FeaturedIn> entities) {
        List<FeaturedInDTO> featuredInDTOS = new ArrayList<>();
        for (FeaturedIn entity : entities) {
            featuredInDTOS.add(mapFromEntity(entity));
        }

        return featuredInDTOS;
    }
}
