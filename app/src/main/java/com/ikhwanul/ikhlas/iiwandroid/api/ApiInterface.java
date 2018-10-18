package com.ikhwanul.ikhlas.iiwandroid.api;

import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.AuthResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.BinaanKDMResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.BinaanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.CalonPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.DollarResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.JamaahResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KDMBelanjaResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KabupatenResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiKDMResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiPSCResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiPelunasanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KwitansiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PPCResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataHistoryStokResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataJualKwitansiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCKomisiRekomendasiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCKwitansiPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PembelianResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ProvinsiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.RewardResponse;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCKwitansiPerwakilan;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {

//    @GET("v1/depotselected/{id_depot}")
//    Call<DepotsResponse> getDepotSelected(@Path("id_depot") int id_depot);
//
//    @GET("v1/getprice/{id_depot}")
//    Call<ItemsResponse> getPrice(@Path("id_depot") int id_depot);
//
//    @GET("v1/achievement/{id_depot}")
//    Call<AchievementResponse> getAchievement(@Path("id_depot") int id_depot);
//
//    @GET("v1/historyorders/{email}")
//    Call<HistoryOrdersResponse> getHistoryOrders(@Path("email") String email);
//
//    @GET("v1/historyorders/{id_depot}/{date}/{month}")
//    Call<HistoryOrdersResponse> getHistoryOrders(@Path("id_depot") int id_depot, @Path("date") String date, @Path("month") int month);
//
//    @GET("v1/historyorder/{status}/{id_depot}")
//    Call<HistoryOrdersResponse> getHistoryOrder(@Path("status") int status, @Path("id_depot") int id_depot);
//
//    @GET("v1/dashboard/{id_depot}")
//    Call<HistoryOrdersResponse> getDashboard(@Path("id_depot") int id_depot);
//
//    @GET("v1/detaildepot/{email}")
//    Call<DepotsResponse> getDetailDepot(@Path("email") String email);
//
//    @GET("v1/reward/{id_depot}")
//    Call<RewardsResponse> getReward(@Path("id_depot") int id_depot);
//
//    @GET("v1/removereward/{id_reward}")
//    Call<ApiResponse> removeReward(@Path("id_reward") int id_reward);
//
//    @POST("v1/postreward")
//    Call<ApiResponse> postReward(@Body Map<String, String> data);
//
    @POST("Api.php?apicall=login")
    Call<AuthResponse> postLogin(@Body Map<String, String> data);

    @POST("Api.php?apicall=binaan")
    Call<BinaanResponse> getDataBinaan(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=jamaah")
    Call<JamaahResponse> getJamaah(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=historyjamaah")
    Call<JamaahResponse> getHistoryJamaah(@Body Map<String, Integer> data);


    @POST("Api.php?apicall=komisiPSC")
    Call<KomisiPSCResponse> getKomisiPSC(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pembelian")
    Call<PembelianResponse> getDataPembelian(@Body Map<String, Integer> data);


    @GET("Api.php?apicall=allprovinsi")
    Call<ProvinsiResponse> getProvinsi();

    @POST("Api.php?apicall=kabupaten")
    Call<KabupatenResponse> getKabupaten(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=komisi")
    Call<KomisiPerwakilanResponse> getKomisiPerwakilan(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=komisipelunasan")
    Call<KomisiPelunasanResponse> getKomisiPelunasan(@Body Map<String, Integer> data);


    @GET("Api.php?apicall=kursdollar")
    Call<DollarResponse> getKursDollar();

    @POST("Api.php?apicall=ambilinfoboxperwakilan")
    Call<KDMBelanjaResponse> getKDMBelanja(@Body Map<String, String> data);

    @POST("Api.php?apicall=ambilinfoboxpsc")
    Call<KDMBelanjaResponse> getInfoPSC(@Body Map<String, String> data);

    //KWITANSI
    @POST("Api.php?apicall=belipsc")
    Call<KwitansiResponse> getKwitansi(@Body Map<String, Integer> data);

    @GET("Api.php?apicall=islogged")
    Call<AuthResponse> isLogged();

    @POST("Api.php?apicall=binaankdm")
    Call<BinaanKDMResponse> getBinaanKDM(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=binaanmm")
    Call<BinaanKDMResponse> getBinaanMM(@Body Map<String, Integer> data);


    @POST("Api.php?apicall=kwitansiforjamaah")
    Call<ApiResponse> getKwitansiForJamaah(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=kwitansiforjamaahfree")
    Call<ApiResponse> getKwitansiForJamaahFree(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=kwitansiforjamaahfreereguler")
    Call<ApiResponse> getKwitansiForJamaahFreeReguler(@Body Map<String, Integer> data);

    @Multipart
    @POST("Api.php?apicall=tambahjamaah")
    Call<ApiResponse> addNewJamaah(@PartMap Map<String, RequestBody> data);

    @Multipart
    @POST("Api.php?apicall=updatejamaah")
    Call<ApiResponse> updateJamaah(@PartMap Map<String, RequestBody> data);

    @POST("Api.php?apicall=reward")
    Call<RewardResponse> getReward(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=komisikdm")
    Call<KomisiKDMResponse> getKomisiKDM(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=komisimm")
    Call<KomisiKDMResponse> getKomisiMM(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=ppc")
    Call<PPCResponse> getPPC(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pscdataperwakilan")
    Call<PSCDataPerwakilanResponse> getPSCPerwakilan(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pscdataasper")
    Call<PSCDataPerwakilanResponse> getPSCAsper(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pscdatakwitansiperwakilan")
    Call<PSCKwitansiPerwakilanResponse> getPSCKwitansiPerwakilan(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pscdatakwitansijamaah")
    Call<PSCKwitansiPerwakilanResponse> getPSCKwitansiJamaah(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pscdatakwitansifree")
    Call<PSCKwitansiPerwakilanResponse> getPSCKwitansiFree(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pscdatahistorystok")
    Call<PSCDataHistoryStokResponse> getHistoryBuyStok(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pscdatapenjualankwitansi")
    Call<PSCDataJualKwitansiResponse> getPenjualanKwitansi(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pscdatakomisirekomendasi")
    Call<PSCKomisiRekomendasiResponse> getKomisiRekomendasi(@Body Map<String, Integer> data);

    @POST("Api.php?apicall=pscdatakwitansifree-datapemberianfree")
    Call<PSCDataJualKwitansiResponse> getPemberianKwitansi(@Body Map<String, Integer> data);

    @GET("Api.php?apicall=generateperwakilan")
    Call<CalonPerwakilanResponse> generatePerwakilan();

    @Multipart
    @POST("Api.php?apicall=tambahperwakilanproses")
    Call<ApiResponse> addNewPerwakilan(@PartMap Map<String, RequestBody> dataAdd);


//
//    @POST("v1/decorder")
//    Call<ApiResponse> decOrder(@Body Map<String, Integer> data);
//
//    @POST("v1/finishedorder")
//    Call<ApiResponse> finishedOrder(@Body Map<String, Integer> data);
//
//    @GET("v1/auth/me")
//    Call<MeResponse> isMe();
//
//    @POST("v1/updateprofile")
//    Call<ApiResponse> postEditProfile(@Body Map<String, String> data);
//
//    @POST("v1/updateprice")
//    Call<ApiResponse> postUpdatePrice(@Body Map<String, String> data);
//
//    @POST("v1/auth/register")
//    Call<AuthResponse> postRegister(@Body Map<String, String> data);
//
//    @POST("v1/createmessage")
//    Call<ApiResponse> postMessage(@Body Map<String, String> data);
}
