package com.yuhi.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.yuhi.Constant;
import com.yuhi.ui.ImContentUtil;
/**
 * 视图方式
 * @author 李森林
 *
 */
public class ImContentView extends ViewPart{

	private Text queryText;
	private Text resultTextText;
	private Button queryButton;
    private Group infoGroup;
    private ImContentUtil iu;
    
    @Override
	public void createPartControl(Composite parent) {
    		if(Constant.flag){
    			//聊天界面入口
    			initMsgContents(parent);
    		}else{
    			MessageDialog.openInformation(
    			getSite().getShell(),
    			"注意",
    			"请先正确配置环境变量-->{Window-Perferences-msgsetting}");
    		}
    }

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "ChatRoom";
	}

    /**
     * 初始化内容视图组件区域
     * @param parent
     */
	private void initMsgContents(Composite parent) {
		  iu=new ImContentUtil();
		  Composite composite = new Composite(parent, SWT.HORIZONTAL);
		  RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		  layout.marginBottom =10;
		  layout.marginTop = 10;
		  layout.marginLeft = 10;
		  layout.marginRight = -30;
		  layout.fill=true;
		  layout.justify=true;
		  layout.pack=true;
		  composite.setLayout(layout);
		  composite.setLayoutData(new RowData(400,500));
		  /*headerComposite...*/
		  Composite headerComposite = new Composite(composite, SWT.HORIZONTAL);
		  headerComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		  headerComposite.setLayoutData(new RowData(400,500));
		  //***GROUP START***//
		  infoGroup = new Group(headerComposite, SWT.HORIZONTAL);
		  infoGroup.setText("聊天窗");
		  RowLayout groupLayout = new RowLayout(SWT.HORIZONTAL);
		  groupLayout.marginBottom = 5;
		  groupLayout.marginTop = 10;
		  groupLayout.marginLeft = 10;
		  groupLayout.marginRight = 10;
		  infoGroup.setLayout(groupLayout);
		  infoGroup.setLayoutData(new RowData());
		  infoGroup.pack();
		  resultTextText = new Text(infoGroup, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		  resultTextText.setLayoutData(new RowData(350,300));
		  resultTextText.setText("" + System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
				""+ System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
		  		""+ System.getProperty("line.separator") + 
		  		"");
		  
		  
		  new Label(headerComposite, SWT.HORIZONTAL).setText("  ");
		  queryText = new Text(headerComposite, SWT.BORDER | SWT.MULTI);
		  queryText.setText(iu.getSelecedTextFromEditor());//设置选中的文字
		  queryText.setLayoutData(new RowData(310,45));
		  queryButton = new Button(headerComposite, SWT.HORIZONTAL);
		  queryButton.setLayoutData(new RowData(50,50));
		  queryButton.setText("发送");
		  //控件添加事件
		  iu.addListenerToButton(queryButton, queryText, resultTextText);
		  iu.initCompent();
		  iu.initContentClient(getSite().getShell(), resultTextText);
	}
    
    
    
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		System.out.println("");
	}


}
