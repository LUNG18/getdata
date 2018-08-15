package com.lung.getdata.controller;

import com.lung.getdata.utils.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthImg {
    private static Logger log = LoggerFactory.getLogger(AuthImg.class);

    @RequestMapping("getImg")
    public void getAuthImg(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

        HttpSession session = request.getSession();
        session.removeAttribute("verCode");
        session.setAttribute("verCode",verifyCode.toLowerCase());
        try {
            int w = 100, h = 30;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        }catch(Exception e){
            log.error("getImg",e);
        }
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(String code,HttpServletRequest request){
        String verCode = (String) request.getSession().getAttribute("verCode");
        if(verCode.equals(code)){
            return verCode;
        }
        return "no";
    }
}
