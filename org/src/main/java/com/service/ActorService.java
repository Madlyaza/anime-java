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

    /**
     * Maps Entity to DTO and returns a list of all actors.
     *
     * @return response entity with list of all actors
     */
    public List<ActorDTO> getActors()
    {
        return actorMapper.mapFromEntityList(actorRepository.getActors());
    }

    /**
     * Maps Entity to DTO and returns a single actor.
     *
     * @param id id of the actor to find
     * @return response entity with single actor
     */
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

    /**
     * Maps Entity to DTO and posts a single actor.
     *
     * @param actorDTO actor to post
     * @return response entity with posted actor
     */
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

    /**
     * Maps Entity to DTO and deletes a single actor.
     *
     * @param id of the actor to delete
     * @return response entity with deleted actor
     */
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

    /**
     * Maps Entity to DTO and puts a single actor.
     *
     * @param id of the actor to put
     * @param actorDTO actor to put
     * @return response entity with put actor
     */
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
