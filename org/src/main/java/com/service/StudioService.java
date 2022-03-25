package com.service;

import com.dto.StudioDTO;
import com.exception.BadRequestException;
import com.exception.DataNotFoundException;
import com.exception.NoContentException;
import com.mapper.StudioMapper;
import com.repository.StudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioService
{
    private final StudioRepository studioRepository;
    private final StudioMapper studioMapper;

    public StudioService (StudioRepository studioRepository, StudioMapper studioMapper)
    {
        this.studioRepository = studioRepository;
        this.studioMapper = studioMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all studios.
     *
     * @return response entity with list of all studios
     */
    public List<StudioDTO> getStudios()
    {
        return studioMapper.mapFromEntityList(studioRepository.getStudios());
    }

    /**
     * Maps Entity to DTO and returns a single studio.
     *
     * @param id id of the studio to find
     * @return response entity with single studio
     */
    public StudioDTO getStudioById(Integer id)
    {
        try
        {
            return studioMapper.mapFromEntity(studioRepository.getStudioById(id));
        }
        catch (Exception ex)
        {
            throw new DataNotFoundException("id: " + id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single studio.
     *
     * @param studioDTO studio to post
     * @return response entity with posted studio
     */
    public StudioDTO createStudio(StudioDTO studioDTO)
    {
        try
        {
            return studioMapper.mapFromEntity(studioRepository.uploadStudio(studioMapper.mapToEntity(studioDTO)));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single studio.
     *
     * @param id of the studio to delete
     * @return response entity with deleted studio
     */
    public StudioDTO deleteStudio(Integer id)
    {
        try
        {
            return studioMapper.mapFromEntity(studioRepository.deleteStudio(id));
        }
        catch (Exception ex)
        {
            throw new NoContentException("id: " + id);
        }
    }

    /**
     * Maps Entity to DTO and puts a single studio.
     *
     * @param id of the studio to put
     * @param studioDTO studio to put
     * @return response entity with put studio
     */
    public StudioDTO updateStudio(StudioDTO studioDTO, Integer id)
    {
        try
        {
            return studioMapper.mapFromEntity(studioRepository.updateStudio(studioMapper.mapToEntity(studioDTO), id));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }
}
