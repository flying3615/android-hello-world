package com.sqisland.android.hello.ui;

import android.content.Context;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sqisland.android.hello.R;


public class FragmentTest extends Fragment {


    private OnFragmentInteractionListener mListener;

    private static EditText nameTextInput;
    private static EditText passwordTextInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_test, container, false);
        // make connection between java code and xml
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


//        GestureActivity gs = new GestureActivity();
//        GestureDetectorCompat mDetector = new GestureDetectorCompat(getActivity(),gs);
//        mDetector.setOnDoubleTapListener(gs);

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        Log.i("in fragment", "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                Log.i("in fragment", "Right to Left");
                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                Log.i("in fragment", "Left to Right");
                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        //have to bound view touch listener with gesture listener
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

        return view;
    }


    //
    public void onButtonPressed(View view) {
        // if listen is not null
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
