package com.ing.customer.loan.services.models;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ing.customer.loan.services.models.CustomerLoan;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CustomerLoanRes
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class CustomerLoanRes {

  private String loanId;

  private BigDecimal totalLoanAmount;

  public String getLoanId() {
    return loanId;
  }

  public void setLoanId(String loanId) {
    this.loanId = loanId;
  }

  public BigDecimal getTotalLoanAmount() {
    return totalLoanAmount;
  }

  public void setTotalLoanAmount(BigDecimal totalLoanAmount) {
    this.totalLoanAmount = totalLoanAmount;
  }
}

