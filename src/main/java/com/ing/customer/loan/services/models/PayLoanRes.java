package com.ing.customer.loan.services.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ing.customer.loan.services.models.PaidInstallments;
import java.math.BigDecimal;
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
 * PayLoanRes
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class PayLoanRes {

  private String loanStatus;

  private BigDecimal spentAmount;

  @Valid
  private List<@Valid PaidInstallments> customerLoanT;

  public PayLoanRes loanStatus(String loanStatus) {
    this.loanStatus = loanStatus;
    return this;
  }

  /**
   * Get loanStatus
   * @return loanStatus
  */
  
  @Schema(name = "loanStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("loanStatus")
  public String getLoanStatus() {
    return loanStatus;
  }

  public void setLoanStatus(String loanStatus) {
    this.loanStatus = loanStatus;
  }

  public PayLoanRes spentAmount(BigDecimal spentAmount) {
    this.spentAmount = spentAmount;
    return this;
  }

  /**
   * Get spentAmount
   * @return spentAmount
  */
  @Valid 
  @Schema(name = "spentAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("spentAmount")
  public BigDecimal getSpentAmount() {
    return spentAmount;
  }

  public void setSpentAmount(BigDecimal spentAmount) {
    this.spentAmount = spentAmount;
  }

  public PayLoanRes customerLoanT(List<@Valid PaidInstallments> customerLoanT) {
    this.customerLoanT = customerLoanT;
    return this;
  }

  public PayLoanRes addCustomerLoanTItem(PaidInstallments customerLoanTItem) {
    if (this.customerLoanT == null) {
      this.customerLoanT = new ArrayList<>();
    }
    this.customerLoanT.add(customerLoanTItem);
    return this;
  }

  /**
   * Get customerLoanT
   * @return customerLoanT
  */
  @Valid 
  @Schema(name = "CustomerLoanT", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("CustomerLoanT")
  public List<@Valid PaidInstallments> getCustomerLoanT() {
    return customerLoanT;
  }

  public void setCustomerLoanT(List<@Valid PaidInstallments> customerLoanT) {
    this.customerLoanT = customerLoanT;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayLoanRes payLoanRes = (PayLoanRes) o;
    return Objects.equals(this.loanStatus, payLoanRes.loanStatus) &&
        Objects.equals(this.spentAmount, payLoanRes.spentAmount) &&
        Objects.equals(this.customerLoanT, payLoanRes.customerLoanT);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loanStatus, spentAmount, customerLoanT);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayLoanRes {\n");
    sb.append("    loanStatus: ").append(toIndentedString(loanStatus)).append("\n");
    sb.append("    spentAmount: ").append(toIndentedString(spentAmount)).append("\n");
    sb.append("    customerLoanT: ").append(toIndentedString(customerLoanT)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

