package cls.com.todoapp.Base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by CLS on 8/8/2018.
 */

public class MyBaseFragment extends Fragment {

    protected MyBaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(MyBaseActivity) context;

    }

    public void ShowConfirmationDialoge(String title,String message,String pos,String neg){
        activity.ShowConfirmationDialoge(title,message,pos,neg);
    }

    public void ShowProgressBar(){
       activity.ShowProgressBar();
    }

    public void HideProgressBar(){
        activity.HideProgressBar();
    }
    public void ShowMessage(String title,String message,String pos){
        activity.ShowMessage(title,message,pos);
    }
}
