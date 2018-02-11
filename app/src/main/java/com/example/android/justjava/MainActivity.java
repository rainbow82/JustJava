package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int price = 5;

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity > 0){
            quantity = quantity - 1;
            display(quantity);
        }

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        displayMessage(createOrderSummary(hasWhippedCream, hasChocolate));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice(){
        return quantity * price;
    }


    /**
     * * Creates a text summary of the order
     * @param addWhippedCream whether or not the user wants whipped cream
     * @return order summary
    */
    private String createOrderSummary(boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name: Shannon Beach\n";
        priceMessage += "Has Whipped Cream: " + addWhippedCream;
        priceMessage += "\nHas Chocolate: " + addChocolate;
        priceMessage += "\nQuantity: " + quantity + "\n";
        priceMessage += "Total: $" + calculatePrice() + "\nThank you!";
        return  priceMessage;
    }

}