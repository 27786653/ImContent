package com.yuhi.util;

import com.yuhi.Constant;
/**
 * 消息处理
 * @author 李森林
 *
 */
public class MsgUtil {

	
	
	/**
	 * 获取消息类型
	 * @param content
	 * @return
	 */
	public static int GetContentType(String content,boolean isCallback){
		int index=content.indexOf("/");
		if(index!=-1){
			char type=content.substring(index+1, index+2).charAt(0);
			switch (type) {
				case 's':
					return Constant.FANYI_CONTENT;
				case 'g':
					if(!isCallback)return Constant.ALERT_CONTENT;
					String nick=content.substring(content.indexOf("(")+1, content.indexOf(")"));
					if(nick==null||nick.length()<=0||nick.equals(Constant.NICK_NAME))return 0;
					else return Constant.ALERT_CONTENT;
			default:
				return Constant.IM_CONTENT;
			}
		}else{
			return Constant.IM_CONTENT;
		}
	}
	
	/**
	 * 根据消息类型获取消息
	 * @param msg
	 * @param content
	 * @return
	 */
	public static String GetContentByType(int msg,String content){
		switch (msg) {
		case Constant.ALERT_CONTENT:
		case Constant.IM_CONTENT:
			return content;
		case Constant.FANYI_CONTENT:
			return content.substring(2);
		}
		return null;
	}
	/**
	 * 判断字符串
	 * @param str
	 * @return
	 */
	public static boolean isNotEmptyStr(String str){
		return str!=null&&str.length()>0;
	}
}
