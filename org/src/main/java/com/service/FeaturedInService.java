package com.service;

import com.dto.FeaturedInDTO;
import com.exception.BadRequestException;
import com.exception.DataNotFoundException;
import com.exception.NoContentException;
import com.mapper.FeaturedInMapper;
import com.repository.FeaturedInRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeaturedInService
{
    private final FeaturedInRepository featuredInRepository;
    private final FeaturedInMapper featuredInMapper;

    public FeaturedInService (FeaturedInRepository featuredInRepository, FeaturedInMapper featuredInMapper)
    {
        this.featuredInRepository = featuredInRepository;
        this.featuredInMapper = featuredInMapper;
    }

    public List<FeaturedInDTO> getFeatured()
    {
        return featuredInMapper.mapFromEntityList(featuredInRepository.getFeatured());
    }

    public FeaturedInDTO getFeaturedById(Integer id)
    {
        try
        {
            return featuredInMapper.mapFromEntity( featuredInRepository.getFeaturedById(id));
        }
        catch (Exception ex)
        {
            throw new DataNotFoundException("id: " + id);
        }
    }

    public FeaturedInDTO createFeatured(FeaturedInDTO featuredInDTO)
    {
        try
        {
            return featuredInMapper.mapFromEntity(featuredInRepository.uploadFeatured(featuredInMapper.mapToEntity(featuredInDTO)));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    public FeaturedInDTO deleteFeatured(Integer id)
    {
        try
        {
            return featuredInMapper.mapFromEntity(featuredInRepository.deleteFeatured(id));
        }
        catch (Exception ex)
        {
            throw new NoContentException("id: " + id);
        }
    }

    public FeaturedInDTO updateFeatured(FeaturedInDTO featuredInDTO, Integer id)
    {
        try
        {
            return featuredInMapper.mapFromEntity(featuredInRepository.updateFeatured(featuredInMapper.mapToEntity(featuredInDTO), id));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }
}
