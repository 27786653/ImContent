package com.yuhi.actions;


import imcontent.Activator;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.yuhi.Constant;
import com.yuhi.util.MsgUtil;
/**
 * 全局环境配置
 * @author 李森林
 *
 */
public class RootPreferencePage extends PreferencePage implements IWorkbenchPreferencePage , ModifyListener {
	
    
    private Text nick, clientip, clientport,appid,securitykey;
    private  IPreferenceStore ps;
	
    public void init(IWorkbench workbench) {
    	setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }
    protected Control createContents(Composite parent) {
    	 Composite topComp = new Composite(parent, SWT.NONE);
         topComp.setLayout(new GridLayout(2, false));

         // 创建三个文本框及其标签
         new Label(topComp, SWT.NONE).setText("昵称:");
         nick = new Text(topComp, SWT.BORDER);
         nick.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

         new Label(topComp, SWT.NONE).setText("服务端IP:");
         clientip = new Text(topComp, SWT.BORDER);
         clientip.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
         
         new Label(topComp, SWT.NONE).setText("端口:");
         clientport = new Text(topComp, SWT.BORDER | SWT.PASSWORD);
         clientport.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
         
         new Label(topComp, SWT.NONE).setText("百度翻译APPID:");
         appid = new Text(topComp, SWT.BORDER | SWT.PASSWORD);
         appid.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
         
         new Label(topComp, SWT.NONE).setText("百度翻译SECURITYKEY:");
         securitykey = new Text(topComp, SWT.BORDER | SWT.PASSWORD);
         securitykey.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
         
         // 取出以前保存的值，并设置到文本框中。如果取出值为空值或空字串，则填入默认值。
         ps = getPreferenceStore();// 取得一个IPreferenceStore对象
         String nicktext = ps.getString(Constant.NICK_KEY);
         if (!MsgUtil.isNotEmptyStr(nicktext))
        	 nick.setText("");//可以赋值默认值
         else
        	 nick.setText(nicktext);

         String clientiptext = ps.getString(Constant.CLIENT_IP_KEY);
         if (!MsgUtil.isNotEmptyStr(clientiptext))
        	 clientip.setText("");
         else
        	 clientip.setText(clientiptext);

         String clientporttext = ps.getString(Constant.CLIENT_PORT_KEY);
         if (!MsgUtil.isNotEmptyStr(clientporttext))
        	 clientport.setText("");
         else
        	 clientport.setText(clientporttext);
         
         String appidtext = ps.getString(Constant.APP_ID_KEY);
         if (!MsgUtil.isNotEmptyStr(appidtext))
        	 appid.setText("");
         else
        	 appid.setText(appidtext);
         
         String securitykeytext = ps.getString(Constant.SECURITY_KEY_KEY);
         if (!MsgUtil.isNotEmptyStr(securitykeytext))
        	 securitykey.setText("");
         else
        	 securitykey.setText(securitykeytext);
         // 添加事件监听器。this代表本类，因为本类实现了ModifyListener接口成了监听器
         nick.addModifyListener(this);
         clientip.addModifyListener(this);
         clientport.addModifyListener(this);
         appid.addModifyListener(this);
         securitykey.addModifyListener(this);
         return topComp;
    }

	@Override
	public void modifyText(ModifyEvent e) {
		 	String errorStr = null;// 将原错误信息清空
		        if (nick.getText().trim().length() == 0) {
		            errorStr = "昵称不能为空！";
		        } else if (clientip.getText().trim().length() == 0) {
		            errorStr = "服务端IP不能为空！";
		        } else if (clientport.getText().trim().length() == 0) {
		            errorStr = "端口不能为空！";
		        } else if (appid.getText().trim().length() == 0) {
		            errorStr = "百度翻译APPID不能为空！";
		        } else if (securitykey.getText().trim().length() == 0) {
		            errorStr = "百度翻译SECURITYKEY不能为空！";
		        }
	        setErrorMessage(errorStr);// errorStr=null时复原为正常的提示文字
	        setValid(errorStr == null);// “确定”按钮
	        getApplyButton().setEnabled(errorStr == null);// “应用”按钮
	}
	
	// 父类方法。单击“应用”按钮时执行此方法，将文本框值保存并弹出成功的提示信息
    protected void performApply() {
        doSave(); // 自定义方法，保存设置
        //MessageDialog.openInformation(getShell(), "信息", "成功保存修改!");
    }

	
	
	// 父类方法。单击“复原默认值”按钮时将执行此方法，取出默认值设置到文本框中
    protected void performDefaults() {
    	nick.setText("");
    	clientip.setText("");
    	clientport.setText("");
    	appid.setText("");
    	securitykey.setText("");
    }
	
 // 父类方法。单击“确定”按钮时执行此方法，将文本框值保存并弹出成功的提示信息
    public boolean performOk() {
        doSave();
        MessageDialog.openInformation(getShell(), "信息", "配置将在eclipse重启后生效！");
        return true; // true表示成功退出
    }
    
 // 自定义方法。保存文本框的值
    private void doSave() {
//    	String nicks = nick.getText();
//    	String cs = clientip.getText();
//    	String cps = clientport.getText();
//    	String apid = appid.getText();
//        String seckey = securitykey.getText();
//        System.out.println("");
        ps.setValue(Constant.NICK_KEY, nick.getText());
        ps.setValue(Constant.CLIENT_IP_KEY, clientip.getText());
        ps.setValue(Constant.CLIENT_PORT_KEY, clientport.getText());
        ps.setValue(Constant.APP_ID_KEY, appid.getText());
        ps.setValue(Constant.SECURITY_KEY_KEY, securitykey.getText());
    }
	
	
	
	
}