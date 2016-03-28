package ro.pub.cs.systems.eim.practicaltest01var02;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;


public class PracticalTest01Var02SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_second);

        final Context context = this;

        final List<EditText> textsList = new LinkedList<EditText>();

        Intent intent = getIntent();

        String stringEditText1 = "";
        String stringEditText2 = "";
        String stringEditText3 = "";
        String stringEditText4 = "";

        final EditText editText1 = (EditText) findViewById(R.id.editTextGS1);
        final EditText editText2 = (EditText) findViewById(R.id.editTextGS2);
        final EditText editText3 = (EditText) findViewById(R.id.editTextGS3);
        final EditText editText4 = (EditText) findViewById(R.id.editTextGS4);

        if (intent != null) {
            stringEditText1 = intent.getStringExtra(Constants.EDIT_TEXT_1);
            stringEditText2 = intent.getStringExtra(Constants.EDIT_TEXT_2);
            stringEditText3 = intent.getStringExtra(Constants.EDIT_TEXT_3);
            stringEditText4 = intent.getStringExtra(Constants.EDIT_TEXT_4);

            editText1.setText(stringEditText1);
            editText2.setText(stringEditText2);
            editText3.setText(stringEditText3);
            editText4.setText(stringEditText4);

            textsList.add(editText1);
            textsList.add(editText2);
            textsList.add(editText3);
            textsList.add(editText4);

        }

        Button sumButton = (Button) findViewById(R.id.sumButton);
        Button productButton = (Button) findViewById(R.id.prodButton);


        sumButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int sum = 0;
                for (int i = 0; i < 3; i++) {
                    sum += Integer.valueOf(textsList.get(i).getText().toString());
                }

                Toast.makeText(context, String.valueOf(sum), Toast.LENGTH_SHORT).show();
            }
        });

        productButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int product = 1;
                for (int i = 0; i < 3; i++) {
                    product *= Integer.valueOf(textsList.get(i).getText().toString());
                }

                Toast.makeText(context, String.valueOf(product), Toast.LENGTH_SHORT).show();
            }
        });

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
}
