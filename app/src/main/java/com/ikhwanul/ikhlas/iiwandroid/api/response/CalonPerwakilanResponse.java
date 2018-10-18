package com.ikhwanul.ikhlas.iiwandroid.api.response;

public class CalonPerwakilanResponse extends ApiResponse{

   public DataCalon data;

   public class DataCalon {
       public String username;
       public String today;
       public String kontrak;
       public String jabatan;
       public String id_perekomendasi;
       public String perekomendasi;
       public int perigkat;

   }
}
