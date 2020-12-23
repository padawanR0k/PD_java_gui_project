import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class saveImg {

	private String savedFileName;
	private String path;
	public static void main(String[] args) throws Exception {
		new saveImg().saveImgFromUrlToLocal("https://img.cgv.co.kr/Movie/Thumbnail/Poster/000084/84153/84153_320.jpg", 38);
	}
	public String getSavedFileName() {
		return savedFileName;
	}

	public String getPath() {
		return path;
	}

	public int saveImgFromUrl(String imgUrl, String path, String fileName) throws IOException {
		URL url = null;
		InputStream in = null;
		OutputStream out = null;

		// 폴더 없으면 만들기
		File Folder = new File(path);
		if (!Folder.exists()) {
			try {
				Folder.mkdir();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		try {

			url = new URL(imgUrl);

			// 헤더에서 파일 확장자 가져오기(content-type)
			URLConnection urlConn = url.openConnection();
			String conType = urlConn.getContentType();
			String conT[] = conType.split("/");
			if (conT[1].equals("jpeg")) {
				conT[1] = "jpg";
			}

			// 파일네임 정하기

			if (fileName.equals("")) {
				fileName = "images";
			}

			// 해당 디렉토리에 해당 파일명을 가진 파일이 존재하는 확인 후 파일네임에 넘버링
			File file = new File(path + "/" + fileName + "." + conT[1]);

			in = urlConn.getInputStream();

			if (file.exists()) {
				int i = 1;
				while (true) {
					File file2 = new File(path + "/" + fileName + "_" + i + "." + conT[1]);
					if (file2.exists()) {
						i++;
					} else {
						fileName = fileName + "_" + i;
						break;
					}
				}
			}

			// 여기부터 쓰기시작
			out = new FileOutputStream(path + "/" + fileName + "." + conT[1]);

			while (true) {
				int data = in.read();
				if (data == -1) {
					break;
				}
				out.write(data);
			}

			in.close();
			out.close();

			this.savedFileName = fileName + "." + conT[1];
			this.path = path;
			return 1;

		} catch (Exception e) {

			e.printStackTrace();
			return -1;

		} finally {

			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}

		}

	}

	public int saveImgFromUrl(String imgUrl, String path) throws IOException {
		URL url = null;
		InputStream in = null;
		OutputStream out = null;

		// 폴더 없으면 만들기
				File Folder = new File(path);
				if (!Folder.exists()) {
					try {
						Folder.mkdir();
					} catch (Exception e) {
						e.getStackTrace();
					}
				}

		String fileName = "";

		try {

			url = new URL(imgUrl);

			// 헤더에서 파일 확장자 가져오기(content-type)
			URLConnection urlConn = url.openConnection();
			String conType = urlConn.getContentType();
			String conT[] = conType.split("/");
			if (conT[1].equals("jpeg")) {
				conT[1] = "jpg";
			}

			// 파일네임 정하기
			String fileNameTem = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
			String filterChar[] = { "?", "<", ">", "/", ":", "\"" };

			if (fileNameTem.contains("." + conT[1].substring(0, 2))) {
				fileNameTem = fileNameTem.substring(0, fileNameTem.indexOf("." + conT[1].substring(0, 2)));
			}

			for (int i = 0; i < filterChar.length; i++) {
				if (fileNameTem.contains(filterChar[i])) {
					fileName = "images";
					break;
				}
			}

			if (fileName.equals("")) {
				fileName = fileNameTem;
			}

			// 해당 디렉토리에 해당 파일명을 가진 파일이 존재하는 확인 후 파일네임에 넘버링
			File file = new File(path + "/" + fileName + "." + conT[1]);

			in = urlConn.getInputStream();

			if (file.exists()) {
				int i = 1;
				while (true) {
					File file2 = new File(path + "/" + fileName + "_" + i + "." + conT[1]);
					if (file2.exists()) {
						i++;
					} else {
						fileName = fileName + "_" + i;
						break;
					}
				}
			}

			// 여기부터 쓰기시작
			out = new FileOutputStream(path + "/" + fileName + "." + conT[1]);

			while (true) {
				int data = in.read();
				if (data == -1) {
					break;
				}
				out.write(data);
			}

			in.close();
			out.close();

			this.savedFileName = fileName + "." + conT[1];
			this.path = path;
			return 1;

		} catch (Exception e) {

			e.printStackTrace();
			return -1;

		} finally {

			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}

		}

	}

	public int saveImgFromUrlToLocal(String imgUrl, int pk) throws IOException {
		URL url = null;
		InputStream in = null;
		OutputStream out = null;

		// 폴더 없으면 만들기
		String folderPath = "./image/poster";
		File Folder = new File(folderPath);
		if (!Folder.exists()) {
			try {
				Folder.mkdir();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		try {

			url = new URL(imgUrl);

			// 헤더에서 파일 확장자 가져오기(content-type)
			URLConnection urlConn = url.openConnection();
			String conType = urlConn.getContentType();
			String conT[] = conType.split("/");
			if (conT[1].equals("jpeg")) {
				conT[1] = "jpg";
			}

			// 파일네임 정하기

			String fileName = "poster";

			// 해당 디렉토리에 해당 파일명을 가진 파일이 존재하는 확인 후 파일네임에 넘버링
			// File file = new File(path + "/" + fileName + "_" + ((Integer)pk).toString() + "." + conT[1]);

			in = urlConn.getInputStream();

			// if (file.exists()) {
			// 	int i = 1;
			// 	while (true) {
			// 		File file2 = new File(path + "/" + fileName + "_" + pk + "." + conT[1]);
			// 		if (file2.exists()) {
			// 			i++;
			// 		} else {
			// 			fileName = fileName + "_" + i;
			// 			break;
			// 		}
			// 	}
			// }

			// 여기부터 쓰기시작
			out = new FileOutputStream(folderPath + "/" + fileName + "_" + ((Integer) pk).toString() + "." + conT[1]);

			while (true) {
				int data = in.read();
				if (data == -1) {
					break;
				}
				out.write(data);
			}

			in.close();
			out.close();

			return 1;

		} catch (Exception e) {

			e.printStackTrace();
			return -1;

		} finally {

			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}

		}
	}
}