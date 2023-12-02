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


package org.openapitools.client.api;

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.openapitools.client.model.CrashData;
import org.openapitools.client.model.State;
import org.openapitools.client.model.UsersRegisterPostRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class DefaultApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public DefaultApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DefaultApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for crashReportsProjectProjectIdGet
     * @param projectId The ID of the project (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Crash data retrieved successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call crashReportsProjectProjectIdGetCall(String projectId, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/crash-reports/project/{projectId}"
            .replaceAll("\\{" + "projectId" + "\\}", localVarApiClient.escapeString(projectId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call crashReportsProjectProjectIdGetValidateBeforeCall(String projectId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'projectId' is set
        if (projectId == null) {
            throw new ApiException("Missing the required parameter 'projectId' when calling crashReportsProjectProjectIdGet(Async)");
        }
        

        okhttp3.Call localVarCall = crashReportsProjectProjectIdGetCall(projectId, _callback);
        return localVarCall;

    }

    /**
     * 
     * Retrieve crash data for a specific project
     * @param projectId The ID of the project (required)
     * @return List&lt;CrashData&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Crash data retrieved successfully </td><td>  -  </td></tr>
     </table>
     */
    public List<CrashData> crashReportsProjectProjectIdGet(String projectId) throws ApiException {
        ApiResponse<List<CrashData>> localVarResp = crashReportsProjectProjectIdGetWithHttpInfo(projectId);
        return localVarResp.getData();
    }

    /**
     * 
     * Retrieve crash data for a specific project
     * @param projectId The ID of the project (required)
     * @return ApiResponse&lt;List&lt;CrashData&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Crash data retrieved successfully </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<CrashData>> crashReportsProjectProjectIdGetWithHttpInfo(String projectId) throws ApiException {
        okhttp3.Call localVarCall = crashReportsProjectProjectIdGetValidateBeforeCall(projectId, null);
        Type localVarReturnType = new TypeToken<List<CrashData>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Retrieve crash data for a specific project
     * @param projectId The ID of the project (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Crash data retrieved successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call crashReportsProjectProjectIdGetAsync(String projectId, final ApiCallback<List<CrashData>> _callback) throws ApiException {

        okhttp3.Call localVarCall = crashReportsProjectProjectIdGetValidateBeforeCall(projectId, _callback);
        Type localVarReturnType = new TypeToken<List<CrashData>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for crashReportsReportPost
     * @param crashData  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Crash data added successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call crashReportsReportPostCall(CrashData crashData, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = crashData;

        // create path and map variables
        String localVarPath = "/crash-reports/report";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call crashReportsReportPostValidateBeforeCall(CrashData crashData, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'crashData' is set
        if (crashData == null) {
            throw new ApiException("Missing the required parameter 'crashData' when calling crashReportsReportPost(Async)");
        }
        

        okhttp3.Call localVarCall = crashReportsReportPostCall(crashData, _callback);
        return localVarCall;

    }

    /**
     * 
     * Report a new crash
     * @param crashData  (required)
     * @return State
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Crash data added successfully </td><td>  -  </td></tr>
     </table>
     */
    public State crashReportsReportPost(CrashData crashData) throws ApiException {
        ApiResponse<State> localVarResp = crashReportsReportPostWithHttpInfo(crashData);
        return localVarResp.getData();
    }

    /**
     * 
     * Report a new crash
     * @param crashData  (required)
     * @return ApiResponse&lt;State&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Crash data added successfully </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<State> crashReportsReportPostWithHttpInfo(CrashData crashData) throws ApiException {
        okhttp3.Call localVarCall = crashReportsReportPostValidateBeforeCall(crashData, null);
        Type localVarReturnType = new TypeToken<State>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Report a new crash
     * @param crashData  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Crash data added successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call crashReportsReportPostAsync(CrashData crashData, final ApiCallback<State> _callback) throws ApiException {

        okhttp3.Call localVarCall = crashReportsReportPostValidateBeforeCall(crashData, _callback);
        Type localVarReturnType = new TypeToken<State>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for usersLoginPost
     * @param usersRegisterPostRequest  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> User authenticated successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call usersLoginPostCall(UsersRegisterPostRequest usersRegisterPostRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = usersRegisterPostRequest;

        // create path and map variables
        String localVarPath = "/users/login";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call usersLoginPostValidateBeforeCall(UsersRegisterPostRequest usersRegisterPostRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'usersRegisterPostRequest' is set
        if (usersRegisterPostRequest == null) {
            throw new ApiException("Missing the required parameter 'usersRegisterPostRequest' when calling usersLoginPost(Async)");
        }
        

        okhttp3.Call localVarCall = usersLoginPostCall(usersRegisterPostRequest, _callback);
        return localVarCall;

    }

    /**
     * 
     * Authenticate a user
     * @param usersRegisterPostRequest  (required)
     * @return State
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> User authenticated successfully </td><td>  -  </td></tr>
     </table>
     */
    public State usersLoginPost(UsersRegisterPostRequest usersRegisterPostRequest) throws ApiException {
        ApiResponse<State> localVarResp = usersLoginPostWithHttpInfo(usersRegisterPostRequest);
        return localVarResp.getData();
    }

    /**
     * 
     * Authenticate a user
     * @param usersRegisterPostRequest  (required)
     * @return ApiResponse&lt;State&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> User authenticated successfully </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<State> usersLoginPostWithHttpInfo(UsersRegisterPostRequest usersRegisterPostRequest) throws ApiException {
        okhttp3.Call localVarCall = usersLoginPostValidateBeforeCall(usersRegisterPostRequest, null);
        Type localVarReturnType = new TypeToken<State>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Authenticate a user
     * @param usersRegisterPostRequest  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> User authenticated successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call usersLoginPostAsync(UsersRegisterPostRequest usersRegisterPostRequest, final ApiCallback<State> _callback) throws ApiException {

        okhttp3.Call localVarCall = usersLoginPostValidateBeforeCall(usersRegisterPostRequest, _callback);
        Type localVarReturnType = new TypeToken<State>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for usersRegisterPost
     * @param usersRegisterPostRequest  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> User registered successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call usersRegisterPostCall(UsersRegisterPostRequest usersRegisterPostRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = usersRegisterPostRequest;

        // create path and map variables
        String localVarPath = "/users/register";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call usersRegisterPostValidateBeforeCall(UsersRegisterPostRequest usersRegisterPostRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'usersRegisterPostRequest' is set
        if (usersRegisterPostRequest == null) {
            throw new ApiException("Missing the required parameter 'usersRegisterPostRequest' when calling usersRegisterPost(Async)");
        }
        

        okhttp3.Call localVarCall = usersRegisterPostCall(usersRegisterPostRequest, _callback);
        return localVarCall;

    }

    /**
     * 
     * Register a new user
     * @param usersRegisterPostRequest  (required)
     * @return State
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> User registered successfully </td><td>  -  </td></tr>
     </table>
     */
    public State usersRegisterPost(UsersRegisterPostRequest usersRegisterPostRequest) throws ApiException {
        ApiResponse<State> localVarResp = usersRegisterPostWithHttpInfo(usersRegisterPostRequest);
        return localVarResp.getData();
    }

    /**
     * 
     * Register a new user
     * @param usersRegisterPostRequest  (required)
     * @return ApiResponse&lt;State&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> User registered successfully </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<State> usersRegisterPostWithHttpInfo(UsersRegisterPostRequest usersRegisterPostRequest) throws ApiException {
        okhttp3.Call localVarCall = usersRegisterPostValidateBeforeCall(usersRegisterPostRequest, null);
        Type localVarReturnType = new TypeToken<State>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Register a new user
     * @param usersRegisterPostRequest  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> User registered successfully </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call usersRegisterPostAsync(UsersRegisterPostRequest usersRegisterPostRequest, final ApiCallback<State> _callback) throws ApiException {

        okhttp3.Call localVarCall = usersRegisterPostValidateBeforeCall(usersRegisterPostRequest, _callback);
        Type localVarReturnType = new TypeToken<State>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}