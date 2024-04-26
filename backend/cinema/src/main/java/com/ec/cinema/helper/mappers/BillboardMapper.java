package com.ec.cinema.helper.mappers;

import org.mapstruct.Mapper;

import com.ec.cinema.domain.dto.billboard.BillboardDTO;
import com.ec.cinema.domain.entity.BillboardEntity;

@Mapper(componentModel = "spring", uses = {MovieMapper.class , RoomMapper.class})
public interface BillboardMapper {

    BillboardEntity toBillboard(BillboardDTO billboardDto);
    BillboardDTO toBillboardDto(BillboardEntity billboard);

}
