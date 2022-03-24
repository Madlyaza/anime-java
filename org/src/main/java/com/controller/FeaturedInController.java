package com.controller;

import com.dto.FeaturedInDTO;
import com.dto.StudioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.FeaturedInService;
import com.util.JsonValidator;
import com.util.XmlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/featured", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class FeaturedInController
{
    private final FeaturedInService featuredInService;

    private FeaturedInController(FeaturedInService featuredInService)
    {
        this.featuredInService = featuredInService;
    }

    @GetMapping()
    public ResponseEntity getFeatured(HttpServletRequest servletRequest)
    {
        if (servletRequest.getContentType().equals("application/json"))
        {
            return new ResponseEntity<>(featuredInService.getFeatured(), HttpStatus.OK);
        }
        else if(servletRequest.getContentType().equals("application/xml"))
        {
            try
            {
                JAXBContext context = JAXBContext.newInstance(FeaturedInDTO.class);
                Marshaller marshaller = context.createMarshaller();
                StringWriter stringWriter = new StringWriter();
                List<FeaturedInDTO> list = featuredInService.getFeatured();
                int counter = 0;
                for (FeaturedInDTO featuredInDTO:list)
                {
                    if(counter == 1)
                    {
                        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                    }
                    marshaller.marshal(featuredInDTO, stringWriter);
                    counter++;
                }

                return new ResponseEntity<>(stringWriter.toString(), HttpStatus.OK);
            }
            catch (JAXBException e)
            {
                throw new RuntimeException();
            }
        }
        throw new RuntimeException();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeaturedInDTO> getFeaturedById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(featuredInService.getFeaturedById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity createFeatured(HttpServletRequest servletRequest)
    {
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(servletRequest);
        try
        {
            FeaturedInDTO featuredInDTO = Validation(request);

            return new ResponseEntity<>(featuredInService.createFeatured(featuredInDTO), HttpStatus.OK);
        }
        catch (IOException|JAXBException e)
        {
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FeaturedInDTO> deleteFeatured(@PathVariable Integer id)
    {
        return new ResponseEntity<>(featuredInService.deleteFeatured(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateAnime(@PathVariable Integer id, HttpServletRequest servletRequest)
    {
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(servletRequest);
        try
        {
            FeaturedInDTO featuredInDTO = Validation(request);

            return new ResponseEntity<>(featuredInService.updateFeatured(featuredInDTO, id), HttpStatus.OK);
        }
        catch (IOException|JAXBException e)
        {
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    private FeaturedInDTO Validation(ContentCachingRequestWrapper request) throws IOException, JAXBException
    {
        String inputString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String schemaName = "featuredIn";
        FeaturedInDTO featuredInDTO = null;

        if(request.getContentType().equals("application/json"))
        {
            JsonValidator jsonValidator = new JsonValidator();
            if(!jsonValidator.validateJson(inputString, schemaName))
            {
                throw new IOException();
            }

            StringReader reader = new StringReader(inputString);
            ObjectMapper objectMapper = new ObjectMapper();
            featuredInDTO = objectMapper.readValue(reader, FeaturedInDTO.class);
        }
        else if(request.getContentType().equals("application/xml"))
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(FeaturedInDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlValidator xmlValidator = new XmlValidator();
            if(!xmlValidator.validateXML(inputString, schemaName))
            {
                throw new IOException();
            }

            StringReader reader = new StringReader(inputString);
            featuredInDTO = (FeaturedInDTO) jaxbUnmarshaller.unmarshal(reader);
        }
        return featuredInDTO;
    }
}
