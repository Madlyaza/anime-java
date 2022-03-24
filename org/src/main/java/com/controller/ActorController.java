package com.controller;

import com.dto.ActorDTO;
import com.dto.StudioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ActorService;
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
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/actors", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ActorController
{
    private final ActorService actorService;

    private ActorController(ActorService actorService)
    {
        this.actorService = actorService;
    }

    @GetMapping()
    public ResponseEntity<List<ActorDTO>> getActors()
    {
        return new ResponseEntity<>(actorService.getActors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorByID(@PathVariable Integer id)
    {
        return new ResponseEntity<>(actorService.getActorsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createActor(HttpServletRequest servletRequest)
    {
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(servletRequest);
        try
        {
            ActorDTO actorDTO = Validation(request);
            return new ResponseEntity<>(actorService.createActor(actorDTO), HttpStatus.OK);
        }
        catch (IOException|JAXBException e)
        {
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorDTO> deleteActor(@PathVariable Integer id)
    {
        return new ResponseEntity<>(actorService.deleteActor(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateActor(@PathVariable Integer id, HttpServletRequest servletRequest)
    {
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(servletRequest);
        try
        {
            ActorDTO actorDTO = Validation(request);
            return new ResponseEntity<>(actorService.updateActor(actorDTO, id), HttpStatus.OK);
        }
        catch (IOException|JAXBException e)
        {
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    private ActorDTO Validation(ContentCachingRequestWrapper request) throws IOException, JAXBException
    {
        String inputString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String schemaName = "Actor";
        ActorDTO actorDTO = null;

        if(request.getContentType().equals("application/json"))
        {
            JsonValidator jsonValidator = new JsonValidator();
            if(!jsonValidator.validateJson(inputString, schemaName))
            {
                throw new IOException();
            }

            StringReader reader = new StringReader(inputString);
            ObjectMapper objectMapper = new ObjectMapper();
            actorDTO = objectMapper.readValue(reader, ActorDTO.class);
        }
        else if(request.getContentType().equals("application/xml"))
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(ActorDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlValidator xmlValidator = new XmlValidator();
            if(!xmlValidator.validateXML(inputString, schemaName))
            {
                throw new IOException();
            }

            StringReader reader = new StringReader(inputString);
            actorDTO = (ActorDTO) jaxbUnmarshaller.unmarshal(reader);
        }
        return actorDTO;
    }
}
