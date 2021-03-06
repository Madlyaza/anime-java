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

    /**
     * Returns a list of all featuredIn.
     *
     * @return response entity with list of all featuredIn
     */
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
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                StringWriter stringWriter = new StringWriter();
                stringWriter.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                stringWriter.append("<root>");
                List<FeaturedInDTO> list = featuredInService.getFeatured();

                for (FeaturedInDTO featuredInDTO:list)
                {
                    marshaller.marshal(featuredInDTO, stringWriter);
                }
                stringWriter.append("</root>");

                return new ResponseEntity<>(stringWriter.toString(), HttpStatus.OK);
            }
            catch (JAXBException e)
            {
                throw new RuntimeException();
            }
        }
        throw new RuntimeException();
    }

    /**
     * Returns a single featuredIn.
     *
     * @param id of the featuredIn to find
     * @return response entity with single featuredIn
     */
    @GetMapping("/{id}")
    public ResponseEntity<FeaturedInDTO> getFeaturedById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(featuredInService.getFeaturedById(id), HttpStatus.OK);
    }

    /**
     * Post a single featuredIn.
     *
     * @param servletRequest request data to post
     * @return response entity with posted featuredIn
     */
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

    /**
     * Delete a single featuredIn.
     *
     * @param id of the featuredIn to delete
     * @return response entity with deleted featuredin
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<FeaturedInDTO> deleteFeatured(@PathVariable Integer id)
    {
        return new ResponseEntity<>(featuredInService.deleteFeatured(id), HttpStatus.OK);
    }

    /**
     * Put a single featuredIn.
     *
     * @param id of the featuredIn to put
     * @param servletRequest request data to put
     * @return response entity with put featuredIn
     */
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

    /**
     * validates the XML or JSON against the schemas.
     *
     * @param request with the data of the post or put
     * @return FeaturedInDTO with the put or post featuredIn
     */
    private FeaturedInDTO Validation(ContentCachingRequestWrapper request) throws IOException, JAXBException
    {
        String inputString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String schemaName = "FeaturedIn";
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
