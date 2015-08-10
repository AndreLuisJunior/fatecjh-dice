package edu.fatecjh.si.dice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.fatecjh.si.dice.engine.Dice;
import edu.fatecjh.si.dice.engine.DiceManager;
import edu.fatecjh.si.dice.engine.Result;

public class DiceActivity extends AppCompatActivity {

    private Spinner spinner1;
    private ImageButton btnRoll;
    private EditText editText;
    private CheckBox chkBox;
    private Button clearButton;

    private boolean saveHistory = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnRoll = (ImageButton) findViewById(R.id.imageButton1);
        editText = (EditText) findViewById(R.id.editText1);
        chkBox = (CheckBox) findViewById(R.id.checkBox1);
        clearButton = (Button) findViewById(R.id.button1);

        prepareSpinner();
        prepareRollButton();
        prepareHistoryStuff();

    }

    /**
     * Add listener to button
     */
    private void prepareRollButton() {
        final boolean sh = this.saveHistory;
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(spinner1.getSelectedItem());
                Dice d = DiceManager.getDice(name);
                Result r = DiceManager.rollTheDice(d, sh);
                editText.append(new ResultFormatter(r).toString());
            }
        });
    }

    /**
     * Add listener to the Spinner, Fill the Spinner
     */
    private void prepareSpinner() {
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        List<String> list = new ArrayList<>(DiceManager.getDiceNames());
        ArrayAdapter<String> spinnerDataAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, list);
        spinnerDataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerDataAdapter);
    }

    private void prepareHistoryStuff() {
        for (Result r : DiceManager.getHistory()) {
            editText.append(new ResultFormatter(r).toString());
        }

        chkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                saveHistory = isChecked;
            }

        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiceManager.clearHistory();
                editText.clearComposingText();
            }

        });
    }

    /**
     *
     * @author Icaro
     *
     */
    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                   long id) {
            Toast.makeText(
                    parent.getContext(),
                    "OnItemSelectedListener : "
                            + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // Auto-generated method stub
        }

    }

    /**
     *
     * @author Icaro
     *
     */
    public class ResultFormatter {

        private final Result result;

        public ResultFormatter(Result r) {
            result = r;
        }

        public String toString() {
            StringBuilder buffer = new StringBuilder();

            buffer.append(result.getName());
            buffer.append(" - ");

            Iterator<Integer> i = result.iterator();
            buffer.append(i.next());
            while (i.hasNext()) {
                buffer.append(" ");
                buffer.append(i.next());
            }

            int modifier = result.getModifier();
            if (modifier < 0) {
                buffer.append(" -");
                buffer.append(modifier);
            } else if (modifier > 0) {
                buffer.append(" +");
                buffer.append(modifier);
            }

            buffer.append(" = ");
            buffer.append(result.getResult());

            buffer.append("\n");

            return buffer.toString();
        }

    }
}
