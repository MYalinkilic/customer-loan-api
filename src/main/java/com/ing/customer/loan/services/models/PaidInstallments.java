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
 * PaidInstallments
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class PaidInstallments {

  private String loanId;

  private String customerId;

  private BigDecimal paidAmount;

  private Integer paidInstallmentOrder;

  public PaidInstallments loanId(String loanId) {
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

  public PaidInstallments customerId(String customerId) {
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

  public PaidInstallments paidAmount(BigDecimal paidAmount) {
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

  public PaidInstallments paidInstallmentOrder(Integer paidInstallmentOrder) {
    this.paidInstallmentOrder = paidInstallmentOrder;
    return this;
  }

  /**
   * Get paidInstallmentOrder
   * @return paidInstallmentOrder
  */
  
  @Schema(name = "paidInstallmentOrder", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("paidInstallmentOrder")
  public Integer getPaidInstallmentOrder() {
    return paidInstallmentOrder;
  }

  public void setPaidInstallmentOrder(Integer paidInstallmentOrder) {
    this.paidInstallmentOrder = paidInstallmentOrder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaidInstallments paidInstallments = (PaidInstallments) o;
    return Objects.equals(this.loanId, paidInstallments.loanId) &&
        Objects.equals(this.customerId, paidInstallments.customerId) &&
        Objects.equals(this.paidAmount, paidInstallments.paidAmount) &&
        Objects.equals(this.paidInstallmentOrder, paidInstallments.paidInstallmentOrder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loanId, customerId, paidAmount, paidInstallmentOrder);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaidInstallments {\n");
    sb.append("    loanId: ").append(toIndentedString(loanId)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    paidAmount: ").append(toIndentedString(paidAmount)).append("\n");
    sb.append("    paidInstallmentOrder: ").append(toIndentedString(paidInstallmentOrder)).append("\n");
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

