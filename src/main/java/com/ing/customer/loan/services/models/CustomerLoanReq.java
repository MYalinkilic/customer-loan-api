package com.ing.customer.loan.services.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;

import com.ing.customer.loan.annotations.ValidateInstallment;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CustomerLoanReq
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class CustomerLoanReq {

  private String customerId;

  private BigDecimal loanAmount;

  @DecimalMin(value = "0.1", inclusive = true, message = "Interest rate must be at least 0.1")
  @DecimalMax(value = "0.5", inclusive = true, message = "Interest rate must not exceed 0.5")
  private BigDecimal interestRate;
  @ValidateInstallment
  private BigDecimal installments;

  public CustomerLoanReq customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  */
  
  @Schema(name = "customerId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("customerId")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public CustomerLoanReq loanAmount(BigDecimal loanAmount) {
    this.loanAmount = loanAmount;
    return this;
  }

  /**
   * Get loanAmount
   * @return loanAmount
  */
  @Valid 
  @Schema(name = "LoanAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("LoanAmount")
  public BigDecimal getLoanAmount() {
    return loanAmount;
  }

  public void setLoanAmount(BigDecimal loanAmount) {
    this.loanAmount = loanAmount;
  }

  public CustomerLoanReq interestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
    return this;
  }

  /**
   * Get interestRate
   * @return interestRate
  */
  @Valid 
  @Schema(name = "interestRate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("interestRate")
  public BigDecimal getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
  }

  public CustomerLoanReq installments(BigDecimal installments) {
    this.installments = installments;
    return this;
  }

  /**
   * Get installments
   * @return installments
  */
  @Valid 
  @Schema(name = "installments", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("installments")
  public BigDecimal getInstallments() {
    return installments;
  }

  public void setInstallments(BigDecimal installments) {
    this.installments = installments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerLoanReq customerLoanReq = (CustomerLoanReq) o;
    return Objects.equals(this.customerId, customerLoanReq.customerId) &&
        Objects.equals(this.loanAmount, customerLoanReq.loanAmount) &&
        Objects.equals(this.interestRate, customerLoanReq.interestRate) &&
        Objects.equals(this.installments, customerLoanReq.installments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, loanAmount, interestRate, installments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerLoanReq {\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    loanAmount: ").append(toIndentedString(loanAmount)).append("\n");
    sb.append("    interestRate: ").append(toIndentedString(interestRate)).append("\n");
    sb.append("    installments: ").append(toIndentedString(installments)).append("\n");
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

