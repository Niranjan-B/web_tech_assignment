package com.ninja.webtech.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ninja.webtech.R;
import com.ninja.webtech.activities.ResultsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button mSearchButton, mClearButton;
    EditText mInputText;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mSearchButton = (Button) view.findViewById(R.id.button_search_home_fragment);
        mClearButton = (Button) view.findViewById(R.id.button_clear_home_fragment);
        mInputText = (EditText) view.findViewById(R.id.editText_home_fragment);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInputText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a keyword!!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getActivity(), ResultsActivity.class);
                    intent.putExtra("query", mInputText.getText().toString());
                    startActivity(intent);
                }
            }
        });
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mInputText.getText().toString().isEmpty()) {
                    mInputText.setText("");
                }
            }
        });

        return view;
    }

}
