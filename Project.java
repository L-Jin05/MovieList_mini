package project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Project_01 {

		// 드라이버 연결준비.
		Connection conn;

		// SQL 드라이버 연결.
		Project_01() throws Exception {
			// 저장할 위치 : sql에서 드라이브 별도 다운로드 받아야 함.
			String driver = "com.mysql.cj.jdbc.Driver";

			// 연결할 테이블 경로.
			String url = "jdbc:mysql://localhost:3306/projectdb";
			String userid = "root";
			String pw = "****";

			Class.forName(driver);
//			System.out.println("드라이버 연결 준비.");
//			System.out.println("드라이버 연결 중...");

			conn = DriverManager.getConnection(url, userid, pw);
//			System.out.println("드라이버 연결 성공!");
		}


public static void main(String[] args) throws Exception {
	// 키보드 입력
	Scanner scan = new Scanner(System.in);
	// 메서드 호출을 위한 객체 생성
	Project_01 pr = new Project_01();


	
	// 데이터 가져오기.
	String key = "****";
	// 관리자 인증을 위한 ID, PW.
	String id = "505";
	String pw = "1234";

	while (true) {

	System.out.println("------------------------------------------------");
	System.out.println("		[주간 상영 영화 조회]");
	System.out.println("------------------------------------------------");

	// 접속자 확인
	System.out.println("   -----[ 접속자 확인 : 1. 관리자 / 2. 일반인 / 0.종료 ]-----");
	int who = scan.nextInt();
	if (who == 0) {
		System.out.println("작업을 종료합니다.");
		break;
	} else if (who == 1) {
//관리자
		System.out.print("--- 관리자 ID :");
		String inputId = scan.next();
		System.out.print("--- 관리자 PW :");
		String inputPw = scan.next();
		if (id.equals(inputId) && pw.equals(inputPw)) {
			System.out.println("\n(sytem : 인증되었습니다.)\n");
			System.out.println("   -----[ 수정할 메뉴를 선택해주세요. ]-----");
			System.out.println("  ---[1.조회 항목 추가 / 2.조회 항목 삭제]---");
			int m1 = scan.nextInt();

			if (m1 == 1) {
				System.out.println("\n(조회항목을 추가합니다.)\n\n   -----[조회 내용에 추가할 항목을 선택하세요.]-----");
				System.out.println("--[1.조회 목록에 개봉일 추가 / 2.상세 조회에 제작사 추가 / 0.종료]--");
				int m2 = scan.nextInt();
				if (m2 == 1) {
					// 조회목록 개봉일 추가 메서드 생성.
					// 조회목록에 데이터 추가 메서드 생성.
					System.out.println("-----[조회 목록에 개봉일을 추가하시겠습니까? (y/n)]-----");
					String or = scan.next();
					if (or.equals("y")) {
						pr.alterDt();
						String reserveD = "20201010";
						pr.searchDt(key, reserveD);
						System.out.println("\n(system : 항목이 정상적으로 추가되었습니다.)");
						System.out.println("(system : 작업을 종료합니다.)");
					} else {
						System.out.println("(system : 작업을 종료합니다.)");
						return;
					}
				} else if (m2 == 2) {
					// 제작사 추가 메서드, 데이터 추가 메서드.
					System.out.println("\n(상세 조회 항목을 추가합니다.)\n\n   -----[상세 조회 내용에 제작사 정보를 추가하시겠습니까? (y/n)]-----");
					String or = scan.next();
					if (or.equals("y")) {
						pr.alter();
						String reserveK = "20235834";
						System.out.println("\n(system : 항목이 정상적으로 추가되었습니다.)");

					}else {
						System.out.println("(system : 작업을 종료합니다.)");
						return;
					}
					// 0.종료
				}
// 조회항목 삭제
			} else if (m1 == 2) {
				System.out.println("[1.조회 목록에서 개봉일 삭제 / 2.상세 조회에서 제작사 제거 / 0.종료]");
				int m3 = scan.nextInt();
				if (m3 == 1) {
					// 조회목록 삭제
					System.out.println("   -----[조회목록에서 개봉일 항목을 삭제하시겠습니까? (y/n)]-----");
					String or = scan.next();
					if (or.equals("y")) {
						pr.drop2();
						System.out.println("\n(system : 항목이 정상적으로 삭제 되었습니다.)");

					}

				} else if (m3 == 2) {
				// 제작사 컬럼 삭제
				System.out.println("   -----[제작사 정보를 항목에서 제거하시겠습니까? (y/n)]-----");
				String or = scan.next();
				if (or.equals("y")) {
					pr.drop();
					String reserveK = "20235834";
					pr.selectAll2(key, reserveK);
					System.out.println("\n(system : 항목이 정상적으로 삭제 되었습니다.)");

				} else if (m3 == 0) {
					System.out.println("(system : 작업을 종료합니다.)");
				}
			}
//로그인 오류
		} else {
			System.out.println("(system : 인증 실패! 재접속 해주세요.)");
			return;
		}


}
//일반인 접속
} else if (who == 2) {
	// 조회날짜 입력
	System.out.print("-----[조회하려는 날짜를 입력해주세요(yyyymmdd) :");
	String day = scan.next();
	pr.searchWeek(key, day);
	pr.selectAll(key, day);


	// 번호 조회
	System.out.println("\n ---[상세 정보를 확인하고싶은 영화 번호를 입력하세요.]---");
	String rnum = scan.next();
	// 번호를 코드로 변환해오기.
	String code = pr.change(rnum);
	// 코드로 데이터 찾아 상세 테이블에 넣기.
	pr.search2(key, code);
	// 상세 테이블 조회.
	pr.selectAll2(key, code);

	System.out.println();

	}

}
} // while문 끝.

//메소드 영역 --------------------------------------------------------------------------------------------------------

//테이블에 데이터 넣기.
	private void insert(p_movieList ml) throws Exception {

		String sql = "insert into movie(rnum, movieNm, movieCd) value(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ml.getrnum());
			pstmt.setString(2, ml.getName());
			pstmt.setString(3, ml.getCd());

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("");
			} else {
				System.out.println("으악!");
			}
			pstmt.close();
	}

	// 데이터 삭제하기.
	private void delete() throws Exception {
		String sql = "delete from movie";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}

	// 상세정보 테이블 데이터 삽입, 리셋.
	private void insert2(P_detail de) throws Exception {

		String sql = "insert into mDetail(movieNm, openDt, genreNm, showTm, directors, actors)" + "value(?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, de.getmNm());
		pstmt.setString(2, de.getdt());
		pstmt.setString(3, de.getgenre());
		pstmt.setString(4, de.gettime());
		pstmt.setString(5, de.getdirec());
		pstmt.setString(6, de.getactors());

		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println("");
		} else {
			System.out.println("으악!");
		}
		pstmt.close();
	}

	// 데이터 삭제하기2.
	private void delete2() throws Exception {
		String sql = "delete from mDetail";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}

	// 새 컬럼 만들기.
	private void alter() throws Exception {
		String sql = "alter table mDetail add column companys varchar(30)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}

//조회목록에 개봉일 추가
	private void alterDt() throws Exception {
		String sql = "alter table movie add column openDt varchar(30)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}

	// 새 컬럼에 값 넣기.
	private void insert3(String cNm) throws Exception {
		String sql = "update mDetail set companys=? where movieNm is not null";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cNm);
		pstmt.executeUpdate();
	}

//개봉일 값 넣기
	private void insertDt(String openDt, String rnum) throws Exception {
		String sql = "update movie set openDt=? where rnum=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, openDt);
		pstmt.setString(2, rnum);
		pstmt.executeUpdate();
	}

// 컬럼 삭제
	private void drop() throws Exception {
		String sql = "alter table mDetail drop column companys";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}

//개봉일 컬럼 삭제
	private void drop2() throws Exception {
		String sql = "alter table movie drop column openDt";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}


	// 테이블 전체 조회.

	private void selectAll(String key, String day) throws Exception {
		Project_01 pr = new Project_01();

		String sql = "select * from movie";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
		int column = meta.getColumnCount();

		while (rs.next()) {
			if (column < 4) {
				System.out.println("\n[no." + rs.getString(1) + "]");
				System.out.println("-[제목: " + rs.getString(2) + "]");
			} else {
				pr.searchDt(key, day);
				pr.selectDt();
				return;
			}
		}

		stmt.close();
		rs.close();
	}

	// 개봉일포함 조회
	private void selectDt() throws Exception {
		String sql = "select * from movie";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.println("\n[no." + rs.getString(1) + "]");
			System.out.println("-[제목: " + rs.getString(2) + "]");
			System.out.println("+[개봉일: " + rs.getString(4) + "]");
		}
		stmt.close();
		rs.close();
	}

