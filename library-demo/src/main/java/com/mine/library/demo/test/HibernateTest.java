package com.mine.library.demo.test;

import com.mine.library.demo.core.ssh.domain.UUIDTest;
import com.mine.library.demo.core.ssh.hibernate.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.UUID;

public class HibernateTest {
	private final static ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
	public Session getSession(){
		Session session= HibernateSessionFactory.getSession();
		Session session2=threadLocal.get();
		System.out.println(session2==null);
		return session;
	}
	public void closeSession(){
		Session session=threadLocal.get();
		System.out.println(session.isOpen());
	}
	@SuppressWarnings("unused")
	public void test(){
		Session session=this.getSession();
		closeSession();
	}
	public void test2(){
		Session session=HibernateSessionFactory.getSession();
		session.getTransaction().begin();
		UUIDTest uuidTest=new UUIDTest("测试11111111");
		session.save(uuidTest);
		session.getTransaction().commit();
		System.out.println(session.isOpen());
		Criteria criteria=session.createCriteria(UUIDTest.class);
		System.out.println(criteria.list().size());
		session.close();
	}
	public void getUUIDTest(){
		Session session=HibernateSessionFactory.getSession();
		UUIDTest uuidTest=(UUIDTest) session.get(UUIDTest.class, "13699ac0a1c9403f8b95ffc86dd018a8");
		System.out.println(uuidTest.getName());
		session.close();
	}
	public String uuid() {
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
}

