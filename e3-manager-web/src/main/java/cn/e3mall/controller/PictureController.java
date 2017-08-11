package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;

@Controller
public class PictureController {

	@Value("${image.server.url}")
	private String server_image_url;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadPicture(MultipartFile uploadFile) {
		/*把文件上传到图片服务器上
		 * //指定上传文件参数名称
		filePostName  : "uploadFile",
		//指定上传文件请求的url。
		uploadJson : '/pic/upload',
		//上传类型，分别为image、flash、media、file
		dir : "image"
		 * */
		//取文件的原始名
		String originalFilename = uploadFile.getOriginalFilename();
		//取文件扩展名
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
		//创建一个FastDfsClient对象
		FastDFSClient fastDFSClient;
		try {
			fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
		
		String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
		url=server_image_url+url;
		//创建一个map
		Map result = new HashMap<>();
		result.put("error", 0);
		result.put("url", url);
		return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message","文件上传失败111！");
			return JsonUtils.objectToJson(result);
		}
	}
}
