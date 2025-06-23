package com.ing.customer.loan.services.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ing.customer.loan.services.models.ErrorInfo;
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
 * ErrorComponent
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class ErrorComponent {

  private String code;

  private String message;

  private String component;

  private String rootcase;

  @Valid
  private List<@Valid ErrorInfo> info;

  public ErrorComponent() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ErrorComponent(String code) {
    this.code = code;
  }

  public ErrorComponent code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Error code
   * @return code
  */
  @NotNull 
  @Schema(name = "code", example = "403", description = "Error code", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ErrorComponent message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Error message
   * @return message
  */
  
  @Schema(name = "message", example = "Internal error", description = "Error message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorComponent component(String component) {
    this.component = component;
    return this;
  }

  /**
   * Error component
   * @return component
  */
  
  @Schema(name = "component", example = "ProcessImpl", description = "Error component", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("component")
  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  public ErrorComponent rootcase(String rootcase) {
    this.rootcase = rootcase;
    return this;
  }

  /**
   * Error cause
   * @return rootcase
  */
  
  @Schema(name = "rootcase", example = "NullPointerException", description = "Error cause", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("rootcase")
  public String getRootcase() {
    return rootcase;
  }

  public void setRootcase(String rootcase) {
    this.rootcase = rootcase;
  }

  public ErrorComponent info(List<@Valid ErrorInfo> info) {
    this.info = info;
    return this;
  }

  public ErrorComponent addInfoItem(ErrorInfo infoItem) {
    if (this.info == null) {
      this.info = new ArrayList<>();
    }
    this.info.add(infoItem);
    return this;
  }

  /**
   * Error information
   * @return info
  */
  @Valid 
  @Schema(name = "info", description = "Error information", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("info")
  public List<@Valid ErrorInfo> getInfo() {
    return info;
  }

  public void setInfo(List<@Valid ErrorInfo> info) {
    this.info = info;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorComponent errorComponent = (ErrorComponent) o;
    return Objects.equals(this.code, errorComponent.code) &&
        Objects.equals(this.message, errorComponent.message) &&
        Objects.equals(this.component, errorComponent.component) &&
        Objects.equals(this.rootcase, errorComponent.rootcase) &&
        Objects.equals(this.info, errorComponent.info);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, component, rootcase, info);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorComponent {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    component: ").append(toIndentedString(component)).append("\n");
    sb.append("    rootcase: ").append(toIndentedString(rootcase)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
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

