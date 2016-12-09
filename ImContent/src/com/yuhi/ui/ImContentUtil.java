package com.yuhi.ui;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

import com.yuhi.Constant;
import com.yuhi.support.fanyi.TransApi;
import com.yuhi.support.net.Client;
import com.yuhi.support.net.Client.MsgCallback;
import com.yuhi.util.MsgUtil;
/**
 * 因为有多个视图公用代码，所以简单抽取一下
 * @author 李森林
 *
 */
public class ImContentUtil {
		private Client client;
		private TransApi api;
		/**
		 * 初始化组件
		 */
		public void initCompent() {
			api = new TransApi(Constant.APP_ID, Constant.SECURITY_KEY);
		}
		/**
		 * 初始化客户端
		 */
		public void initContentClient(final Shell shell,final Text resultTextText) {
			  client= new Client();
			  client.stratUp(new MsgCallback() {
				@Override
				public void getContent(String content) {
					final int type=MsgUtil.GetContentType(content,true);//获取数据类型
					final String parseContent=MsgUtil.GetContentByType(type, content);//根据类型格式化数据
						shell.getDisplay().syncExec(new Runnable() {
		                   @Override
		                   public void run() {
		                	   switch (type) {
				   					case Constant.IM_CONTENT:
				   						if(parseContent!=null&&parseContent.length()>0)
				   					 resultTextText.setText(parseContent+"\n"+resultTextText.getText());
				   						break;
				   					case Constant.ALERT_CONTENT:
				   					 MessageDialog.openInformation(shell,"请注意！",
				   								parseContent==null&&parseContent.length()>0
				   								?"":parseContent
				   								);
				   						break;
				   					}
		                   }
		               });
				}
			  });
		}
		/**
		 * 捆绑侦听
		 * @param queryButton
		 * @param queryText
		 */
		public void addListenerToButton(Button queryButton,final Text queryText,final Text resultTextText){
			queryButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					MessageDao(queryText,resultTextText);
					super.mouseDown(e);
				}
			});
			queryText.addKeyListener(new KeyListener() {
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					switch (e.keyCode) {
					case 13:
						MessageDao(queryText,resultTextText);
						break;
					}
				}
			});
		}
		/**
		 * 消息处理和操作
		 */
		private void MessageDao(Text queryText,Text resultTextText){
			String qtext = queryText.getText();
			if(!MsgUtil.isNotEmptyStr(qtext))return;
			qtext = textrm(queryText.getText());
			int type=MsgUtil.GetContentType(qtext,false);//获取数据类型
			qtext=MsgUtil.GetContentByType(type, qtext);//根据类型格式化数据
			if(MsgUtil.isNotEmptyStr(qtext)){
				switch (type) {
				case Constant.IM_CONTENT:
					client.sendMes(qtext);
					break;
				case Constant.FANYI_CONTENT:
			        String form="zh";
			        if(TransApi.isContainChinese(qtext)){
			        	form="en";
			        }
			        String fresult="";
					try {
						fresult = api.getTransResult(qtext, "auto", form);
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
			        String result=TransApi.parseResult(fresult);
					//设值
					resultTextText.setText("翻译结果是 : "+result+"\n"+resultTextText.getText());
					break;
				case Constant.ALERT_CONTENT:
					client.sendMes(qtext);
					break;
				}
			}else resultTextText.setText("数据异常！");
			queryText.setText("");
		}
		/**
		 * 解决windows平台换行符影响
		 * @param qtext
		 * @return
		 */
		private String textrm(String qtext){
//			qtext.replace("\r\n","")
			if(MsgUtil.isNotEmptyStr(qtext)){
				Pattern p = Pattern.compile("\\s*|t|r|n");
				Matcher m = p.matcher(qtext);
				return m.replaceAll("");
			}else return "";
		}
		/**
		 * 释放资源
		 */
		public void close(){
			api=null;
			client.closeContentThread();
		}
		/**
		 * 文本选中
		 * @return
		 */
		public  String getSelecedTextFromEditor() {
			String selectedText = "";
			try {               
			    IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			    if ( part instanceof ITextEditor ) {
			        final ITextEditor editor = (ITextEditor)part;
			        ISelection sel = editor.getSelectionProvider().getSelection();
			        if (sel instanceof TextSelection ) {
			            TextSelection textSel = (TextSelection)sel;
			            selectedText = textSel.getText();
			        }
			    }
			} catch ( Exception ex ) {
			    ex.printStackTrace();
			}
			return selectedText;
		}
		
}
