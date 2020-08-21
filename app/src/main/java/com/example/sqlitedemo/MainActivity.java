package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sqlitedemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    SQLHelper sqlHelper;
    ActivityMainBinding binding;
    String name;
    int price =0;
    int quantity =0;
    int id = 0;
    private static int VERSION_TABLE = 2;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        sqlHelper = new SQLHelper(this);
        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.etName.getText().toString();
                price = Integer.parseInt(binding.etPrice.getText().toString());
                quantity = Integer.parseInt(binding.etQuantity.getText().toString());
                sqlHelper.onInsertToDB(name,price,quantity);
                sqlHelper.onUpgrade(sqLiteDatabase, VERSION_TABLE,VERSION_TABLE);
                VERSION_TABLE++;
                Toast.makeText(getBaseContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.etName.getText().toString();
                price = Integer.parseInt(binding.etPrice.getText().toString());
                quantity = Integer.parseInt(binding.etQuantity.getText().toString());
                id = Integer.parseInt(binding.etId.getText().toString());
                sqlHelper.onUpdateProcduct(id,name,price,quantity);
                sqlHelper.onUpgrade(sqLiteDatabase, VERSION_TABLE,VERSION_TABLE);
                VERSION_TABLE++;
                Toast.makeText(getBaseContext(),"Cập nhật thành công",Toast.LENGTH_LONG).show();
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = Integer.parseInt(binding.etId.getText().toString());
                sqlHelper.onDeleteProduct(id);
                sqlHelper.onUpgrade(sqLiteDatabase, VERSION_TABLE,VERSION_TABLE);
                VERSION_TABLE++;
                if (sqlHelper.onDeleteProduct(id)==0){
                    Toast.makeText(getBaseContext(),"Xóa thành công",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(),"Xóa không thành công",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}