package sg.edu.rp.c346.id20032316.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    Button btnStore;
    Button btnRetrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etGPA = findViewById(R.id.etGPA);
        btnStore = findViewById(R.id.btnStore);
        btnRetrieve = findViewById(R.id.btnRetrieve);

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());

                SharedPreferences pref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = pref.edit();
                prefEdit.putString("name", name);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.commit();
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getPreferences(MODE_PRIVATE);
                String name = pref.getString("name", "No name entered");
                float gpa = pref.getFloat("gpa", 4.0f);

                etName.setText(name);
                etGPA.setText(gpa + "");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        //save data

        String name = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());

        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = pref.edit();
        prefEdit.putString("name", name);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //retrieve data

        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        String name = pref.getString("name", "No name entered");
        float gpa = pref.getFloat("gpa", 4.0f);

        etName.setText(name);
        etGPA.setText(gpa + "");

    }
}