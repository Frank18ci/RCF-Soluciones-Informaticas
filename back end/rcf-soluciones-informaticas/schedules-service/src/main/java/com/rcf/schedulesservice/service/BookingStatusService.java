package com.rcf.schedulesservice.service;

import com.rcf.schedulesservice.dto.BookingStatusRequest;
import com.rcf.schedulesservice.dto.BookingStatusResponse;

import java.util.List;

public interface BookingStatusService {
    List<BookingStatusResponse> getAllBookingStatuses();
    BookingStatusResponse getBookingStatusById(Long id);
    BookingStatusResponse saveBookingStatus(BookingStatusRequest bookingStatusRequest);
    BookingStatusResponse updateBookingStatus(Long id, BookingStatusRequest bookingStatusRequest);
    void deleteBookingStatus(Long id);
}
