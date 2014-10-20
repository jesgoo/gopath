package org.dong.proto.util.email;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class EmailHelper {
	
	String sender = "jbdong@travelsky.com";
	String senderName = "中国航信";
	//邮件的配置
	String smtp = "smtpav.travelsky.com";
	final String username = sender;
	final String password = "123";
	
	boolean isDebug = false;//显示debug信息
	
	private EmailHelper() {
	}
	
	public static EmailHelper init(){
		return new EmailHelper();
	}
	

	/**
	 * 发送邮件
	 * @param subject		主题
	 * @param content		正文
	 * @param receivers		收件人
	 * @param attchs		附件
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public void send( String subject,String content, String receiver) throws MessagingException, AddressException,
			UnsupportedEncodingException {
		//收件人
		List<String> receivers = new ArrayList<String>();
		receivers.add(receiver);
		this.send(sender, senderName, subject, content, receivers, null, null,
				smtp, username, password);
	}

	/**
	 * 发送邮件
	 * @param subject		主题
	 * @param content		正文
	 * @param receivers		收件人
	 * @param attchs		附件
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public void send( String subject,String content, String receiver, String attch) throws MessagingException, AddressException,
			UnsupportedEncodingException {
		//收件人
		List<String> receivers = new ArrayList<String>();
		receivers.add(receiver);
		//附件
		List<String> attchs = new ArrayList<String>();
		attchs.add(attch);
		this.send(sender, senderName, subject, content, receivers, null, attchs,
				smtp, username, password);
	}
	
	/**
	 * 发送邮件
	 * @param subject		主题
	 * @param content		正文
	 * @param receivers		收件人
	 * @param ccs			抄送人
	 * @param attchs		附件
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public void send( String subject,String content, List<String> receivers, List<String> ccs, List<String> attchs) throws MessagingException, AddressException,
			UnsupportedEncodingException {
		this.send(sender, senderName, subject, content, receivers, ccs, attchs,
				smtp, username, password);
	}
	
	/**
	 * 发送邮件
	 * @param sender		发件人
	 * @param senderName	发件人的别名
	 * @param subject		主题
	 * @param content		正文
	 * @param receivers		收件人
	 * @param ccs			抄送人
	 * @param attchs		附件
	 * @param smtp			邮件服务器地址
	 * @param username		发件人账号
	 * @param password		发件人密码
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public void send(String sender, String senderName, String subject,
			String content, List<String> receivers, List<String> ccs,
			List<String> attchs, String smtp, final String username,
			final String password) throws MessagingException, AddressException,
			UnsupportedEncodingException {
		/**
		 * 创建会话，Properties设置会话参数
		 * 将权限验证信息添加在session中
		 */
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");//需要权限认证
		props.setProperty("mail.transport.protocol", "smtp");//传输协议为smtp
		props.setProperty("mail.host", smtp);//smtp的地址
		Session session = Session.getInstance(props,
				new Authenticator()
				{
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(username,password);
					}
				}
		);
		session.setDebug(isDebug);//调试信息
		
		/**
		 * 创建邮件
		 */
		Message msg = new MimeMessage(session);//邮件需要和会话session绑定
		msg.setFrom(new InternetAddress("\"" + MimeUtility.encodeText(senderName) + "\" <"+sender+">"));//发件人
		
		//添加收件人
		msg.setRecipients(RecipientType.TO, InternetAddress.parse(getReceivers(receivers)));
		//添加抄送人
		if (null != ccs && ccs.size() > 0) {
			msg.setRecipients(RecipientType.CC, InternetAddress.parse(getReceivers(ccs)));
		}
		//添加主题
		msg.setSubject(subject);
		
		//添加邮件内容
		msg.setContent(createContent(content, attchs));
		
		//保存邮件
		msg.saveChanges();
		
		
		/**
		 * 直接调用静态方法发送邮件
		 * 会自动创建和关闭连接
		 */
		Transport.send(msg);
	}


	/**
	 * 创建邮件内容，包括正文和附件
	 * @param content	正文
	 * @param attchs	附件
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	private MimeMultipart createContent(String content, List<String> attchs)
			throws MessagingException, UnsupportedEncodingException {
		
		MimeMultipart msgMultipart = new MimeMultipart("mixed");//定义结构体，类型为复合类型
		//添加正文
		msgMultipart.addBodyPart(createContentPart(content));//添加正文
		//添加附件
		if (null != attchs && attchs.size() > 0) {
			for(String path : attchs){
				msgMultipart.addBodyPart(createAttchPart(path));
			}
		}
		
		
		return msgMultipart;
	}


	/**
	 * 解析收件人
	 * @param receivers
	 * @return
	 */
	private String getReceivers(List<String> receivers) {
		StringBuffer recStr = new StringBuffer();
		for(String r : receivers){
			recStr.append(r).append(",");
		}
		return recStr.toString();
	}


	/**
	 * 创建正文
	 * @param content
	 * @return
	 * @throws MessagingException
	 */
	private MimeBodyPart createContentPart(String content)
			throws MessagingException {
		MimeBodyPart contentPart = new MimeBodyPart();//正文部分
		contentPart.setContent(content, "text/html;charset=gbk");
		return contentPart;
	}


	/**
	 * 创建附件
	 * @param attchPath	附件文件的路径"e:/1.png";
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	private MimeBodyPart createAttchPart(String attchPath)
			throws MessagingException, UnsupportedEncodingException {
		MimeBodyPart attch = new MimeBodyPart();
		DataSource ds1 = new FileDataSource(attchPath);
		DataHandler dh1 = new DataHandler(ds1 );
		attch.setDataHandler(dh1);
		attch.setFileName(MimeUtility.encodeText(attchPath.substring(attchPath.lastIndexOf("/"))));
		return attch;
	}
}
