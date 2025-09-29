package com.rcf.schedulesservice.repository;

import com.rcf.schedulesservice.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatusRepository extends JpaRepository<BookingStatus, Long> {
}
