package com.journaldev.loginwithfbexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    CallbackManager callbackManager;
    LikeAdapter likeAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        callbackManager = CallbackManager.Factory.create();
        getUserProfile(AccessToken.getCurrentAccessToken());
    }
    private void getUserProfile(AccessToken currentAccessToken) {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/likes",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        Log.d("TAG", "like" + response.getJSONObject());
                        try {
                            ArrayList<FBLike> fbLikes = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(response.getRawResponse());
                            JSONArray data = jsonObject.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                String created = data.getJSONObject(i).getString("created_time");
                                String name = data.getJSONObject(i).getString("name");
                                String id = data.getJSONObject(i).getString("id");
                                fbLikes.add(new FBLike(name, id, created));
                            }
                            Toast.makeText(getApplicationContext(), String.valueOf(fbLikes.size()), Toast.LENGTH_SHORT).show();
                            Log.d("Size", String.valueOf(fbLikes.size()));

                            recyclerView = findViewById(R.id.rv_likes);
                            layoutManager = new LinearLayoutManager(MainActivity4.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setHasFixedSize(true);
                            likeAdapter = new LikeAdapter(fbLikes);
                            recyclerView.setAdapter(likeAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }
    }