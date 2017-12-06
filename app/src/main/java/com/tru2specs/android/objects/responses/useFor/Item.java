
package com.tru2specs.android.objects.responses.useFor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("use_for")
    @Expose
    private String useFor;
    @SerializedName("desc1")
    @Expose
    private String desc1;
    @SerializedName("desc2")
    @Expose
    private String desc2;
    @SerializedName("img")
    @Expose
    private String img;

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
