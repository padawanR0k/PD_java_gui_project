package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {
	public Connection connect = null;
	public Statement stmt = null;

	private String DB_URL = "jdbc:mysql://34.64.77.183:3306";
	private String DB_PW = null;
	private String DB_USERNAME = null;

	public static void main(String[] args) throws Exception {
		// 연결 테스트를 위한 코드

		/*
			DB db = new DB();
			List response = db.query("select Host FROM mysql.user ");
			System.out.println(response);
		*/
	}

	public DB() {
		// Connection 객체를 자동완성으로 import할 때는 com.mysql.connection이 아닌
		// java 표준인 java.sql.Connection 클래스를 import해야 한다.

		try {
			// 1. 드라이버 로딩
			// 드라이버 인터페이스를 구현한 클래스를 로딩
			// mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
			// mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			// mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
			// heroku clearDB 정보
			Map<String, String> env = System.getenv();
			this.DB_USERNAME = env.get("DB_ID");
			this.DB_PW = env.get("DB_PW");
			System.out.println(this.DB_USERNAME);
			System.out.println(this.DB_PW);

			if (this.DB_USERNAME == "" || this.DB_PW == "") {
				System.out.println("환경변수를 입력하셨나요?");
			}

			this.connect = this.getConnection();
			System.out.println("연결 성공");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (this.connect != null && !this.connect.isClosed()) {
					this.connect.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * DB의 커넥션을 가져옴(커넥션은 한번쓰고나서 close하여 닫기떄문에 쓸때마다 새로 만든다.)
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.DB_URL, this.DB_USERNAME, this.DB_PW);
	}

	public List<Map<String, Object>> query(String query) {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			// 3. 쿼리 수행을 위한 Statement 객체 생성
			connect = this.getConnection();
			PreparedStatement pstmt = connect.prepareStatement(query);
			System.out.println(query);

			try {
				ResultSet res = pstmt.executeQuery();

				return this.resultSetToArrayList(res);
			} catch (SQLException e) {
				System.out.println(e);
				System.out.println("쿼리전달 시 오류발생");
				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		} finally {
			try {
				if (connect != null && !connect.isClosed()) {
					connect.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * update, create문 실행
	 * @param query
	 * @return
	 */
	public int update(String query) {
		try {
			connect = this.getConnection();
			PreparedStatement pstmt = connect.prepareStatement(query);
			System.out.println(query);

			try {
				int res = pstmt.executeUpdate(query);

				return res;
			} catch (SQLException e) {
				System.out.println(e);
				System.out.println("쿼리전달 시 오류발생");
				return -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (connect != null && !connect.isClosed()) {
					connect.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 쿼리를 날려 받아온 결과값을 ArrayList로 사람이 보기 쉬운 형태로 변환함
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> resultSetToArrayList(ResultSet rs) throws SQLException {
		// response의 컬럼명 같은 메타데이터 가져옴
		ResultSetMetaData md = rs.getMetaData();
		System.out.println(md);

		int columns = md.getColumnCount();
		List<Map<String, Object>> list = new ArrayList<>();
		while (rs.next()) {
			Map<String, Object> row = new HashMap<>(columns);
			for (int i = 1; i <= columns; ++i) {
				String columnName = md.getColumnName(i);
				Object tuple = rs.getObject(i);
				row.put(columnName, tuple);
			}
			list.add(row);
		}

		return list;
	}

	public void disconnect() {
		try {

			this.connect.close();
		} catch (SQLException e) {
			System.out.println("close시 에러발생");

		}
	}
}