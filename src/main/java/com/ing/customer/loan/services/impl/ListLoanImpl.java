package com.ing.customer.loan.services.impl;

import com.ing.customer.loan.services.api.ListLoanApi;
import com.ing.customer.loan.auth.AuthenticatedUserDetails;
import com.ing.customer.loan.db.Loans;
import com.ing.customer.loan.db.repos.LoansRepository;
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
public class ListLoanImpl extends AuthenticatedUserDetails implements ListLoanApi {


    private final LoansRepository loansRepository;

    public ListLoanImpl(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }
    @Override
    public ResponseEntity<Object> listLoanGet(String customerId, String loanId, String isPaid) {



        try{
            List<Loans> customerLoans = loansRepository.findByCustomerIdAndLoanIdAndOptionalIsPaid(operatedCustomer(customerId),loanId,isPaid);
            if(customerLoans.isEmpty())
                throw new SQLException("Data not found");
            return ResponseEntity.ok().body(customerLoans);
        }
        catch (SQLException e) {
            Error error = new Error();
            error.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            error.message(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
        catch (NullPointerException e){
            Error error = new Error();
            error.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            error.message("Data not found");
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
