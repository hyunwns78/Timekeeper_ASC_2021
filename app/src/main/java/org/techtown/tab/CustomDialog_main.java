package org.techtown.tab;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialog_main extends Dialog implements View.OnClickListener{

    private Button positiveButton;
    private Button negativeButton;
    private EditText act_name;
    private EditText act_start;
    private EditText act_finish;
    private Context context;

    private CustomDialogListener customDialogListener;

    public CustomDialog_main(Context context){
        super(context);
        this.context = context;
    }

    interface CustomDialogListener{
        void onPositiveClicked(String active_name, String start, String finish);
        void onNegativeClicked();
    }

    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_dialong_main);

        positiveButton = (Button) findViewById(R.id.madd_button);
        negativeButton = (Button) findViewById(R.id.mcan_button);
        act_name = (EditText) findViewById(R.id.medt_name);
        act_start = (EditText) findViewById(R.id.medt_start);
        act_finish = (EditText) findViewById(R.id.medt_finish);

        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.madd_button:
                String activ_name = act_name.getText().toString();
                String start = act_start.getText().toString();
                String finish = act_finish.getText().toString();

                customDialogListener.onPositiveClicked(activ_name, start, finish);
                dismiss();
                break;
            case R.id.mcan_button:
                cancel();
                break;
        }
    }
}
