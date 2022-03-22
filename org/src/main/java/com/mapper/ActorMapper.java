package com.mapper;

import com.dto.ActorDTO;
import com.model.Actor;
import com.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("actorMapperComponent")
public class ActorMapper implements EntityMapper<Actor, ActorDTO>
{
    @Override
    public ActorDTO mapFromEntity(Actor actor) {
        return new ActorDTO(
                actor.getId(),
                actor.getName(),
                actor.getBirth_date(),
                actor.getBirth_place(),
                actor.getAge()
        );
    }

    @Override
    public Actor mapToEntity(ActorDTO actorDTO) {
        return new Actor(
                actorDTO.getId(),
                actorDTO.getName(),
                actorDTO.getBirth_date(),
                actorDTO.getBirth_place(),
                actorDTO.getAge()
        );
    }

    public List<ActorDTO> mapFromEntityList(List<Actor> entities) {
        List<ActorDTO> actorDTOList = new ArrayList<>();
        for (Actor entity : entities) {
            actorDTOList.add(mapFromEntity(entity));
        }

        return actorDTOList;
    }
}
