
import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class Fastdfs {

	@Test
	public void testUpLoadFile() throws Exception{
		ClientGlobal.init("E:/e3mall/e3-manager-web/src/main/resources/conf/client.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageClient storageClient = new StorageClient(trackerServer, null);
		//使用storageClient上传文件，返回文件的路径文件件名
		//参数1：文件名及路径，参数2：扩展名，3；元数据
		String[] strings = storageClient.upload_file("C:/Users/Public/Pictures/Sample Pictures/Desert.jpg","jpg",null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastDFCClient() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("E:/e3mall/e3-manager-web/src/main/resources/conf/client.conf");
		String file = fastDFSClient.uploadFile("C:/Users/Public/Pictures/Sample Pictures/Penguins.jpg","jpg",null);
		System.out.println(file);

		
	}
}