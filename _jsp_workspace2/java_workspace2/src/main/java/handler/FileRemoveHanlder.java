package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRemoveHanlder {
	
	private static final Logger log = LoggerFactory.getLogger(FileRemoveHanlder.class);
	//파일 이름과 경로를 받아 파일을 삭제하는 메서드
	//리턴타입 int => 잘 삭제 되었다면 1, 아니면 0
	public int deleteFile(String imageFileName, String savePath) {
		
		boolean isDel = false; //삭제가 잘되었는지 체크하는 변수
		log.info(">>> deleteFile method 접근 완료!!" + imageFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imageFileName);
		File removeThumFile = new File(fileDir+File.separator+"th_"+imageFileName);
		
		//파일이 존재해야 삭제
		if(removeFile.exists() || removeThumFile.exists()) {
			isDel = removeFile.delete();
			log.info(">>>> fileRemove : "+(isDel ? "OK" : "Fail"));
			if (isDel) {
				isDel = removeThumFile.delete();
				log.info(">>> filethumremove : " + (isDel ? "OK" : "Fail"));
			}
		}
		
		log.info(">remove ok");
		
		
		return isDel ? 1:0;
	}
}
