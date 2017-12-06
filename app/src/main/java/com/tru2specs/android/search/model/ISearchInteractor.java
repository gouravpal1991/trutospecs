package com.tru2specs.android.search.model;

import com.tru2specs.android.objects.request.SearchRequest;
import com.tru2specs.android.search.listener.OnSearchResponseListener;

/**
 * Created by Selva
 */

public interface ISearchInteractor {
    void getSearchedData(OnSearchResponseListener listener, SearchRequest request);
}
