package com.service;

import com.dto.StudioDTO;
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

    public List<StudioDTO> getStudios()
    {
        return studioMapper.mapFromEntityList(studioRepository.getStudios());
    }

    public StudioDTO getStudioById(Integer id)
    {
        System.out.println("Service ------------------------------------");
//        try
//        {
            return studioMapper.mapFromEntity(studioRepository.getStudioById(id));
//        }
//        catch (Exception ex)
//        {
//            throw new DataNotFoundException("id: " + id);
//        }
    }

    public StudioDTO createStudio(StudioDTO studioDTO)
    {
//        try
//        {
            return studioMapper.mapFromEntity(studioRepository.uploadStudio(studioMapper.mapToEntity(studioDTO)));
//        }
//        catch (Exception ex)
//        {
//            throw new BadRequestException();
//        }
    }

    public StudioDTO deleteStudio(Integer id)
    {
//        try
//        {
            return studioMapper.mapFromEntity(studioRepository.deleteStudio(id));
//        }
//        catch (Exception ex)
//        {
//            throw new NoContentException("id: " + id);
//        }
    }

    public StudioDTO updateStudio(StudioDTO studioDTO, Integer id)
    {
//        try
//        {
            return studioMapper.mapFromEntity(studioRepository.updateStudio(studioMapper.mapToEntity(studioDTO), id));
//        }
//        catch (Exception ex)
//        {
//            throw new BadRequestException();
//        }
    }
}
