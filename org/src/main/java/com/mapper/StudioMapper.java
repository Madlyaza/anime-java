package com.mapper;

import com.dto.StudioDTO;
import com.model.Studio;
import com.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("studioMapperComponent")
public class StudioMapper implements EntityMapper<Studio, StudioDTO>
{
    @Override
    public StudioDTO mapFromEntity(Studio studio) {
        return new StudioDTO(
            studio.getId(),
            studio.getName(),
            studio.getFounded(),
            studio.getHeadquarters(),
            studio.getType()
        );
    }

    @Override
    public Studio mapToEntity(StudioDTO studioDTO) {
        return new Studio(
                studioDTO.getId(),
                studioDTO.getName(),
                studioDTO.getFounded(),
                studioDTO.getHeadquarters(),
                studioDTO.getType()
        );
    }

    public List<StudioDTO> mapFromEntityList(List<Studio> entities) {
        List<StudioDTO> studioDTOList = new ArrayList<>();
        for (Studio entity : entities) {
            studioDTOList.add(mapFromEntity(entity));
        }

        return studioDTOList;
    }
}
