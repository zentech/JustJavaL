package com.example.android.justjaval;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView counterDisplay;
    TextView orderSummary;
    CheckBox wheapCream;
    EditText customerName;
    CheckBox chocolate;
    private int counter = 0;
    private int pricePerCup = 5;
    private int totalPrice = 0;
    String subject = "JustJava order for: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //references to views
        wheapCream = (CheckBox) findViewById(R.id.wheap_cream_checkbox);
        chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        counterDisplay = (TextView) findViewById(R.id.counterTextView);
        wheapCream = (CheckBox) findViewById(R.id.wheap_cream_checkbox);
        customerName = (EditText) findViewById(R.id.name_editText);
    }

    /*
    * increase order method
    */
    public void increaseOrder(View view) {
        if (counter > 100) {
            Toast.makeText(this, "Maximum order 100 cups", Toast.LENGTH_LONG).show();
            return;
        }
        counter++;
        displayQuantity();
    }

    /*
    * decrease order quantity
    */
    public void decreaseOrder(View view) {
        if (counter <= 1) {
            Toast.makeText(this, "You need at least 1 cup", Toast.LENGTH_LONG).show();
            return;
        }
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
        boolean hasWheapCream = wheapCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        String name = customerName.getText().toString();
        //toast message telling the user to enter name
        if(name.isEmpty()) {
            Toast.makeText(this, "Enter customer name!", Toast.LENGTH_SHORT).show();
            return;
        }
        totalPrice = calculatePrice(counter, pricePerCup, hasWheapCream, hasChocolate);
        Log.v("MainActivity", "counter: " + counter + " total: " + totalPrice);
        String summary = orderSummary(totalPrice, name, hasWheapCream, hasChocolate);
        subject += name;
        //(intent code) sending email with order summary
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

//    /*
//    * clear order
//    */
//    public void clearOrder(View view) {
//        counter = 0;
//        totalPrice = 0;
//        counterDisplay.setText("0");
//        orderSummary.setText("$0.0");
//        wheapCream.setChecked(false);
//        chocolate.setChecked(false);
//        customerName.setText("");
//    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity    is the number of cups of coffee ordered
     * @param pricePerCup is the price of a single cup of coffee
     */
    private int calculatePrice(int quantity, int pricePerCup, boolean hasCream, boolean hasChocolate) {
        int basePrice = 0;
        int creamPrice = 1;
        int chocolatePrice = 2;
        //calculate price for one cup of coffee (with or without toppings)
        if (hasCream) {
            basePrice = pricePerCup + creamPrice;
        }
        if (hasChocolate) {
            basePrice = pricePerCup + chocolatePrice;
        }
        return quantity * basePrice;
    }

    /**
    * create order sumary
    *
    * @param totalPrice is the total price of the order
    * @param name is the name of the customer
     *@param hasCream has toppings wheap cream (boolean)
     *@param hasChocolate has toppings chocolate (boolean)
     *
    * @return formats the output to be displayed
    */
    public String orderSummary(int totalPrice, String name, boolean hasCream, boolean hasChocolate) {
        String output = "Name: " + name + "\n" +
                "Wheap Cream: " + hasCream + "\n" +
                "Chocolate: " + hasChocolate + "\n" +
                "Quantity: " + counter + "\n" +
                "Total: $" + totalPrice + "\n" +
                "Thank You!";
        return output;
    }

}

