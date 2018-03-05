package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shannonbeach on 3/4/18.
 */

public class CoffeeActivity extends AppCompatActivity {

    int quantity = 1;
    int price = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_options);
    }

    public void increment(View view) {
        if(quantity >= 100) {
            Toast.makeText(this, "Maximum cups ordered is 100", Toast.LENGTH_SHORT).show();
            display(quantity);
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity <= 1){
            Toast.makeText(this, "Must have Minimum of 1 cup", Toast.LENGTH_SHORT).show();
            display(quantity);
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is called when the coffee_options button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        CheckBox vanillaCreamerCheckbox = findViewById(R.id.vanillaCreamer_checkbox);
        boolean hasVaniallCreamer = vanillaCreamerCheckbox.isChecked();

        EditText userNameField = (EditText) findViewById(R.id.user_name_field);
        String userName =  userNameField.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java coffee_options for " + userName);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(hasWhippedCream, hasChocolate, hasVaniallCreamer, userName));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     *
     * @param addWhippedCream
     * @param addChocolate
     * @return
     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        if(addChocolate){
            price += 2;
        }
        if(addWhippedCream){
            price += 1;
        }
        return price * quantity;
    }


    /**
     * * Creates a text summary of the coffee_options
     * @param addWhippedCream whether or not the user wants whipped cream
     * @param addChocolate yes or no to chocolate option
     * @param userName
     * @return coffee_options summary
     */
    private String createOrderSummary(boolean addWhippedCream, boolean addChocolate, boolean addVaniallaCreamer, String userName){
        String priceMessage = getString(R.string.order_summary_name, userName);
        priceMessage += "\n"+ getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_vanillaCreamer,addVaniallaCreamer );
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\nTotal: $" + calculatePrice(addWhippedCream, addChocolate) + "\nThank you!";
        return  priceMessage;
    }

}
