package com.vgnarray.hanumanchalisa;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

/**
 * Created by Vinay on 22-10-2015.
 */
public class CustomFab extends FloatingActionButton {
    private Context mCotext;
    private PlaySongIntface mSonglistener;

    public PlaySongIntface getmSonglistener() {
        return mSonglistener;
    }

    public void setmSonglistener(PlaySongIntface context) {
        this.mSonglistener = context;
    }

    public CustomFab(Context context) {
        super(context);
        this.mCotext = context;
    }

    public CustomFab(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCotext = context;

    }

    public CustomFab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCotext = context;
    }



    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        mSonglistener.playCurr(this, isSelected());
    }
}
