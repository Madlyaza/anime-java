package com.repository;

import com.model.Actor;
import com.model.Studio;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ActorRepository
{
    @PersistenceContext
    private EntityManager manager;

    /**
     * Select queries all actors and returns them in a list.
     *
     * @return response entity with list of all actors
     */
    public List<Actor> getActors()
    {
        TypedQuery<Actor> query = manager.createQuery("SELECT g FROM Actor g", Actor.class);
        return query.getResultList();
    }

    /**
     * Find a single studio and return it.
     *
     * @param id id of the studio to find
     * @return response entity with a single studio
     */
    public Actor getActorById(Integer id)
    {
        TypedQuery<Actor> query = manager.createQuery("SELECT g FROM Actor WHERE id = :id", Actor.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Post a single actor
     *
     * @param actor to post
     * @return response entity with posted actor
     */
    public Actor uploadActor(Actor actor)
    {
        manager.persist(actor);
        return actor;
    }

    /**
     * Delete a single actor and return it.
     *
     * @param id of the actor to delete
     * @return response entity with deleted actor
     */
    public Actor deleteActor(Integer id)
    {
        Actor actor = manager.find(Actor.class, id);
        manager.remove(actor);
        return actor;
    }

    /**
     * Put a single Actor
     * Updates all fields.
     *
     * @param id of the actor to put
     * @param actor to put
     * @return response entity with put actor
     */
    public Actor updateActor(Actor actor, Integer id)
    {
        return manager.merge(actor);
    }
}
