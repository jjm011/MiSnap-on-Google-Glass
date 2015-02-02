package com.luyaozhou.myapplicationforglass;

/**
 * Created by luyao on 2/2/15.
 */
public class Category {
    public Category(String name) {
        mName = name;
    }
    public Category(String name, String photoFileName){
        this(name);
        mPhotoFileName = photoFileName;
    }

    public String getName() {
        return mName;
    }

    private String mName;

    private String getPhotoFileName(){
        return mPhotoFileName;
    }

    public void setPhotoFileName(String photoFileName){
        photoFileName = photoFileName;

    }

    private String mPhotoFileName;
}
