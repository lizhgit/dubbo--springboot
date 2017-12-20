package com.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.provider.entity.User;
import com.test.provider.service.IUserBiz;
import com.test.utils.DocUtil;

@Controller
public class HelloController {
	@Autowired
	@Qualifier("userBiz")
	private IUserBiz userBiz;

	@RequestMapping("/test")
	public ModelAndView test() {

		String str = "My First SpringMVC Demo";
		System.out.println(str);
		return new ModelAndView("message", "str", str);
	}

	@RequestMapping(value = "userList", produces = "text/plain;charset=utf-8")
	public ModelAndView userList(User user) {
		List<User> uList = userBiz.findUser(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userList");
		mav.addObject("uList", uList);
		return mav;
	}

	@RequestMapping(value = "login", produces = "text/plain;charset=utf-8")
	public ModelAndView login(User user) {
		ModelAndView mav = new ModelAndView();
		List<User> uList = userBiz.findUser(user);
		if (uList != null && uList.size() > 0) {
			mav.setViewName("userList");
			mav.addObject("uList", userBiz.findUser(new User()));
			mav.addObject("msg", "你好,"+user.getName());
		} else {
			mav.setViewName("message");
			mav.addObject("str", "用戶名或密码错误");
		}
		return mav;
	}
	@RequestMapping(value = "reg", produces = "text/plain;charset=utf-8")
	public ModelAndView register(User user) {
		ModelAndView mav = new ModelAndView();
		int a = userBiz.insertUser(user);
		if (a==1) {
			mav.setViewName("userList");
			List<User> uList=userBiz.findUser(new User());
			mav.addObject("uList", uList);
			mav.addObject("msg", "你好,"+user.getName());
		} else {
			mav.setViewName("message");
			mav.addObject("str", "注册失败");
		}
		return mav;
	}

	@RequestMapping("/downUserList")
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
		List<User> uList = userBiz.findUser(new User());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < uList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rowid", uList.get(i).getId());
			map.put("rowname", uList.get(i).getName());
			map.put("rowphone", uList.get(i).getPhone());
			list.add(map);
		}
		File file = DocUtil.createWord(list);
		byte[] body = null;
		InputStream is = new FileInputStream(file);
		body = new byte[is.available()];
		is.read(body);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attchement;filename=" + file.getName());
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
		is.close();
		return entity;
	}
}
