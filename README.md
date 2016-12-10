## **[CSDN博客](http://blog.csdn.net/qqqqq210/article/details/53538144)**
## **插件说明**
适用于3.5以上版本的eclipse, 支持嵌入式view与dialog快捷键多方式打开聊天, 中英互翻,灵活配置,让你避开领导的注视,小伙伴们你也赶紧来一个吧~先来一张全局的效果图

![这里写图片描述](http://img.blog.csdn.net/20161209141040363?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFxcXEyMTA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## **插件功能**
1. 基于Eclipse Luna开发的插件  
2. 适用于3.5以上版本的eclipse
3. 基于ServerSocket套接字实现的聊天
4. 基于百度翻译api实现中英互翻
5. 弹窗提醒
6. 快捷键支持
7. view与dialog两种方式支持
8. 自定义配置

## **插件功能说明**


### **后台部署**   [服务端下载](http://download.csdn.net/detail/qqqqq210/9706599)
1.[ 启动 ] : server工程编译运行或者直接下载,
使用一下命令进行运行服务端
```
java -jar server.jar -s -p 11118
[ -s:客户端上线显示 -p 服务侦听端口 ]
```
启动后看见如下文字即为成功
```
C:\Users\李森林\Desktop\server>java -jar server.jar -s -p 11118
服务开启成功！

```
### **插件部署**  [插件下载](http://download.csdn.net/detail/qqqqq210/9707168)
1. **安装 **
> 解压ZIP里面的JAR放在eclipse的plugins文件夹下,重启eclipse.

2. **配置环境变量-->{Window-Perferences-msgsetting}**
![这里写图片描述](http://img.blog.csdn.net/20161209140007834?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFxcXEyMTA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

3.  **使用说明**

>**3.1. 发送普通消息  **
   输入内容回车或者点击发送即可  
   
>**3.2. 翻译(语法：/s 单词或者中文 )  **
输入的语句带有中文都会以中文翻译英文为主,输入纯英文将会以英文翻译成中文
![这里写图片描述](http://img.blog.csdn.net/20161209141245319?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFxcXEyMTA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)  

>**3.3.  弹窗(语法：/g 内容)  **

>**3.4.  启动方式**
>
**①快捷启动dialog形式 CTRl+ALT+M **
![这里写图片描述](http://img.blog.csdn.net/20161209140814634?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFxcXEyMTA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
**②点击工具栏新增的图标**
**③工具菜单  **
**④Window-Show View-Other-curplugin以视图view方式启动**
![这里写图片描述](http://img.blog.csdn.net/20161209140848969?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFxcXEyMTA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


## **项目源码 [GitHub](https://github.com/27786653/ImContent) **
## **项目由来(真实小故事)**
#### **1. 初始需求:  **
打开qq聊天实在太招摇很容易被领导巡视发现导致针对,如果可以在eclipse的一个视图进行聊天多好啊~ 

#### **2. 初始实现 **
>发挥程序员的能力学习，然后赶出了一个初版（实在资料不多或许是我寻找的姿势不太对，哈哈~）初版是一个dialog的形式只能发文字，服务端基于serversocke快速实现,程序员的折腾本性出来了,提出了很多需求  

如下：  
>1.1 这个弹窗不能切换，更麻烦啊，也不能像view一样嵌入到eclipse的工作空间里面

> 1.2 就不能多个翻译吗，难得去百度了，起类名也难，还有我输入中文就翻译英文，输入英文就翻译中文这才方便啊  

> 1.3这个eclipse的消息一点都不醒目，都不能发出什么声音提醒下？  

>1.4 有没有快捷键快速打开啊  

>1.5 能不能多种显示方式  

>1.6 配置可不可以自定义啊，万一以后要开源尼 

### **后续 :** 被人撩起性子就是停不下来,闲暇时间开发，持续了一个星期,踩了很多坑，稍微重构了下，本人不才，如有什么建议可以随时提交lssues,如果喜欢的话劳烦star一个，让更多的人获得便利~

