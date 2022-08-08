package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import domain.User;

public class UserDaoImpl implements UserDao {
	
	//フィールド
	private DataSource ds;

	//コンストラクタ
	public UserDaoImpl(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public void insert(User user) throws Exception {
		
	}

	@Override
	public User findById(String loginId) throws Exception {
		User user = new User();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM users WHERE login_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,loginId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = mapToUser(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return user;
	}

	@Override
	public User findByIdAndPass(String loginId, String loginPass) throws Exception {
		User user = findById(loginId);
		if (user == null) {
			System.out.println("存在しないIDです");
			return null;
		}

		if (!BCrypt.checkpw(loginPass, user.getLoginPass())) {
			System.out.println("パスワードが違います");
			return null;
		}

		return user;
	}
	
	public User mapToUser(ResultSet rs) throws Exception {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLoginId(rs.getString("login_id"));
		user.setLoginPass(rs.getString("login_pass"));
		user.setName(rs.getString("name"));
		return user;
	}
}
