package io.keyss.u36.home.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

import io.keyss.base.utils.KeyCommonUtil;
import io.keyss.base.view.BaseFragment;
import io.keyss.base.widget.KeySureCancelDialog;
import io.keyss.u36.R;
import io.keyss.u36.databinding.FragmentSettingsBinding;
import io.keyss.u36.home.data.SettingsData;

/**
 * @author Key
 * Time: 2019/02/20 16:06
 * Description:
 */
public class SettingsFragment extends BaseFragment<FragmentSettingsBinding> {

    private SettingsData settings;
    private SipAccountData sipAccount;
    private BroadcastEventReceiver receiver;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initLayout() {
        settings = new SettingsData();
        binding.setSettings(settings);
        binding.intercept.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    if (settings.isLogin.get()) {
                        new AlertDialog.Builder(mContext)
                                .setTitle("提示")
                                .setMessage("确定要关闭拦截功能吗？\n这会带来风险")
                                .setPositiveButton("确定", (dialog, which) -> {
                                    settings.isLogin.set(false);
                                    Toast.makeText(mContext, "已关闭", Toast.LENGTH_SHORT).show();
                                    unRegister();
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    } else {
                        new AlertDialog.Builder(mContext)
                                .setTitle("提示")
                                .setMessage("使用本功能需要先登陆")
                                .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        settings.isLogin.set(true);
                                        Toast.makeText(mContext, "登陆成功，已打开", Toast.LENGTH_SHORT).show();
                                        registerSip();
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    }
                }
                return true;
            }
        });

        sipAccount = new SipAccountData();
        sipAccount.setHost("47.110.34.64")
                // 拼接在@后面
                .setRealm("47.110.34.64")
                .setUsername("18142009483")
                .setPassword("1234");
        binding.bOnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SipServiceCommand.makeCall(mContext, sipAccount.getIdUri(),
                        "sip:" + binding.etNumber.getText().toString().trim() + "@47.110.34.64");
            }
        });

        binding.bGd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SipServiceCommand.hangUpActiveCalls(mContext, sipAccount.getIdUri());
                KeySureCancelDialog dialog = new KeySureCancelDialog(mContext);
                dialog.show();
            }
        });


    }

    private void registerSip() {
        if (sipAccount.isValid()) {
            SipServiceCommand.start(mContext);
            SipServiceCommand.setAccount(mContext, sipAccount);

            SipServiceCommand.getRegistrationStatus(mContext, sipAccount.getIdUri());

            receiver = new BroadcastEventReceiver() {
                @Override
                public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
                    super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);
                    KeyCommonUtil.logE("accountID: " + accountID +
                            ", callID: " + callID +
                            ", displayName: " + displayName +
                            ", remoteUri: " + remoteUri);
                    new AlertDialog.Builder(mContext)
                            .setNegativeButton("挂断", (dialog, which) -> SipServiceCommand.hangUpCall(mContext, accountID, callID))
                            .setPositiveButton("接听", (dialog, which) -> SipServiceCommand.acceptIncomingCall(mContext, accountID, callID))
                            .setTitle("有来电呼入")
                            .setMessage("accountID: " + accountID +
                                    ", callID: " + callID +
                                    ", displayName: " + displayName +
                                    ", remoteUri: " + remoteUri +
                                    "\n以下录音转文字～～")
                            .show();
                }
            };
            receiver.register(mContext);
        }
    }

    private void unRegister() {
        SipServiceCommand.removeAccount(mContext, sipAccount.getIdUri());
        SipServiceCommand.stop(mContext);
        receiver.unregister(mContext);
    }
}
