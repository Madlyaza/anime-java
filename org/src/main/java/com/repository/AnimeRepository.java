package com.repository;

import com.model.Actor;
import com.model.Anime;
import com.model.FeaturedIn;
import com.model.Studio;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.sound.midi.SysexMessage;
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
    public List<Anime> getAnimes()
    {
        TypedQuery<Anime> query = manager.createQuery("SELECT g FROM Anime g", Anime.class);
        return query.getResultList();
    }

    /**
     * Find a single anime and return it.
     *
     * @param id id of the anime to find
     * @return response entity with a single anime
     */
    public Anime getAnimeById(Integer id)
    {
        return manager.find(Anime.class, id);
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
        anime.setStudio(manager.find(Studio.class, anime.getStudio().getId()));
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
        Studio studio = manager.find(Studio.class, anime.getStudio().getId());
        anime.setStudio(studio);
        anime.setId(id);

        return manager.merge(anime);
    }
}
