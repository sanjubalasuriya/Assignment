package com.microservice.itemservice.service.serviceImpl;

import com.microservice.itemservice.dto.paginateDto.ItemPaginateDto;
import com.microservice.itemservice.dto.requestDto.ItemRequestDto;
import com.microservice.itemservice.dto.responseDto.ItemResponseDto;
import com.microservice.itemservice.model.Item;
import com.microservice.itemservice.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void itemSaveTest(){

        ItemRequestDto itemRequestDto = new ItemRequestDto("item1",1000,10);
        Item item = new Item(123,"item1",1000,10);
        Item savedItem = new Item(123,"item1",1000,10);

        String response =itemService.saveItem(itemRequestDto);
        when(itemRepository.save(item)).thenReturn(savedItem);
        assertEquals( "Item saved "+ itemRequestDto.getName(),  response);
    }

    @Test
    public void getAllItems(){

        int page = 0;
        int size = 10;
        String searchText = "";

        List<Item> items = new ArrayList<>();
        items.add( new Item(1,"item1",1000,10));

        List<ItemResponseDto> dtos = new ArrayList<>();
        items.forEach(e->{
            dtos.add(new ItemResponseDto(
                    e.getItemId(),
                    e.getName(),
                    e.getPrice(),
                    e.getQty()
            ));
        });
        int count = 1;

        ItemPaginateDto itemPaginateDto1 = new ItemPaginateDto(dtos,count);


        when(itemRepository.findAllItems(searchText, PageRequest.of(page, size))).thenReturn(items);
        when(itemRepository.findAllItemCount(searchText)).thenReturn((long) count);
        ItemPaginateDto itemPaginateDto = itemService.getAllItems(page,size,searchText);
        assertEquals( itemPaginateDto1,  itemPaginateDto);

    }

    @Test
    public void updateItemTest(){

        ItemRequestDto itemRequestDto = new ItemRequestDto("item1",1000,10);
        Item item = new Item(123,"item1",1000,10);
        Item savedItem = new Item(123,"item1",1000,10);
        long itemId = 1;
        String response = itemService.saveItem(itemRequestDto);
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        when(itemRepository.save(item)).thenReturn(savedItem);
        assertEquals( "Item saved "+ itemRequestDto.getName(),  response);
    }

    @Test
    public void getItemTest(){

        Item item = new Item(1,"item1",1000,10);
        long itemId = 1;
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        ItemResponseDto itemResponseDto =  itemService.findByItemId(itemId);

        assertEquals(itemResponseDto.getItemId(), item.getItemId());
    }

    @Test
    public void deleteItemTest(){

        Item item = new Item(1,"item1",1000,10);
        long itemId = 1;
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        itemService.delete(itemId);

        verify(itemRepository, times(1));
    }



}
