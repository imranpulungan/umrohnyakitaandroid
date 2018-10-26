package com.ikhwanul.ikhlas.iiwandroid.presenters;

import android.content.Context;

import com.ikhwanul.ikhlas.iiwandroid.MainActivity;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.ApiClient;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.AuthResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.BinaanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.CalonPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.JamaahResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KabupatenResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiPSCResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ProvinsiResponse;
import com.ikhwanul.ikhlas.iiwandroid.utils.Session;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class DataFormPresenter extends Presenter {

    protected static Context context;
    protected iPresenterResponse presenterresponse;

    public DataFormPresenter(Context context, iPresenterResponse iPresenterResponse){
        super(context, iPresenterResponse);
        this.context = context;
        this.presenterresponse = iPresenterResponse;
    }

    public void getProvinsi(){
        ApiClient.getInstance(context).getApi().getProvinsi().enqueue(new Callback<ProvinsiResponse>() {
            @Override
            public void onResponse(Call<ProvinsiResponse> call, Response<ProvinsiResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_PROVINSI);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void getKwitansiForJamaah(String id_perwakilan){
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", Integer.valueOf(id_perwakilan));
        ApiClient.getInstance(context).getApi().getKwitansiForJamaah(data).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KWITANSI_JAMAAH);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void getKwitansiForJamaahFree(String id_perwakilan){
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", Integer.valueOf(id_perwakilan));
        ApiClient.getInstance(context).getApi().getKwitansiForJamaahFree(data).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KWITANSI_JAMAAH);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void getKwitansiForJamaahFreeReguler(String id_perwakilan){
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", Integer.valueOf(id_perwakilan));
        ApiClient.getInstance(context).getApi().getKwitansiForJamaahFreeReguler(data).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KWITANSI_JAMAAH);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void getKabupaten(Integer id_provinsi){
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_provinsi", id_provinsi);
        ApiClient.getInstance(context).getApi().getKabupaten(data).enqueue(new Callback<KabupatenResponse>() {
            @Override
            public void onResponse(Call<KabupatenResponse> call, Response<KabupatenResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KABUPATEN);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<KabupatenResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void addNewJamaah(Map<String, RequestBody> data) {
        ApiClient.getInstance(context).getApi().addNewJamaah(data).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), ADD_NEW_JAMAAH);
                    }else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void updateJamaah(Map<String, RequestBody> data) {
        ApiClient.getInstance(context).getApi().updateJamaah(data).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), UPDATE_NEW_JAMAAH);
                    }else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void shareKwitansi(Map<String, String> data) {
        ApiClient.getInstance(context).getApi().shareKwitansi(data).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), SHARE_KWITANSI);
                    }else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void generateCalonPerwakilan(int id_perwakilan) {
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().generatePerwakilan(data).enqueue(new Callback<CalonPerwakilanResponse>() {
            @Override
            public void onResponse(Call<CalonPerwakilanResponse> call, Response<CalonPerwakilanResponse> response) {
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), RES_GET_GENERATE_PERWAKILAN);
                    }else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CalonPerwakilanResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void addNewPerwakilan(Map<String, String> dataAdd) {

        ApiClient.getInstance(context).getApi().addNewPerwakilan(dataAdd).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), ADD_NEW_PERWAKILAN);
                    }else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void getStokkwitansi(int id) {
        Map<String, Integer> data = new HashMap<>();
        data.put("id_perwakilan", id);
        ApiClient.getInstance(context).getApi().getStokkwitansi(data).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), GET_STOK_KWITANSI);
                    }else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }



    public void sellKwitansi(Map<String, String> data) {
        ApiClient.getInstance(context).getApi().sellKwitansi(data).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), SELL_KWITANSI);
                    }else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void updateProfilePerwakilan(Map<String, String> data) {
        ApiClient.getInstance(context).getApi().updateProfilePerwakilan(data).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), UPDATE_PROFILE_PERWAKILAN);
                    }else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }

    public void getPerwakilanByCode(Map<String, String> data) {
        ApiClient.getInstance(context).getApi().getPerwakilanByCode(data).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                try {
                    if (response.isSuccessful()){
                        presenterresponse.doSuccess(response.body(), GET_DATA_PERWAKILAN_BY_CODE);
                    }else{
                        handleError(response.errorBody(), response.code());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                DataFormPresenter.this.onFailure(t);
            }
        });
    }
}
