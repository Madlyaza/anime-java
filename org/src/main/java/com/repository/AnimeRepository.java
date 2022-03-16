package com.repository;

import com.model.Anime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class AnimeRepository
{
    @PersistenceContext
    private EntityManager manager;

    /**
     * Select queries all anime and returns them in a list.
     *
     * @return response entity with list of all anime
     */
    public Set<Anime> getAnimes()
    {
        TypedQuery<Anime> query = manager.createQuery("SELECT g FROM Anime g", Anime.class);
        return (Set<Anime>) query.getResultList();
    }

    /**
     * Find a single anime and return it.
     *
     * @param id id of the anime to find
     * @return response entity with a single anime
     */
    public Anime getAnimeById(Integer id)
    {
        TypedQuery<Anime> query = manager.createQuery("SELECT g FROM Anime WHERE id = :id", Anime.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Post a single anime
     *
     * @param anime to post
     * @return response entity with posted anime
     */
    public Anime uploadAnime(Anime anime)
    {
        manager.persist(anime);
        return anime;
    }

    /**
     * Delete a single anime and return it.
     *
     * @param id of the anime to delete
     * @return response entity with deleted anime
     */
    public Anime deleteAnime(Integer id)
    {
        Anime anime = manager.find(Anime.class, id);
        manager.remove(anime);
        return anime;
    }

    /**
     * Put a single anime
     * Updates all fields.
     *
     * @param id of the anime to put
     * @param anime to put
     * @return response entity with put anime
     */
    public Anime updateAnime(Anime anime, Integer id)
    {
        return manager.merge(anime);
    }
}
