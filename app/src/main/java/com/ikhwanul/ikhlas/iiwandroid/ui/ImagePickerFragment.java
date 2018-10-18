package com.ikhwanul.ikhlas.iiwandroid.ui;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.BuildConfig;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagePickerFragment extends BottomSheetDialogFragment {
    private static final int REQUEST_CAMERA = 501;
    private static final int REQUEST_GALERY = 502;

    private static final int OPEN_CAMERA = 101;
    private static final int OPEN_GALERY = 102;

    private int requestCode;
    private boolean enabledCrop = true;
    private int aspecRatioX;
    private int aspecRatioY;
    private boolean fixAspectRatio = false;
    private OnFinishedListener listener;

    private IntentItem selectedIntent;

    @BindView(R.id.recycler_app)
    protected RecyclerView recyclerView;

    public static ImagePickerFragment newInstance(OnFinishedListener listener, int requestCode) {

        Bundle args = new Bundle();
        args.putInt("request_code", requestCode);

        ImagePickerFragment fragment = new ImagePickerFragment();
        fragment.setArguments(args);
        fragment.listener = listener;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestCode = getArguments().getInt("request_code");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.layout_bottom_sheet_image, container, false);
        ButterKnife.bind(this, view);

        ArrayList<IntentItem> listIntent = new ArrayList<>();

        Uri outputFileUri = getCaptureImageOutputUri();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = getActivity().getPackageManager().queryIntentActivities(cameraIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(cameraIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri);
            }

            IntentItem item = new IntentItem();
            item.intent = intent;
            try {
                ApplicationInfo app = getActivity().getPackageManager().getApplicationInfo(res.activityInfo.packageName, 0);
                item.appIcon = getActivity().getPackageManager().getApplicationIcon(app);
                item.appName = (String) getActivity().getPackageManager().getApplicationLabel(app);
                item.type = OPEN_CAMERA;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            listIntent.add(item);
        }

        listIntent.addAll(getGalleryIntents(getActivity().getPackageManager(), Intent.ACTION_PICK));

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        ItemIntentAdapter adapter = new ItemIntentAdapter(getContext(), listIntent);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // permission was granted, yay! Do the
            // contacts-related task you need to do.
            Log.d("PERMITION", "Permition granted");

            switch (requestCode){
                case REQUEST_CAMERA:
                    if (selectedIntent != null) startActivityForResult(selectedIntent.intent, selectedIntent.type);
                    break;
                case REQUEST_GALERY:
                    if (selectedIntent != null) startActivityForResult(selectedIntent.intent, selectedIntent.type);
                    break;
            }
        } else {
            // permission denied, boo! Disable the
            // functionality that depends on this permission.
            Log.d("PERMITION", "Permition denied");
        }
        return;
    }

    protected boolean checkPermition(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{permission},
                    requestCode);
            return false;
        }else{
            return true;
        }
    }

    private void openCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = getActivity().getPackageManager().queryIntentActivities(cameraIntent, 0);
        ResolveInfo res = listCam.get(0);
        startActivityForResult(cameraIntent, OPEN_CAMERA);
//        CropImage.startPickImageActivity(getActivity());
    }

    private void openGalery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, OPEN_GALERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == OPEN_GALERY || requestCode == OPEN_CAMERA){
                if (this.enabledCrop){
                    CropImage.ActivityBuilder builder = (requestCode == OPEN_GALERY)
                            ? CropImage.activity(data.getData()).setGuidelines( CropImageView.Guidelines.ON)
                            : CropImage.activity(CropImage.getPickImageResultUri(getActivity(), data)).setGuidelines(CropImageView.Guidelines.ON);

                    if (fixAspectRatio){
                        builder.setAspectRatio(aspecRatioX, aspecRatioY);
                    }

                    builder.start(getContext(), this);
                }else if(requestCode == OPEN_CAMERA){
                    listener.onFinished(this.requestCode, resultCode, CropImage.getPickImageResultUri(getActivity(), data));
                    dismiss();
                }else{
                    listener.onFinished(this.requestCode, resultCode, data.getData());
                    dismiss();
                }
            }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri resultUri = result.getUri();
                listener.onFinished(this.requestCode, resultCode, resultUri);
                dismiss();
            }
        }
    }

    /**
     * Get URI to image received from capture by camera.
     *
     *     activity/fragment/widget.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getActivity().getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = FileProvider.getUriForFile(getContext(),
                    BuildConfig.APPLICATION_ID + ".provider",
                    new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }

    public List<IntentItem> getGalleryIntents(
            @NonNull PackageManager packageManager, String action) {
        List<IntentItem> listIntentItem = new ArrayList<>();

        Intent galleryIntent =
                action == Intent.ACTION_GET_CONTENT
                        ? new Intent(action)
                        : new Intent(action, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);

            IntentItem item = new IntentItem();
            item.intent = intent;
            try {
                ApplicationInfo app = getActivity().getPackageManager().getApplicationInfo(res.activityInfo.packageName, 0);
                item.appIcon = getActivity().getPackageManager().getApplicationIcon(app);
                item.appName = (String) getActivity().getPackageManager().getApplicationLabel(app);
                item.type = OPEN_GALERY;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            listIntentItem.add(item);
        }

        // remove documents intent
        for (IntentItem intent : listIntentItem) {
            if (intent.intent
                    .getComponent()
                    .getClassName()
                    .equals("com.android.documentsui.DocumentsActivity")) {
                listIntentItem.remove(intent);
                break;
            }
        }


        return listIntentItem;
    }

    public ImagePickerFragment enableCrop(boolean enabled){
        this.enabledCrop = enabled;
        return this;
    }

    public ImagePickerFragment setAspectRatio(int aspectRatioX, int aspectRatioY){
        this.aspecRatioX = aspectRatioX;
        this.aspecRatioY = aspectRatioY;
        this.fixAspectRatio = true;
        return this;
    }

    /**
     * OnFinished Listener
     * */

    public interface OnFinishedListener{
        void onFinished(int requestCode, int resultCode, Uri uri);
    }

    /**
     * Layout Item Adapter Class
     * */
    private class ItemIntentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context context;
        private List<IntentItem> listIntent;

        public ItemIntentAdapter(Context context, List<IntentItem> data){
            this.context = context;
            listIntent = data;
        }

        public void setData(List<IntentItem> data){
            listIntent = data;
            notifyDataSetChanged();
        }

        @Override
        public ImagePickerFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_picker_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(ImagePickerFragment.ViewHolder holder, int position) {
            final IntentItem item = listIntent.get(position);

            holder.name.setText(item.appName);
            holder.image.setImageDrawable(item.appIcon);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.type == OPEN_CAMERA){
                        if (checkPermition(Manifest.permission.CAMERA, REQUEST_CAMERA)){
                            startActivityForResult(item.intent, item.type);
                        }else{
                            selectedIntent = item;
                        }
                    }else{
                        if (checkPermition( Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_CAMERA)){
                            startActivityForResult(item.intent, item.type);
                        }else{
                            selectedIntent = item;
                        }
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return listIntent.size();
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView image;

        public ViewHolder(View view){
            super(view);

            name = (TextView)view.findViewById(R.id.app_name);
            image = (ImageView) view.findViewById(R.id.app_image);
        }
    }

    private class IntentItem{
        public String appName;
        public Drawable appIcon;
        public int type;
        public Intent intent;
    }
}
