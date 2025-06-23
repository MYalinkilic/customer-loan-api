package com.ing.customer.loan.services.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CustomerLoan
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class CustomerLoan {

  private String loanId;

  private String customerId;

  private BigDecimal loanAmount;

  private Integer numberOfInstallments;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date createDate;

  private Integer isPaid;

  public CustomerLoan loanId(String loanId) {
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

  public CustomerLoan customerId(String customerId) {
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

  public CustomerLoan loanAmount(BigDecimal loanAmount) {
    this.loanAmount = loanAmount;
    return this;
  }

  /**
   * Get loanAmount
   * @return loanAmount
  */
  @Valid 
  @Schema(name = "loanAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("loanAmount")
  public BigDecimal getLoanAmount() {
    return loanAmount;
  }

  public void setLoanAmount(BigDecimal loanAmount) {
    this.loanAmount = loanAmount;
  }

  public CustomerLoan numberOfInstallments(Integer numberOfInstallments) {
    this.numberOfInstallments = numberOfInstallments;
    return this;
  }

  /**
   * Get numberOfInstallments
   * @return numberOfInstallments
  */
  
  @Schema(name = "numberOfInstallments", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberOfInstallments")
  public Integer getNumberOfInstallments() {
    return numberOfInstallments;
  }

  public void setNumberOfInstallments(Integer numberOfInstallments) {
    this.numberOfInstallments = numberOfInstallments;
  }

  public CustomerLoan createDate(Date createDate) {
    this.createDate = createDate;
    return this;
  }

  /**
   * Get createDate
   * @return createDate
  */
  @Valid 
  @Schema(name = "createDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createDate")
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public CustomerLoan isPaid(Integer isPaid) {
    this.isPaid = isPaid;
    return this;
  }

  /**
   * Get isPaid
   * @return isPaid
  */
  
  @Schema(name = "isPaid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isPaid")
  public Integer getIsPaid() {
    return isPaid;
  }

  public void setIsPaid(Integer isPaid) {
    this.isPaid = isPaid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerLoan customerLoan = (CustomerLoan) o;
    return Objects.equals(this.loanId, customerLoan.loanId) &&
        Objects.equals(this.customerId, customerLoan.customerId) &&
        Objects.equals(this.loanAmount, customerLoan.loanAmount) &&
        Objects.equals(this.numberOfInstallments, customerLoan.numberOfInstallments) &&
        Objects.equals(this.createDate, customerLoan.createDate) &&
        Objects.equals(this.isPaid, customerLoan.isPaid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loanId, customerId, loanAmount, numberOfInstallments, createDate, isPaid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerLoan {\n");
    sb.append("    loanId: ").append(toIndentedString(loanId)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    loanAmount: ").append(toIndentedString(loanAmount)).append("\n");
    sb.append("    numberOfInstallments: ").append(toIndentedString(numberOfInstallments)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
    sb.append("    isPaid: ").append(toIndentedString(isPaid)).append("\n");
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

