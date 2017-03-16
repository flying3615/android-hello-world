package com.sqisland.android.hello.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sqisland.android.hello.R;

public class FragementShow extends Fragment {


    EditText nameEditText;
    EditText passwdEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragement_show, container, false);
        nameEditText = (EditText) view.findViewById(R.id.name);
        passwdEditText = (EditText) view.findViewById(R.id.password);
        return view;
    }


    public void setNameAndPasswd(@Nullable String name, @Nullable String passwd) {

        if(name!=null) nameEditText.setText(name);
        if(passwd!=null) passwdEditText.setText(passwd);

    }

}
