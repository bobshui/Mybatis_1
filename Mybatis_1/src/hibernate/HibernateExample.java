package hibernate;

import org.hibernate.classic.Session;

import src.Role;

public class HibernateExample {
	public static void main(String[] args) {
		Session session=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Role role =(Role)session.get(Role.class,1L);
			System.err.println(role.getRoleName());
			System.err.println(role.getNote());
		}finally{
			if(session!=null)
				session.close();
		}
	}
}
