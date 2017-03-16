package com.sqisland.android.hello.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sqisland.android.hello.R;


public class FragmentTest extends Fragment {


    private OnFragmentInteractionListener mListener;

    private static EditText nameTextInput;
    private static EditText passwordTextInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_test, container, false);

        nameTextInput = (EditText) view.findViewById(R.id.name);
        passwordTextInput = (EditText) view.findViewById(R.id.password);
        Button button = (Button) view.findViewById(R.id.sign_in);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonPressed(v);
                    }
                }
        );

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View view) {
        if (mListener != null) {
            String name = nameTextInput.getText().toString();
            String passwd = passwordTextInput.getText().toString();
            mListener.onFragmentInteraction(name,passwd);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            //context is main activity
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and nameEditText
        void onFragmentInteraction(String name,String passwd);
    }
}
