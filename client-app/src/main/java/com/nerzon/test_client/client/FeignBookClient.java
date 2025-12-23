package com.nerzon.test_client.client;

import com.nerzon.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "feignBookClient", url = "http://localhost:8080/api/books")
public interface FeignBookClient {

    @PostMapping
    BookDto saveBook(@RequestBody BookDto book);

    @GetMapping("/{id}")
    BookDto getBook(@PathVariable String id);
}
