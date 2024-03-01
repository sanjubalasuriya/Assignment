package com.microservice.itemservice.service;

import com.microservice.itemservice.dto.paginateDto.ItemPaginateDto;
import com.microservice.itemservice.dto.requestDto.ItemRequestDto;
import com.microservice.itemservice.dto.responseDto.ItemResponseDto;

public interface ItemService {
    String saveItem(ItemRequestDto itemRequestDto);

    ItemResponseDto findByItemId(long itemId);

    String updateItem(ItemRequestDto itemRequestDto, long itemId);

    String delete(long itemId);

    ItemPaginateDto getAllItems(int page, int size, String searchText);

    boolean isExistItem(long itemId);
}
