package com.vgnarray.hanumanchalisa.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vgnarray.hanumanchalisa.hanumanchalisa.R;
public class ArtiFragment extends Fragment {

	TextView tv;
	private String[] myContent;

	public static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		Bundle arg = getArguments();
		int position = arg.getInt(ARG_SECTION_NUMBER);
		Log.d("GAGAN", "position" + position);

		myContent = getResources().getStringArray(R.array.content);
		tv = (TextView) view.findViewById(R.id.vidhi);
		tv.setText(myContent[position]);


		return view;
	}


}

