package com.vgnarray.hanumanchalisa;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vgnarray.hanumanchalisa.hanumanchalisa.R;

public class ArtiFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    private TextView tv;
    Activity context;
    private int position;
    private String str;
    ImageView icon;
    private String[] myContent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        str = getResources().getStringArray(R.array.content)[position];
        tv.setText(str);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle arg = getArguments();
        position = arg.getInt(ARG_SECTION_NUMBER);
        if (MainActivity.DEBUG)
            Log.d("GAGAN", "position" + position);

        int[] bIcons = {R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5, R.drawable.icon_6};
        int cImage = bIcons[position];

        myContent = getResources().getStringArray(R.array.content);
        icon = (ImageView) view.findViewById(R.id.imgLabel);
        tv = (TextView) view.findViewById(R.id.vidhi);
 //       tv2 = (TextView) view.findViewById(R.id.vidhi2);
        icon.setImageResource(cImage);
		tv.setText(myContent[position]);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                str = getResources().getStringArray(R.array.content)[position];
//                context.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
////                        if (position == 5) {
////                            tv.setText(str);
////                            tv2.setVisibility(View.VISIBLE);
////                            tv2.setText(getResources().getStringArray(R.array.content)[position + 1]);
////                        } else {
//                            tv.setText(str);
//                            tv2.setVisibility(View.GONE);
////                        }
//
//                    }
//                });
//            }
//        }).start();

        return view;
    }


}

