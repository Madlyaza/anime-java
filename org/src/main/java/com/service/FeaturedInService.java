package com.service;

import com.exception.BadRequestException;
import com.exception.DataNotFoundException;
import com.exception.NoContentException;
import com.model.FeaturedIn;
import com.repository.FeaturedInRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeaturedInService
{
    private final FeaturedInRepository featuredInRepository;

    public FeaturedInService (FeaturedInRepository featuredInRepository)
    {
        this.featuredInRepository = featuredInRepository;
    }

    public List<FeaturedIn> getFeatured()
    {
        return featuredInRepository.getFeatured();
    }

    public FeaturedIn getFeaturedById(Integer id)
    {
        try
        {
            return featuredInRepository.getFeaturedById(id);
        }
        catch (Exception ex)
        {
            throw new DataNotFoundException("id: " + id);
        }
    }

    public FeaturedIn createFeatured(FeaturedIn featuredIn)
    {
        try
        {
            return featuredInRepository.uploadFeatured(featuredIn);
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    public FeaturedIn deleteFeatured(Integer id)
    {
        try
        {
            return featuredInRepository.deleteFeatured(id);
        }
        catch (Exception ex)
        {
            throw new NoContentException("id: " + id);
        }
    }

    public FeaturedIn updateFeatured(FeaturedIn featuredIn, Integer id)
    {
        try
        {
            return featuredInRepository.updateFeatured(featuredIn, id);
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }
}
