package uhack.mac_n_bitz.view.activity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

import uhack.mac_n_bitz.R;
import uhack.mac_n_bitz.model.Device;
import uhack.mac_n_bitz.viewmodel.UhackViewmodel;

public class HomeActivity extends AppCompatActivity {

    private UhackViewmodel uhackViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        uhackViewmodel = ViewModelProviders.of(this).get(UhackViewmodel.class);

        uhackViewmodel.getUserDevices("2")
                .observe(this, new Observer<List<Device>>() {
                    @Override
                    public void onChanged(List<Device> devices) {
                        Log.d("taggerLIST", new Gson().toJson(devices));
                    }
                });
    }

}
