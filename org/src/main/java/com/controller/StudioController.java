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
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
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

    @GetMapping()
    public ResponseEntity<List<StudioDTO>> getStudios()
    {
        return new ResponseEntity<>(studioService.getStudios(), HttpStatus.OK);
        //TODO: LOOK AT
//        try
//        {
//            JAXBContext context = JAXBContext.newInstance(StudioDTO.class);
//            Marshaller marshaller = context.createMarshaller();
//            StringWriter stringWriter = new StringWriter();
//            List<StudioDTO> list = studioService.getStudios();
//            int counter = 0;
//            for (StudioDTO studioDTO:list)
//            {
//                if(counter > 0)
//                {
//                    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//                    marshaller.marshal(studioDTO, stringWriter);
//                }
//                marshaller.marshal(studioDTO, stringWriter);
//                counter++;
//            }
//
//            return new ResponseEntity<>(stringWriter.toString(), HttpStatus.OK);
//        }
//        catch (JAXBException e)
//        {
//            e.printStackTrace();
//        }
//        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudioDTO> getStudioById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(studioService.getStudioById(id), HttpStatus.OK);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<StudioDTO> deleteStudio(@PathVariable Integer id)
    {
        return new ResponseEntity<>(studioService.deleteStudio(id), HttpStatus.OK);
    }

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
