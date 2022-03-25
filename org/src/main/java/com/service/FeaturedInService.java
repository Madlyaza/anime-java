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

    /**
     * Maps Entity to DTO and returns a list of all featuredIn.
     *
     * @return response entity with list of all featuredIn
     */
    public List<FeaturedInDTO> getFeatured()
    {
        return featuredInMapper.mapFromEntityList(featuredInRepository.getFeatured());
    }

    /**
     * Maps Entity to DTO and returns a single featuredIn.
     *
     * @param id id of the featuredIn to find
     * @return response entity with single featuredIn
     */
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

    /**
     * Maps Entity to DTO and posts a single featuredIn.
     *
     * @param featuredInDTO featuredIn to post
     * @return response entity with posted featuredIn
     */
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

    /**
     * Maps Entity to DTO and deletes a single featuredIn.
     *
     * @param id of the featuredIn to delete
     * @return response entity with deleted featuredIn
     */
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

    /**
     * Maps Entity to DTO and puts a single featuredIn.
     *
     * @param id of the featuredIn to put
     * @param featuredInDTO featuredIn to put
     * @return response entity with put featuredIn
     */
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
