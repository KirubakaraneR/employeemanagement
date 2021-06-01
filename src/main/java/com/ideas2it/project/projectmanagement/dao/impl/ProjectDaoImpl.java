package com.ideas2it.project.projectmanagement.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;

import com.ideas2it.project.exceptions.UserDefinedException;
import com.ideas2it.project.logger.Loggers;
import com.ideas2it.project.projectmanagement.dao.ProjectDao;
import com.ideas2it.project.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnection;

/**
 * We perform create, read, update operations to the table in data base.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public class ProjectDaoImpl implements ProjectDao {
	private DataBaseConnection dataBaseConnection = 
			DataBaseConnection.getInstance();
	private Loggers log = new Loggers(ProjectDaoImpl.class);

	/**
	 * {@inheritdoc}
	 * @throws EmployeeManagementException 
	 */
	@Override
	public boolean addOrUpdateProject(Project project) 
			throws UserDefinedException {
		Session session = null;
		boolean checkIsAdded = true;

		try {
			session = dataBaseConnection.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(project);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			checkIsAdded = false;
			session.getTransaction().rollback();
			log.logError("Issue while adding or updating the values.", e);
			throw new UserDefinedException("Issue while adding "
					+ "or updating the values.");
		} finally {
			closeSession(session);
		}
		return checkIsAdded;
	}

	/**
	 * {@inheritdoc}
	 * @throws EmployeeManagementException 
	 */
	@Override
	public List<Project> getAllProject() throws UserDefinedException {
		Session session = null;
		List<Project> project = null;

		try {
			session = dataBaseConnection.getSessionFactory().openSession();
			Query query = session.createQuery("FROM Project project "
					+ "WHERE is_deleted = false");
			project = 	query.list();
		} catch (HibernateException e) {
			log.logError("Issue in fetching all datas from the table.", e);
			throw new UserDefinedException("Issue in fetching all datas "
					+ "from the table.");
		} finally {

			if(session != null) {
				session.close();
			}
		}
		return project;
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectManagementException 
	 */
	@Override
	public Project getProject(int id) throws UserDefinedException {
		Session session = null;
		Project project = null;

		try {
			session = dataBaseConnection.getSessionFactory().openSession();
			session.beginTransaction();
			project = (Project)session.get(Project.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			log.logError("Issue in fetching individual project "
					+ "details from table", e);
			throw new UserDefinedException("Issue in fetching individual "
					+ "project details from table.");
		} finally {
			closeSession(session);
		}
		return project;
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectManagementException 
	 */
	@Override
	public int getIdCount(int id) throws UserDefinedException {
		Session session = null;
		int count = 0;

		try {
			session = dataBaseConnection.getSessionFactory().openSession();
			Query query = session.createQuery("SELECT COUNT(id) FROM Project project WHERE"
					+ " is_deleted = false AND id = :id");
			query.setParameter("id", id);
			count = ((Long)query.uniqueResult()).intValue();
		} catch (HibernateException e) {
			count = 0;
			log.logError("Issue in getting count value", e);
			throw new UserDefinedException("Issue in getting count value.");
		} finally {
			closeSession(session);
		}
		return count;
	}

	/**
	 * {@inheritdoc}
	 * @throws ProjectManagementException 
	 */
	@Override
	public int getDeletedIdCount(int id) throws UserDefinedException {
		Session session = null;
		int count = 0;

		try {
			session = dataBaseConnection.getSessionFactory().openSession();
			Query query = session.createQuery("SELECT COUNT(id) FROM Project project WHERE"
					+ " is_deleted = true AND id = :id");
			query.setParameter("id", id);
			count = ((Long)query.uniqueResult()).intValue();
		} catch (HibernateException e) {
			count = 0;
			log.logError("Issue in getting count value.", e);
			throw new UserDefinedException("Issue in getting count value.");
		} finally {
			closeSession(session);
		}
		return count;
	}

	/**
	 * We close the session object.
	 */
	private void closeSession(Session session) {
		if(session != null) {
			session.close();
		}
	}
}