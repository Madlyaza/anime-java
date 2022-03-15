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

    public List<Studio> getStudios()
    {
        TypedQuery<Studio> query = manager.createQuery("SELECT g FROM Studio g", Studio.class);
        return query.getResultList();
    }
}
