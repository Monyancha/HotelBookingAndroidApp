package id.sch.smktelkom_mlg.tugas01.xiirpl1021.hotelbookingandroidapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import id.sch.smktelkom_mlg.tugas01.xiirpl1021.hotelbookingandroidapp.adapter.KotaAdapter;

public class MainActivity extends AppCompatActivity {

    EditText etNama;
    EditText etEmail;
    EditText etDeparture;
    EditText etArrival;
    RadioGroup rgStatus;


    EditText date;
    EditText Arrival;
    DatePickerDialog datePickerDialog;

    Spinner spProvinsi, spKota;
    TextView tvHasil;
    String[][] arKota = {{"Jakarta Barat", "Jakarta Pusat", "Jakarta Selatan",
            "Jakarta Timur", "Jakarta Utara"},
            {"Bandung", "Cirebon", "Bekasi"}, {"Semarang",
            "Magelang", "Surakarta"},
            {"Surabaya", "Malang", "Blitar"}, {"Denpasar"}};
    ArrayList<String> listKota = new ArrayList<>();
    KotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Seach Ticket In Travesia");

        etNama = (EditText) findViewById(R.id.editTextNama);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etDeparture = (EditText) findViewById(R.id.EditTextDeparture);
        etArrival = (EditText) findViewById(R.id.EditTextArrival);

        spProvinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        spKota = (Spinner) findViewById(R.id.spinnerKota);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        rgStatus = (RadioGroup) findViewById(R.id.radioGroupStatus);

        adapter = new KotaAdapter(this, listKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKota.setAdapter(adapter);


        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listKota.clear();
                listKota.addAll(Arrays.asList(arKota[pos]));
                adapter.setProvinsi((String) spProvinsi.getItemAtPosition(pos));
                adapter.notifyDataSetChanged();
                spKota.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonBM) {
                    findViewById(R.id.tilJA).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.tilJA).setVisibility(View.VISIBLE);
                }
            }
        });


        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doClick();
                final String email = etEmail.getText().toString();
                if (!isValidEmail(email)) {
                    etEmail.setError("Invalid Email");
                }
            }

        });

        // initiate the date picker and a button
        date = (EditText) findViewById(R.id.EditTextDeparture);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });

        // initiate the date picker and a button
        Arrival = (EditText) findViewById(R.id.EditTextArrival);
        // perform click event on edit text
        Arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                Arrival.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void doClick() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name : ");
        builder.append(etNama.getText().toString());
        builder.append("\n");

        builder.append("Email : ");
        builder.append(etEmail.getText().toString());
        builder.append("\n");

        builder.append("Wilayah Provinsi ");
        builder.append(spProvinsi.getSelectedItem().toString());
        builder.append(" Kota ");
        builder.append(spKota.getSelectedItem().toString());
        builder.append("\n");

        builder.append("Departure : ");
        builder.append(etDeparture.getText().toString());
        builder.append("\n");

        builder.append("Arrival : ");
        builder.append(etArrival.getText().toString());
        builder.append("\n");

        builder.append("\n\n\n");

        tvHasil.setText(builder);


    }
}