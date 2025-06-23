package com.ing.customer.loan.services.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PayLoanReq
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class PayLoanReq {

  private String loanId;

  private String customerId;

  private BigDecimal paidAmount;

  public PayLoanReq loanId(String loanId) {
    this.loanId = loanId;
    return this;
  }

  /**
   * Get loanId
   * @return loanId
  */
  
  @Schema(name = "loanId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("loanId")
  public String getLoanId() {
    return loanId;
  }

  public void setLoanId(String loanId) {
    this.loanId = loanId;
  }

  public PayLoanReq customerId(String customerId) {
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

  public PayLoanReq paidAmount(BigDecimal paidAmount) {
    this.paidAmount = paidAmount;
    return this;
  }

  /**
   * Get paidAmount
   * @return paidAmount
  */
  @Valid 
  @Schema(name = "paidAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("paidAmount")
  public BigDecimal getPaidAmount() {
    return paidAmount;
  }

  public void setPaidAmount(BigDecimal paidAmount) {
    this.paidAmount = paidAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayLoanReq payLoanReq = (PayLoanReq) o;
    return Objects.equals(this.loanId, payLoanReq.loanId) &&
        Objects.equals(this.customerId, payLoanReq.customerId) &&
        Objects.equals(this.paidAmount, payLoanReq.paidAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loanId, customerId, paidAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayLoanReq {\n");
    sb.append("    loanId: ").append(toIndentedString(loanId)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    paidAmount: ").append(toIndentedString(paidAmount)).append("\n");
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

