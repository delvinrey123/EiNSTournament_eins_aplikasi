package com.delvinstudio.einstournament.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.delvinstudio.einstournament.R;

public class PilihanPubg extends AppCompatActivity {

    //define spinner
    Spinner spinner1, spinner2;
    TextView display_rank, display_kill, hasil_rank, hasil_kill, hasil_point;
    Button hitung_point, hapus_teks;

    //make string array
    String rank[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    String kill[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    //defins array adapter of string type
    ArrayAdapter<String> adapter, adapter2;
    private String record;
    private String record2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_pubg);


        //tombol back atas


        //untuk penjumlahan rank dan kill
        hasil_rank = (TextView) findViewById(R.id.tvDisplayRank);
        hasil_kill = (TextView) findViewById(R.id.tvDisplayKill);
        hitung_point = (Button) findViewById(R.id.btnHasil);
        hapus_teks = (Button) findViewById(R.id.btnHapus);
        hasil_point = (TextView) findViewById(R.id.tvHasilPoint);


        //spinner rank
        spinner1 = (Spinner) findViewById(R.id.spinnerRank);
        //spinner kill
        spinner2 = (Spinner) findViewById(R.id.spinnerKill);
        //adapter dengan list item
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rank);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, kill);
        //memanggil text view display
        display_rank = (TextView) findViewById(R.id.tvDisplayRank);
        display_kill = (TextView) findViewById(R.id.tvDisplayKill);

        //metode adapter ke spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        //ketika spinner rank dipencet
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //use position value
                switch (position) {
                    case 0:
                        record = "20";
                        break;

                    case 1:
                        record = "14";
                        break;

                    case 2:
                        record = "10";
                        break;

                    case 3:
                        record = "8";
                        break;

                    case 4:
                        record = "7";
                        break;

                    case 5:
                        record = "6";
                        break;

                    case 6:
                        record = "5";
                        break;

                    case 7:
                        record = "4";
                        break;

                    case 8:
                        record = "3";
                        break;

                    case 9:
                        record = "2";
                        break;

                    case 10:
                        record = "1";
                        break;

                    case 11:
                        record = "1";
                        break;

                    case 12:
                        record = "1";
                        break;

                    case 13:
                        record = "1";
                        break;

                    case 14:
                        record = "1";
                        break;

                    case 15:
                        record = "1";
                        break;

                    case 16:
                        record = "1";
                        break;

                    case 17:
                        record = "1";
                        break;

                    case 18:
                        record = "1";
                        break;

                    case 19:
                        record = "1";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //ketika spinner kill dipencet
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //use position value
                switch (position) {
                    case 0:
                        record2 = "1";
                        break;

                    case 1:
                        record2 = "2";
                        break;

                    case 2:
                        record2 = "3";
                        break;

                    case 3:
                        record2 = "4";
                        break;

                    case 4:
                        record2 = "5";
                        break;

                    case 5:
                        record2 = "6";
                        break;

                    case 6:
                        record2 = "7";
                        break;

                    case 7:
                        record2 = "8";
                        break;

                    case 8:
                        record2 = "9";
                        break;

                    case 9:
                        record2 = "10";
                        break;

                    case 10:
                        record2 = "11";
                        break;

                    case 11:
                        record2 = "12";
                        break;

                    case 12:
                        record2 = "13";
                        break;

                    case 13:
                        record2 = "14";
                        break;

                    case 14:
                        record2 = "15";
                        break;

                    case 15:
                        record2 = "16";
                        break;

                    case 16:
                        record2 = "17";
                        break;

                    case 17:
                        record2 = "18";
                        break;

                    case 18:
                        record2 = "19";
                        break;

                    case 19:
                        record2 = "20";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //set display rank dan kill atas
    public void displayRank(View view) {
        display_rank.setText(record);
    }

    public void displayKill(View view) {
        display_kill.setText(record2);
    }

    public void hapusTeks(View view) {
        display_rank.setText("");
        display_kill.setText("");
        hasil_point.setText("");
    }

    //sett display total rank dan kill
    public void displayRankKill(View view) {
        try {
            int rank = Integer.parseInt(hasil_rank.getText().toString());
            int kill = Integer.parseInt(hasil_kill.getText().toString());
            int total_rank_kill = rank + kill;

            hasil_point.setText(String.valueOf(total_rank_kill));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}