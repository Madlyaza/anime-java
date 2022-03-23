package com.repository;

import com.model.Actor;
import com.model.Anime;
import com.model.FeaturedIn;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class FeaturedInRepository
{
    @PersistenceContext
    private EntityManager manager;

    /**
     * Select queries all featuredIn and returns them in a list.
     *
     * @return response entity with list of all featuredIn
     */
    public List<FeaturedIn> getFeatured()
    {
        TypedQuery<FeaturedIn> query = manager.createQuery("SELECT g FROM FeaturedIn g", FeaturedIn.class);
        return query.getResultList();
    }

    /**
     * Find a single featuredIn and return it.
     *
     * @param id id of the featuredIn to find
     * @return response entity with a single featuredIn
     */
    public FeaturedIn getFeaturedById(Integer id)
    {
        return manager.find(FeaturedIn.class, id);
    }

    /**
     * Post a single featuredIn
     *
     * @param featuredIn to post
     * @return response entity with posted featuredIn
     */
    public FeaturedIn uploadFeatured(FeaturedIn featuredIn)
    {
        manager.persist(featuredIn);
        return featuredIn;
    }

    /**
     * Delete a single featuredIn and return it.
     *
     * @param id of the featuredIn to delete
     * @return response entity with deleted featuredIn
     */
    public FeaturedIn deleteFeatured(Integer id)
    {
        FeaturedIn featuredIn = manager.find(FeaturedIn.class, id);
        manager.remove(featuredIn);
        return featuredIn;
    }

    /**
     * Put a single featuredIn
     * Updates all fields.
     *
     * @param id of the featuredIn to put
     * @param featuredIn to put
     * @return response entity with put featuredIn
     */
    public FeaturedIn updateFeatured(FeaturedIn featuredIn, Integer id)
    {
        Actor actor = manager.find(Actor.class, featuredIn.getActor().getId());
        Anime anime = manager.find(Anime.class, featuredIn.getAnime().getId());

        featuredIn.setActor(actor);
        featuredIn.setAnime(anime);
        featuredIn.setId(id);
        return manager.merge(featuredIn);
    }
}
