package com.hdev.qrcode;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
29 April 2019 Hendriyawan
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text_input)
    EditText inputTextQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BindView
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_generate)
    public void onGenerateQR(View v) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(inputTextQr.getText().toString(), BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            showDisplayQr(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void showDisplayQr(Bitmap bitmap) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.display_qr_code_layout, null, false);

        ImageView qrView = view.findViewById(R.id.qr_view);
        ImageButton dialogClose = view.findViewById(R.id.dialog_close);
        qrView.setImageBitmap(bitmap);
        builder.setView(view);

        final Dialog dialog = builder.create();
        dialog.show();
        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}