package com.dengemo.TekWulf.CANBusGuardian;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlarmInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmInformationFragment extends Fragment {
    public static AlarmInformationFragment newInstance(String info) {
        Bundle args = new Bundle();
        AlarmInformationFragment fragment = new AlarmInformationFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.fragment_alarm_information, null);
        return view;
    }
}