package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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

        EditText userNameField = (EditText) findViewById(R.id.user_name_field);
        String userName =  userNameField.getText().toString();

        displayMessage(createOrderSummary(hasWhippedCream, hasChocolate, userName));
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

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        if(addWhippedCream && addChocolate){
            return (price + 3) * quantity;
        }
        else if(addChocolate){
            return (price + 2) * quantity;
        }
        else if(addWhippedCream){
            return (price + 1) * quantity;
        }else{
            return quantity * price;
        }

    }


    /**
     * * Creates a text summary of the order
     * @param addWhippedCream whether or not the user wants whipped cream
     * @param addChocolate yes or no to chocolate option
     * @param userName
     * @return order summary
    */
    private String createOrderSummary(boolean addWhippedCream, boolean addChocolate, String userName){
        String priceMessage = "Name: " + userName;
        priceMessage += "\nHas Whipped Cream: " + addWhippedCream;
        priceMessage += "\nHas Chocolate: " + addChocolate;
        priceMessage += "\nQuantity: " + quantity + "\n";
        priceMessage += "Total: $" + calculatePrice(addWhippedCream, addChocolate) + "\nThank you!";
        return  priceMessage;
    }

}