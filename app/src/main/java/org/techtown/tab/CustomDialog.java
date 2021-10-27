package org.techtown.tab;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialog extends Dialog implements View.OnClickListener{

    private Button positiveButton;
    private Button negativeButton;
    private EditText act_name;
    private EditText act_time;
    private Context context;

    private CustomDialogListener customDialogListener;

    public CustomDialog(Context context){
        super(context);
        this.context = context;
    }

    interface CustomDialogListener{
        void onPositiveClicked(String active_name, String time);
        void onNegativeClicked();
    }

    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_dialog);

        positiveButton = (Button) findViewById(R.id.add_button);
        negativeButton = (Button) findViewById(R.id.can_button);
        act_name = (EditText) findViewById(R.id.act_name);
        act_time = (EditText) findViewById(R.id.act_hour);

        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_button:
                String activ_name = act_name.getText().toString();
                String activ_time = act_time.getText().toString();

                customDialogListener.onPositiveClicked(activ_name, activ_time);
                dismiss();
                break;
            case R.id.can_button:
                cancel();
                break;
        }
    }
}
