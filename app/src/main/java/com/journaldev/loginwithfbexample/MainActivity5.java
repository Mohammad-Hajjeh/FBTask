package com.journaldev.loginwithfbexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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

public class MainActivity5 extends AppCompatActivity {
    CallbackManager callbackManager;
    PostAdapter postAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        callbackManager = CallbackManager.Factory.create();
        getUserProfile(AccessToken.getCurrentAccessToken());
    }
    private void getUserProfile(AccessToken currentAccessToken) {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/posts",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        Log.d("TAG", "post" + response.getJSONObject());
                        try {
                            ArrayList<FBPost> fbPosts = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(response.getRawResponse());
                            JSONArray data = jsonObject.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                String created = data.getJSONObject(i).getString("created_time");
                                String message;
                                if (!data.getJSONObject(i).has("message"))
                                    message = "no message";
                                else
                                    message = data.getJSONObject(i).getString("message");

                                String id = data.getJSONObject(i).getString("id");
                                fbPosts.add(new FBPost(created, message, id));
                            }
                            Toast.makeText(getApplicationContext(), String.valueOf(fbPosts.size()), Toast.LENGTH_SHORT).show();
                            Log.d("Size", String.valueOf(fbPosts.size()));

                            recyclerView = findViewById(R.id.rv_posts);
                            layoutManager = new LinearLayoutManager(MainActivity5.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setHasFixedSize(true);
                            postAdapter = new PostAdapter(fbPosts);
                            recyclerView.setAdapter(postAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }
}