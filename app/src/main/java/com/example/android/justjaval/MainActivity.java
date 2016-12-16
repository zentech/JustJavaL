package com.example.android.justjaval;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView counterDisplay;
    TextView orderSummary;
    private int counter = 0;
    private int pricePerCup = 5;
    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    * increase order method
    */
    public void increaseOrder(View view) {
        counter++;
        displayQuantity();
    }

    /*
    * decrease order quantity
    */
    public void decreaseOrder(View view) {
        counter--;
        displayQuantity();
    }

    /*
    * method to update number of cup
    */
    public void displayQuantity() {
        counterDisplay = (TextView) findViewById(R.id.counterTextView);
        counterDisplay.setText("" + counter);
    }

    /*
    * counter method
    */
    public void submitOrder(View view) {
        orderSummary = (TextView) findViewById(R.id.order_summary_text_view);
        totalPrice = calculatePrice(counter, pricePerCup);
        orderSummary.setText(orderSummary(totalPrice));
    }

    /*
    * clear order
    */
    public void clearOrder(View view) {
        counter = 0;
        totalPrice = 0;
        counterDisplay = (TextView) findViewById(R.id.counterTextView);
        orderSummary = (TextView) findViewById(R.id.order_summary_text_view);
        counterDisplay.setText("0");
        orderSummary.setText("$0.0");
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity    is the number of cups of coffee ordered
     * @param pricePerCup is the price of a single cup of coffee
     */
    private int calculatePrice(int quantity, int pricePerCup) {
        return quantity * pricePerCup;
    }

    /*
    * create order sumary
    *
    * @param totalPrice is the total price of the order
    * @return formats the output to be displayed
    */
    public String orderSummary(int totalPrice) {
        String output = "Name: George Louis\n" +
                "Quantity: " + counter + "\n" +
                "Total: $" + totalPrice + "\n" +
                "Thank You!";
        return output;
    }


}

