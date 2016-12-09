package com.yuhi;

import imcontent.Activator;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * 系统内部环境变量
 * @author 李森林
 *
 */
public class Constant {
	/**
	 * CLIENT
	 */
	public static String NICK_NAME="";
	public static  String CLIENT_IP="";
	public static  Integer CLIENT_PORT=0;
	/**
	 * 消息类型
	 */
	public static final int IM_CONTENT=1;
	public static final int FANYI_CONTENT=2;
	public static final int ALERT_CONTENT = 3;
	/**
	 * 百度翻译
	 */
	// 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
	public static String APP_ID = "";
	public static String SECURITY_KEY = "";
	/**
	 * 全部配置KEY
	 */
    public static final String NICK_KEY = "$NICK_NAME";
    public static final String CLIENT_IP_KEY = "$CLIENT_IP_KEY";
    public static final String CLIENT_PORT_KEY = "$CLIENT_PORT_KEY";
    public static final String APP_ID_KEY = "$APP_ID_KEY";
    public static final String SECURITY_KEY_KEY = "$SECURITY_KEY_KEY";
	/**
	 * 全局配置
	 * 
	 */
    public static boolean flag=false;
    /**
     * 初始化全局配置
     */
    static{
    	if(!Constant.flag){
			IPreferenceStore ps=Activator.getDefault().getPreferenceStore();
			Constant.NICK_NAME=ps.getString(Constant.NICK_KEY);
			Constant.CLIENT_IP=ps.getString(Constant.CLIENT_IP_KEY);
			Constant.CLIENT_PORT=ps.getInt(Constant.CLIENT_PORT_KEY);
			Constant.APP_ID=ps.getString(Constant.APP_ID_KEY);
			Constant.SECURITY_KEY=ps.getString(Constant.SECURITY_KEY_KEY);
			Constant.flag=Constant.NICK_NAME!=null&&Constant.CLIENT_IP!=null&&Constant.CLIENT_PORT!=0&&Constant.CLIENT_PORT!=null&&Constant.APP_ID!=null&&Constant.SECURITY_KEY!=null;
		}
    }
	
	
}