// 상세 정보 조회.
	private void selectAll2(String key, String code) throws Exception {
		Project_01 pr = new Project_01();

		String sql = "select * from mDetail";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
		int column = meta.getColumnCount();

		while (rs.next()) {
			if (column == 6) {
				System.out.println("*[영화 이름: " + rs.getString(1) + "]");
				System.out.println("-[개봉일: " + rs.getString(2) + "]");
				System.out.println("-[장르: " + rs.getString(3) + "]");
				System.out.println("-[재생시간: " + rs.getString(4) + "분]");
				System.out.println("-[감독: " + rs.getString(5) + "]");
				System.out.println("-[출연배우: " + rs.getString(6) + "]");
			} else {
				pr.searchCom(key, code);
				pr.selectcom();
		}
	}

		stmt.close();
		rs.close();
	}

// 상세 정보 새컬럼까지 전부 조회
	private void selectcom() throws Exception {
		String sql = "select * from mDetail";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.println("*[영화 이름: " + rs.getString(1) + "]");
			System.out.println("-[개봉일: " + rs.getString(2) + "]");
			System.out.println("-[장르: " + rs.getString(3) + "]");
			System.out.println("-[재생시간: " + rs.getString(4) + "분]");
			System.out.println("-[감독: " + rs.getString(5) + "]");
			System.out.println("-[출연배우: " + rs.getString(6) + "]");
			System.out.println("+[제작사: " + rs.getString(7) + "]");
		}
		stmt.close();
		rs.close();
	}


