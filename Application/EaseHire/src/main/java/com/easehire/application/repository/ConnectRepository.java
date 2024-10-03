package com.easehire.application.repository;

import com.easehire.application.entity.Connect;
import com.easehire.application.utility.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ConnectRepository extends JpaRepository<Connect, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Connect c SET c.status = :status WHERE c.connectId = :connectId")
    void updateStatusByConnectId(@Param("connectId") Long connectId, @Param("status") Status status);
}
