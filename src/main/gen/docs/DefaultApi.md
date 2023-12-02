# DefaultApi

All URIs are relative to *http://0.0.0.0:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**crashReportsProjectProjectIdGet**](DefaultApi.md#crashReportsProjectProjectIdGet) | **GET** /crash-reports/project/{projectId} |  |
| [**crashReportsReportPost**](DefaultApi.md#crashReportsReportPost) | **POST** /crash-reports/report |  |
| [**usersLoginPost**](DefaultApi.md#usersLoginPost) | **POST** /users/login |  |
| [**usersRegisterPost**](DefaultApi.md#usersRegisterPost) | **POST** /users/register |  |


<a name="crashReportsProjectProjectIdGet"></a>
# **crashReportsProjectProjectIdGet**
> List&lt;CrashData&gt; crashReportsProjectProjectIdGet(projectId)



Retrieve crash data for a specific project

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://0.0.0.0:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String projectId = "projectId_example"; // String | The ID of the project
    try {
      List<CrashData> result = apiInstance.crashReportsProjectProjectIdGet(projectId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#crashReportsProjectProjectIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **projectId** | **String**| The ID of the project | |

### Return type

[**List&lt;CrashData&gt;**](CrashData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Crash data retrieved successfully |  -  |

<a name="crashReportsReportPost"></a>
# **crashReportsReportPost**
> State crashReportsReportPost(crashData)



Report a new crash

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://0.0.0.0:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    CrashData crashData = new CrashData(); // CrashData | 
    try {
      State result = apiInstance.crashReportsReportPost(crashData);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#crashReportsReportPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **crashData** | [**CrashData**](CrashData.md)|  | |

### Return type

[**State**](State.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Crash data added successfully |  -  |

<a name="usersLoginPost"></a>
# **usersLoginPost**
> State usersLoginPost(usersRegisterPostRequest)



Authenticate a user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://0.0.0.0:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UsersRegisterPostRequest usersRegisterPostRequest = new UsersRegisterPostRequest(); // UsersRegisterPostRequest | 
    try {
      State result = apiInstance.usersLoginPost(usersRegisterPostRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#usersLoginPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **usersRegisterPostRequest** | [**UsersRegisterPostRequest**](UsersRegisterPostRequest.md)|  | |

### Return type

[**State**](State.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | User authenticated successfully |  -  |

<a name="usersRegisterPost"></a>
# **usersRegisterPost**
> State usersRegisterPost(usersRegisterPostRequest)



Register a new user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://0.0.0.0:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    UsersRegisterPostRequest usersRegisterPostRequest = new UsersRegisterPostRequest(); // UsersRegisterPostRequest | 
    try {
      State result = apiInstance.usersRegisterPost(usersRegisterPostRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#usersRegisterPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **usersRegisterPostRequest** | [**UsersRegisterPostRequest**](UsersRegisterPostRequest.md)|  | |

### Return type

[**State**](State.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | User registered successfully |  -  |

