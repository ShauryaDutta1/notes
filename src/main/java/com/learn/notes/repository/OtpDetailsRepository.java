package com.learn.notes.repository;

import com.learn.notes.model.OtpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpDetailsRepository extends JpaRepository<OtpDetails,Long> {

    @Query(value = "select otp.otp from tbl_otpdetails where otp.email=:email order by 1 desc limit 1", nativeQuery = true)
    String findOtpByEmail(String email);
}
