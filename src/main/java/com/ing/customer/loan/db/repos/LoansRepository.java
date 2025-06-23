package com.ing.customer.loan.db.repos;
import com.ing.customer.loan.db.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<Loans,Long> {


    @Query("SELECT l FROM Loans l " +
            "WHERE l.customerId = :customerId " +
            "AND l.loanId = :loanId " +
            "AND (:isPaid IS NULL OR l.isPaid = :isPaid)")
    List<Loans> findByCustomerIdAndLoanIdAndOptionalIsPaid(
            @Param("customerId") String customerId,
            @Param("loanId") String loanId,
            @Param("isPaid") String isPaid
    );

    Loans findByCustomerIdAndLoanId(String customerId, String loanId);

    @Query(value = "SELECT * FROM Loans ORDER BY create_date DESC LIMIT 1", nativeQuery = true)
    Loans findLatestLoanNative();


    //TODO: add more filter for webservice parameters


}
