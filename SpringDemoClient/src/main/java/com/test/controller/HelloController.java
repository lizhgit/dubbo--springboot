package com.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.entity.Log;
import com.test.entity.User;
import com.test.service.IUserBiz;
import com.test.utils.DocUtil;
import com.test.utils.JDBC;

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
		List<User> uList1 = userBiz.checkUser(user);
		if (uList1 != null && uList1.size() > 0) {
			Log log = new Log();
			log.setLog_object(user.getUsername());
			log.setLog_content("用户：【" + user.getUsername() + "】，成功登陆！");
			JDBC.insert(log);
			mav.setViewName("userList");
			mav.addObject("uList", userBiz.findUser(new User()));
			mav.addObject("loginUser", uList1.get(0));
			mav.addObject("msg", "你好," + user.getUsername());
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
		if (a == 1) {
			Log log = new Log();
			log.setLog_object(user.getUsername());
			log.setLog_content("用户：【" + user.getUsername() + "】，成功注册！");
			JDBC.insert(log);
			mav.setViewName("userList");
			List<User> uList = userBiz.findUser(new User());
			mav.addObject("uList", uList);
			mav.addObject("loginUser", userBiz.findUser(user).get(0));
			mav.addObject("msg", "你好," + user.getUsername());
		} else {
			mav.setViewName("message");
			mav.addObject("str", "注册失败");
		}
		return mav;
	}

	@RequestMapping("/downUserList")
	public ResponseEntity<byte[]> download(String name) throws IOException {
		List<User> uList = userBiz.findUser(new User());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < uList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rowuserid", uList.get(i).getUserid());
			map.put("rowusername", uList.get(i).getUsername());
			map.put("rowpassword", uList.get(i).getPassword());
			list.add(map);
		}
		File file = DocUtil.createWord(list, name);
		byte[] body = null;
		InputStream is = new FileInputStream(file);
		body = new byte[is.available()];
		is.read(body);
		HttpHeaders headers = new HttpHeaders();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = formatter.format(new Date());// 格式化数据

		headers.add("Content-Disposition", "attchement;filename=" + date+".doc");
		headers.add("ContentType", "application/msword");
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
		is.close();
		Log log = new Log();
		log.setLog_object(name);
		log.setLog_content("用户：【" + name + "】，导出列表！");
		JDBC.insert(log);
		return entity;
	}

	@RequestMapping("/downUserList1")
	public void download1(HttpServletResponse response) throws IOException {
		String name = "李征";
		List<User> uList = userBiz.findUser(new User());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < uList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rowid", uList.get(i).getUserid());
			map.put("rowname", uList.get(i).getUsername());
			list.add(map);
		}
		File file = DocUtil.createWord(list, name);
		// response.setContentType("multipart/form-data");
		// 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
		response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
		ServletOutputStream out;
		// 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
		// File file = new File(path + "download/" + "download.pdf");

		try {
			FileInputStream inputStream = new FileInputStream(file);

			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();

			int b = 0;
			byte[] buffer = new byte[512];
			while (b != -1) {
				b = inputStream.read(buffer);
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			inputStream.close();
			out.close();
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
