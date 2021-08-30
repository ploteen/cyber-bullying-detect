package com.example.cyberbullingdetect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends Fragment {

    private View view;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private TextView name;
    private ImageButton helper_btn;
    private ImageButton cyber_btn;
    private ImageButton police_btn;
    private ImageView score_image;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.activity_score, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        editor = preferences.edit();
        name = view.findViewById(R.id.helper_name);
        score_image = view.findViewById(R.id.score_image);
        //score 이미지 변경
        SharedPreferences result = view.getContext().getSharedPreferences("classify", view.getContext().MODE_PRIVATE);
        int total =  result.getInt("total", 0);
        int hate = result.getInt("hate", 0);
        Log.d("score 이미지 변경", "total : " + Integer.toString(total)
        + "hate : " + Integer.toString(hate));
        int score;
        if (total != 0)
            score = hate / total * 100 ;
        else
            score = 0;
        if (score >= 70) score_image.setImageResource(R.drawable.high);
        else if (score >= 50) score_image.setImageResource(R.drawable.middle);
        else score_image.setImageResource(R.drawable.low);
        //name 바꾸기
        name.setText(preferences.getString("name","없음"));
        //helper button 바꾸기
        helper_btn = view.findViewById(R.id.helper_button);
        String num = preferences.getString("number","0");

        helper_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + num));
                startActivity(intent);
            }
        });
        //cyber button
        cyber_btn = view.findViewById(R.id.cyber_button);
        cyber_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1388"));
                startActivity(intent);
            }
        });
        //police button
        police_btn = view.findViewById(R.id.police_button);
        police_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:117"));
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}