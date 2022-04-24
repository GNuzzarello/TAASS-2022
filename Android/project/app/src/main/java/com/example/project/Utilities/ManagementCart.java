package com.example.project.Utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.project.Interface.ChangeNumberItemsListener;
import com.example.project.Model.TicketOrder;
import com.example.project.Model.TicketOrders;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertTicketOrder(TicketOrder item) {
        ArrayList<TicketOrder> listticket = getListCard();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listticket.size(); i++) {
            if (listticket.get(i).getTicket().getId().equals(item.getTicket().getId())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listticket.get(n).setNum(item.getNum());
        } else {
            listticket.add(item);
        }

        tinyDB.putListObject("CardList", listticket);
        Toast.makeText(context, "Added To Your Card", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<TicketOrder> getListCard() {
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberFood(ArrayList<TicketOrder> listticket, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listticket.get(position).setNum(listticket.get(position).getNum() + 1);
        tinyDB.putListObject("CardList", listticket);
        changeNumberItemsListener.changed();
    }

    public void MinusNumerFood(ArrayList<TicketOrder> listticket, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listticket.get(position).getNum() == 1) {
            listticket.remove(position);
        } else {
            listticket.get(position).setNum(listticket.get(position).getNum()-1);
        }
        tinyDB.putListObject("CardList", listticket);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<TicketOrder> listp = getListCard();
        double fee = 0;
        for (int i = 0; i < listp.size(); i++) {
            fee = fee + (listp.get(i).getTicket().getPrice() * listp.get(i).getNum());
        }
        return fee;
    }

    public void payment() throws IOException {
        if(!getListCard().isEmpty()){new PostDataTask().execute();}
        else {System.out.println("Errore");}
    }
    private class PostDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            TicketOrders po = new TicketOrders(getListCard());
            Gson gson = new Gson();
            HttpHandler httpHandler = new HttpHandler();
            httpHandler.sendHTTPData("http://192.168.1.132:8080/api/orders", po);
            return null;
        }

        @Override
        protected void onPostExecute(String dataFetched) {
            //parse the JSON data and then display
        }

    }

}