//영화 번호 -> 코드로 변환	
	private String change(String rnum) throws Exception {
		String sql = "select moviecd from movie where rnum= '" + rnum + "'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			String code = rs.getString("moviecd");
			return code;
		}
		pstmt.close();
		return "코드 조회 오류";
	}

// ===============================================================================================


	public void searchWeek(String key, String day) throws Exception {
		Project_01 pr = new Project_01();

		try {
			URL url2 = new URL(
					"http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key="
							+ key + "&targetDt=" + day);

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"));
			String result = bf.readLine();

			JSONParser par = new JSONParser();
			JSONObject jobj = (JSONObject) par.parse(result);
			JSONObject box = (JSONObject) jobj.get("boxOfficeResult");

			String type = (String) box.get("boxofficeType");
			System.out.println("{" + type + "}");
			String range = (String) box.get("showRange");
			System.out.println("{ 조회기간 : " + range + "}");

			// 일반조회.
			JSONArray list = (JSONArray) box.get("weeklyBoxOfficeList");

			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					pr.delete();
				}
				JSONObject movie = (JSONObject) list.get(i);

				String rnum = (String) movie.get("rnum");
				String name = (String) movie.get("movieNm");
				String cd = (String) movie.get("movieCd");

				p_movieList mo = new p_movieList(rnum, name, cd);
				pr.insert(mo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문제발생! 코드를 확인하세요!");
		}

	}

//상세 데이터 테이블에 넣기.
	public void search2(String key, String code) throws Exception {
		Project_01 pr = new Project_01();

		try {
			URL url2 = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="
					+ key + "&movieCd=" + code);

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"));
			String result = bf.readLine();

			JSONParser par = new JSONParser();
			JSONObject jobj = (JSONObject) par.parse(result);
			JSONObject mResult = (JSONObject) jobj.get("movieInfoResult");
			JSONObject info = (JSONObject) mResult.get("movieInfo");

			int reset = 0;
			if (reset == 0) {
				pr.delete2();
			}

			String mNm = (String) info.get("movieNm");
			String open = (String) info.get("openDt");

			JSONArray genres = (JSONArray) info.get("genres");
			String gNm = null;
			for (int i = 0; i < 1; i++) {
				JSONObject genreNm = (JSONObject) genres.get(0);
				gNm = (String) genreNm.get("genreNm");
			}
			String time = (String) info.get("showTm");

			JSONArray directors = (JSONArray) info.get("directors");
			String dNm = null;
			for (int i = 0; i < directors.size(); i++) {
				JSONObject peopleNm = (JSONObject) directors.get(0);
				dNm = (String) peopleNm.get("peopleNm");
			}

			JSONArray actors = (JSONArray) info.get("actors");
			String pNm = "";
			for (int i = 0; i < actors.size(); i++) {
				JSONObject pepleNm2 = (JSONObject) actors.get(i);
				String nm = (String) pepleNm2.get("peopleNm");
				pNm += (nm + ",");
			}

			P_detail de = new P_detail(mNm, open, gNm, time, dNm, pNm);
			pr.insert2(de);
			// 상세 정보 테이블 생성/리셋.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchCom(String key, String code) throws Exception {
		Project_01 pr = new Project_01();

		try {

			URL url2 = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="
					+ key + "&movieCd=" + code);

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"));
			String result = bf.readLine();

			JSONParser par = new JSONParser();
			JSONObject jobj = (JSONObject) par.parse(result);
			JSONObject mResult = (JSONObject) jobj.get("movieInfoResult");
			JSONObject info = (JSONObject) mResult.get("movieInfo");

			JSONArray companys = (JSONArray) info.get("companys");
			for (int i = 0; i < 1; i++) {
				if (i == 0) {
					pr.drop();
					pr.alter();
				}

				JSONObject comNm = (JSONObject) companys.get(0);
				String cNm = (String) comNm.get("companyNm");
				// 추가 메소드 3번, 컬럼삭제 메소드1.
				pr.insert3(cNm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//개봉일 뽑기
	public void searchDt(String key, String day) throws Exception {
		Project_01 pr = new Project_01();

		try {
			URL url2 = new URL(
					"http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key="
							+ key + "&targetDt=" + day);

			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"));
			String result = bf.readLine();

			JSONParser par = new JSONParser();
			JSONObject jobj = (JSONObject) par.parse(result);
			JSONObject box = (JSONObject) jobj.get("boxOfficeResult");

			// 일반조회.
			JSONArray list = (JSONArray) box.get("weeklyBoxOfficeList");

			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					pr.drop2();
					pr.alterDt();
				}
				JSONObject movie = (JSONObject) list.get(i);
				String openDt = (String) movie.get("openDt");
				String rnum = (String) movie.get("rnum");

				pr.insertDt(openDt, rnum);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문제문제!");
		}
	}

	}
