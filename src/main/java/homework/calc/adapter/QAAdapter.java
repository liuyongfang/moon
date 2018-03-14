package homework.calc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import homework.calc.R;
import homework.calc.dataType.Formula;

public class QAAdapter extends BaseAdapter {

    private Context context;
    private List<Formula> formulaList;

    public QAAdapter(Context context, List<Formula> formulas) {
        this.context = context;
        this.formulaList = formulas;
    }

    @Override
    public int getCount() {
        return formulaList.size();
    }

    @Override
    public Object getItem(int position) {
        return formulaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder h;
        if (convertView == null) {
            h = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_unit_question, parent, false);
            h.countText = convertView.findViewById(R.id.count_text);
            h.questionText = convertView.findViewById(R.id.question_text);
            h.userAnswer = convertView.findViewById(R.id.user_answer_text);
            h.correctAnswer = convertView.findViewById(R.id.correct_answer_text);
            convertView.setTag(h);
        } else {
            h = (ViewHolder) convertView.getTag();
        }
        h.countText.setText(String.valueOf(position + 1));
        Formula formula = formulaList.get(position);
        h.questionText.setText(formula.getFormulaString());
        h.userAnswer.setText("");
        if (formula.userAnswer != null) {
            h.userAnswer.setText(formula.userAnswer);
        }
        h.correctAnswer.setText("");
        if (formula.correctAnswer != null) {
            h.correctAnswer.setText(formula.correctAnswer);
        }
        return convertView;
    }

    private class ViewHolder {
        TextView countText;
        TextView questionText;
        TextView userAnswer;
        TextView correctAnswer;
    }
}
