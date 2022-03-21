package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anime")
public class Anime
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "studio_id", nullable = false)
    private Studio studio;

    @NotNull
    private String name;

    @NotNull
    private Integer critic_score;

    @Column(name = "release_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "CET")
    private Date release_date;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "animes")
    @JsonIgnore
    private Set<Actor> actors = new HashSet<Actor>();
}
