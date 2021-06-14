package com.example.cipboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    Button btnCopyData;
    TextInputLayout tilData;
    TextView txtResult;
    private final String CLIPBOARD_LABEL = "CLIPBOARD_LABEL";
    private ClipboardManager clipboardManager;
    StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCopyData = findViewById(R.id.btnCopyData);
        tilData = findViewById(R.id.tilData);
        txtResult = findViewById(R.id.txtResult);

        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        btnCopyData.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String data = tilData.getEditText().getText().toString();
                if (!TextUtils.isEmpty(data)) {
                    ClipData clip = ClipData.newPlainText(CLIPBOARD_LABEL, data);
                    clipboardManager.setPrimaryClip(clip);

                    for (int i = 0; i < clipboardManager.getPrimaryClip().getItemCount(); i++) {
                        stringBuilder.append(clipboardManager.getPrimaryClip().getItemAt(i) + "\n");
                    }

                    txtResult.setText(stringBuilder.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Filed is empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}