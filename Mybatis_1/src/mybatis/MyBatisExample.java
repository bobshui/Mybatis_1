package mybatis;

import org.apache.ibatis.session.SqlSession;

import src.Role;

public class MyBatisExample {
	public static void main(String[] args) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.getRole(1L);
			System.err.println(role.getRoleName());
		} finally {
			sqlSession.close();
		}
	}
}
