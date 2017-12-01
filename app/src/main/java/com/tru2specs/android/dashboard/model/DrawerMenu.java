package com.tru2specs.android.dashboard.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pb00471065 on 11/24/2017.
 */

public class DrawerMenu implements Parcelable {

    private String drawerMenuItemName;
    // Type can only be static and dynamic
    // use enum here
    private String type;
    public static final String STATIC_ITEM = "static";
    public static final String DYNAMIC_ITEM = "dynamic";

    public DrawerMenu(String drawerMenuItemName, String type) {
        this.drawerMenuItemName = drawerMenuItemName;
        this.type = type;

    }

    protected DrawerMenu(Parcel in) {
        drawerMenuItemName = in.readString();
        type = in.readString();
    }

    public static final Creator<DrawerMenu> CREATOR = new Creator<DrawerMenu>() {
        @Override
        public DrawerMenu createFromParcel(Parcel in) {
            return new DrawerMenu(in);
        }

        @Override
        public DrawerMenu[] newArray(int size) {
            return new DrawerMenu[size];
        }
    };

    public String getDrawerMenuItemName() {
        return drawerMenuItemName;
    }

    public String getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(drawerMenuItemName);
        dest.writeString(type);
    }
}
