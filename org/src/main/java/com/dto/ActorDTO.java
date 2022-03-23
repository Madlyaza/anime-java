package com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.util.LocalDateAdapter;
import com.util.LocalDateDeserializer;
import com.util.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "actor")
public class ActorDTO
{
    private int id;

    @NotBlank
    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birth_date;

    @NotNull
    private String birth_place;

    @NotNull
    private Integer age;

    @XmlElement(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlElement(name = "birth_date")
    public LocalDate getBirth_date()
    {
        return birth_date;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setBirth_date(LocalDate birth_date)
    {
        this.birth_date = birth_date;
    }

    @XmlElement(name = "birth_place")
    public String getBirth_place()
    {
        return birth_place;
    }

    public void setBirth_place(String birth_place)
    {
        this.birth_place = birth_place;
    }

    @XmlElement(name = "age")
    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }
}
