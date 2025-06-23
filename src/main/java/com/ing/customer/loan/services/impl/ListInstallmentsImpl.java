package com.ing.customer.loan.services.impl;

import com.ing.customer.loan.services.api.ListInstallmentsApi;
import com.ing.customer.loan.auth.AuthenticatedUserDetails;
import com.ing.customer.loan.db.LoanInstallments;
import com.ing.customer.loan.db.repos.InstallmentsRepository;
import com.ing.customer.loan.services.models.Error;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@Data
@Slf4j
@RestController
public class ListInstallmentsImpl extends AuthenticatedUserDetails implements ListInstallmentsApi {

    private final InstallmentsRepository installmentsRepository;

    // dependency injected
    public ListInstallmentsImpl(InstallmentsRepository installmentsRepository) {
        this.installmentsRepository = installmentsRepository;
    }
    // dependency injected

    @Override
    public ResponseEntity<Object> listInstallmentsGet(String customerId, String loanId) {

        try{
            List<LoanInstallments> loanInstallments = installmentsRepository.findByCustomerIdAndLoanId(operatedCustomer(customerId),loanId);
            if(loanInstallments.size() == 0)
                throw new SQLException("Data not found");
            return ResponseEntity.ok().body(loanInstallments);

        }catch (SQLException e) {
            Error error = new Error();
            error.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            error.message(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
        catch (Exception e){
            Error error = new Error();
            error.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            error.message(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }

    }
}
