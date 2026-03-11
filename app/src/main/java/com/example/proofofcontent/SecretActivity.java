package com.example.proofofcontent;

import android.os.Bundle;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import org.w3c.dom.Text;

public class SecretActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secret);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String deadString1 = "This string is never used in the application logic.";
        String deadString2 = "Another piece of useless data.";
        int deadResult = 0;

        // Condicional sempre falsa que contém mais código inútil
        if (5 == 7) {
            deadResult = 100 + 500;
        } else {
            // Este bloco sempre é executado, mas a lógica é inútil
            long startTime = System.nanoTime();
            for (int i = 0; i < 50; i++) {
                deadResult += i * 2; // Acumulação inútil
            }
            long endTime = System.nanoTime();
            // A variável deadResult é sobrescrita e nunca usada
            deadResult = (int) (endTime - startTime);
        }

        Intent receivedIntent = getIntent();
        String sharedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
        if(sharedText!=null){
            TextView debugText = findViewById(R.id.debug_text);
            debugText.setText("Shared: "+sharedText);
         }
    }
}