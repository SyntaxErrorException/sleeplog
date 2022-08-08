package dao;

import domain.User;

public interface UserDao {
	//ユーザ新規登録用（Create）
	void insert(User user) throws Exception;
	
	//重複するログインID確認用（Read）
	User findById(String loginId) throws Exception;
	
	//ログイン認証用（Read）
	User findByIdAndPass(String loginId,String loginPass) throws Exception;
}
