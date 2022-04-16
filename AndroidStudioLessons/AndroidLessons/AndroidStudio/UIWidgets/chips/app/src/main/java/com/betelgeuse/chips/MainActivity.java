package com.betelgeuse.chips;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ChipGroup chipGroupA;
    ChipGroup chipGroupB;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chipGroupA = findViewById(R.id.chipGroup);
        chipGroupA.setSingleSelection(true);
        chipGroupB = findViewById(R.id.chipGroupB);
        chipGroupB.setSingleSelection(true);

        List<Integer> checkedChipIds = chipGroupA.getCheckedChipIds();


        chipGroupA.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (ChipGroup group, int checkedId) {
                //chipGroupB ile buradaki group değişkeni aynı!
                Log.e("TAG", "ChipGroup : " + group.toString() + "checkedId : " + String.valueOf(checkedId).toString());
                Chip chip = (Chip) group.findViewById(checkedId);
                if (chip != null && chip.isChecked()) {
                    Toast.makeText(MainActivity.this, chip.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                if (chip == null) {
                    Toast.makeText(MainActivity.this, "something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

     /*
        chipGroupA.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (ChipGroup group, int checkedId) {
                for (int i = 0; i <chipGroupA.getChildCount() ; i++) {
                    Chip chip = (Chip) group.getChildAt(i);
                    if (chip.isChecked()){
                        Toast.makeText(MainActivity.this,chip.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
       */
        /*---------------------------------------------------------------------*/
      /*  chipGroupB.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (ChipGroup group, int checkedId) {
                Chip chip = (Chip) group.getChildAt(checkedId);
                if (chip.isChecked()) {
                    Toast.makeText(MainActivity.this,chip.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }); */
        chipGroupB.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (ChipGroup group, int checkedId) {
                for (int i = 0; i < chipGroupB.getChildCount(); i++) {
                    Chip chip = (Chip) group.getChildAt(i);
                    if (chip.isChecked()) {
                        Toast.makeText(MainActivity.this, chip.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}