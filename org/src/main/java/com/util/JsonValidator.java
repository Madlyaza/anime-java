package com.util;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class JsonValidator
{
    public boolean validateJson(String payload, String schemaLocation)
    {
        try(InputStream inputStream = getClass().getResourceAsStream("../../../classes/schemas/json/"+schemaLocation+".json"))
        {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(new JSONObject(payload));
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
    }
}
