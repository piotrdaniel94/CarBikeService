package com.example.bikecarservice.serviceCentre;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bikecarservice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ServiceDashboard extends AppCompatActivity {

    Button btn_viewService, btn_addService, btn_addServiceType;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_dashboard);

        btn_viewService = findViewById(R.id.btn_viewServices);
        btn_addService = findViewById(R.id.btn_addService);
        btn_addServiceType = findViewById(R.id.btn_addServiceType);

        btn_viewService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceDashboard.this, ViewService.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        String restName = "";
        btn_addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(restName);
            }
        });
        btn_addServiceType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog2(restName);
            }
        });

        progressDialog = new ProgressDialog(ServiceDashboard.this);

    }

    private void createDialog2(String restName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ServiceDashboard.this);
        View view = LayoutInflater.from(ServiceDashboard.this).inflate(R.layout.add_service_type, null, false);
        builder.setView(view);
        EditText et_vehicleType = view.findViewById(R.id.et_username);
        EditText et_costForCar = view.findViewById(R.id.et_costForCar);
        EditText et_costForBike = view.findViewById(R.id.et_costForBike);
        EditText et_costForActiva = view.findViewById(R.id.et_costForActiva);
        Button btn_addService = view.findViewById(R.id.btn_addService);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
        btn_addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String username = et_username.getText().toString();
//                String vehicleType = et_vehicleType.getText().toString();
//                String vehicleNumber = et_vehicleNumber.getText().toString();
//                String serviceCost = et_serviceCost.getText().toString();
//                if (username.isEmpty()) {
//                    et_username.setError("Empty");
//                } else if (vehicleType.isEmpty()) {
//                    et_vehicleType.setError("Empty");
//                } else if (vehicleNumber.isEmpty()) {
//                    et_vehicleNumber.setError("Empty");
//                } else {
                    progressDialog.setMessage("Adding Your Food");
                    progressDialog.setTitle("Adding...");
                    progressDialog.setCanceledOnTouchOutside(false);
//                    createFood(username, vehicleType, vehicleNumber, serviceCost, restName);
//                    alertDialog.dismiss();
//                }
                progressDialog.setMessage("Adding Service Type");
                progressDialog.setTitle("Adding...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                alertDialog.dismiss();
            }
        });
    }

    private void createDialog(String restName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ServiceDashboard.this);
        View view = LayoutInflater.from(ServiceDashboard.this).inflate(R.layout.add_service, null, false);
        builder.setView(view);
        EditText et_username = view.findViewById(R.id.et_username);
        EditText et_vehicleType = view.findViewById(R.id.et_vehicleType);
        EditText et_vehicleNumber = view.findViewById(R.id.et_vehicleNumber);
        EditText et_serviceCost = view.findViewById(R.id.et_serviceCost);
        Button btn_addService = view.findViewById(R.id.btn_addService);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
        btn_addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String username = et_username.getText().toString();
//                String vehicleType = et_vehicleType.getText().toString();
//                String vehicleNumber = et_vehicleNumber.getText().toString();
//                String serviceCost = et_serviceCost.getText().toString();
//                if (username.isEmpty()) {
//                    et_username.setError("Empty");
//                } else if (vehicleType.isEmpty()) {
//                    et_vehicleType.setError("Empty");
//                } else if (vehicleNumber.isEmpty()) {
//                    et_vehicleNumber.setError("Empty");
//                } else {
//                    progressDialog.setMessage("Adding Your Food");
//                    progressDialog.setTitle("Adding...");
//                    progressDialog.setCanceledOnTouchOutside(false);
//                    createFood(username, vehicleType, vehicleNumber, serviceCost, restName);
//                    alertDialog.dismiss();
//                }
                progressDialog.setMessage("Adding Service");
                progressDialog.setTitle("Adding...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                alertDialog.dismiss();
            }
        });
    }

    private void createFood(String foodName, String foodDesc, String foodPrice, String restName, String foodImage) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(ServiceRegister.SERVICE_CENTRE).child(restName);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("foodName", foodName);
        hashMap.put("foodDesc", foodDesc);
        hashMap.put("foodPrice", foodPrice);
        hashMap.put("foodImage", foodImage);
        hashMap.put("id", userId);
        reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
//                    getAllFood(restName);
                    Toast.makeText(ServiceDashboard.this, "Added Successfully", Toast.LENGTH_SHORT).show();
//                    imageString = "";
                } else {
                    Toast.makeText(ServiceDashboard.this, "Created Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}