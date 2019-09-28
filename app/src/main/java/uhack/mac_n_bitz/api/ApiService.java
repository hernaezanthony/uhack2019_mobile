package uhack.mac_n_bitz.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import uhack.mac_n_bitz.model.Device;

public interface ApiService {

     String BASE_URL = "http://76bbdf1e.ngrok.io";

    @GET("/api/device/getUsersDevices/{userId}")
    Call<List<Device>> getUserDevices(@Path("userId") String userId);




}
