package com.repository;

import com.model.Studio;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class StudioRepository
{
    @PersistenceContext
    private EntityManager manager;

    /**
     * Select queries all studios and returns them in a list.
     *
     * @return response entity with list of all studios
     */
    public List<Studio> getStudios()
    {
        TypedQuery<Studio> query = manager.createQuery("SELECT g FROM Studio g", Studio.class);
        return query.getResultList();
    }

    /**
     * Find a single studio and return it.
     *
     * @param id id of the studio to find
     * @return response entity with a single studio
     */
    public Studio getStudioById(Integer id)
    {
        return manager.find(Studio.class, id);
    }

    /**
     * Post a single studio
     *
     * @param studio to post
     * @return response entity with posted studio
     */
    public Studio uploadStudio(Studio studio)
    {
        manager.persist(studio);
        return studio;
    }

    /**
     * Delete a single studio and return it.
     *
     * @param id of the studio to delete
     * @return response entity with deleted studio
     */
    public Studio deleteStudio(Integer id)
    {
        Studio studio = manager.find(Studio.class, id);
        manager.remove(studio);
        return studio;
    }

    /**
     * Put a single studio
     * Updates all fields.
     *
     * @param id of the studio to put
     * @param studio to put
     * @return response entity with put studio
     */
    public Studio updateStudio(Studio studio, Integer id)
    {
        return manager.merge(studio);
    }
}
