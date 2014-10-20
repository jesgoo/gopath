package org.dong.proto.webapp.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/randCode")
@SessionAttributes("randCode")
public class RandCodeController {
	
	@RequestMapping("/back")
	public void manage(ModelMap model,HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//不要缓存
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		//创建图片
		int width = 82, height = 26;
		BufferedImage image =
			new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获取图片的画笔
		Graphics g = image.createGraphics();

		Random random = new Random();
		//画干扰的圈圈
		g.setColor(getRandColor(180, 250));
		g.fillRect(0, 0, width, height);
		for (int i = 0; i < 5; i++) {
			g.setColor(getRandColor(50, 100));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawOval(x, y, 4, 4);
		}

		//画验证码
		g.setFont(new Font("", Font.BOLD, 27));
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = getCode();
			sRand += rand;
			g.setColor(
				new Color(
					20 + random.nextInt(80),
					20 + random.nextInt(100),
					20 + random.nextInt(90)));
			g.drawString(rand, (18 + random.nextInt(3)) * i, height);
			//画干扰虚线
			for (int k = 0; k < 12; k++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(9);
				int yl = random.nextInt(9);
				g.drawLine(x, y, x + xl, y + yl);
			}
		}
        
//		req.getSession().setAttribute("rand",sRand);
		model.addAttribute("randCode",sRand);
		
		//清空画笔缓存，即显示图片
		g.dispose();
		//传递图片
		ImageIO.write(image, "JPEG", resp.getOutputStream());
	}
	
	

	private String getCode() {
		
		Random random = new Random();
		int codeType = random.nextInt(2);
		int r = 48;
		char c = '0' ;
		//0、生成数字，1、生成字母
		if (0 == codeType) {
			r = (48+random.nextInt(10));
			c = (char) r ;
		} else {
			r = (65+random.nextInt(26));
			c = (char) r ;
		}
		
		return c+"";
	}

	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
