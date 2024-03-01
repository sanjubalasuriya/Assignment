package com.microservice.itemservice.dto.paginateDto;

import com.microservice.itemservice.dto.responseDto.ItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemPaginateDto {

    List<ItemResponseDto> list;
    private long dataCount;
}
