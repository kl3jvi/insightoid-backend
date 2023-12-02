/*
 * Application API
 * Application API
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * CrashData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-12-02T02:25:36.328457+01:00[Europe/Tirane]")
public class CrashData {
  public static final String SERIALIZED_NAME_THREAD_NAME = "threadName";
  @SerializedName(SERIALIZED_NAME_THREAD_NAME)
  private String threadName;

  public static final String SERIALIZED_NAME_THREAD_ID = "threadId";
  @SerializedName(SERIALIZED_NAME_THREAD_ID)
  private Integer threadId;

  public static final String SERIALIZED_NAME_EXCEPTION_NAME = "exceptionName";
  @SerializedName(SERIALIZED_NAME_EXCEPTION_NAME)
  private String exceptionName;

  public static final String SERIALIZED_NAME_EXCEPTION_MESSAGE = "exceptionMessage";
  @SerializedName(SERIALIZED_NAME_EXCEPTION_MESSAGE)
  private String exceptionMessage;

  public static final String SERIALIZED_NAME_STACK_TRACE = "stackTrace";
  @SerializedName(SERIALIZED_NAME_STACK_TRACE)
  private String stackTrace;

  public CrashData() {
  }

  public CrashData threadName(String threadName) {
    
    this.threadName = threadName;
    return this;
  }

   /**
   * Get threadName
   * @return threadName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getThreadName() {
    return threadName;
  }


  public void setThreadName(String threadName) {
    this.threadName = threadName;
  }


  public CrashData threadId(Integer threadId) {
    
    this.threadId = threadId;
    return this;
  }

   /**
   * Get threadId
   * @return threadId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getThreadId() {
    return threadId;
  }


  public void setThreadId(Integer threadId) {
    this.threadId = threadId;
  }


  public CrashData exceptionName(String exceptionName) {
    
    this.exceptionName = exceptionName;
    return this;
  }

   /**
   * Get exceptionName
   * @return exceptionName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getExceptionName() {
    return exceptionName;
  }


  public void setExceptionName(String exceptionName) {
    this.exceptionName = exceptionName;
  }


  public CrashData exceptionMessage(String exceptionMessage) {
    
    this.exceptionMessage = exceptionMessage;
    return this;
  }

   /**
   * Get exceptionMessage
   * @return exceptionMessage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getExceptionMessage() {
    return exceptionMessage;
  }


  public void setExceptionMessage(String exceptionMessage) {
    this.exceptionMessage = exceptionMessage;
  }


  public CrashData stackTrace(String stackTrace) {
    
    this.stackTrace = stackTrace;
    return this;
  }

   /**
   * Get stackTrace
   * @return stackTrace
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getStackTrace() {
    return stackTrace;
  }


  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CrashData crashData = (CrashData) o;
    return Objects.equals(this.threadName, crashData.threadName) &&
        Objects.equals(this.threadId, crashData.threadId) &&
        Objects.equals(this.exceptionName, crashData.exceptionName) &&
        Objects.equals(this.exceptionMessage, crashData.exceptionMessage) &&
        Objects.equals(this.stackTrace, crashData.stackTrace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(threadName, threadId, exceptionName, exceptionMessage, stackTrace);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CrashData {\n");
    sb.append("    threadName: ").append(toIndentedString(threadName)).append("\n");
    sb.append("    threadId: ").append(toIndentedString(threadId)).append("\n");
    sb.append("    exceptionName: ").append(toIndentedString(exceptionName)).append("\n");
    sb.append("    exceptionMessage: ").append(toIndentedString(exceptionMessage)).append("\n");
    sb.append("    stackTrace: ").append(toIndentedString(stackTrace)).append("\n");
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


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("threadName");
    openapiFields.add("threadId");
    openapiFields.add("exceptionName");
    openapiFields.add("exceptionMessage");
    openapiFields.add("stackTrace");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to CrashData
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (CrashData.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in CrashData is not found in the empty JSON string", CrashData.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!CrashData.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `CrashData` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }
      if ((jsonObj.get("threadName") != null && !jsonObj.get("threadName").isJsonNull()) && !jsonObj.get("threadName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `threadName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("threadName").toString()));
      }
      if ((jsonObj.get("exceptionName") != null && !jsonObj.get("exceptionName").isJsonNull()) && !jsonObj.get("exceptionName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `exceptionName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("exceptionName").toString()));
      }
      if ((jsonObj.get("exceptionMessage") != null && !jsonObj.get("exceptionMessage").isJsonNull()) && !jsonObj.get("exceptionMessage").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `exceptionMessage` to be a primitive type in the JSON string but got `%s`", jsonObj.get("exceptionMessage").toString()));
      }
      if ((jsonObj.get("stackTrace") != null && !jsonObj.get("stackTrace").isJsonNull()) && !jsonObj.get("stackTrace").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `stackTrace` to be a primitive type in the JSON string but got `%s`", jsonObj.get("stackTrace").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!CrashData.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'CrashData' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<CrashData> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(CrashData.class));

       return (TypeAdapter<T>) new TypeAdapter<CrashData>() {
           @Override
           public void write(JsonWriter out, CrashData value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public CrashData read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of CrashData given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of CrashData
  * @throws IOException if the JSON string is invalid with respect to CrashData
  */
  public static CrashData fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, CrashData.class);
  }

 /**
  * Convert an instance of CrashData to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

