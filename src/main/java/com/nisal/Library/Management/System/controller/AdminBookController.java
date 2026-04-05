package com.nisal.Library.Management.System.controller;

import com.nisal.Library.Management.System.exception.BookException;
import com.nisal.Library.Management.System.payload.dto.BookDTO;
import com.nisal.Library.Management.System.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/books")
public class AdminBookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO bookDTO) throws BookException {
        BookDTO createdBook = bookService.createBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
}
