package com.ikhwanul.ikhlas.iiwandroid.presenters;

import android.content.Context;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.ApiClient;
import com.ikhwanul.ikhlas.iiwandroid.api.response.AuthResponse;
import com.ikhwanul.ikhlas.iiwandroid.ui.ProgressDialogHolder;

import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenter extends Presenter {
    public static final String RES_REGISTER = "register";
    public static final String RES_LOGIN = "login";
    public static final String RES_ME = "is_me";
    public static final String RES_LOGOUT = "logout";
    public static final String RES_UPDATE_PASSWORD = "update_password";
    public static final String RES_GET_NUMBER = "get_number";
    public static final String RES_RESET_PASSWORD = "reset_password";

    public AuthPresenter(Context context, iPresenterResponse iPresenterResponse) {
        super(context, iPresenterResponse);
    }

    //
    public void login(Map<String, String> data){
        ApiClient.getInstance(context).getApi().postLogin(data).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                ProgressDialogHolder.with(context).dismissDialog();
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), RES_LOGIN);
                    }
                    else if(response.code() == 401) {
                        presenterresponse.doFail(handleErrorBody(response.errorBody()).message);
                    }
                    else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                ProgressDialogHolder.with(context).dismissDialog();
                if (t instanceof ConnectException){
                    presenterresponse.doConnectionError();
                }else{
                    presenterresponse.doFail(context.getString(R.string.failed_connection));
                }
            }
        });
    }

//    public void logout(){
//        ProgressDialogHolder.with(context)
//                .showLoadingDialog(R.string.loading);
//
//        ApiClient.getInstance(context).getApi().logout().enqueue(new Callback<AuthResponse>() {
//            @Override
//            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
//                ProgressDialogHolder.with(context).dismissDialog();
//                try {
//                    if (response.isSuccessful()) {
//                        presenterresponse.doSuccess(response.body(), RES_LOGOUT);
//                    } else {
//                        handleError(response.errorBody(), response.code());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    presenterresponse.doFail(context.getResources().getString(R.string.application_error));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AuthResponse> call, Throwable t) {
//                if (t instanceof ConnectException){
//                    presenterresponse.doConnectionError();
//                }else{
//                    presenterresponse.doFail(context.getString(R.string.failed_connection));
//                }
//                ProgressDialogHolder.with(context).dismissDialog();
//            }
//        });
//    }
//
//    public void verifyNumber(String number){
//        ProgressDialogHolder.with(context)
//                .showLoadingDialog(R.string.checking_phone_number);
//
//        ApiClient.getInstance(context).getApi().verifyNumber(number).enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                ProgressDialogHolder.with(context).dismissDialog();
//                try {
//                    if (response.isSuccessful()){
//                        presenterresponse.doSuccess(response.body(), RES_GET_NUMBER);
//                    }
//                    else{
//                        handleError(response.errorBody(), response.code());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    presenterresponse.doFail(context.getResources().getString(R.string.application_error));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                if (t instanceof ConnectException){
//                    presenterresponse.doConnectionError();
//                }else{
//                    presenterresponse.doFail(context.getString(R.string.failed_connection));
//                }
//                ProgressDialogHolder.with(context).dismissDialog();
//            }
//        });
//    }
//
//    public void changePassword(Map<String, String> data){
//        ProgressDialogHolder.with(context)
//                .showLoadingDialog(R.string.loading);
//
//        ApiClient.getInstance(context).getApi().changePassword(data).enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                ProgressDialogHolder.with(context).dismissDialog();
//                try {
//                    if (response.isSuccessful()){
//                        presenterresponse.doSuccess(response.body(), RES_UPDATE_PASSWORD);
//                    }else if(response.code() == 401) {
//                        presenterresponse.doFail(handleErrorBody(response.errorBody()).message);
//                    }
//                    else{
//                        handleError(response.errorBody(), response.code());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                ProgressDialogHolder.with(context).dismissDialog();
//                AuthPresenter.super.onFailure(t);
//            }
//        });
//    }
//
//    public void resetPassword(Map<String, String> data){
//        ProgressDialogHolder.with(context)
//                .showLoadingDialog(R.string.loading);
//
//        ApiClient.getInstance(context).getApi().resetPassword(data).enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                try {
//                    if (response.isSuccessful()){
//                        presenterresponse.doSuccess(response.body(), RES_RESET_PASSWORD);
//                    }else{
//                        handleError(response.errorBody(), response.code());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    presenterresponse.doFail(context.getResources().getString(R.string.application_error));
//                }
//                ProgressDialogHolder.with(context)
//                        .dismissDialog();
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                ProgressDialogHolder.with(context)
//                        .dismissDialog();
//                AuthPresenter.super.onFailure(t);
//            }
//        });
//    }
}
