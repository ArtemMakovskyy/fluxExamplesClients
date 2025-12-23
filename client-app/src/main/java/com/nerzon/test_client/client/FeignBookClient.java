// src/main/java/com/nerzon/test_client/client/FeignBookClient.java
package com.nerzon.test_client.client;

import com.nerzon.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "feignBookClient", url = "http://localhost:8080/api/books")
public interface FeignBookClient {

    @PostMapping
    Book saveBook(@RequestBody Book book);

    @GetMapping("/{id}")
    Book getBook(@PathVariable("id") int id);
}
