package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.activities.DetailJamaahActivity;
import com.ikhwanul.ikhlas.iiwandroid.entities.Jamaah;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.DOWNLOAD_SERVICE;


public class JamaahAdapter extends
        RecyclerView.Adapter<JamaahAdapter.MyViewHolder> {

    private List<Jamaah> jamaahList;
    private ArrayList<Jamaah> arraylist;

    // Progress Dialog
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;

    // File url to download
    private static String file_url = "http://www.qwikisoft.com/demo/ashade/20001.kml";

    Context context;

    ProgressDialog mProgressDialog;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNoKwitansi;
        public TextView tvNameJamaah;
        public TextView tvDateCreated;
        public TextView textPhoneItem;
        public TextView tvDateGo;
        public TextView tvHarga;
        private ImageButton imgbtnCall;
        private ImageButton imgbtnDownload;
        public MyViewHolder(View view) {
            super(view);
            tvHarga = (TextView) view.findViewById(R.id.tv_harga);
            tvDateGo = (TextView) view.findViewById(R.id.tv_date_go);
            tvNameJamaah = (TextView) view.findViewById(R.id.tv_name_jamaah);
            imgbtnCall = (ImageButton) view.findViewById(R.id.imgbtn_call);
            imgbtnDownload = (ImageButton) view.findViewById(R.id.imgbtn_download);
            tvNoKwitansi = (TextView) view.findViewById(R.id.tv_no_kwitansi);
            tvDateCreated = (TextView) view.findViewById(R.id.tv_date_created);
            textPhoneItem = (TextView) view.findViewById(R.id.tv_phone_jamaah);
        }
    }

    public JamaahAdapter(Context context, List<Jamaah> jamaahList) {
        this.context = context;
        this.jamaahList = jamaahList;
        this.arraylist = new ArrayList<Jamaah>();
        this.arraylist.addAll(jamaahList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Jamaah dataJamaah = jamaahList.get(position);
        holder.tvHarga.setText("$"+dataJamaah.getHarga());
        holder.tvNoKwitansi.setText(dataJamaah.getNo_kwitansi());
        holder.tvDateCreated.setText(dataJamaah.getTgl_dibuat());
        holder.textPhoneItem.setText(dataJamaah.getNo_hp());
        holder.tvNameJamaah.setText(dataJamaah.getNama_jamaah());
        holder.tvDateGo.setText(dataJamaah.getTgl_berangkat());


        holder.imgbtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(dataJamaah.getNo_hp());
                Toast.makeText(context, dataJamaah.getNo_hp(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgbtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "https://www.ikhwanulikhlaswisata.com/perwakilan/pdf/pdf.php?page=jamaah&id=" + dataJamaah.getId_pendaftaraan().toString(), Toast.LENGTH_LONG).show();
// declare the dialog as a member field of your activity
// instantiate it within the onCreate method
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setMessage("A message");
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(true);

// execute this when the downloader must be fired
                final DownloadTask downloadTask = new DownloadTask(context);
                downloadTask.execute("https://www.ikhwanulikhlaswisata.com/perwakilan/pdf/pdf.php?page=jamaah&id=" + dataJamaah.getId_pendaftaraan().toString());

                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        downloadTask.cancel(true);
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailJamaahActivity.class);
                intent.putExtra("data", dataJamaah);
                context.startActivity(intent);
            }
        });
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        jamaahList.clear();
        if (charText.length() == 0) {
            jamaahList.addAll(arraylist);
        } else {
            for (Jamaah wp : arraylist) {
                if (wp.getNama_jamaah().toLowerCase(Locale.getDefault()).contains(charText)) {
                    jamaahList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void makeCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: " + phoneNumber.toString()));
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return jamaahList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_jamaah, parent, false);
        return new MyViewHolder(v);
    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream("/sdcard/file_name.extension");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null)
                Toast.makeText(context, "Download error: " + result, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show();
        }
    }
}