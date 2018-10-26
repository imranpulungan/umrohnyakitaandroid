package com.ikhwanul.ikhlas.iiwandroid.presenters;

import android.app.ProgressDialog;
import android.content.Context;

import com.ikhwanul.ikhlas.iiwandroid.MainActivity;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.ApiClient;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.AuthResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.BannerResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.BinaanKDMResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.BinaanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.DPResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.DetailJamaahResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.DollarResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.GroupResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.JamaahResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KDMBelanjaResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiKDMResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiPSCResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiPelunasanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KwitansiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.LaporanPembelianResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PPCResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataHistoryStokResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataJualKwitansiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataPerwakilanDetailResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCKomisiRekomendasiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCKwitansiPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PembelianResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ProductResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.RewardResponse;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataJualKwitansi;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataPerwakilanDetail;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCKwitansiPerwakilan;
import com.ikhwanul.ikhlas.iiwandroid.utils.Session;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class Presenter extends MainActivity {
    public static final String RES_GET_DATA_BINAAN = "get_data_binaan";
    public static final String RES_GET_DATA_BINAAN_DETAIL = "get_data_binaan_detail";
    public static final String RES_GET_DATA_BINAAN_KDM = "get_data_binaan_kdm";
    public static final String RES_GET_DATA_BINAAN_MM = "get_data_binaan_mm";
    public static final String RES_GET_DATA_JAMAAH = "get_data_jamaah";
    public static final String RES_GET_DATA_KOMISI_PSC = "get_data_komisi_psc";
    public static final String RES_GET_DATA_PROVINSI = "get_data_provinsi";
    public static final String RES_GET_DATA_KABUPATEN = "get_data_kabupaten";
    public static final String RES_GET_DATA_DOLLAR = "get_data_dollar";
    public static final String RES_GET_DATA_KDM_BELANJA = "get_data_kdm_belanja";
    public static final String RES_GET_DATA_KOMISI = "get_data_komisi";
    public static final String RES_GET_DATA_KWITANSI = "get_data_kwitansi";
    public static final String RES_IS_LOGGED = "is_user_logged";
    public static final String ADD_NEW_JAMAAH = "add_new_jamaah";
    public static final String ADD_NEW_PERWAKILAN = "add_new_perwakilan";
    public static final String RES_GET_DATA_KWITANSI_JAMAAH = "get_data_kwitansi_jamaah";
    public static final String UPDATE_NEW_JAMAAH = "update_data_jamaah";
    public static final String RES_GET_DATA_PEMBELIAN = "get_data_pembelian";
    public static final String RES_GET_DATA_REWARD = "get_data_reward";
    public static final String RES_GET_DATA_KOMISI_KDM = "get_data_komisi_kdm";
    public static final String RES_GET_DATA_PPC = "get_data_ppc";
    public static final String RES_GET_GENERATE_PERWAKILAN = "res_get_generate_perwakilan";
    public static final String GET_STOK_KWITANSI = "res_get_stok_kwitansi";
    public static final String SHARE_KWITANSI = "res_post_share_kwitansi";
    public static final String SELL_KWITANSI = "res_sell_kwitansi";
    public static final String UPDATE_PROFILE_PERWAKILAN = "res_update_profile_perwakilan";
    public static final String RES_GET_ALL_PRODUCT = "get_all_product";
    public static final String RES_GET_ALL_DP = "get_all_dp";
    public static final String RES_GET_ALL_GROUP = "get_all_group";
    public static final String RES_GET_DETAIL_JAMAAH = "get_detail_jamaah";
    public static final String RES_GET_LAPORAN_PEMBELIAN = "get_laporan_pembelian";
    public static final String RES_GET_BANNER = "get_slide_banner";
    public static final String GET_DATA_PERWAKILAN_BY_CODE = "get_data_perwakilan";


    protected static Context context;
    protected iPresenterResponse presenterresponse;

    public Presenter(Context context, iPresenterResponse iPresenterResponse){
        this.context = context;
        this.presenterresponse = iPresenterResponse;
    }

    public ApiResponse handleErrorBody(ResponseBody errorBody) throws IOException{
        Converter<ResponseBody, ApiResponse> converter = ApiClient.getInstance(context).getClient().responseBodyConverter(ApiResponse.class, new Annotation[0]);
        ApiResponse responBody = converter.convert(errorBody);
        return responBody;
    }

    protected void handleError(ResponseBody errorBody, int code) throws IOException{
        ApiResponse responBody = handleErrorBody(errorBody);
        if (code == 422)
            presenterresponse.doValidationError(responBody.error);
        else if (code == 401)
            Session.with(context).clearLoginSession();
        else if (code >= 400 || code < 500)
            presenterresponse.doFail(responBody.message);
        else if (code >= 500)
            presenterresponse.doFail(context.getResources().getString(R.string.server_error));
        else
            presenterresponse.doConnectionError();
    }

    public void onFailure(Throwable t){
        if (t instanceof ConnectException)
            presenterresponse.doConnectionError();
        else
            presenterresponse.doFail(context.getResources().getString(R.string.failed_connection));
    }

    public void getBinaanPerwakilan(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getDataBinaan(data).enqueue(new Callback<BinaanResponse>() {
            @Override
            public void onResponse(Call<BinaanResponse> call, Response<BinaanResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<BinaanResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getDetailPerwakilan(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id", id_perwakilan);
        ApiClient.getInstance(context).getApi().getDetailPerwakilan(data).enqueue(new Callback<PSCDataPerwakilanDetailResponse>() {
            @Override
            public void onResponse(Call<PSCDataPerwakilanDetailResponse> call, Response<PSCDataPerwakilanDetailResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_DETAIL);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCDataPerwakilanDetailResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getDataPembelian(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getDataPembelian(data).enqueue(new Callback<PembelianResponse>() {
            @Override
            public void onResponse(Call<PembelianResponse> call, Response<PembelianResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_PEMBELIAN);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PembelianResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getJamaah(String id_perwakilan, final ProgressDialog progressDialog, boolean isFirst, int month){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", Integer.valueOf(id_perwakilan));
        data.put("month", Integer.valueOf(month));
        ApiClient.getInstance(context).getApi().getJamaah(data).enqueue(new Callback<JamaahResponse>() {
            @Override
            public void onResponse(Call<JamaahResponse> call, Response<JamaahResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_JAMAAH);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<JamaahResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getHistoryJamaah(String id_perwakilan, final ProgressDialog progressDialog, boolean isFirst) {
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", Integer.valueOf(id_perwakilan));
        ApiClient.getInstance(context).getApi().getHistoryJamaah(data).enqueue(new Callback<JamaahResponse>() {
            @Override
            public void onResponse(Call<JamaahResponse> call, Response<JamaahResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_JAMAAH);
                    else handleError(response.errorBody(), response.code());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<JamaahResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getKomisiPSC(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getKomisiPSC(data).enqueue(new Callback<KomisiPSCResponse>() {
            @Override
            public void onResponse(Call<KomisiPSCResponse> call, Response<KomisiPSCResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KOMISI_PSC);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<KomisiPSCResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }


    public void getKursDollar(){
        ApiClient.getInstance(context).getApi().getKursDollar().enqueue(new Callback<DollarResponse>() {
            @Override
            public void onResponse(Call<DollarResponse> call, Response<DollarResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_DOLLAR);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<DollarResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }

    public void getKomisiPerwakilan(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getKomisiPerwakilan(data).enqueue(new Callback<KomisiPerwakilanResponse>() {
            @Override
            public void onResponse(Call<KomisiPerwakilanResponse> call, Response<KomisiPerwakilanResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KOMISI);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<KomisiPerwakilanResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getKomisiPelunasan(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getKomisiPelunasan(data).enqueue(new Callback<KomisiPelunasanResponse>() {
            @Override
            public void onResponse(Call<KomisiPelunasanResponse> call, Response<KomisiPelunasanResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KOMISI);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<KomisiPelunasanResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getKomisiKDM(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getKomisiKDM(data).enqueue(new Callback<KomisiKDMResponse>() {
            @Override
            public void onResponse(Call<KomisiKDMResponse> call, Response<KomisiKDMResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KOMISI_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<KomisiKDMResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getKomisiMM(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getKomisiMM(data).enqueue(new Callback<KomisiKDMResponse>() {
            @Override
            public void onResponse(Call<KomisiKDMResponse> call, Response<KomisiKDMResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KOMISI_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<KomisiKDMResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getKdmBelanja(String id_perwakilan, String peringkat){
        Map<String, String> data = new HashMap<String, String>();
        data.put("id_perwakilan", id_perwakilan);
        data.put("peringkat", peringkat);
        ApiClient.getInstance(context).getApi().getKDMBelanja(data).enqueue(new Callback<KDMBelanjaResponse>() {
            @Override
            public void onResponse(Call<KDMBelanjaResponse> call, Response<KDMBelanjaResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KDM_BELANJA);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<KDMBelanjaResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }

    public void getInfoPSC(String id_perwakilan, String peringkat){
        Map<String, String> data = new HashMap<String, String>();
        data.put("id_perwakilan", id_perwakilan);
        data.put("peringkat", peringkat);
        ApiClient.getInstance(context).getApi().getInfoPSC(data).enqueue(new Callback<KDMBelanjaResponse>() {
            @Override
            public void onResponse(Call<KDMBelanjaResponse> call, Response<KDMBelanjaResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KDM_BELANJA);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<KDMBelanjaResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }

    public void getKwitansi(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getKwitansi(data).enqueue(new Callback<KwitansiResponse>() {
            @Override
            public void onResponse(Call<KwitansiResponse> call, Response<KwitansiResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_KWITANSI);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<KwitansiResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void isLogged(){
        ApiClient.getInstance(context).getApi().isLogged().enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_IS_LOGGED);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }

    public void getBinaanKDM(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getBinaanKDM(data).enqueue(new Callback<BinaanKDMResponse>() {
            @Override
            public void onResponse(Call<BinaanKDMResponse> call, Response<BinaanKDMResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<BinaanKDMResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getBinaanMM(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getBinaanMM(data).enqueue(new Callback<BinaanKDMResponse>() {
            @Override
            public void onResponse(Call<BinaanKDMResponse> call, Response<BinaanKDMResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<BinaanKDMResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getReward(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getReward(data).enqueue(new Callback<RewardResponse>() {
            @Override
            public void onResponse(Call<RewardResponse> call, Response<RewardResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_REWARD);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<RewardResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getDataPPC(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getPPC(data).enqueue(new Callback<PPCResponse>() {
            @Override
            public void onResponse(Call<PPCResponse> call, Response<PPCResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_PPC);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PPCResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getPSCPerwakilan(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getPSCPerwakilan(data).enqueue(new Callback<PSCDataPerwakilanResponse>() {
            @Override
            public void onResponse(Call<PSCDataPerwakilanResponse> call, Response<PSCDataPerwakilanResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCDataPerwakilanResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getPSCKwitansiPerwakilan(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getPSCKwitansiPerwakilan(data).enqueue(new Callback<PSCKwitansiPerwakilanResponse>() {
            @Override
            public void onResponse(Call<PSCKwitansiPerwakilanResponse> call, Response<PSCKwitansiPerwakilanResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCKwitansiPerwakilanResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getPSCKwitansiJamaah(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getPSCKwitansiJamaah(data).enqueue(new Callback<PSCKwitansiPerwakilanResponse>() {
            @Override
            public void onResponse(Call<PSCKwitansiPerwakilanResponse> call, Response<PSCKwitansiPerwakilanResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCKwitansiPerwakilanResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getPSCKwitansiFree(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getPSCKwitansiFree(data).enqueue(new Callback<PSCKwitansiPerwakilanResponse>() {
            @Override
            public void onResponse(Call<PSCKwitansiPerwakilanResponse> call, Response<PSCKwitansiPerwakilanResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCKwitansiPerwakilanResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getHistoryBuyStok(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getHistoryBuyStok(data).enqueue(new Callback<PSCDataHistoryStokResponse>() {
            @Override
            public void onResponse(Call<PSCDataHistoryStokResponse> call, Response<PSCDataHistoryStokResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCDataHistoryStokResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getPenjualanKwitansi(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst, int month){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        data.put("month", month);
        ApiClient.getInstance(context).getApi().getPenjualanKwitansi(data).enqueue(new Callback<PSCDataJualKwitansiResponse>() {
            @Override
            public void onResponse(Call<PSCDataJualKwitansiResponse> call, Response<PSCDataJualKwitansiResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCDataJualKwitansiResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getPemberianKwitansi(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getPenjualanKwitansi(data).enqueue(new Callback<PSCDataJualKwitansiResponse>() {
            @Override
            public void onResponse(Call<PSCDataJualKwitansiResponse> call, Response<PSCDataJualKwitansiResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCDataJualKwitansiResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getKomisiRekomendasi(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getKomisiRekomendasi(data).enqueue(new Callback<PSCKomisiRekomendasiResponse>() {
            @Override
            public void onResponse(Call<PSCKomisiRekomendasiResponse> call, Response<PSCKomisiRekomendasiResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCKomisiRekomendasiResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }


    public void getPemberianKwitansi_walk(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getPemberianKwitansi(data).enqueue(new Callback<PSCDataJualKwitansiResponse>() {
            @Override
            public void onResponse(Call<PSCDataJualKwitansiResponse> call, Response<PSCDataJualKwitansiResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCDataJualKwitansiResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getPSCAsper(int id_perwakilan, final ProgressDialog progressDialog, boolean isFirst){
        if (isFirst)
            progressDialog.show();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_perwakilan", id_perwakilan);
        ApiClient.getInstance(context).getApi().getPSCAsper(data).enqueue(new Callback<PSCDataPerwakilanResponse>() {
            @Override
            public void onResponse(Call<PSCDataPerwakilanResponse> call, Response<PSCDataPerwakilanResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DATA_BINAAN_KDM);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<PSCDataPerwakilanResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
                progressDialog.dismiss();
            }
        });
    }

    public void getAllProduct() {
        ApiClient.getInstance(context).getApi().getAllProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_ALL_PRODUCT);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }

    public void getAllDP() {
        ApiClient.getInstance(context).getApi().getAllDp().enqueue(new Callback<DPResponse>() {
            @Override
            public void onResponse(Call<DPResponse> call, Response<DPResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_ALL_DP);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<DPResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }

    public void getAllGroup() {
        ApiClient.getInstance(context).getApi().getAllGroup().enqueue(new Callback<GroupResponse>() {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_ALL_GROUP);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }

    public void getDetailJamaah(int id_pendaftaraan) {
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("id_pendaftaran", id_pendaftaraan);
        ApiClient.getInstance(context).getApi().getDetailJamaah(data).enqueue(new Callback<DetailJamaahResponse>() {
            @Override
            public void onResponse(Call<DetailJamaahResponse> call, Response<DetailJamaahResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_DETAIL_JAMAAH);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<DetailJamaahResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }

    public void getLaporanPembelian(String id_pendaftaraan, String mulai, String akhir) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("id_pendaftaran", id_pendaftaraan);
        ApiClient.getInstance(context).getApi().getLaporanPembelian(data).enqueue(new Callback<LaporanPembelianResponse>() {
            @Override
            public void onResponse(Call<LaporanPembelianResponse> call, Response<LaporanPembelianResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_LAPORAN_PEMBELIAN);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<LaporanPembelianResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }

    public void getSlide() {
        ApiClient.getInstance(context).getApi().getSlide().enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                try {
                    if (response.isSuccessful())
                        presenterresponse.doSuccess(response.body(), RES_GET_BANNER);
                    else handleError(response.errorBody(), response.code());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
                Presenter.this.onFailure(t);
            }
        });
    }
}
