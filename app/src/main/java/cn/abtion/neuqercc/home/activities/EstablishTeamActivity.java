package cn.abtion.neuqercc.home.activities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.team.activities.EstablishTeamFinishActivity;
import cn.abtion.neuqercc.team.models.EstablishTeamRequest;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author lszr
 * @since 2017/11/20 下午7:16
 * email wsyglszr@gmail.com
 */

public class EstablishTeamActivity extends ToolBarActivity {
    @BindView(R.id.edit_team_name)
    EditText editTeamName;
    @BindView(R.id.edit_want_join)
    TextView editWantJoin;
    @BindView(R.id.edit_declaration)
    EditText editDeclaration;
    @BindView(R.id.edit_want_good_at)
    EditText editWantGoodAt;

    public String contestName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_establish_team_from_home;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        initTitle();

    }

    @Override
    protected void loadData() {

    }


    public void initTitle() {

        Intent intent=getIntent();
        contestName=intent.getStringExtra("contestName");
        editWantJoin.setText(contestName);

        setActivityTitle(getString(R.string.title_establish_team));
        setTextOver(getString(R.string.title_over));
        setOnOverTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EstablishTeamRequest establishTeamRequest = new EstablishTeamRequest(
                        LoginActivity.phoneNumber,
                        editTeamName.getText().toString().trim(),
                        contestName,
                        editDeclaration.getText().toString().trim(),
                        editWantGoodAt.getText().toString().trim()
                );

                if(isInformationCorrect(establishTeamRequest)){

                    Intent intent = new Intent(EstablishTeamActivity.this, EstablishTeamFinishActivity.class);


                    String gson = new Gson().toJson(establishTeamRequest);

                    intent.putExtra("establishTeamRequest", gson);


                    startActivity(intent);
                }

            }
        });

    }

    private boolean isInformationCorrect(EstablishTeamRequest establishTeamRequest) {
        boolean flag = true;
        if (establishTeamRequest.getGood_at().equals(Config.EMPTY_FIELD) ||
                        establishTeamRequest.getDeclaration().equals(Config.EMPTY_FIELD) ||
                        establishTeamRequest.getTeam_name().equals(Config.EMPTY_FIELD)) {
            flag = false;
            ToastUtil.showToast("队伍信息不完整，请检查后补充完整");
        }
        return flag;

    }
    public static void startEstablishActivity(Context context,String contestName){
        Intent intent=new Intent(context,EstablishTeamActivity.class);
        intent.putExtra("contestName",contestName);
        context.startActivity(intent);


    }


}
