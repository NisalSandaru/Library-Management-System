package com.nisal.Library.Management.System.controller;

import com.nisal.Library.Management.System.exception.BookException;
import com.nisal.Library.Management.System.payload.dto.BookDTO;
import com.nisal.Library.Management.System.payload.request.BookSearchRequest;
import com.nisal.Library.Management.System.payload.response.ApiResponse;
import com.nisal.Library.Management.System.payload.response.PageResponse;
import com.nisal.Library.Management.System.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid BookDTO bookDTO) throws BookException {
        BookDTO createdBook = bookService.createBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<BookDTO>> createBooksBulk(@Valid List<BookDTO> bookDTOS) throws BookException {
        List<BookDTO> createdBook = bookService.createBooksBulk(bookDTOS);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable long id) throws BookException {
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) throws BookException {
        BookDTO book = bookService.getBookByISBN(isbn);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable long id,
            @RequestBody BookDTO bookDTO) throws BookException {
            BookDTO updatedBook = bookService.updateBook(id, bookDTO);
            return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable long id) throws BookException {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse("Book deleted successfully", true));
    }

    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<ApiResponse> hardDeleteBook(@PathVariable long id) throws BookException {
        bookService.hardDeleteBook(id);
        return ResponseEntity.ok(new ApiResponse("Book permanently deleted", true));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookDTO>> searchBooks(
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false, defaultValue = "false") Boolean availableOnly,
            @RequestParam(defaultValue = "true") boolean activeOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection){

        // Build search request from query parameters
        BookSearchRequest searchRequest = new BookSearchRequest();
        searchRequest.setGenreId(genreId);
        searchRequest.setAvailableOnly(availableOnly);
        searchRequest.setPage(page);
        searchRequest.setSize(size);
        searchRequest.setSortBy(sortBy);
        searchRequest.setSortDirection(sortDirection);

        PageResponse<BookDTO> books = bookService.searchBooksWithFilters(searchRequest);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/search")
    public ResponseEntity<PageResponse<BookDTO>> advancedSearch(
            @RequestBody BookSearchRequest searchRequest
            ){
        PageResponse<BookDTO> books = bookService.searchBooksWithFilters(searchRequest);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/stats")
    public ResponseEntity<BookStatsResponse> getBookStats() {
        long totalActive = bookService.getTotalActiveBooks();
        long totalAvailable = bookService.getTotalAvailableBooks();

        BookStatsResponse bookStatsResponse = new BookStatsResponse(totalActive, totalAvailable);
        return ResponseEntity.ok(bookStatsResponse);
    }

    public static  class BookStatsResponse {
        public long totalActiveBooks;
        public long totalAvailableBooks;

        public BookStatsResponse(long totalActiveBooks, long totalAvailableBooks) {
            this.totalActiveBooks = totalActiveBooks;
            this.totalAvailableBooks = totalAvailableBooks;
        }
    }
}
