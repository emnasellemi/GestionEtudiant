package com.messaoudi.devart.gestionetudiant;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public class AbscenceDialog extends DialogFragment {
        public View rootView;
        public Context context;
        public ListView listView;

        public AbscenceDialog() {
        }

         public static AbscenceDialog newInstance() {
            AbscenceDialog abscenceDialog = new AbscenceDialog();
            return abscenceDialog;
        }


         @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            this.context = context;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            return dialog;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.absence_layout_fragment, container, false);
            initInterface(container);
            return rootView;
        }

        @Override
        public void onStart() {
            super.onStart();
            Dialog dialog = getDialog();
            if (dialog != null) {
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        }


        public void initInterface(View v) {
            listView = (ListView) rootView.findViewById(R.id.absenceListe);
            AppelActivity ap = (AppelActivity) context;
            List<String> lst = ap.getAbsent();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ap,
                    android.R.layout.simple_list_item_1, lst);
            listView.setAdapter(adapter);
        }


    }
