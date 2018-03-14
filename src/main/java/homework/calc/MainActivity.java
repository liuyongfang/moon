package homework.calc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import homework.calc.adapter.QAAdapter;
import homework.calc.dataType.Formula;
import homework.calc.decorator.BracketDecor;
import homework.calc.decorator.DecimalDecor;
import homework.calc.decorator.FractionDecor;
import homework.calc.decorator.MultiAndDivideDecor;
import homework.calc.decorator.NegativeDecor;
import homework.calc.decorator.RemainsDecor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemClickListener {

    @BindView(R.id.check_box_mul_and_divide)
    AppCompatCheckBox checkBoxMulAndDivide;
    @BindView(R.id.check_box_bracket)
    AppCompatCheckBox checkBoxBracket;
    @BindView(R.id.check_box_negative)
    AppCompatCheckBox checkBoxNegative;
    @BindView(R.id.check_box_remains)
    AppCompatCheckBox checkBoxRemains;
    @BindView(R.id.check_box_fraction)
    AppCompatCheckBox checkBoxFraction;
    @BindView(R.id.check_box_decimal)
    AppCompatCheckBox checkBoxDecimal;
    @BindView(R.id.minimum_value)
    EditText minimumValue;
    @BindView(R.id.maximum_value)
    EditText maximumValue;
    @BindView(R.id.line_space)
    SeekBar lineSpace;
    @BindView(R.id.button_generate)
    Button buttonGenerate;
    @BindView(R.id.button_check)
    Button buttonCheck;
    @BindView(R.id.list_questions)
    ListView listQuestions;
    private QAAdapter adapter;
    private List<Formula> formulaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        buttonGenerate.setOnClickListener(this);
        buttonCheck.setOnClickListener(this);
        adapter = new QAAdapter(this, formulaList);
        listQuestions.setAdapter(adapter);
        listQuestions.setOnItemClickListener(this);
        lineSpace.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonGenerate) {
            formulaList.clear();
            for (int i = 0; i < 10; i++) {
                Formula formula = new Formula(
                        Integer.valueOf(minimumValue.getText().toString())
                        , Integer.valueOf(maximumValue.getText().toString()));
                if (checkBoxMulAndDivide.isChecked()) {
                    formula = new MultiAndDivideDecor(formula);
                }
                if (checkBoxBracket.isChecked()) {
                    formula = new BracketDecor(formula);
                }
                if (checkBoxNegative.isChecked()) {
                    formula = new NegativeDecor(formula);
                }
                if (checkBoxRemains.isChecked()) {
                    formula = new RemainsDecor(formula);
                }
                if (checkBoxFraction.isChecked()) {
                    formula = new FractionDecor(formula);
                }
                if (checkBoxDecimal.isChecked()) {
                    formula = new DecimalDecor(formula);
                }
                formulaList.add(formula);
            }
            adapter.notifyDataSetChanged();
        } else if (v == buttonCheck) {
            for (Formula f : formulaList) {
                f.correctAnswer = String.valueOf(f.evaluate());
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        listQuestions.setDividerHeight(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Formula f = formulaList.get(position);
        final EditText et = new EditText(this);
        et.setHint("两位小数");
        et.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        new AlertDialog.Builder(this).setTitle("输入答案")
                .setView(et).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = et.getText().toString();
                if (!s.equals("")) {
                    f.userAnswer = s;
                }
            }
        }).show();
        adapter.notifyDataSetChanged();
    }
}
