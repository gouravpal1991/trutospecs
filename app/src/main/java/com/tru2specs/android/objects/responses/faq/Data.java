package com.tru2specs.android.objects.responses.faq;

/**
 * Created by palgour on 12/3/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("faqs")
    @Expose
    private List<Faq> faqs = null;

    public List<Faq> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<Faq> faqs) {
        this.faqs = faqs;
    }

}