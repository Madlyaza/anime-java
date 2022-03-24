package com.service;

import com.dto.ActorDTO;
import com.exception.BadRequestException;
import com.exception.DataNotFoundException;
import com.exception.NoContentException;
import com.mapper.ActorMapper;
import com.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService
{
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public ActorService (ActorRepository actorRepository, ActorMapper actorMapper)
    {
        this.actorMapper = actorMapper;
        this.actorRepository = actorRepository;
    }

    public List<ActorDTO> getActors()
    {
        return actorMapper.mapFromEntityList(actorRepository.getActors());
    }

    public ActorDTO getActorsById(Integer id)
    {
        try
        {
            return actorMapper.mapFromEntity(actorRepository.getActorById(id));
        }
        catch (Exception ex)
        {
            throw new DataNotFoundException("id: " + id);
        }
    }

    public ActorDTO createActor(ActorDTO actorDTO)
    {
        try
        {
            return actorMapper.mapFromEntity(actorRepository.uploadActor(actorMapper.mapToEntity(actorDTO)));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    public ActorDTO deleteActor(Integer id)
    {
        try
        {
            return actorMapper.mapFromEntity(actorRepository.deleteActor(id));
        }
        catch (Exception ex)
        {
            throw new NoContentException("id: " + id);
        }
    }

    public ActorDTO updateActor(ActorDTO actorDTO, Integer id)
    {
        try
        {
            return actorMapper.mapFromEntity(actorRepository.updateActor(actorMapper.mapToEntity(actorDTO), id));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }
}
