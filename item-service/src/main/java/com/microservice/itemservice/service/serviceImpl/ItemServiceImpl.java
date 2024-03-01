package com.microservice.itemservice.service.serviceImpl;

import com.microservice.itemservice.dto.paginateDto.ItemPaginateDto;
import com.microservice.itemservice.dto.requestDto.ItemRequestDto;
import com.microservice.itemservice.dto.responseDto.ItemResponseDto;
import com.microservice.itemservice.exception.NotFoundException;
import com.microservice.itemservice.model.Item;
import com.microservice.itemservice.repository.ItemRepository;
import com.microservice.itemservice.service.ItemService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public String saveItem(ItemRequestDto itemRequestDto) {
        Item item = new Item();
        item.setName(itemRequestDto.getName());
        item.setQty(itemRequestDto.getQty());
        item.setPrice(itemRequestDto.getPrice());

        itemRepository.save(item);
        return "Item saved "+ item.getName();
    }

    @Override
    public ItemResponseDto findByItemId(long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isEmpty()) {
            throw new NotFoundException("Item Not Found");
        }
        else {
            return new ItemResponseDto(
                    item.get().getItemId(),
                    item.get().getName(),
                    item.get().getPrice(),
                    item.get().getQty()
            );
        }
    }

    @Override
    public String updateItem(ItemRequestDto itemRequestDto, long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isEmpty()){
            throw new NotFoundException("Item Not Found");

        }else {
            item.get().setName(itemRequestDto.getName());
            item.get().setQty(itemRequestDto.getQty());
            item.get().setPrice(itemRequestDto.getPrice());

            itemRepository.save(item.get());
            return "Item saved "+ item.get().getName();
        }
    }

    @Override
    public String delete(long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isEmpty()){
            throw new NotFoundException("Item Not Found");
        }else {
            itemRepository.deleteById(itemId);

            return "deleted " + item.get().getName();
        }
    }

    @Override
    public ItemPaginateDto getAllItems(int page, int size, String searchText) {
        List<Item> itemList = itemRepository.findAllItems(searchText, PageRequest.of(page, size));
        Long count = itemRepository.findAllItemCount(searchText);
        List<ItemResponseDto> dtos = new ArrayList<>();
        System.out.println(itemList);
        itemList.forEach(e->{
            dtos.add(new ItemResponseDto(
                    e.getItemId(),
                    e.getName(),
                    e.getPrice(),
                    e.getQty()
            ));
        });
        return new ItemPaginateDto(dtos,count);
    }

    @Override
    public boolean isExistItem(long itemId) {
        return itemRepository.existsById(itemId);

    }
}
