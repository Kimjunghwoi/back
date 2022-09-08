package com.shop.back.controller.api;

import com.shop.back.controller.BookForm;
import com.shop.back.domain.item.Book;
import com.shop.back.domain.item.Item;
import com.shop.back.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemRestController {


    private final ItemService itemService;

    @PostMapping("/new")
    public ResponseEntity<Item> create(@RequestBody BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        return ResponseEntity.ok(new Item());

    }


}
