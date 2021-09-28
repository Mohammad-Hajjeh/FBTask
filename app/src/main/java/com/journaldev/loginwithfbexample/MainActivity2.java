package com.journaldev.loginwithfbexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    CallbackManager callbackManager;
    MyAdapter myAdapter;
    PostAdapter postAdapter;
    RecyclerView.LayoutManager layoutManager;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_manage);
        View headerView = navigationView.getHeaderView(0);
        ImageView imageView = headerView.findViewById(R.id.imageView);
        TextView name = headerView.findViewById(R.id.name);
        TextView email = headerView.findViewById(R.id.email);
        total = headerView.findViewById(R.id.total);
        Picasso.with(MainActivity2.this).load(intent.getStringExtra("ImageUrl")).into(imageView);
        name.setText(intent.getStringExtra("FirstName") + " " + intent.getStringExtra("LastName"));
        email.setText(intent.getStringExtra("Email"));
        callbackManager = CallbackManager.Factory.create();
        getUserProfile(AccessToken.getCurrentAccessToken());


    }

    private void getUserProfile(AccessToken currentAccessToken) {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        Log.d("TAG", "post" + response.getJSONObject());
                        try {

                            String jsonObject = response.getJSONObject().getString("summary");
                            jsonObject = jsonObject.replace('{', ' ');
                            jsonObject = jsonObject.replace('"', ' ');
                            jsonObject = jsonObject.replace('}', ' ');
                            jsonObject = jsonObject.replaceAll("total_count", "total_friends");
                            jsonObject = jsonObject.trim();
                            total.setText(jsonObject);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//
//        Fragment fragment = null;
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
        if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_like) {
            Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_post) {
            Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
            startActivity(intent);
        }
//        } else if (id == R.id.nav_gallery) {
//            fragment = new GalleryFragment();
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
