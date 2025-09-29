package com.rcf.schedulesservice.service.impl;

import com.rcf.schedulesservice.dto.BookingStatusRequest;
import com.rcf.schedulesservice.dto.BookingStatusResponse;
import com.rcf.schedulesservice.exception.ResourceNotFound;
import com.rcf.schedulesservice.model.BookingStatus;
import com.rcf.schedulesservice.repository.BookingStatusRepository;
import com.rcf.schedulesservice.service.BookingStatusService;
import com.rcf.schedulesservice.uti.BookingStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingStatusServiceImpl implements BookingStatusService {
    private final BookingStatusRepository bookingStatusRepository;
    private final BookingStatusMapper bookingStatusMapper;

    @Override
    public List<BookingStatusResponse> getAllBookingStatuses() {
        return bookingStatusMapper.toDtoList(bookingStatusRepository.findAll());
    }

    @Override
    public BookingStatusResponse getBookingStatusById(Long id) {
        return bookingStatusMapper.toDto(bookingStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Booking status not found with id: " + id)
        ));
    }

    @Override
    public BookingStatusResponse saveBookingStatus(BookingStatusRequest bookingStatusRequest) {
        return bookingStatusMapper.toDto(bookingStatusRepository.save(bookingStatusMapper.toEntity(bookingStatusRequest)));
    }

    @Override
    public BookingStatusResponse updateBookingStatus(Long id, BookingStatusRequest bookingStatusRequest) {
        BookingStatus bookingStatusFound = bookingStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Booking status not found with id: " + id)
        );
        bookingStatusFound.setCode(bookingStatusRequest.code());
        bookingStatusFound.setSortOrder(bookingStatusRequest.sortOrder());

        return bookingStatusMapper.toDto(bookingStatusRepository.save(bookingStatusFound));
    }

    @Override
    public void deleteBookingStatus(Long id) {
        BookingStatus bookingStatusFound = bookingStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Booking status not found with id: " + id)
        );
        bookingStatusRepository.delete(bookingStatusFound);
    }
}
