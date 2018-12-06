package com.DButil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dj.Application;
import com.dj.ApplicationId;
import com.dj.Company;
import com.dj.Job;
import com.dj.SavedJob;
import com.dj.SavedJobId;
import com.dj.User;

public class DButil 
{

	private static DButil instance;
	
	private SessionFactory sessionFactory;
	
	public static DButil getInstance()
	{
		if (instance == null) {
			instance = new DButil();
		}
		
		return instance;
	}
	
	private DButil()
	{	
		sessionFactory  = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Job.class).
				addAnnotatedClass(Company.class).
				addAnnotatedClass(User.class).
				addAnnotatedClass(Application.class).
				addAnnotatedClass(ApplicationId.class).
				addAnnotatedClass(SavedJob.class).
				addAnnotatedClass(SavedJobId.class).
				buildSessionFactory();
	}
	
	public Map<String,String> getEmailAndPassword()
	{
		Session session = sessionFactory.getCurrentSession();
		Map<String,String> myMap = new HashMap<String,String>();
		String email;
		String password;
		
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<User> list = session.createQuery("from User").getResultList();
		
		session.getTransaction().commit();
		
		for(User user : list)
		{
			email = user.getEmail();
			password = user.getPassword();
			myMap.put(email, password);
		}
		session.close();
		return myMap;
		
	}
	
	public void addUser(User user)
	{	
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		session.save(user);
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	public void apply(int uid,int jobid)
	{
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		User user =  session.get(User.class, uid);
		Job job = session.get(Job.class, jobid);
		List<Application> userApps = user.getUserApplications();
				
		for (Application application : userApps) 
		{
			if (application.getId().getJob().getJobid().intValue() == jobid) {
				return;
			}
		}
		
		Application application = new Application(new ApplicationId(session.get(User.class, uid), session.get(Job.class, jobid)));
		
		user.getUserApplications().add(application);
		
		application.getId().setUser(user);
		
		job.getJobApplications().add(application);
		
		application.getId().setJob(job);
		
		session.get(Job.class, jobid).setNumbOfApplicants(session.get(Job.class, jobid).getNumbOfApplicants() + 1);
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	public User getUserAfterLogin(String loginEmail)
	 {
		User usr = null ;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<User> list = session.createQuery("from User").getResultList();
		for (User user : list) 
		{
			if (user.getEmail().equals(loginEmail)) 
			{
				usr = user;
				break;
			}
		}
		session.getTransaction().commit();
		session.close();
		return usr;
	 }
	
	public Job getJob(int jobid)
	 {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Job job = session.get(Job.class, jobid);
		
		session.getTransaction().commit();
		session.close();
		return job;
	 }

	public boolean verifyEmail(String email)
	{	
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<User> list = session.createQuery("from User").getResultList();
		
		session.getTransaction().commit();
		
		for (User user : list) 
		{
			if (user.getEmail().equals(email)) 
			{
				return true;
			}
		}
		session.close();
		return false;
		
	}
	
	public ArrayList<Job> getUserAppliedJobs(int uid)
	{	
		Session session = sessionFactory.getCurrentSession();
		ArrayList<Job> jobs = new ArrayList<Job>();
		session.beginTransaction();
		
		User user = session.get(User.class, uid);
		List<Application> list = user.getUserApplications();
		
		for (Application application : list) 
		{
			jobs.add(application.getId().getJob());
		}
		
		session.getTransaction().commit();
		session.close();
		return jobs;
	}
	
	public ArrayList<Job> getUserSavedJobs(int uid)
	{
		Session session = sessionFactory.getCurrentSession();
		ArrayList<Job> jobs = new ArrayList<Job>();
		session.beginTransaction();
		
		User user = session.get(User.class, uid);
		List<SavedJob> list = user.getSavedJobs();
		
		for (SavedJob savedJob : list) 
		{
			jobs.add(savedJob.getId().getJob());
		}
		
		session.getTransaction().commit();
		session.close();
		return jobs;
	}
	
	public void deleteApplication(int uid , int jobid)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Application application = session.get(Application.class, new ApplicationId(session.get(User.class, uid),session.get(Job.class, jobid)));
		session.delete(application);
		Job job = session.get(Job.class, jobid);
		job.setNumbOfApplicants(job.getNumbOfApplicants() - 1);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteSavedJob(int uid , int jobid)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		SavedJob savedJob = session.get(SavedJob.class, new SavedJobId(session.get(User.class, uid),session.get(Job.class, jobid)));
		session.delete(savedJob);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void saveJob(int uid,int jobid)
	{
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		User user =  session.get(User.class, uid);
		Job job = session.get(Job.class, jobid);
		List<SavedJob> userSavedJobs = user.getSavedJobs();
				
		for (SavedJob savedJob : userSavedJobs) 
		{
			if (savedJob.getId().getJob().getJobid().intValue() == jobid) {
				return;
			}
		}
				
		SavedJob savedJob = new SavedJob (new SavedJobId(session.get(User.class, uid), session.get(Job.class, jobid)));
		
		user.getSavedJobs().add(savedJob);
		
		savedJob.getId().setUser(user);
		
		job.getSavings().add(savedJob);
		
		savedJob.getId().setJob(job);
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	public ArrayList<Job> getAllJobs()
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		ArrayList<Job> allJobs = (ArrayList<Job>) session.createQuery("from Job").getResultList();
		
		session.getTransaction().commit();
		session.close();
		return allJobs;
		
	}
	
	//save any type of document to database
	public void saveCvToDB(int uid, byte[] file)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		User user = session.get(User.class, uid);
		user.setCV(file);
		
		session.getTransaction().commit();
		session.close();
		
	}

	public byte[] getCV(int uid)
	{	
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		User user = session.get(User.class, uid);
		
		session.getTransaction().commit();
		session.close();
		
		return user.getCV();
		
	}
	public Company getCompany(int cid)
	{	
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
			
		Company company = session.get(Company.class, cid);
			
		session.getTransaction().commit();
		
		session.close();
		
		return company;
		
	}
	
	public ArrayList<Job>searchJobsByLocation(String searchLocationCountry ,String searchLocationCity ,List<Job> jobsList)
	{
		ArrayList<Job> results = new ArrayList<Job>();
		if(!searchLocationCity.equals("All"))
		{
			for (Job job : jobsList) 
			{
				if(job.getLocation() == null)
					continue;
				if(job.getLocation().equals(searchLocationCity + ", " + searchLocationCountry))
				{
					results.add(job);
				}
			}
			return results;
		}
		else
		{
			for (Job job : jobsList) 
			{
				if(job.getLocation() == null)
					continue;
				if (job.getLocation().endsWith(searchLocationCountry))
				{
					results.add(job);
				}
			}
			return results;
		}
	}
	
	public  List<Company> getUMCompanies(int uid)
	{
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
			
		User user = session.get(User.class, uid);
		Hibernate.initialize(user.getManagedCompanies());
		List<Company> userCompanies = user.getManagedCompanies();
		
		session.getTransaction().commit();
		
		session.close();
		
		return userCompanies;
	}
	
	public List<Job> getCompanyJobs(int cid)
	{
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
			
		Company company = session.get(Company.class, cid);
		Hibernate.initialize(company.getJobs());
		List<Job> companyJobs = company.getJobs();
		
		session.getTransaction().commit();
		
		session.close();
		return companyJobs;
		
	}
	
	public void addCompany(Company company , int uid)
	{
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		User user = session.get(User.class , uid);

		user.getManagedCompanies().add(company);
		
		company.getResponsableUsers().add(user);
		
		session.save(company);
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	public User getUser(int uid)
	{
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		User user = session.get(User.class, uid);
		
		session.getTransaction().commit();
		
		session.close();
		
		return user;
	}
	
	public void addJobToCompany(Job job , int uid , int cid)
	{
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		User user = session.get(User.class, uid);
		
		Company company = session.get(Company.class, cid);
		
		company.getJobs().add(job);
		
		job.setCompany(company);
		
		user.getUploadedJobs().add(job);
		
		job.setUploader(user);
		
		session.save(job);
		
		session.getTransaction().commit();
				
		session.close();
	}
	
	public void deleteCompany(int cid)
	{
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		session.delete(session.get(Company.class, cid));
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	public void deleteJob(int jobid)
	{
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		session.delete(session.get(Job.class, jobid));
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Company> getAllCompanies()
	{
		Session session = sessionFactory.getCurrentSession(); 

		List<Company> allCompanies = null;
		
		session.beginTransaction();
		
		allCompanies = session.createQuery("from Company").getResultList();
		
		session.getTransaction().commit();
		
		return allCompanies;
	}

	public List<User> getJobCandidates(int jobid) 
	{
		List<User> candidates = new ArrayList<User>();
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		Job job = session.get(Job.class, jobid);
		List<Application> applications = job.getJobApplications();
		for (Application application : applications) 
		{
			candidates.add(application.getId().getUser());
		}
		
		session.getTransaction().commit();
		
		session.close();
		
		return candidates;
	}
	
	public ArrayList<Job> getUserCompanyUploadedJobs(int cid , int uid)
	{
		ArrayList<Job> jobs = new ArrayList<>();
		
		for (Job job : getCompanyJobs(cid)) 
		{
			if (job.getUploader().getUid() == uid) 
			{
				jobs.add(job);
			}
		}
		return jobs;
	}
	
	
	
	
	
	
	
	
	
	
	
}