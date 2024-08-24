package Input;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DB_connection {

	String driver = "org.mariadb.jdbc.Driver";
	String DB_IP = "localhost";
	String DB_PORT = "3306";
	String DB_NAME = "contents";
	String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

//클래스 한번에 닫기 cntl+sft+/

	public void Insert_Year_Mon(String year, String month, String regdate, String REG_NAME) {

		try {
			LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
			String Format_now = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			String sql = String.format(
					"INSERT INTO year_mon (year, month, REGDATE, UPDATE_DATE,REG_NAME) VALUES (%s, %s, %s, %s, %s)",
					"'" + year + "'", "'" + month + "'", "'" + regdate + "'", "'" + Format_now + "'",
					"'" + REG_NAME + "'");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}

	public void Delete_Year_Mon(String RollbackYear) {

		try {
			String[] ary_RollbackYear = RollbackYear.split("_");
			String Temp_RollbackYear = ary_RollbackYear[1];

			String sql = "DELETE FROM year_mon WHERE reg_name='" + Temp_RollbackYear + "'";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}

	public String[] Year_Mon_List(String Year) {

		try {
			String sql = "SELECT MONTH,reg_name\r\n" + "FROM year_mon\r\n" + "WHERE YEAR = " + Year + "";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로
			String[] ary = new String[rs_len];

			for (int i = 0; i < rs_len; i++) {

				ary[i] = rs.getString("MONTH") + "_" + rs.getString("reg_name");
				rs.next();

			}

			return ary;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

	public void connect() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(DB_URL, "root", "xkrwo1202!");
			if (conn != null) {
				System.out.println("Hi, DB Connection Success");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}

	}

	public void exceute_sql() {

		try {
			String sql = "select * from notice";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
		}

	}

	public void writter(String ID, String CONTENT, String HIT, String FILES, String TITLE, String WRITER_ID,
			String REGDATE) {

		System.out.println(ID);

		LocalDate now = LocalDate.now();
		System.out.println(now);
		try {
			String sql = String.format(
					"INSERT INTO notice (ID, CONTENT, HIT, FILES, TITLE, WRITER_ID, REGDATE) VALUES (%s, %s, %s, %s, %s,%s,%s)",
					ID, CONTENT, HIT, FILES, TITLE, WRITER_ID, "'" + now + "'");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

	}

	public void Excel_Data_Insert(String NAME, String REGDATE, String ID, String DATE, String DAY, String TIME,
			String DESC, String MONEY, String MEMO, String CAT_L1, String CAT_L2, String PAYMENT) {

		try {

			String sql = String.format(
					"INSERT INTO expense (NAME, REGDATE, ID, DATE, DAY, TIME, SPD_DESC, MONEY,MEMO,CAT_L1,CAT_L2,PAYMENT) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",
					NAME, REGDATE, ID, DATE, DAY, TIME, DESC, MONEY, MEMO, CAT_L1, CAT_L2, PAYMENT);
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

	}

	public String[][] List_View(String DATE) {

		try {
			String sql = String.format(
					"SELECT Y.L1, NAME,regdate, id, DATE, DAY, TIME, spd_desc, money, memo, X.CAT_L1, X.cat_l2, payment, y.CAT_L2 AS DIF \r\n"
							+ "FROM expense x LEFT JOIN (SELECT DISTINCT L1, cat_l1, cat_l2 FROM master\r\n"
							+ "WHERE mapping_key IN (select distinct(mapping_key) FROM master_record)) y ON (x.CAT_L2=y.CAT_L2)\r\n"
							+ "WHERE REGDATE ='%S' ORDER BY X.cat_l2",
					DATE);

			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로
			String[][] ary = new String[rs_len][14];

			for (int i = 0; i < rs_len; i++) {

				ary[i][0] = rs.getString("ID");
				ary[i][1] = rs.getString("REGDATE");
				ary[i][2] = rs.getString("DATE");
				ary[i][3] = rs.getString("DAY");
				ary[i][4] = rs.getString("TIME");
				ary[i][5] = rs.getString("SPD_DESC");
				ary[i][6] = rs.getString("MONEY");
				ary[i][7] = rs.getString("MEMO");
				ary[i][8] = rs.getString("CAT_L1");
				ary[i][9] = rs.getString("CAT_L2");
				ary[i][10] = rs.getString("PAYMENT");
				ary[i][11] = rs.getString("NAME");
				ary[i][12] = rs.getString("DIF");
				ary[i][13] = rs.getString("L1");
				rs.next();

			}

			return ary;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

	public String[][] Select_Detail(String row, String month, String year) {

		try {
			String sql = "SELECT * \r\n" + "FROM expense\r\n" + "WHERE regdate IN (\r\n"
					+ "SELECT distinct(regdate)\r\n" + "FROM year_mon " + "WHERE YEAR=" + year + " AND MONTH=" + month
					+ ") AND cat_l2='" + row + "'";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로
			String[][] ary = new String[rs_len][10];

			for (int i = 0; i < rs_len; i++) {

				ary[i][0] = rs.getString("NAME");
				ary[i][1] = rs.getString("DATE");
				ary[i][2] = rs.getString("DAY");
				ary[i][3] = rs.getString("TIME");
				ary[i][4] = rs.getString("SPD_DESC");
				ary[i][5] = rs.getString("MONEY");
				ary[i][6] = rs.getString("MEMO");
				ary[i][7] = rs.getString("CAT_L1");
				ary[i][8] = rs.getString("CAT_L2");
				ary[i][9] = rs.getString("PAYMENT");

				rs.next();

			}

			return ary;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

	public String[][] Select_Detail_2(String row, String month, String year) {

		try {
			String sql2 = "SELECT * \r\n" + "FROM expense\r\n" + "WHERE regdate IN (\r\n"
					+ "SELECT distinct(regdate)\r\n" + "FROM year_mon " + "WHERE YEAR=" + year + " AND MONTH=" + month
					+ ") AND cat_l2='" + row + "'";
			String sql = "SELECT A.NAME, A.DATE, A.DAY, A.TIME, A.SPD_DESC, A.MONEY, A. MEMO, A.CAT_L1, A.CAT_L2, A.PAYMENT FROM\r\n"
+ "(SELECT * FROM expense WHERE regdate IN ( SELECT distinct(regdate) FROM year_mon WHERE YEAR="+year+" AND MONTH="+month+")) A LEFT JOIN \r\n"
+ "(SELECT L1,CAT_L2 from master a LEFT JOIN master_record b ON a.Mapping_Key=b.Mapping_Key WHERE b.year="+year+") B ON A.CAT_L2=B.CAT_L2\r\n"
+ "WHERE (A.CAT_L2!='제외' AND B.L1='"+row+"')";
			
			System.out.println(sql2);
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로
			String[][] ary = new String[rs_len][10];

			for (int i = 0; i < rs_len; i++) {

				ary[i][0] = rs.getString("NAME");
				ary[i][1] = rs.getString("DATE");
				ary[i][2] = rs.getString("DAY");
				ary[i][3] = rs.getString("TIME");
				ary[i][4] = rs.getString("SPD_DESC");
				ary[i][5] = rs.getString("MONEY");
				ary[i][6] = rs.getString("MEMO");
				ary[i][7] = rs.getString("CAT_L1");
				ary[i][8] = rs.getString("CAT_L2");
				ary[i][9] = rs.getString("PAYMENT");

				rs.next();

			}

			return ary;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

	
	public void Data_Delete(String date_deleted) {

		try {
			System.out.println(date_deleted);
			String sql = String.format("delete from expense where regdate=%s", "'" + date_deleted + "'");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}

	}

	public String[] Date_list() {

		try {
			String sql = "SELECT DISTINCT(REGDATE) FROM expense WHERE REGDATE NOT IN (SELECT DISTINCT(REGDATE) FROM year_mon)";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로
			String[] DATE_ary = new String[rs_len];

			for (int i = 0; i < rs_len; i++) {
				DATE_ary[i] = rs.getString("REGDATE");

				rs.next();

			}

			return DATE_ary;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

	public String[] Master_Record() {

		try {
			String sql = "SELECT YEAR\r\n" + "FROM master_record";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로
			String[] Master_Record = new String[rs_len];

			for (int i = 0; i < rs_len; i++) {
				Master_Record[i] = rs.getString("YEAR");

				rs.next();

			}

			return Master_Record;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

	public String[] YearList() {

		try {
			String sql = "SELECT distinct(YEAR) FROM year_mon";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로

			String[] YearList = new String[rs_len];
			for (int i = 0; i < rs_len; i++) {
				YearList[i] = rs.getString("year");
				rs.next();
			}
			return YearList;

		}

		catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;
	}

	public String[] Budget_Year_List() {

		try {
			String sql = "SELECT YEAR\r\n" + "from master_record\r\n" + "ORDER BY year desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로

			String[] Budget_Year_List = new String[rs_len];
			for (int i = 0; i < rs_len; i++) {
				Budget_Year_List[i] = rs.getString("year");
				rs.next();
			}
			return Budget_Year_List;

		}

		catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}

		return null;
	}

	public String[][] Budget_Detail_List(String Budget_Year) {
		try {

			String sql = "SELECT L1,L2,CAT_L1,CAT_L2,BUD,b.Mapping_Key\r\n"
					+ "FROM master a LEFT JOIN master_record b ON a.Mapping_key=b.Mapping_key\r\n" + "WHERE b.year="
					+ Budget_Year + "";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로
			String[][] Dashboard_MAS_LIST = new String[rs_len][6];

			for (int i = 0; i < rs_len; i++) {
				Dashboard_MAS_LIST[i][0] = rs.getString("L1");
				Dashboard_MAS_LIST[i][1] = rs.getString("L2");
				Dashboard_MAS_LIST[i][2] = rs.getString("CAT_L1");
				Dashboard_MAS_LIST[i][3] = rs.getString("CAT_L2");
				Dashboard_MAS_LIST[i][4] = rs.getString("BUD");
				Dashboard_MAS_LIST[i][5] = rs.getString("Mapping_Key");

				rs.next();
			}
			return Dashboard_MAS_LIST;
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;
	}

	public void Add_Budget_Items(String data) {

		try {
			String sql = "INSERT INTO master (L1,L2,CAT_L1,CAT_L2,BUD,MAPPING_KEY) VALUES " + data + ";";

			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}

	public void Insert_New_Budget(String data, String Mapping_Key, String Commited_Budget_Year) {

		try {
			String sql = "INSERT INTO master (L1,L2,CAT_L1,CAT_L2,BUD,MAPPING_KEY) VALUES " + data + ";";
			String sql2 = "update master_record set mapping_key='" + Mapping_Key + "' WHERE YEAR="
					+ Commited_Budget_Year + "";
			System.out.println(sql);
			System.out.println(sql2);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(sql2);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}

	public String[][] Dashboard_MAS_LIST(String param, String SelectedYear) {
		try {
			if (param.equals("year")) {
				String sql = "SELECT Y.l1 AS 'L1', y.CAT_L2 AS 'CAT_L2', \r\n"
						+ "					 ROUND(MAX(bud)/10000) 'BUD',\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=1) then y.money END)/10000) 1M,\r\n" + "					 ROUND(sum(case when (YEAR="
						+ SelectedYear + "&&MONTH=2) then y.money END)/10000) 2M,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=3) then y.money END)/10000) 3M,\r\n" + "					 ROUND(sum(case when (YEAR="
						+ SelectedYear + "&&MONTH=4) then y.money END)/10000) 4M,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=5) then y.money END)/10000) 5M,\r\n" + "					 ROUND(sum(case when (YEAR="
						+ SelectedYear + "&&MONTH=6) then y.money END)/10000) 6M,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=7) then y.money END)/10000) 7M,\r\n" + "					 ROUND(sum(case when (YEAR="
						+ SelectedYear + "&&MONTH=8) then y.money END)/10000) 8M,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=9) then y.money END)/10000) 9M,\r\n" + "					 ROUND(sum(case when (YEAR="
						+ SelectedYear + "&&MONTH=10) then y.money END)/10000) 10M,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=11) then y.money END)/10000) 11M,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=12) then y.money END)/10000) 12M,\r\n"
						+ "					 ROUND(avg(case when (YEAR=" + SelectedYear
						+ ") then y.money END)/10000) AVG_YEAR,\r\n" + "					 ROUND(sum(case when (YEAR="
						+ SelectedYear + ") then y.money END)/10000) SUM_YEAR\r\n" + "					 \r\n"
						+ "					 FROM\r\n" + "					  \r\n"
						+ "					 					 (SELECT X.l1, X.CAT_L2, X.YEAR, X.MONTH, SUM(X.MONEY) AS 'MONEY', MAX(X.BUD) AS 'BUD'\r\n"
						+ "					 					 FROM \r\n"
						+ "					 					 (SELECT X.L1,X.CAT_L2, X.BUD, Y.YEAR,Y.MONTH, Y.MONEY\r\n"
						+ "					 					 FROM (SELECT L1,L2,CAT_L1,CAT_L2,BUD,x.Mapping_key\r\n"
						+ "					 					 FROM master X, master_record Y\r\n"
						+ "					 					 WHERE (X.Mapping_Key IN (Y.Mapping_Key)) AND y.year="
						+ SelectedYear + ") x LEFT JOIN (SELECT YEAR,MONTH,CAT_L2,MONEY\r\n"
						+ "					 					 FROM year_mon a left join expense b ON a.regdate=b.regdate AND a.reg_name=b.name) y ON x.CAT_L2=y.cat_l2) x\r\n"
						+ "					 					 GROUP BY X.l1, X.CAT_L2, X.YEAR, X.MONTH \r\n"
						+ "					 ) Y\r\n" + "					 WHERE (Y.l1='특별')\r\n"
						+ "					 GROUP BY Y.l1, y.cat_l2\r\n" + "					 ORDER BY CAT_L2";

				pstmt = conn.prepareStatement(sql);

				rs = pstmt.executeQuery();
				rs.last(); // 커서를 제일 뒤로.
				int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
				rs.first(); // 커서를 처음으로
				String[][] Dashboard_MAS_LIST = new String[rs_len][17];

				for (int i = 0; i < rs_len; i++) {
					Dashboard_MAS_LIST[i][0] = rs.getString("L1");
					Dashboard_MAS_LIST[i][1] = rs.getString("CAT_L2");
					Dashboard_MAS_LIST[i][2] = rs.getString("BUD");
					Dashboard_MAS_LIST[i][3] = rs.getString("1M");
					Dashboard_MAS_LIST[i][4] = rs.getString("2M");
					Dashboard_MAS_LIST[i][5] = rs.getString("3M");

					Dashboard_MAS_LIST[i][6] = rs.getString("4M");

					Dashboard_MAS_LIST[i][7] = rs.getString("5M");

					Dashboard_MAS_LIST[i][8] = rs.getString("6M");
					Dashboard_MAS_LIST[i][9] = rs.getString("7M");
					Dashboard_MAS_LIST[i][10] = rs.getString("8M");
					Dashboard_MAS_LIST[i][11] = rs.getString("9M");
					Dashboard_MAS_LIST[i][12] = rs.getString("10M");
					Dashboard_MAS_LIST[i][13] = rs.getString("11M");
					Dashboard_MAS_LIST[i][14] = rs.getString("12M");
					Dashboard_MAS_LIST[i][15] = rs.getString("AVG_YEAR");
					Dashboard_MAS_LIST[i][16] = rs.getString("SUM_YEAR");
					rs.next();

				}

				return Dashboard_MAS_LIST;

			}

			else if (param.equals("month")) {

				String sql = "					 SELECT Y.l1 AS 'L1', y.CAT_L2 AS 'CAT_L2', \r\n"
						+ "					 ROUND(MAX(bud)/10000) 'BUD',\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=1) then y.money END)/10000) 1M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=1) then y.money END)/10000) 1M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=2) then y.money END)/10000) 2M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=2) then y.money END)/10000) 2M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=3) then y.money END)/10000) 3M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=3) then y.money END)/10000) 3M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=4) then y.money END)/10000) 4M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=4) then y.money END)/10000) 4M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=5) then y.money END)/10000) 5M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=5) then y.money END)/10000) 5M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=6) then y.money END)/10000) 6M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=6) then y.money END)/10000) 6M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=7) then y.money END)/10000) 7M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=7) then y.money END)/10000) 7M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=8) then y.money END)/10000) 8M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=8) then y.money END)/10000) 8M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=9) then y.money END)/10000) 9M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=9) then y.money END)/10000) 9M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=10) then y.money END)/10000) 10M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=10) then y.money END)/10000) 10M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=11) then y.money END)/10000) 11M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=11) then y.money END)/10000) 11M_GAP,\r\n"
						+ "					 ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=12) then y.money END)/10000) 12M,\r\n"
						+ "					 ROUND(MAX(bud)/10000) - ROUND(sum(case when (YEAR=" + SelectedYear
						+ "&&MONTH=12) then y.money END)/10000) 12M_GAP,\r\n"
						+ "					 ROUND(avg(case when (YEAR=" + SelectedYear
						+ ") then y.money END)/10000) AVG_YEAR,\r\n" + "					 ROUND(sum(case when (YEAR="
						+ SelectedYear + ") then y.money END)/10000) SUM_YEAR\r\n" + "					 \r\n"
						+ "					 FROM (\r\n" + "					 \r\n"
						+ "					 SELECT X.l1, X.CAT_L2, X.YEAR, X.MONTH, SUM(X.MONEY) AS 'MONEY', MAX(X.BUD) AS 'BUD'\r\n"
						+ "					 FROM \r\n"
						+ "					 (SELECT X.L1,X.CAT_L2, X.BUD, Y.YEAR,Y.MONTH, Y.MONEY\r\n"
						+ "					 FROM (SELECT L1,L2,CAT_L1,CAT_L2,BUD,x.Mapping_key\r\n"
						+ "					 FROM master X, master_record Y\r\n"
						+ "					 WHERE (X.Mapping_Key IN (Y.Mapping_Key)) AND y.year=" + SelectedYear
						+ ") x LEFT JOIN (SELECT YEAR,MONTH,CAT_L2,MONEY\r\n"
						+ "					 FROM year_mon a left join expense b ON a.regdate=b.regdate AND a.reg_name=b.name) y ON x.CAT_L2=y.cat_l2) x\r\n"
						+ "					 GROUP BY X.l1, X.CAT_L2, X.YEAR, X.MONTH\r\n"
						+ "					 ) Y\r\n" + "					 WHERE (Y.l1='고정' OR Y.l1='변동')\r\n"
						+ "					 GROUP BY Y.l1, y.cat_l2\r\n" + "					 ORDER BY CAT_L2;";

				pstmt = conn.prepareStatement(sql);

				rs = pstmt.executeQuery();
				rs.last(); // 커서를 제일 뒤로.
				int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
				rs.first(); // 커서를 처음으로
				String[][] Dashboard_MAS_LIST = new String[rs_len][29];

				for (int i = 0; i < rs_len; i++) {
					Dashboard_MAS_LIST[i][0] = rs.getString("L1");
					Dashboard_MAS_LIST[i][1] = rs.getString("CAT_L2");
					Dashboard_MAS_LIST[i][2] = rs.getString("BUD");
					Dashboard_MAS_LIST[i][3] = rs.getString("1M");
					Dashboard_MAS_LIST[i][4] = rs.getString("1M_GAP");
					Dashboard_MAS_LIST[i][5] = rs.getString("2M");
					Dashboard_MAS_LIST[i][6] = rs.getString("2M_GAP");
					Dashboard_MAS_LIST[i][7] = rs.getString("3M");
					Dashboard_MAS_LIST[i][8] = rs.getString("3M_GAP");
					Dashboard_MAS_LIST[i][9] = rs.getString("4M");
					Dashboard_MAS_LIST[i][10] = rs.getString("4M_GAP");
					Dashboard_MAS_LIST[i][11] = rs.getString("5M");
					Dashboard_MAS_LIST[i][12] = rs.getString("5M_GAP");
					Dashboard_MAS_LIST[i][13] = rs.getString("6M");

					Dashboard_MAS_LIST[i][14] = rs.getString("6M_GAP");
					Dashboard_MAS_LIST[i][15] = rs.getString("7M");
					Dashboard_MAS_LIST[i][16] = rs.getString("7M_GAP");
					Dashboard_MAS_LIST[i][17] = rs.getString("8M");
					Dashboard_MAS_LIST[i][18] = rs.getString("8M_GAP");
					Dashboard_MAS_LIST[i][19] = rs.getString("9M");
					Dashboard_MAS_LIST[i][20] = rs.getString("9M_GAP");
					Dashboard_MAS_LIST[i][21] = rs.getString("10M");
					Dashboard_MAS_LIST[i][22] = rs.getString("10M_GAP");
					Dashboard_MAS_LIST[i][23] = rs.getString("11M");
					Dashboard_MAS_LIST[i][24] = rs.getString("11M_GAP");
					Dashboard_MAS_LIST[i][25] = rs.getString("12M");
					Dashboard_MAS_LIST[i][26] = rs.getString("12M_GAP");

					Dashboard_MAS_LIST[i][27] = rs.getString("AVG_YEAR");
					Dashboard_MAS_LIST[i][28] = rs.getString("SUM_YEAR");
					rs.next();

				}

				return Dashboard_MAS_LIST;

			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

	public String[][] Income_Expense_List(String SelectedYear) {

		try {
			String sql_income = "SELECT Y.l1 AS 'L1',\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=1) then y.money END)/10000) 1M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=2) then y.money END)/10000) 2M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=3) then y.money END)/10000) 3M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=4) then y.money END)/10000) 4M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=5) then y.money END)/10000) 5M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=6) then y.money END)/10000) 6M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=7) then y.money END)/10000) 7M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=8) then y.money END)/10000) 8M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=9) then y.money END)/10000) 9M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=10) then y.money END)/10000) 10M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=11) then y.money END)/10000) 11M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=12) then y.money END)/10000) 12M,\r\n"
					+ "					 					 ROUND(avg(case when (YEAR=" + SelectedYear
					+ ") then y.money END)/10000) AVG_YEAR,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ ") then y.money END)/10000) SUM_YEAR\r\n" + "					 					 \r\n"
					+ "					 					 FROM\r\n" + "					 					  \r\n"
					+ "					 					 					 (SELECT X.l1, X.CAT_L2, X.YEAR, X.MONTH, SUM(X.MONEY) AS 'MONEY', MAX(X.BUD) AS 'BUD'\r\n"
					+ "					 					 					 FROM \r\n"
					+ "					 					 					 (SELECT X.L1,X.CAT_L2, X.BUD, Y.YEAR,Y.MONTH, Y.MONEY\r\n"
					+ "					 					 					 FROM (SELECT L1,L2,CAT_L1,CAT_L2,BUD,x.Mapping_key\r\n"
					+ "					 					 					 FROM master X, master_record Y\r\n"
					+ "					 					 					 WHERE (X.Mapping_Key IN (Y.Mapping_Key)) AND y.year="
					+ SelectedYear + ") x LEFT JOIN (SELECT YEAR,MONTH,CAT_L2,MONEY\r\n"
					+ "					 					 					 FROM year_mon a left join expense b ON a.regdate=b.regdate AND a.reg_name=b.name) y ON x.CAT_L2=y.cat_l2) x\r\n"
					+ "					 					 					 GROUP BY X.l1, X.CAT_L2, X.YEAR, X.MONTH \r\n"
					+ "					 					 ) Y\r\n"
					+ "					 					 /*WHERE (l1='고정' OR l1='변동' OR l1='특별')*/\r\n"
					+ "										WHERE(Y.CAT_L2!='제외' AND Y.l1='소득')\r\n"
					+ "					 					 GROUP BY Y.l1\r\n" + "UNION\r\n"
					+ "SELECT Y.l1 AS 'L1',\r\n" + "					 					 ROUND(sum(case when (YEAR="
					+ SelectedYear + "&&MONTH=1) then y.money END)/10000) 1M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=2) then y.money END)/10000) 2M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=3) then y.money END)/10000) 3M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=4) then y.money END)/10000) 4M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=5) then y.money END)/10000) 5M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=6) then y.money END)/10000) 6M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=7) then y.money END)/10000) 7M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=8) then y.money END)/10000) 8M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=9) then y.money END)/10000) 9M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=10) then y.money END)/10000) 10M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=11) then y.money END)/10000) 11M,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ "&&MONTH=12) then y.money END)/10000) 12M,\r\n"
					+ "					 					 ROUND(avg(case when (YEAR=" + SelectedYear
					+ ") then y.money END)/10000) AVG_YEAR,\r\n"
					+ "					 					 ROUND(sum(case when (YEAR=" + SelectedYear
					+ ") then y.money END)/10000) SUM_YEAR\r\n" + "					 					 \r\n"
					+ "					 					 FROM\r\n" + "					 					  \r\n"
					+ "					 					 					 (SELECT X.l1, X.CAT_L2, X.YEAR, X.MONTH, SUM(X.MONEY) AS 'MONEY', MAX(X.BUD) AS 'BUD'\r\n"
					+ "					 					 					 FROM \r\n"
					+ "					 					 					 (SELECT X.L1,X.CAT_L2, X.BUD, Y.YEAR,Y.MONTH, Y.MONEY\r\n"
					+ "					 					 					 FROM (SELECT L1,L2,CAT_L1,CAT_L2,BUD,x.Mapping_key\r\n"
					+ "					 					 					 FROM master X, master_record Y\r\n"
					+ "					 					 					 WHERE (X.Mapping_Key IN (Y.Mapping_Key)) AND y.year="
					+ SelectedYear + ") x LEFT JOIN (SELECT YEAR,MONTH,CAT_L2,MONEY\r\n"
					+ "					 					 					 FROM year_mon a left join expense b ON a.regdate=b.regdate AND a.reg_name=b.name) y ON x.CAT_L2=y.cat_l2) x\r\n"
					+ "					 					 					 GROUP BY X.l1, X.CAT_L2, X.YEAR, X.MONTH \r\n"
					+ "					 					 ) Y\r\n"
					+ "					 					 WHERE (l1='고정' OR l1='변동' OR l1='특별')\r\n" + "\r\n"
					+ "					 					 GROUP BY Y.l1";

			System.out.println("1");
			pstmt = conn.prepareStatement(sql_income);
			System.out.println("2");
			rs = pstmt.executeQuery();
			System.out.println("3");

			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로

			String[][] Income_Expense_List = new String[rs_len + 1][15];

			for (int i = 0; i < rs_len; i++) {
				Income_Expense_List[i][0] = rs.getString("L1");
				Income_Expense_List[i][1] = rs.getString("1M");
				Income_Expense_List[i][2] = rs.getString("2M");
				Income_Expense_List[i][3] = rs.getString("3M");
				Income_Expense_List[i][4] = rs.getString("4M");
				Income_Expense_List[i][5] = rs.getString("5M");
				Income_Expense_List[i][6] = rs.getString("6M");
				Income_Expense_List[i][7] = rs.getString("7M");
				Income_Expense_List[i][8] = rs.getString("8M");
				Income_Expense_List[i][9] = rs.getString("9M");
				Income_Expense_List[i][10] = rs.getString("10M");
				Income_Expense_List[i][11] = rs.getString("11M");
				Income_Expense_List[i][12] = rs.getString("12M");
				Income_Expense_List[i][13] = rs.getString("AVG_YEAR");
				Income_Expense_List[i][14] = rs.getString("SUM_YEAR");
				rs.next();

			}

			/* --- 쿼리 결과에 대한 sum값 생성 로직 --- */
			Income_Expense_List[rs_len][0] = "합계";
			for (int i = 1; i < 15; i++) {
				if (Income_Expense_List[0][i] != null) {
					int sum = Integer.valueOf(Income_Expense_List[0][i]);
					for (int j = 1; j < rs_len; j++) {
						if (Income_Expense_List[j][i] != null) {
							sum = sum - Integer.valueOf(Income_Expense_List[j][i]);
						}
					}
					Income_Expense_List[rs_len][i] = Integer.toString(sum);
				}
			}
			/* ------------------------------- */

			return Income_Expense_List;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

	public String[][] MAS_LIST(String year) {

		try {
			String sql = "SELECT L1, CAT_L1, CAT_L2\r\n" + "FROM MASTER\r\n" + "WHERE Mapping_Key IN (\r\n"
					+ "SELECT Mapping_Key\r\n" + "FROM master_record\r\n" + "WHERE YEAR = " + year + ")\r\n"
					+ "\r\n" + "ORDER BY L1, CAT_L1,CAT_L2";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로
			String[][] MAS_ARY = new String[rs_len][3];

			for (int i = 0; i < rs_len; i++) {
				MAS_ARY[i][0] = rs.getString("L1");
				MAS_ARY[i][1] = rs.getString("CAT_L1");
				MAS_ARY[i][2] = rs.getString("CAT_L2");

				rs.next();

			}

			return MAS_ARY;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

//List.jsp에서 호출 할 때 소득인 경우 콤보박스에 나타나는 list들이 L1에서 소득인 목록만 나오도록 데이터 불러옴
	public String[][] MAS_LIST_INCOME(String year) {

		try {
			String sql = "SELECT L1, CAT_L1, CAT_L2\r\n" + "FROM MASTER\r\n" + "WHERE Mapping_Key IN (\r\n"
					+ "SELECT Mapping_Key\r\n" + "FROM master_record\r\n" + "WHERE YEAR = " + year + ")\r\n"
					+ "AND (L1 = '소득' )\r\n" + "ORDER BY CAT_L1,CAT_L2";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음
			rs.first(); // 커서를 처음으로
			String[][] MAS_ARY = new String[rs_len][3];

			for (int i = 0; i < rs_len; i++) {
				MAS_ARY[i][0] = rs.getString("L1");
				MAS_ARY[i][1] = rs.getString("CAT_L1");
				MAS_ARY[i][2] = rs.getString("CAT_L2");

				rs.next();

			}

			return MAS_ARY;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return null;

	}

	public void disconnect() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void Change_Values(String regdate, String i, String cat_l2) {
		try {
			String sql = String.format("update expense set cat_l2 = %s where regdate=%s and id=%s", cat_l2, regdate, i);
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}

	}

	public int len_selected_data(String regdate) throws SQLException {
		try {
			String sql = String.format("SELECT * FROM EXPENSE WHERE REGDATE ='%S'", regdate);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last(); // 커서를 제일 뒤로.
			int rs_len = rs.getRow(); // 몇개의 tuple인지 알 수 있음

			return rs_len;
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

		}
		return 0;

	}

	public static void main(String[] args) {

	}

}
