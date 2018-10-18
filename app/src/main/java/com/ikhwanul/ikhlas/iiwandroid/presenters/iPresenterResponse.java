package com.ikhwanul.ikhlas.iiwandroid.presenters;

import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;

import java.util.List;

public interface iPresenterResponse {

    void doSuccess(ApiResponse response, String tag);
    void doFail(String message);
    void doValidationError(boolean error);
    void doConnectionError();
}
