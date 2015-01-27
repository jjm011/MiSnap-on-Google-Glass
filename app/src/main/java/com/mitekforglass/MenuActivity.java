package com.mitekforglass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MenuActivity extends Activity {
    private boolean mAttachedToWindow;
    private boolean mOptionMenuOpen;
    android.os.Handler mHandler;
    @Override
    public void openOptionsMenu() {
        if (!mOptionMenuOpen && mAttachedToWindow) {
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
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean handled = true;
        int id = item.getItemId();

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


    private void handleStartNewGame() {
        Toast.makeText(this, "Start selected", Toast.LENGTH_LONG).show();
    }

    private void handleTakeAPicture() {
        Toast.makeText(this, "Take Picture", Toast.LENGTH_LONG).show();
    }
    private void handleViewCategories() {
        Toast.makeText(this, "Categories Selected", Toast.LENGTH_LONG).show();
    }
    private void handleStop() {
        Toast.makeText(this, "Stop Selected", Toast.LENGTH_LONG).show();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                stopService(new Intent(MenuActivity.this, LiveCardService.class));
            }
        });
        stopService(new Intent(this, LiveCardService.class));

    }
}