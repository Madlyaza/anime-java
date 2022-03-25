package com.controller;

import com.dto.StudioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.StudioService;
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
@RequestMapping(value = "/studios", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class StudioController
{
    private final StudioService studioService;

    private StudioController(StudioService studioService)
    {
        this.studioService = studioService;
    }


    /**
     * Returns a list of all studios.
     *
     * @return response entity with list of all studios
     */
    @GetMapping()
    public ResponseEntity getStudios(HttpServletRequest servletRequest)
    {
        if (servletRequest.getContentType().equals("application/json"))
        {
            return new ResponseEntity<>(studioService.getStudios(), HttpStatus.OK);
        }
        else if(servletRequest.getContentType().equals("application/xml"))
        {
            try
            {
                JAXBContext context = JAXBContext.newInstance(StudioDTO.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                StringWriter stringWriter = new StringWriter();
                stringWriter.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                stringWriter.append("<root>");
                List<StudioDTO> list = studioService.getStudios();

                for (StudioDTO studioDTO:list)
                {
                    marshaller.marshal(studioDTO, stringWriter);
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
     * Returns a single studio.
     *
     * @param id of the studio to find
     * @return response entity with single studio
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudioDTO> getStudioById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(studioService.getStudioById(id), HttpStatus.OK);
    }

    /**
     * Post a single studio.
     *
     * @param servletRequest request data to post
     * @return response entity with posted studio
     */
    @PostMapping()
    public ResponseEntity createStudio(HttpServletRequest servletRequest)
    {
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(servletRequest);
        try
        {
            StudioDTO studioDTO = Validation(request);

            return new ResponseEntity<>(studioService.createStudio(studioDTO), HttpStatus.OK);
        }
        catch (IOException|JAXBException e)
        {
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a single studio.
     *
     * @param id of the studio to delete
     * @return response entity with deleted studio
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<StudioDTO> deleteStudio(@PathVariable Integer id)
    {
        return new ResponseEntity<>(studioService.deleteStudio(id), HttpStatus.OK);
    }

    /**
     * Put a single studio.
     *
     * @param id of the studio to put
     * @param servletRequest request data to put
     * @return response entity with put studio
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity updateStudio(@PathVariable Integer id, HttpServletRequest servletRequest)
    {
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(servletRequest);
        try
        {
            StudioDTO studioDTO = Validation(request);

            return new ResponseEntity<>(studioService.updateStudio(studioDTO, id), HttpStatus.OK);
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
     * @return StudioDTO with the put or post studio
     */
    private StudioDTO Validation(ContentCachingRequestWrapper request) throws IOException, JAXBException
    {
        String inputString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String schemaName = "Studio";
        StudioDTO studioDTO = null;

        if(request.getContentType().equals("application/json"))
        {
            JsonValidator jsonValidator = new JsonValidator();
            if(!jsonValidator.validateJson(inputString, schemaName))
            {
                throw new IOException();
            }

            StringReader reader = new StringReader(inputString);
            ObjectMapper objectMapper = new ObjectMapper();
            studioDTO = objectMapper.readValue(reader, StudioDTO.class);
        }
        else if(request.getContentType().equals("application/xml"))
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(StudioDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlValidator xmlValidator = new XmlValidator();
            if(!xmlValidator.validateXML(inputString, schemaName))
            {
                throw new IOException();
            }

            StringReader reader = new StringReader(inputString);
            studioDTO = (StudioDTO) jaxbUnmarshaller.unmarshal(reader);
        }
        return studioDTO;
    }
}
