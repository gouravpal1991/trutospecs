package com.tru2specs.android.rest;

import com.tru2specs.android.objects.request.AddressRequest;
import com.tru2specs.android.objects.request.CartRequest;
import com.tru2specs.android.objects.request.LoginRequest;
import com.tru2specs.android.objects.request.ProudctListingRequest;
import com.tru2specs.android.objects.request.SavedAddressesRequest;
import com.tru2specs.android.objects.request.SignUpRequest;
import com.tru2specs.android.objects.request.UpdateUserRequest;
import com.tru2specs.android.objects.request.filterrequest.FilterRequest;
import com.tru2specs.android.objects.responses.address.AddressResponse;
import com.tru2specs.android.objects.responses.dashboard.DashboardResponse;
import com.tru2specs.android.objects.responses.filter.FilterResponse;
import com.tru2specs.android.objects.responses.loginSignup.LoginSignUpResponse;
import com.tru2specs.android.objects.responses.product.ProductResponse;
import com.tru2specs.android.objects.responses.productlisting.ProductListingResponse;
import com.tru2specs.android.objects.responses.savedaddresses.Address;
import com.tru2specs.android.objects.responses.savedaddresses.SavedAddressesResponse;
import com.tru2specs.android.objects.responses.store.StoreListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by GP00471911 on 03-03-2017.
 */

public interface APIService {
    @POST("store/get")
    Call<StoreListResponse> getStoresList();

    @POST("dashboard/get")
    Call<DashboardResponse> getDashboardData();

    @POST("product/get")
    Call<ProductResponse> getProductData();

    @POST("login")
    Call<LoginSignUpResponse> doLogin(@Body LoginRequest request);

    @POST("register")
    Call<LoginSignUpResponse> doSignUp(@Body SignUpRequest request);

    @POST("address/add")
    Call<AddressResponse> addAddress(@Body AddressRequest request);

    @POST("address/update")
    Call<AddressResponse> updateAddress(@Body Address request);

    @POST("user/udpate")
    Call<LoginSignUpResponse> updateUser(@Body UpdateUserRequest request);

    @POST("address/get")
    Call<SavedAddressesResponse> getAddresses(@Body SavedAddressesRequest request);


    @POST("product/get")
    Call<ProductListingResponse> getProducts();

    @POST("product/get")
    Call<ProductListingResponse> getProductById(@Body ProudctListingRequest request);

    @POST("cart/get")
    Call<ProductListingResponse> getCartItems(@Body CartRequest request);

    @POST("filter/get")
    Call<ProductResponse> getFilterdData(@Body FilterRequest request);


    @POST("product/getMaster")
    Call<FilterResponse> getFilterdKeys();
//    @GET("get_booking_types.php")
//    Call<BookingTypeResponse> getBookingTypeAndProjects();
//
//    @FormUrlEncoded
//    @POST("add_enquiry.php")
//    Call<EnquiryResponse> addEnquiry(@Field("person_name") String name, @Field("address") String address,
//                                     @Field("contact") String contact, @Field("message") String message, @Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("add_online_booking.php")
//    Call<BookingResponse> addOnineBooking(@Field("project") String project,
//                                          @Field("type") String type,
//                                          @Field("person_name") String name,
//                                          @Field("address") String address,
//                                          @Field("contact") String contact,
//                                          @Field("message") String message,
//                                          @Field("email") String email);
//
//
//    @FormUrlEncoded
//    @POST("add_feedback.php")
//    Call<FeedbackResponse> addFeedback(@Field("person_name") String name, @Field("address") String address,
//                                       @Field("contact") String contact, @Field("comment") String comment);
//
//
//    @Streaming
//    @GET
//    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
}
