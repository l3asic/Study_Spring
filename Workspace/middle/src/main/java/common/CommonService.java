package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonService {
	
	
	// 파일 업로드 처리
	public String fileupload(String category, MultipartFile file,
			HttpSession session) {
		String resources = session.getServletContext().getRealPath("resources");
	// D:\Study_Spring\Workspace\.metadata\.plugins\org.eclipse.wst.server.core
	//			\tmp0\wtpwebapps\iot\resources			
		String folder = resources + "/upload" + category + "/"
				+ new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	// /upload/notice/2022/01/07/00000000000saasf_123.png	
		
		String uuid = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		File dir = new File (folder);
		if (! dir.exists() ) dir.mkdirs();
			try {
				file.transferTo(new File(folder, uuid));
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return folder.substring(resources.length() + 1) + "/" + uuid;
	}	
	
}








