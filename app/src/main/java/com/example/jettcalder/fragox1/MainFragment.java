package com.example.jettcalder.fragox1;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    FrameLayout mFrame;
    Button mPrev, mNext, mGoto;
    EditText mPage;
    int countChild = 0, currentPage = 0;

    Random r = new Random();


    private void initialFrame(int numOfFrame) {
        ArrayList<LinearLayout> layoutArrayList= new ArrayList<>();
        ArrayList<TextView> textViewArrayList = new ArrayList<>();
        for (int i = 0; i < numOfFrame; i++) {
            textViewArrayList.add(new TextView(getContext()));
            textViewArrayList.get(i).setText(String.format("Current Page: %d", (i + 1)));

            layoutArrayList.add(new LinearLayout(getContext()));
            layoutArrayList.get(i).addView(textViewArrayList.get(i));

            mFrame.addView(layoutArrayList.get(i));
            mFrame.getChildAt(i).setBackgroundColor(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

            mFrame.getChildAt(i).setVisibility(View.GONE);
        }
        mFrame.getChildAt(0).setVisibility(View.VISIBLE);
    }

    private void updatePage() {
        for (int i = 0; i < countChild; i++) {
            mFrame.getChildAt(i).setVisibility(View.GONE);
        }
        mFrame.getChildAt(currentPage).setVisibility(View.VISIBLE);
    }

    private void updatePage(int page) {
        for (int i = 0; i < countChild; i++) {
            mFrame.getChildAt(i).setVisibility(View.GONE);
        }
        mFrame.getChildAt(page).setVisibility(View.VISIBLE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mFrame = view.findViewById(R.id.main_frame);
        mPrev = view.findViewById(R.id.main_prev);
        mNext = view.findViewById(R.id.main_next);
        mGoto = view.findViewById(R.id.main_goto);
        mPage = view.findViewById(R.id.main_page);

        initialFrame(5);
        countChild = mFrame.getChildCount();

        mPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage > 0) {
                    currentPage--;
                    updatePage();
                }
            }
        });
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage < countChild-1){
                    currentPage++;
                    updatePage();
                }
            }
        });
        mGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String page = mPage.getText().toString();
                if (!page.isEmpty()){
                    int tmp_page = currentPage;
                    currentPage = Integer.parseInt(page)-1;
                    if (!(currentPage > countChild-1)) {
                        updatePage(currentPage);
                    }
                    else {
                        currentPage = tmp_page;
                    }
                }
            }
        });
        return view;
    }

}
