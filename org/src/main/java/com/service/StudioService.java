package com.service;

import com.exception.BadRequestException;
import com.exception.DataNotFoundException;
import com.exception.NoContentException;
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

    public Studio getStudioById(Integer id)
    {
        try
        {
            return studioRepository.getStudioById(id);
        }
        catch (Exception ex)
        {
            throw new DataNotFoundException("id: " + id);
        }
    }

    public Studio createStudio(Studio studio)
    {
        try
        {
            return studioRepository.uploadStudio(studio);
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    public Studio deleteStudio(Integer id)
    {
        try
        {
            return studioRepository.deleteStudio(id);
        }
        catch (Exception ex)
        {
            throw new NoContentException("id: " + id);
        }
    }

    public Studio updateStudio(Studio studio, Integer id)
    {
        try
        {
            return studioRepository.updateStudio(studio, id);
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }
}