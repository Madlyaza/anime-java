package com.service;

import com.model.Studio;
import com.repository.StudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioService
{
    private final StudioRepository studioRepository;

    public StudioService (StudioRepository studioRepository)
    {
        this.studioRepository = studioRepository;
    }

    public List<Studio> getStudios()
    {
        return studioRepository.getStudios();
    }
}
