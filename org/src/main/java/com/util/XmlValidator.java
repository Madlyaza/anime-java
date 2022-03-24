package com.util;

import com.exception.BadRequestException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.Scanner;

public class XmlValidator
{
    public boolean validateXML(String payload, String schemaLocation)
    {
        try
        {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            File schemaFile = new File("../../../classes/schemas/xml/"+schemaLocation+".xsd");

            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();

            Source source = new StreamSource(new StringReader(payload));

            try  {
                validator.validate(source);
                return true;
            }
            catch (SAXException e)
            {
                return false;
            }

        }
        catch (SAXException | IOException e)
        {
            return false;
        }
    }
}
