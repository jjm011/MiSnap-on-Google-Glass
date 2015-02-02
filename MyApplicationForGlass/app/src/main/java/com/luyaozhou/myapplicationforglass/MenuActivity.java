package com.luyaozhou.myapplicationforglass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.glass.content.Intents;


public class MenuActivity extends Activity {
    private static final int PHOTO_REQUEST_CODE = 1;
    private boolean mAttachedToWindow;
    private boolean mOptionMenuOpen;
    private boolean mTakingPhoto;
//    android.os.Handler mHandler;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void openOptionsMenu() {
        if(!mOptionMenuOpen && mAttachedToWindow){
            mOptionMenuOpen = true;
            super.openOptionsMenu();

        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAttachedToWindow = true;
        openOptionsMenu();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttachedToWindow = false;

    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        mOptionMenuOpen = false;
        if(!mTakingPhoto) {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean  handled = true;
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
        case R.id.action_start_new_game:
        handleStartNewGame();
        break;
        case R.id.action_take_a_picture:
        handleTakeAPicture();
        break;
        case R.id.action_view_categories:
        handleViewCategories();
        break;
        case R.id.action_stop:
        handleStop();
        break;
        default:
        handled = super.onOptionsItemSelected(item);

    }
    return handled;

    }

    private void handleStartNewGame(){
        Toast.makeText(this, "Start Selected", Toast.LENGTH_LONG).show();
    }

    private void handleTakeAPicture(){
        //Toast.makeText(this, "Take Picture", Toast.LENGTH_LONG).show();

        if(mHandler == null)
            return;

        mTakingPhoto = true;

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);

            }
        });


//        Intent takePic = new Intent(this,CameraActivity.class);

//        startActivity(takePic);
    }

    private void handleViewCategories(){
        Toast.makeText(this, "Categories Selected", Toast.LENGTH_LONG).show();

    }

    private void handleStop(){
        Toast.makeText(this, "Stop Selected", Toast.LENGTH_LONG).show();
        //mTakingPhoto = true;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                stopService(new Intent(MenuActivity.this, LiveCardService.class));
            }
        });
        stopService(new Intent(this, LiveCardService.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PHOTO_REQUEST_CODE && resultCode == RESULT_OK){
            String photoFileName = data.getStringExtra(Intents.EXTRA_THUMBNAIL_FILE_PATH);
            Toast.makeText(this, "Photo:" + photoFileName, Toast.LENGTH_LONG).show();
        }
        finish();
    }

}
