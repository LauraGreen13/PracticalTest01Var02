package ro.pub.cs.systems.eim.practicaltest01var02;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;


public class PracticalTest01Var02MainActivity extends Activity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;

    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;
    private IntentFilter startedServiceIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_main);

        final Context context = this;

        editText1 = (EditText) findViewById(R.id.editTextG1);
        editText2 = (EditText) findViewById(R.id.editTextG2);
        editText3 = (EditText) findViewById(R.id.editTextG3);
        editText4 = (EditText) findViewById(R.id.editTextG4);
        if ((savedInstanceState != null) && (savedInstanceState.containsKey(Constants.EDIT_TEXT_1))
                && (savedInstanceState.containsKey(Constants.EDIT_TEXT_2))
                && (savedInstanceState.containsKey(Constants.EDIT_TEXT_3))
                && (savedInstanceState.containsKey(Constants.EDIT_TEXT_4))) {
            editText1.setText(savedInstanceState.getString(Constants.EDIT_TEXT_1));
            editText2.setText(savedInstanceState.getString(Constants.EDIT_TEXT_2));
            editText3.setText(savedInstanceState.getString(Constants.EDIT_TEXT_3));
            editText4.setText(savedInstanceState.getString(Constants.EDIT_TEXT_4));
        }


        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver();
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction(Constants.RANDOM_NUMBER);

        final List<EditText> textsList = new LinkedList<EditText>();
        textsList.add(editText1);
        textsList.add(editText2);
        textsList.add(editText3);
        textsList.add(editText4);


        Button setButton = (Button) findViewById(R.id.setButton);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regexStr = "^[0-9]*$";
                boolean isNumber = true;
                for (int i = 0; i < 3; i++) {
                    if (!textsList.get(i).getText().toString().trim().matches(regexStr)) {
                        isNumber = false;
                    }
                }

                if (isNumber) {
                    Intent intent = new Intent(context, PracticalTest01Var02SecondActivity.class);
                    intent.putExtra(Constants.EDIT_TEXT_1, editText1.getText().toString());
                    intent.putExtra(Constants.EDIT_TEXT_2, editText2.getText().toString());
                    intent.putExtra(Constants.EDIT_TEXT_3, editText3.getText().toString());
                    intent.putExtra(Constants.EDIT_TEXT_4, editText4.getText().toString());

                    startActivity(intent);
                } else {
                    Toast.makeText(context, "NEED NUMBERS", Toast.LENGTH_SHORT).show();
                }

            }
        });



        Button startServiceButton = (Button) findViewById(R.id.startServiceButton);
        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startServiceIntent = new Intent(context, PracticalTest01Var02Service.class);
                startService(startServiceIntent);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }


    @Override
    protected void onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver);
        super.onPause();
    }



    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(Constants.EDIT_TEXT_1, editText1.getText().toString());
        savedInstanceState.putString(Constants.EDIT_TEXT_2, editText2.getText().toString());
        savedInstanceState.putString(Constants.EDIT_TEXT_3, editText3.getText().toString());
        savedInstanceState.putString(Constants.EDIT_TEXT_4, editText4.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if ((savedInstanceState != null) && (savedInstanceState.containsKey(Constants.EDIT_TEXT_1))
                && (savedInstanceState.containsKey(Constants.EDIT_TEXT_2))
                && (savedInstanceState.containsKey(Constants.EDIT_TEXT_3))
                && (savedInstanceState.containsKey(Constants.EDIT_TEXT_4))) {
            editText1.setText(savedInstanceState.getString(Constants.EDIT_TEXT_1));
            editText2.setText(savedInstanceState.getString(Constants.EDIT_TEXT_2));
            editText3.setText(savedInstanceState.getString(Constants.EDIT_TEXT_3));
            editText4.setText(savedInstanceState.getString(Constants.EDIT_TEXT_4));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var02_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

        public StartedServiceBroadcastReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String data = null;

            if (data != null) {
                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();

                editText1.setText(String.valueOf(intent.getIntExtra(Constants.RANDOM_NUMBER, 0)));
                editText2.setText(String.valueOf(intent.getIntExtra(Constants.RANDOM_NUMBER, 0)));
                editText3.setText(String.valueOf(intent.getIntExtra(Constants.RANDOM_NUMBER, 0)));
                editText4.setText(String.valueOf(intent.getIntExtra(Constants.RANDOM_NUMBER, 0)));

            } else {
//                Intent startedServiceActivityIntent = new Intent(context, PracticalTest01MainActivity.class);
//                startedServiceActivityIntent.putExtra(Constants.DATA, data);
//                startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                context.startActivity(startedServiceActivityIntent);

            }


        }
    }

}
