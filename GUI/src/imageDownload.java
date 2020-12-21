import java.io.IOException;

import saveImg;

public class imageDownload {
	public static void main(String[] args) {
		//String imgUrl = "https://img.cgv.co.kr/Movie/Thumbnail/Poster/000084/84049/84049_1000.jpg";
		String imgUrl = "https://img.cgv.co.kr/Movie/Thumbnail/Poster/000079/79729/79729_1000.jpg";
		String path = "./image/poster";
		String fileName = "poster";

		saveImg s = new saveImg();

		try {
			int result = s.saveImgFromUrl(imgUrl, path, fileName); // 성공 시 1 리턴, 오류 시 -1 리턴
			if (result == 1) {
				System.out.println("저장된경로 : " + s.getPath());
				System.out.println("저장된파일이름 : " + s.getSavedFileName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

