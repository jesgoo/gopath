//package org.dong.proto.util.email;
//
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.Address;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.Message.RecipientType;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.internet.MimeUtility;
//
//import org.junit.Test;
//
//public class EmailTest {
//	
//	
//	@Test
//	/**
//	 * 最普通的发送邮件方式
//	 * 邮件内容是纯文本
//	 */
//	public void test1() throws Exception{
//		/**
//		 * 创建会话，Properties设置会话参数,
//		 */
//		Properties props = new Properties();
//		props.setProperty("mail.smtp.auth", "true");//需要权限认证
//		props.setProperty("mail.transport.protocol", "smtp");//传输协议为smtp
//		Session session = Session.getInstance(props);
//		session.setDebug(true);//调试信息
//		
//		/**
//		 * 创建邮件
//		 */
//		Message msg = new MimeMessage(session);//邮件需要和会话session绑定
//		msg.setFrom(new InternetAddress("jbdong@travelsky.com"));//发件人
//		msg.setSubject("邮件测试");//主题
//		msg.setText("你好吗？");//正文
//		
//	
//		/**
//		 * 创建发送器，用于发送邮件
//		 */
//		Transport transport = session.getTransport();
//		transport.connect("smtpav.travelsky.com", 25, "jbdong@travelsky.com", "123");//权限验证并建立连接
//		//添加收件人，并发送邮件
//		transport.sendMessage(msg,
//				new Address[]{new InternetAddress("jbdong@travelsky.com")
//				,new InternetAddress("dongjibo520@163.com")});
//		//关闭连接
//		transport.close();
//	}
//	
//	
//	@Test
//	/**
//	 * 将权限验证添加到session中，
//	 * 这样发送邮件的时候，就可以使用Transport.send(msg);的静态方法了
//	 * 收件人等也放在邮件中，使得邮件的内容更加完善
//	 * 添加多个抄送人或者收件人的方法
//	 * msg.setRecipients(RecipientType.CC, InternetAddress.parse("dongjibo520@163.com,dongjibo1986@163.com"));//抄送人
//	 * 但是这样会使得邮件体积比较大，如果要发送给大批量的收件人的话，
//	 * 还是选择在发送器中添加发件人
//	 * Transport.send(msg,InternetAddress.parse("itcast_test@sohu.com"));
//	 */
//	public void test2()throws Exception{
//		
//		/**
//		 * 创建会话，Properties设置会话参数
//		 * 将权限验证信息添加在session中
//		 */
//		Properties props = new Properties();
//		props.setProperty("mail.smtp.auth", "true");//需要权限认证
//		props.setProperty("mail.transport.protocol", "smtp");//传输协议为smtp
//		props.setProperty("mail.host", "smtpav.travelsky.com");//smtp的地址
//		Session session = Session.getInstance(props,
//				new Authenticator()
//				{
//					protected PasswordAuthentication getPasswordAuthentication()
//					{
//						return new PasswordAuthentication("jbdong","123");
//					}
//				}
//		);
//		session.setDebug(true);//调试信息
//		
//		/**
//		 * 创建邮件
//		 */
//		Message msg = new MimeMessage(session);//邮件需要和会话session绑定
//		msg.setFrom(new InternetAddress("jbdong@travelsky.com"));//发件人
//		msg.setRecipient(RecipientType.TO, new InternetAddress("jbdong@travelsky.com"));//收件人
//		msg.setRecipients(RecipientType.CC, InternetAddress.parse("dongjibo520@163.com,dongjibo1986@163.com"));//抄送人
//		msg.setSubject("邮件测试");//主题
//		msg.setText("你好吗？");//正文
//		
//		/**
//		 * 直接调用静态方法发送邮件
//		 * 会自动创建和关闭连接
//		 */
//		Transport.send(msg);
//		
//	}
//	
//	
//	@Test
//	/**
//	 * 发送带附件的复杂邮件
//	 * 1、给发件人添加别名，抄送人的别名同理
//	 * 由于stmp协议不支持中文，所以需要先包装中文字符MimeUtility.encodeText("中国航信")
//	 * msg.setFrom(new InternetAddress("\"" + MimeUtility.encodeText("中国航信") + "\" <jbdong@travelsky.com>"));//发件人
//	 * 2、邮件内容为复杂结构的组合体
//	 * 	msgMultipart.addBodyPart(attch1);//添加附件1		
//		msgMultipart.addBodyPart(attch2);//添加附件2		
//		msgMultipart.addBodyPart(content);//添加正文
//	 */
//	public void test3() throws Exception{
//
//		/**
//		 * 创建会话，Properties设置会话参数
//		 * 将权限验证信息添加在session中
//		 */
//		Properties props = new Properties();
//		props.setProperty("mail.smtp.auth", "true");//需要权限认证
//		props.setProperty("mail.transport.protocol", "smtp");//传输协议为smtp
//		props.setProperty("mail.host", "smtpav.travelsky.com");//smtp的地址
//		Session session = Session.getInstance(props,
//				new Authenticator()
//				{
//					protected PasswordAuthentication getPasswordAuthentication()
//					{
//						return new PasswordAuthentication("jbdong","123");
//					}
//				}
//		);
//		session.setDebug(true);//调试信息
//		
//		/**
//		 * 创建邮件
//		 */
//		Message msg = new MimeMessage(session);//邮件需要和会话session绑定
//		msg.setFrom(new InternetAddress("\"" + MimeUtility.encodeText("中国航信") + "\" <jbdong@travelsky.com>"));//发件人
//		msg.setRecipient(RecipientType.TO, new InternetAddress("jbdong@travelsky.com"));//收件人
//		msg.setRecipients(RecipientType.CC, InternetAddress.parse(MimeUtility.encodeText("董继波520") +"<dongjibo520@163.com>,"+MimeUtility.encodeText("董继波1986")+"<dongjibo1986@163.com>"));//抄送人
//		msg.setSubject("邮件测试");//主题
//
//		/**
//		 * 邮件的内容是一个复合结构体
//		 * 包括附件、正文（可以使html格式的）
//		 */
//		MimeMultipart msgMultipart = new MimeMultipart("mixed");//定义结构体，类型为复合类型
//		msg.setContent(msgMultipart);
//		
//		//搭建邮件内容结构体的框架
//		MimeBodyPart attch1 = new MimeBodyPart();//附件1		
//		MimeBodyPart attch2 = new MimeBodyPart();//附件2		
//		MimeBodyPart content = new MimeBodyPart();//正文部分
//		msgMultipart.addBodyPart(attch1);//添加附件1		
//		msgMultipart.addBodyPart(attch2);//添加附件2		
//		msgMultipart.addBodyPart(content);//添加正文
//		
//		//初始化附件1
//		DataSource ds1 = new FileDataSource("e:/1.png"	);
//		DataHandler dh1 = new DataHandler(ds1 );
//		attch1.setDataHandler(dh1);
//		attch1.setFileName(	MimeUtility.encodeText("复杂邮件的结构.png"));
//		//初始化附件2
//		DataSource ds2 = new FileDataSource("e:/1.txt"	);
//		DataHandler dh2 = new DataHandler(ds2 );
//		attch2.setDataHandler(dh2);
//		attch2.setFileName(	MimeUtility.encodeText("1.txt"));
//		
//		//初始化正文
//		content.setContent("邮件文本正文", "text/html;charset=gbk");
//		
//		//保存邮件
//		msg.saveChanges();
//		
//		
//		/**
//		 * 直接调用静态方法发送邮件
//		 * 会自动创建和关闭连接
//		 */
//		Transport.send(msg);
//	}
//	
//	@Test
//	public void test4() throws Exception{
//		
//		String sender = "jbdong@travelsky.com";
//		String senderName = "中国航信";
//		//主题
//		String subject = "邮件测试";
//		//正文
//		String content = "邮件文本正文2";
//		//收件人
//		List<String> receivers = new ArrayList<String>();
//		receivers.add("dongjibo520@163.com");
//		receivers.add("dongjibo1986@163.com");
//		//抄送人
//		List<String> ccs = new ArrayList<String>();
//		ccs.add("dongjibo520@163.com");
//		ccs.add("dongjibo1986@163.com");
//		//附件
//		List<String> attchs = new ArrayList<String>();
//		attchs.add("e:/1.png");
//		attchs.add("e:/1.txt");
//		//邮件的配置
//		String smtp = "smtpav.travelsky.com";
//		final String username = sender;
//		final String password = "123";
//		//发送邮件
////		EmailHelper.init().send(sender, senderName, subject, content, receivers, ccs, attchs, smtp, username, password);
////		
////		EmailHelper.init().send(subject, content+"1", receivers, ccs, attchs);
////		
////		EmailHelper.init().send(subject, content+"2", "dongjibo520@163.com",  "e:/1.png");
////		
////		EmailHelper.init().send(subject, content+"3", "dongjibo520@163.com");
//	}
//	
//	
//}
