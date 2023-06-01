package sg.edu.rp.c346.id21037598.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Initialize variables
    EditText activityInput;
    Button addBtn, clearBtn, delBtn;
    ListView activityList;
    Spinner activitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link elements into XML
        activityInput = findViewById(R.id.editTextInput);
        addBtn = findViewById(R.id.btnAdd);
        clearBtn = findViewById(R.id.btnClear);
        activityList = findViewById(R.id.listViewActivities);
        activitySpinner = findViewById(R.id.spinnerActivity);
        delBtn = findViewById(R.id.btnDel);

        // Create empty array list
        ArrayList<String> activityArray = new ArrayList<>();

        // Link array list to list view
        ArrayAdapter activityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activityArray);
        activityList.setAdapter(activityAdapter);

        // Adds input into array list, refresh ListView
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activityResponse = activityInput.getText().toString();

                // Checks if input is empty
                if (!activityResponse.isBlank())
                    activityArray.add(activityResponse);
                else
                    Toast.makeText(MainActivity.this, "Error. Input is empty.", Toast.LENGTH_SHORT).show();

                activityAdapter.notifyDataSetChanged();;
            }
        });

        // Clears array list, refreshes ListView
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityArray.clear();
                activityAdapter.notifyDataSetChanged();
            }
        });

        // Deletes specified index from array list
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index;
                String activityResponse = activityInput.getText().toString();

                // Checks if input is a number
                if (activityResponse.matches("[0-9]+")) {
                    index = Integer.parseInt(activityResponse);

                    // Checks if array list is empty
                    if (!activityArray.isEmpty()) {

                        // Checks if index is within the array list
                        if (index < activityArray.size())
                            activityArray.remove(index);

                        else
                            Toast.makeText(MainActivity.this, "Error, incorrect index number.", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MainActivity.this, "Error, there are no tasks to remove", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Error, please input number.", Toast.LENGTH_SHORT).show();

                activityAdapter.notifyDataSetChanged();;
            }
        });

        // Spinner to determine whether to add or remove tasks
        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Depending on spinner selected, enable or disable certain buttons
                switch(position) {
                    case 0:
                        activityInput.setHint("Type in a new task here");
                        activityInput.setText("");
                        addBtn.setEnabled(true);
                        delBtn.setEnabled(false);
                        break;

                    case 1:
                        activityInput.setHint("Type in the index of the task to be removed");
                        activityInput.setText("");
                        addBtn.setEnabled(false);
                        delBtn.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}