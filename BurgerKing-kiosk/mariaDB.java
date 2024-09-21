import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mariaDB {
  public static void main(String[] args) {

    // MySQL 드라이버 로딩
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("MySQL JDBC 드라이버가 연결되었습니다.");
    } catch (ClassNotFoundException e) {
      System.out.println("MySQL JDBC 드라이버를 찾을 수 없습니다..");
    }

    // MySQL 연결 정보
    String url = "jdbc:mysql://localhost:3306/burgerdb"; 
    String username = "root";
    String password = "2012";

    try {
      // MySQL에 연결
      Connection conn = DriverManager.getConnection(url, username, password);
      System.out.println("Connected to MySQL/MariaDB!");

      // SQL 쿼리 실행
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM burger_menu");

      // 결과 출력
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("id") +
          ", Name: " + rs.getString("name") +
          ", Calories: " + rs.getInt("calories") +
          ", Price: " + rs.getInt("price"));
      }

      // 연결 종료
      rs.close();
      stmt.close();
      conn.close();
      }
    catch (SQLException e){}
  }
}


