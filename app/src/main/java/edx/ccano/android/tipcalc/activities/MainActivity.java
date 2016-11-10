package edx.ccano.android.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import butterknife.OnClick;
import edx.ccano.android.tipcalc.Models.TipRecord;
import edx.ccano.android.tipcalc.R;
import edx.ccano.android.tipcalc.TipCalcApp;

import butterknife.Bind;
import butterknife.ButterKnife;
import edx.ccano.android.tipcalc.fragments.TipHistoryListFragment;
import edx.ccano.android.tipcalc.fragments.TipHistoryListFragmentListener;

public class MainActivity extends AppCompatActivity {

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        //guarda la instancia para que los valores no sean reainiciados cuando se camibia la configuracion/rota pantalla etc...
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about){
           about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleClicklSubmit() {
        hideKeyBoard();

        String strInputString = inputBill.getText().toString().trim();

        if (!strInputString.isEmpty()) {
            double total = Double.parseDouble(strInputString);
            int tipPercentage = getTipPercentage();

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());

            String strTip = String.format(getString(R.string.global_message_tip), tipRecord.getTip());
            String strDate = tipRecord.getDateFormated();
            fragmentListener.addList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
        fragmentListener.clearList();
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease() {
        hideKeyBoard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease() {
        hideKeyBoard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    private void handleTipChange(int change) {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if (tipPercentage > 0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }

    private int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        if (!strInputTipPercentage.isEmpty()){
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        } else {
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }

    private void hideKeyBoard(){
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e){
            Log.e(getLocalClassName(), Log.getStackTraceString(e));
        }
    }
    private void about() {
        TipCalcApp app = (TipCalcApp)getApplication();
        String strURL = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strURL));
        startActivity(intent);

    }
}
