package com.service;

import com.exception.BadRequestException;
import com.exception.DataNotFoundException;
import com.exception.NoContentException;
import com.model.Actor;
import com.repository.ActorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActorService
{
    private final ActorRepository actorRepository;

    public ActorService (ActorRepository actorRepository)
    {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getActors()
    {
        return actorRepository.getActors();
    }

    public Actor getActorsById(Integer id)
    {
        try
        {
            return actorRepository.getActorById(id);
        }
        catch (Exception ex)
        {
            throw new DataNotFoundException("id: " + id);
        }
    }

    public Actor createActor(Actor actor)
    {
        try
        {
            return actorRepository.uploadActor(actor);
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    public Actor deleteActor(Integer id)
    {
        try
        {
            return actorRepository.deleteActor(id);
        }
        catch (Exception ex)
        {
            throw new NoContentException("id: " + id);
        }
    }

    public Actor updateActor(Actor actor, Integer id)
    {
        try
        {
            return actorRepository.updateActor(actor, id);
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }
}
