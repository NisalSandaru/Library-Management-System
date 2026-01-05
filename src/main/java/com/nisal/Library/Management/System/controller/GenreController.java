package com.nisal.Library.Management.System.controller;

import com.nisal.Library.Management.System.exception.GenreException;
import com.nisal.Library.Management.System.model.Genre;
import com.nisal.Library.Management.System.payload.dto.GenreDTO;
import com.nisal.Library.Management.System.payload.response.ApiResponse;
import com.nisal.Library.Management.System.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genre) {
        GenreDTO createdGenre = genreService.createGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<GenreDTO> getGenreById(@RequestParam("genreId") Long genreId) throws GenreException {
        GenreDTO genres = genreService.getGenreById(genreId);
        return ResponseEntity.ok(genres);
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<GenreDTO> updateGenre(
            @PathVariable("genreId") Long genreId,
            @RequestBody GenreDTO genreDTO) throws GenreException {
        GenreDTO genres = genreService.updateGenre(genreId, genreDTO);
        return ResponseEntity.ok(genres);
    }

    @DeleteMapping("/{genreId}")
    public ResponseEntity<ApiResponse> deleteGenre(
            @PathVariable("genreId") Long genreId
    ) throws GenreException {
        genreService.deleteGenre(genreId);
        ApiResponse response = new ApiResponse("Genre Deleted - soft delete", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{genreId}/hard")
    public ResponseEntity<ApiResponse> hardDeleteGenre(
            @PathVariable("genreId") Long genreId
    ) throws GenreException {
        genreService.hardDeleteGenre(genreId);
        ApiResponse response = new ApiResponse("Genre Deleted - hard delete", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top-level")
    public ResponseEntity<List<GenreDTO>> getTopLevelGenres(){
        List<GenreDTO> genres = genreService.getTopLevelGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getTotalActiveGenres() {
        Long genres = genreService.getTotalActiveGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}/book-count")
    public ResponseEntity<?> getBoolCountByGenres(
            @PathVariable Long id
    ) {
        Long count = genreService.getBookCountByGenre(id);
        return ResponseEntity.ok(count);
    }

}
