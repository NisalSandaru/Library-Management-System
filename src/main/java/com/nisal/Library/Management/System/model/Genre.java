package com.nisal.Library.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Genre Code is Mandatory")
    private String code;

    @NotBlank(message = "Genre name is Mandatory")
    private String name;

    @Size(max = 500, message = "description must not exceed 500 characters")
    private String description;

    @Min(value = 0, message = "displayOrder can not be negative")
    private Integer displayOrder;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne
    private Genre parentGenre;

    @OneToMany
    private List<Genre> subGenres = new ArrayList<Genre>();

//    @OneToMany(mappedBy = "genre", cascade = CascadeType.PERSIST)
//    private List<Books> books = new ArrayList<Books>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
