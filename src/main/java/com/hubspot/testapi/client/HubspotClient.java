package com.hubspot.testapi.client;

import com.hubspot.testapi.api.ScheduleRequest;
import com.hubspot.testapi.api.PartnersListResponse;
import com.hubspot.testapi.api.ScheduleResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HubspotClient {
    @GET("/candidateTest/v3/problem/dataset?userKey=e9f0989c049251966702c05af744")
    public Call<PartnersListResponse> getAvailability();

    @POST("/candidateTest/v3/problem/result?userKey=e9f0989c049251966702c05af744")
    public Call<ScheduleResponse> updateInvitation(@Body ScheduleRequest scheduleRequest);
}
