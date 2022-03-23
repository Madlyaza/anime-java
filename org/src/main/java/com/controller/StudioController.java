package com.controller;

import com.dto.StudioDTO;
import com.model.Studio;
import com.service.StudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

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
    public ResponseEntity<StudioDTO> createStudio(@RequestBody StudioDTO studioDTO)
    {
        return new ResponseEntity<>(studioService.createStudio(studioDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudioDTO> deleteStudio(@PathVariable Integer id)
    {
        return new ResponseEntity<>(studioService.deleteStudio(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StudioDTO> updateStudio(@PathVariable Integer id, @RequestBody StudioDTO studioDTO)
    {
        return new ResponseEntity<>(studioService.updateStudio(studioDTO, id), HttpStatus.OK);
    }
}
