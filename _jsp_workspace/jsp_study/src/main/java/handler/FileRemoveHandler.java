package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRemoveHandler {
	private static final Logger log = LoggerFactory.getLogger(FileRemoveHandler.class);
	//파일 이름하고 경로를 받아서 파일을 삭제하는 메서드 생성
	//리턴타입: int => 삭제가 잘 되면 1, 안되면 0을 리턴하도록 설정
	public int deleteFile(String imageFileName, String savePath) {
		boolean isDel = false; //삭제가 잘 되었는지 체크하는 변수
		log.info(">>>>> deleteFile method 접근 완료! "+imageFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imageFileName);
		File removeThumbFile = new File(fileDir+File.separator+"_th_"+imageFileName);
		//파일이 존재해야 삭제가 가능함
		//파일이 존재하는지 확인
		if(removeFile.exists() || removeThumbFile.exists()) {
			isDel = removeFile.delete();
			log.info(">>>>> fileRemove : "+(isDel ? "ok" : "fail"));
			if(isDel) {
				isDel = removeThumbFile.delete(); //원본파일 지워지면 썸네일도 지워지도록 설정
				log.info(">>>>> fileThumbRemove: "+(isDel ? "ok":"fail"));
			}
		}
		log.info(">>>> remove ok");
		return isDel ? 1:0;
	}
}
