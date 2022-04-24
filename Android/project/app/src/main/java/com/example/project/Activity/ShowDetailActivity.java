package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project.Model.Ticket;
import com.example.project.Model.TicketOrder;
import com.example.project.Utilities.ManagementCart;
import com.example.project.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCardBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, descriptionPlace, descriptionDate;
    private ImageView plusBtn, minusBtn, pic;
    private Ticket object1;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);

        initView();
        getBundle();
    }

    private void getBundle() {
      object1 = (Ticket) getIntent().getSerializableExtra("object");
      TicketOrder object = new TicketOrder(object1, 0);

        int drawableResourceId = this.getResources().getIdentifier(object.getTicket().getPictureUrl(), "drawable", this.getPackageName());
        String picUrl = object.getTicket().getPictureUrl();

        Glide.with(this)
                .load(picUrl)
                .into(pic);

        titleTxt.setText(object.getTicket().getName());
        feeTxt.setText("$" + object.getTicket().getPrice());
        descriptionTxt.setText(object.getTicket().getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        descriptionPlace.setText(object.getTicket().getPlace());
        descriptionDate.setText(object.getTicket().getDate());



        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNum(numberOrder);
                managementCart.insertTicketOrder(object);
            }
        });
    }

    private void initView() {
        addToCardBtn = findViewById(R.id.addToCardBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionPlace = findViewById(R.id.descriptionPlace);
        descriptionDate = findViewById(R.id.descriptionDate);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        pic = findViewById(R.id.foodPic);
    }
}