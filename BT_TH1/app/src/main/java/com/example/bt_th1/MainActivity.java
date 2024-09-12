package com.example.bt_th1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button downloadButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.TextView01);
        downloadButton = findViewById(R.id.readwebpage);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadWebPageTask(textView).execute("https://routine.vn/?utm_source=google&utm_medium=cpc&utm_campaign=ON_SEARCH_MASS_BRAND_3.6&utm_content=Brand_Key&utm_keyword=routine&utm_matchtype=b&campaign_id=21349646282&ad_group_id=163867603835&ad_id=701309428010&matchtype=b&device=c&product_partition_id=&adposition=&loc_interest_ms=&loc_physical_ms=9198159&feeditemid=&gad_source=1&gclid=CjwKCAjwooq3BhB3EiwAYqYoErgABhP4aXzlkiIP8qBaGEXCQxAxBm4sf2Do6OVEgQVk6dQjaqWzXxoC_OoQAvD_BwE");
            }
        });
    }
}