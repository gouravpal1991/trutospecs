package com.tru2specs.android.menu.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.tru2specs.android.dashboard.model.DrawerMenu;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by palgour on 10/30/17.
 */

public class MenuItem implements Parcelable {
    private String categoryName;
    private ArrayList<DrawerMenu> menuItems;

    protected MenuItem(Parcel in) {
        categoryName = in.readString();
        menuItems = in.createTypedArrayList(DrawerMenu.CREATOR);
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    public String getCategoryName() {
        return categoryName;
    }


    public ArrayList<DrawerMenu> getMenuItems() {
        return menuItems;
    }

    public MenuItem() {
    }

    public MenuItem(String categoryName, ArrayList<DrawerMenu> menuItems) {
        this.categoryName = categoryName;
        this.menuItems = menuItems;
    }

    public static ArrayList<MenuItem> getStaticData() {
        ArrayList<DrawerMenu> menuItems = new ArrayList<>();
        menuItems.add(new DrawerMenu(DrawerMenu.MENU_HOME, DrawerMenu.STATIC_ITEM));
        menuItems.add(new DrawerMenu("New Arrivals", DrawerMenu.DYNAMIC_ITEM));
        menuItems.add(new DrawerMenu("Hot Deals", DrawerMenu.DYNAMIC_ITEM));
        menuItems.add(new DrawerMenu("Sunglasses", DrawerMenu.DYNAMIC_ITEM));
        menuItems.add(new DrawerMenu("Eyeglasses", DrawerMenu.DYNAMIC_ITEM));
        menuItems.add(new DrawerMenu("Contact Lenses", DrawerMenu.DYNAMIC_ITEM));
        menuItems.add(new DrawerMenu(DrawerMenu.MENU_STORES, DrawerMenu.STATIC_ITEM));
        menuItems.add(new DrawerMenu(DrawerMenu.MENU_BRANDS, DrawerMenu.STATIC_ITEM));
        menuItems.add(new DrawerMenu(DrawerMenu.MENU_ORDER_TRACKING, DrawerMenu.STATIC_ITEM));
        menuItems.add(new DrawerMenu(DrawerMenu.MENU_FAQ, DrawerMenu.STATIC_ITEM));
        menuItems.add(new DrawerMenu(DrawerMenu.MENU_PRIVACY_POLICY, DrawerMenu.STATIC_ITEM));
        menuItems.add(new DrawerMenu(DrawerMenu.MENU_ABOUT, DrawerMenu.STATIC_ITEM));

        MenuItem itemMen = new MenuItem("MEN", menuItems);
        MenuItem itemWomen = new MenuItem("WOMEN", menuItems);
        MenuItem itemKids = new MenuItem("KIDS", menuItems);

        ArrayList<MenuItem> drawerMenuList = new ArrayList<>();
        drawerMenuList.add(itemMen);
        drawerMenuList.add(itemWomen);
        drawerMenuList.add(itemKids);

        return drawerMenuList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryName);
        dest.writeTypedList(menuItems);
    }
}
