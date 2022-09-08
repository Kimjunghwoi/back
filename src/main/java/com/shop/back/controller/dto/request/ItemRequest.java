package com.shop.back.controller.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {

    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;


}
