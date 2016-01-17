package ieee.app_001_led;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import ieee.hardlibrary.*;

public class MainActivity extends ActionBarActivity {
    private  boolean ledOn = false;
    private Button bn1 = null;
    private CheckBox checkBox1 = null;
    private CheckBox checkBox2 = null;
    private CheckBox checkBox3 = null;
    private CheckBox checkBox4 = null;

    class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            HardControl hardControl = new HardControl();

            ledOn = !ledOn;
            if (ledOn) {
                bn1.setText("ALL ON");
                checkBox1.setChecked(true);
                checkBox2.setChecked(true);
                checkBox3.setChecked(true);
                checkBox4.setChecked(true);

                HardControl.ledCtrl(0,1);
                HardControl.ledCtrl(1,1);

            } else {
                bn1.setText("ALL OFF");
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);

                HardControl.ledCtrl(0, 0);
                HardControl.ledCtrl(1,0);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView tx1 = (TextView) findViewById(R.id.text1);
        tx1.setText("LED text ! ");

        //打开led
        //HardControl.ledOpen();

        bn1 = (Button) findViewById(R.id.bn1);

        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkbox4);

        bn1.setOnClickListener(new MyButtonListener());

    }

    public void onCheckboxClicked(View view){

        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){

            case R.id.checkbox1:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED1 ON",Toast.LENGTH_SHORT).show();
                    //HardControl.ledCtrl(0,1);
                } else {
                    Toast.makeText(getApplicationContext(),"LED1 OFF",Toast.LENGTH_SHORT).show();
                    //HardControl.ledCtrl(0, 0);
                }
                break;

            case R.id.checkbox2:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED2 ON",Toast.LENGTH_SHORT).show();
                    //HardControl.ledCtrl(1,1);

                } else {
                    Toast.makeText(getApplicationContext(),"LED2 OFF",Toast.LENGTH_SHORT).show();
                    //HardControl.ledCtrl(1,0);
                }
                break;

            case R.id.checkbox3:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED3 ON",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"LED3 OFF",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.checkbox4:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED4 ON",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"LED4 OFF",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
