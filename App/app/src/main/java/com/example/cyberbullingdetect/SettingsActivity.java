package com.example.cyberbullingdetect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Fragment implements View.OnClickListener{

    private Button btn;
    private EditText name;
    private EditText number;
    private View root ;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        root = inflater.inflate(R.layout.settings_activity, container, false);
        btn = root.findViewById(R.id.btn);
        name = root.findViewById(R.id.name);
        number = root.findViewById(R.id.number);
        btn.setOnClickListener(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        editor = preferences.edit();
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onClick(View view){
        String n = name.getText().toString();
        String num = number.getText().toString();
        editor.putString("name", n);
        editor.putString("number",num);
        editor.apply();

    }

}
