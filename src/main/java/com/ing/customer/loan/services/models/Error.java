package com.ing.customer.loan.services.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Error
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class Error {

  private String code;

  private String message;

  private String type;

  private String context;

  private String exception;

  private String component;

  private String application;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date timestamp;

  @Valid
  private List<@Valid Error> errors = new ArrayList<>();

  public Error() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Error(String code, String message, String type, String component, String application, Date timestamp, List<@Valid Error> errors) {
    this.code = code;
    this.message = message;
    this.type = type;
    this.component = component;
    this.application = application;
    this.timestamp = timestamp;
    this.errors = errors;
  }

  public Error code(String code) {
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

  public Error message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Error description
   * @return message
  */
  @NotNull 
  @Schema(name = "message", example = "Internal error in the service", description = "Error description", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Error type
   * @return type
  */
  @NotNull 
  @Schema(name = "type", example = "Null pointer", description = "Error type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Error context(String context) {
    this.context = context;
    return this;
  }

  /**
   * Error context
   * @return context
  */
  
  @Schema(name = "context", example = "Process query action", description = "Error context", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("context")
  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public Error exception(String exception) {
    this.exception = exception;
    return this;
  }

  /**
   * Exception
   * @return exception
  */
  
  @Schema(name = "exception", example = "NullPointerException", description = "Exception", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("exception")
  public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  public Error component(String component) {
    this.component = component;
    return this;
  }

  /**
   * Error component
   * @return component
  */
  @NotNull 
  @Schema(name = "component", example = "ProcessImpl", description = "Error component", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("component")
  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  public Error application(String application) {
    this.application = application;
    return this;
  }

  /**
   * Error application
   * @return application
  */
  @NotNull 
  @Schema(name = "application", example = "Process_Backend", description = "Error application", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("application")
  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public Error timestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Error time
   * @return timestamp
  */
  @NotNull @Valid 
  @Schema(name = "timestamp", example = "2019-01-13T18:27:41.511Z", description = "Error time", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("timestamp")
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public Error errors(List<@Valid Error> errors) {
    this.errors = errors;
    return this;
  }

  public Error addErrorsItem(Error errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Error list
   * @return errors
  */
  @NotNull @Valid 
  @Schema(name = "errors", description = "Error list", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("errors")
  public List<@Valid Error> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid Error> errors) {
    this.errors = errors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.message, error.message) &&
        Objects.equals(this.type, error.type) &&
        Objects.equals(this.context, error.context) &&
        Objects.equals(this.exception, error.exception) &&
        Objects.equals(this.component, error.component) &&
        Objects.equals(this.application, error.application) &&
        Objects.equals(this.timestamp, error.timestamp) &&
        Objects.equals(this.errors, error.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, type, context, exception, component, application, timestamp, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    context: ").append(toIndentedString(context)).append("\n");
    sb.append("    exception: ").append(toIndentedString(exception)).append("\n");
    sb.append("    component: ").append(toIndentedString(component)).append("\n");
    sb.append("    application: ").append(toIndentedString(application)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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

