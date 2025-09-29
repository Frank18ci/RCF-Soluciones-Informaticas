package com.rcf.schedulesservice.uti;

import com.rcf.schedulesservice.dto.BookingStatusRequest;
import com.rcf.schedulesservice.dto.BookingStatusResponse;
import com.rcf.schedulesservice.model.BookingStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingStatusMapper {
    BookingStatus toEntity(BookingStatusRequest bookingStatus);
    BookingStatusResponse toDto(BookingStatus bookingStatus);
    List<BookingStatusResponse> toDtoList(List<BookingStatus> bookingStatuses);
}
