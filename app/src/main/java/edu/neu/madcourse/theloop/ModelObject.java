package edu.neu.madcourse.theloop;

public enum ModelObject {

    // Define Colors
    RED(R.string.red, R.layout.view_red),
    Blue(R.string.blue, R.layout.view_blue);


    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId){
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getmTitleResId() {
        return mTitleResId;
    }

    public int getmLayoutResId() {
        return mLayoutResId;
    }


}
