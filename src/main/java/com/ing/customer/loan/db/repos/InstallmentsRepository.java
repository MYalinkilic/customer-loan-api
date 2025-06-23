package com.ing.customer.loan.db.repos;

import com.ing.customer.loan.db.Customers;
import com.ing.customer.loan.db.LoanInstallments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InstallmentsRepository extends JpaRepository<LoanInstallments,Long> {

    List<LoanInstallments> findByCustomerIdAndLoanId(String customerId,String loanId);

    List<LoanInstallments> findByCustomerIdAndLoanIdAndIsPaidOrderByInstallmentOrder(String customerId,String loanId, String isPaid);
    List<LoanInstallments> findTop3ByCustomerIdAndLoanIdAndIsPaidOrderByInstallmentOrderAsc(String customerId, String loanId, String isPaid);


}
