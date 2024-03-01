package com.microservice.itemservice.controller;

import com.microservice.itemservice.dto.paginateDto.ItemPaginateDto;
import com.microservice.itemservice.dto.requestDto.ItemRequestDto;
import com.microservice.itemservice.dto.responseDto.ItemResponseDto;
import com.microservice.itemservice.service.ItemService;
import com.microservice.itemservice.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemRequestDto itemRequestDto) {

        String message = itemService.saveItem(itemRequestDto);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/get-by-id",
            params = "itemId"
    )
    public ResponseEntity<StandardResponse> getById(long itemId) {

        ItemResponseDto message = itemService.findByItemId(itemId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<StandardResponse> updateItem(@RequestBody ItemRequestDto itemRequestDto,@PathVariable long itemId) {
        String message = itemService.updateItem(itemRequestDto,itemId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteItem(@PathVariable(value = "id") long itemId) {
        String message = itemService.delete(itemId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @GetMapping(value = "/all", params = {"page","size","searchText"})
    public ResponseEntity<StandardResponse> getAlItems(@RequestParam int page, @RequestParam int size, @RequestParam String searchText) {
        ItemPaginateDto allItems = itemService.getAllItems(page,size,searchText);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", allItems)
                , HttpStatus.OK);
    }

    @GetMapping( "/isExist/{itemId}")
    public ResponseEntity<Boolean> isExist(@PathVariable long itemId) {

        boolean message = itemService.isExistItem(itemId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
