package com.controller;

import com.dto.AnimeDTO;
import com.dto.FeaturedInDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AnimeService;
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
@RequestMapping(value = "/animes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class AnimeController
{
    private final AnimeService animeService;

    private AnimeController(AnimeService animeService)
    {
        this.animeService = animeService;
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity getAnime(HttpServletRequest servletRequest)
    {
        if (servletRequest.getContentType().equals("application/json"))
        {
            return new ResponseEntity<>(animeService.getAnime(), HttpStatus.OK);
        }
        else if(servletRequest.getContentType().equals("application/xml"))
        {
            try
            {
                JAXBContext context = JAXBContext.newInstance(AnimeDTO.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                StringWriter stringWriter = new StringWriter();
                stringWriter.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                stringWriter.append("<root>");
                List<AnimeDTO> list = animeService.getAnime();
                for (AnimeDTO animeDTO:list)
                {
                    marshaller.marshal(animeDTO, stringWriter);
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

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AnimeDTO> getAnimeByID(@PathVariable Integer id)
    {
        return new ResponseEntity<>(animeService.getAnimeById(id), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity createAnime(HttpServletRequest servletRequest)
    {
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(servletRequest);
        try
        {
            AnimeDTO animeDTO = Validation(request);
            return new ResponseEntity<>(animeService.createAnime(animeDTO), HttpStatus.OK);
        }
        catch (IOException|JAXBException e)
        {
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnimeDTO> deleteAnime(@PathVariable Integer id)
    {
        return new ResponseEntity<>(animeService.deleteAnime(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateAnime(@PathVariable Integer id, HttpServletRequest servletRequest)
    {
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(servletRequest);
        try
        {
            AnimeDTO animeDTO = Validation(request);
            return new ResponseEntity<>(animeService.updateAnime(animeDTO, id), HttpStatus.OK);
        }
        catch (IOException | JAXBException e)
        {
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    private AnimeDTO Validation(ContentCachingRequestWrapper request) throws IOException, JAXBException
    {
        String inputString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String schemaName = "Anime";
        AnimeDTO animeDTO = null;

        if(request.getContentType().equals("application/json"))
        {
            JsonValidator jsonValidator = new JsonValidator();
            if(!jsonValidator.validateJson(inputString, schemaName))
            {
                throw new IOException();
            }

            StringReader reader = new StringReader(inputString);
            ObjectMapper objectMapper = new ObjectMapper();
            animeDTO = objectMapper.readValue(reader, AnimeDTO.class);
        }
        else if(request.getContentType().equals("application/xml"))
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(AnimeDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlValidator xmlValidator = new XmlValidator();
            if(!xmlValidator.validateXML(inputString, schemaName))
            {
                throw new IOException();
            }

            StringReader reader = new StringReader(inputString);
            animeDTO = (AnimeDTO) jaxbUnmarshaller.unmarshal(reader);
        }
        return animeDTO;
    }
}
