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
    private EditText act_starttime,act_endtime;
    private Context context;

    private CustomDialog_activity.CustomDialogListener customDialogListener;

    public CustomDialog_main(Context context){
        super(context);
        this.context = context;
    }

    interface CustomDialogListener{
        void onPositiveClicked(String active_name, String active_starttime, String active_endtime);
        void onNegativeClicked();
    }

    public void setDialogListener(CustomDialog_activity.CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_dialong_main);

        positiveButton = (Button) findViewById(R.id.madd_button);
        negativeButton = (Button) findViewById(R.id.mcan_button);
        act_name = (EditText) findViewById(R.id.medt_name);
        act_starttime = (EditText) findViewById(R.id.medt_start);
        act_endtime = (EditText) findViewById(R.id.medt_finish);

        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.madd_button:
                String active_name = act_name.getText().toString();
                String active_starttime = act_starttime.getText().toString();
                String active_endtime = act_endtime.getText().toString();

                customDialogListener.onPositiveClicked(active_name, active_starttime, active_endtime);
                dismiss();
                break;
            case R.id.mcan_button:
                cancel();
                break;
        }
    }
}
