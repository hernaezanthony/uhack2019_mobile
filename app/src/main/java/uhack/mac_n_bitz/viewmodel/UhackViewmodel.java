package uhack.mac_n_bitz.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uhack.mac_n_bitz.api.ApiService;
import uhack.mac_n_bitz.model.Device;

public class UhackViewmodel extends ViewModel {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    ApiService service = retrofit.create(ApiService.class);


    public MutableLiveData<List<Device>> getUserDevices(String id){

        final MutableLiveData<List<Device>> deviceList = new MutableLiveData<>();

        service.getUserDevices(id).enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                deviceList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                deviceList.postValue(null);
            }
        });

        return deviceList;
    }

}
