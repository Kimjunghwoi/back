package com.shop.back.controller;

import com.shop.back.domain.item.Book;
import com.shop.back.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

//   백, 프론트 분리로 필요없어짐.
//    @GetMapping("/new")
//    public String createForm(Model model) {
//        model.addAttribute("form", new BookForm());
//        return "items/createItemForm";
//    }

    @PostMapping("/items/new")
    public String create(BookForm form) {

        //validation 생략.. 나중에 추가..
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        //위와 같은 set보단 create book 을 만들어서 set을 안쓰도록하는게 좋음
        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        model.addAttribute("items", itemService.findItems());
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {
        itemService.updateItem(form.getId(), form.getName(), form.getPrice(),form.getStockQuantity());
        return "redirect:/items";
    }
}
