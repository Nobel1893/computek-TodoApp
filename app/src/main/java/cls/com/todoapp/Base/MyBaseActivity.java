package cls.com.todoapp.Base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import cls.com.todoapp.R;


/**
 * Created by CLS on 8/1/2018.
 */

public class MyBaseActivity extends AppCompatActivity {

    protected Activity activity;
    protected MaterialDialog dialog;

   public MyBaseActivity(){
     super();
     activity=this;
    }
   public void ShowConfirmationDialoge(String title,String message,String pos,String neg){
       dialog= new MaterialDialog.Builder(activity)
                .title(title)
                .content(message)
                .positiveText(pos)
                .negativeText(neg)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .show();
    }
    public void ShowMessage(String title,String message,String pos){
        dialog= new MaterialDialog.Builder(activity)
                .title(title)
                .content(message)
                .positiveText(pos)

                .show();
    }
    public void ShowProgressBar(){
        dialog=new MaterialDialog.Builder(activity)
                .title("Loading")
                .content("please wait")
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    public void HideProgressBar(){
        if (dialog!=null)
        dialog.dismiss();
    }

    public void ShowHomeAsUpEnabled(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (getIntent().getStringExtra("title")!=null){
           TextView title= findViewById(R.id.title);
           title.setText(getIntent().getStringExtra("title"));
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private final String SHAREDPREFRENCESNAME = "Todo_App";

    public void saveData(String key,String value){
        getSharedPreferences(SHAREDPREFRENCESNAME,MODE_PRIVATE).edit()
                .putString(key,value)
                .apply();

    }
    public String getSavedData(String key , String defValue){
        return getSharedPreferences(SHAREDPREFRENCESNAME,MODE_PRIVATE).getString(key,defValue);
    }
}
