package com.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.pojo.User;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@RequestMapping(value="/up", method=RequestMethod.POST)
	public String fileup(User user,MultipartFile file, Model model, HttpServletRequest req ) {
		System.out.println(user);
		if(file.isEmpty()) {
			model.addAttribute("msg", "未选择文件");
			return "sccess";
		}
		String filename = "";
		try {
			//上传文件的名字
			filename = file.getOriginalFilename();
			//上传物理路径
			String path = req.getServletContext().getRealPath("/files");
			//构建上传文件对象
			File descFile = new File(path, filename);
			file.transferTo(descFile);
			model.addAttribute("msg", filename+"上传成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("msg", filename+"上传失败");
		} 
		return "success";
	}
	
	@RequestMapping("/down")
	public void filedown(@RequestParam("filename")String fn,HttpServletResponse res,HttpSession session, Model model) throws IOException {
		String path = session.getServletContext().getRealPath("/files");
		File file = new File(path, fn);
		//获取file中的内容
		byte[] data = FileUtils.readFileToByteArray(file);
		//设置响应方式为附加下载
	//	fn = new String(fn.getBytes("utf-8"),"iso8859-1");
		String urlfn = URLEncoder.encode(fn, "utf-8");
		//在IE浏览器中必须设置setContentType("application/x-msdownload;");
		res.setContentType("application/x-msdownload;");
		res.setHeader("Content-disposition","attachment; filename=" +urlfn);
		res.getOutputStream().write(data);
	}

}
