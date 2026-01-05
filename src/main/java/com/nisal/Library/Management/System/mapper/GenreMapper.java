package com.nisal.Library.Management.System.mapper;

import com.nisal.Library.Management.System.model.Genre;
import com.nisal.Library.Management.System.payload.dto.GenreDTO;
import com.nisal.Library.Management.System.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreMapper {

    private final GenreRepository genreRepository;

    public static GenreDTO toDTO(Genre genre) {
        if (genre == null) {
            return null;
        }
        GenreDTO dto = GenreDTO.builder()
                .id(genre.getId())
                .code(genre.getCode())
                .name(genre.getName())
                .description(genre.getDescription())
                .displayOrder(genre.getDisplayOrder())
                .active(genre.getActive())
                .createAt(genre.getCreatedAt())
                .updateAt(genre.getUpdatedAt())
                .build();

        if (genre.getParentGenre()!=null){
            dto.setParentGenreId(genre.getParentGenre().getId());
            dto.setParentGenreName(genre.getParentGenre().getName());
        }

        if (genre.getSubGenres()!=null && !genre.getSubGenres().isEmpty()){
            dto.setSubGenre(genre.getSubGenres().stream()
                    .filter(Genre::getActive)
                    .map(GenreMapper::toDTO).collect(Collectors.toList()));
        }

//        dto.setBookCount();

        return dto;
    }

    public Genre toEntity(GenreDTO genreDTO) {
        if (genreDTO == null) {
            return null;
        }

        Genre genre = Genre.builder()
                .code(genreDTO.getCode())
                .name(genreDTO.getName())
                .description(genreDTO.getDescription())
                .displayOrder(genreDTO.getDisplayOrder())
                .active(true)
                .build();

        if (genreDTO.getParentGenreId()!=null){
            genreRepository.findById(genreDTO.getParentGenreId())
                    .ifPresent(genre::setParentGenre);
        }

        return genre;
    }

    public void updateEntityFromDTO(GenreDTO dto, Genre existingGenre) {
        if (dto == null || existingGenre == null) {
            return;
        }

        existingGenre.setCode(dto.getCode());
        existingGenre.setName(dto.getName());
        existingGenre.setDescription(dto.getDescription());
        existingGenre.setDisplayOrder(dto.getDisplayOrder() !=null ? dto.getDisplayOrder() : 0);
        if (dto.getActive()!=null){
            existingGenre.setActive(dto.getActive());
        }
        if (dto.getParentGenreId()!=null){
            genreRepository.findById(dto.getParentGenreId())
                    .ifPresent(existingGenre::setParentGenre);
        }

    }

    public List<GenreDTO> toDTOList(List<Genre> genreList) {
        return genreList.stream().map(GenreMapper::toDTO).collect(Collectors.toList());
    }
}
