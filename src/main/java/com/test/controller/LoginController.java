package com.test.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.druid.pool.GetConnectionTimeoutException;
import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DiffuseRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.MarbleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.test.entity.User;
import com.test.service.IUserService;
import com.test.util.MD5Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * patchca生成多彩验证码
 *
 * @author
 */
@Controller
public class LoginController {
	@Autowired
	private IUserService userService;
	
	// 验证码处理器工厂
	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	private static Random random = new Random();
	static {
		
	    cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
		cs.setColorFactory(new ColorFactory() {
			public Color getColor(int x) {
				int[] c = new int[3];
				int i = random.nextInt(c.length);
				for (int fi = 0; fi < c.length; fi++) {
					if (fi == i) {
						c[fi] = random.nextInt(71);
					} else {
						c[fi] = random.nextInt(256);
					}
				}
				return new Color(c[0], c[1], c[2]);
			}
		});
		// 生成的单词设置
		RandomWordFactory wf = new RandomWordFactory();
		wf.setCharacters("23456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ");
		wf.setMaxLength(4);
		wf.setMinLength(4);
		cs.setWordFactory(wf);
	}
	
	@RequestMapping(value="/login")
	public String tologin(HttpServletRequest request, HttpServletResponse response,Model model) {		
		return "login";
	}
	
	
	String code="";
	/**
	 * 请求登录，验证用户
	 * @param session
	 * @param loginname
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.POST)
	public String loginPost(HttpServletRequest request,String username,String password,Model model,String authcode ){		
		HttpSession session=request.getSession();
		//从session中获取验证码
		String key=(String) session.getAttribute("captchaToken");
		User user=userService.findUserByUsername(username);
		//判断用户输入的验证码是否正确
		if(authcode.equalsIgnoreCase(key)&&user!=null) {
			//获得当前用户 状态为未认证
			Subject subject=SecurityUtils.getSubject();			
			UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),password);
			try {
				subject.login(token);				
			} catch (UnknownAccountException e) {//用户名不存在异常
				e.printStackTrace();
				this.addActionError(getText("usernameNotExist"));
				return "login";
			}catch (IncorrectCredentialsException e) {//密码不正确异常
				e.printStackTrace();
				this.addActionError(getText("loginError"));
				return "login";
			}
			;
			model.addAttribute("user",  subject.getPrincipal());
			session.setAttribute("loginUser",  subject.getPrincipal());
			return "demo";
		}else {
			//验证码错误
			this.addActionError(this.getText("checkcodeError"));
			return "login";
		}		
	}
	
		
	private void addActionError(Object text) {
		// TODO Auto-generated method stub
		
	}


	private Object getText(String string) {
		
		return string;
	}


	@RequestMapping("/pcrimg")
    public void crimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (random.nextInt(5)) {
            case 0:
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        setResponseHeaders(response);
        
        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
        session.setAttribute("captchaToken", token);
       
        code=token;
        System.out.println("当前的SessionID=" + session.getId() + "，验证码=" + token);
    }
	

	protected void setResponseHeaders(HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}
}