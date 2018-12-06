package com.dj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.Part;

import com.DButil.DButil;

@ManagedBean(eager=true)
@SessionScoped

@Entity
@Table(name = "company")
public class Company 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid")
	private Integer cid;
	
	@Transient
	private int tempCompanyId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "year_founded")
	private String foundationYear;
	
	@Column(name = "company_type")
	private String type;
	
	@Column(name = "company_size")
	private String size;
	
	@Transient
	private String headquartersCountry;
	
	@Transient
	private String headquartersCity;
	
	@Column(name = "headquarters")
	private String headquarters;
	
	@Column(name = "website")
	private String website;
	
	@Transient
	private Part companyLogo0;
	
	@Column(name = "company_logo")
	@Lob
	private byte[] companyLogo;
	
	@OneToMany(mappedBy = "company" ,cascade = {CascadeType.ALL})
	private List<Job> jobs;
	
	@Transient
	private List<Job> jobs1;
	
	@ManyToMany(mappedBy = "managedCompanies", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<User> responsableUsers = new ArrayList<User>();
	
	@Transient
	private Map<String,String> countriesCities;
	@Transient
	private ArrayList<String> cities;
	
	public Company() 
	{
		countriesCities = new HashMap<String,String>();
		countriesCities.put("usa1", "California");countriesCities.put("usa2", "NewYork");countriesCities.put("usa3", "Michigan");
		countriesCities.put("germany1", "Berlin");countriesCities.put("germany2", "Frankfurt");countriesCities.put("germany3", "Dortmund");
		countriesCities.put("tunisia1", "Ariana");countriesCities.put("tunisia2", "Tunis");countriesCities.put("tunisia3", "Sfax");
		
		ArrayList<String> a1 = new ArrayList<String>();
		this.setCities(a1);
	}


	public Company(Integer cid, String name, String foundationYear, String type, String size, String headquarters,
			String website, byte[] companyLogo) {
		super();
		this.cid = cid;
		this.name = name;
		this.foundationYear = foundationYear;
		this.type = type;
		this.size = size;
		this.headquarters = headquarters;
		this.website = website;
		this.companyLogo = companyLogo;
	}


	public Company(String name, String foundationYear, String type, String size, String headquarters,
			String website, byte[] companyLogo) {
		super();
		this.name = name;
		this.foundationYear = foundationYear;
		this.type = type;
		this.size = size;
		this.headquarters = headquarters;
		this.website = website;
		this.companyLogo = companyLogo;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public int getTempCompanyId() {
		return tempCompanyId;
	}

	public void setTempCompanyId(int tempCompanyId) {
		this.tempCompanyId = tempCompanyId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFoundationYear() {
		return foundationYear;
	}

	public void setFoundationYear(String foundationYear) {
		this.foundationYear = foundationYear;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getHeadquartersCountry() {
		return headquartersCountry;
	}

	public void setHeadquartersCountry(String headquartersCountry) {
		this.headquartersCountry = headquartersCountry;
	}

	public String getHeadquartersCity() {
		return headquartersCity;
	}

	public void setHeadquartersCity(String headquartersCity) {
		this.headquartersCity = headquartersCity;
	}

	public String getHeadquarters() {
		return headquarters;
	}

	public void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public Part getCompanyLogo0() {
		return companyLogo0;
	}

	public void setCompanyLogo0(Part companyLogo0) {
		this.companyLogo0 = companyLogo0;
	}

	public byte[] getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(byte[] companyLogo) {
		this.companyLogo = companyLogo;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Job> getJobs1() {
		return jobs1;
	}

	public void setJobs1(List<Job> jobs1) {
		this.jobs1 = jobs1;
	}

	public List<User> getResponsableUsers() {
		return responsableUsers;
	}

	public void setResponsableUsers(List<User> responsableUsers) {
		this.responsableUsers = responsableUsers;
	}
	
	public Map<String, String> getCountriesCities() {
		return countriesCities;
	}

	public void setCountriesCities(Map<String, String> countriesCities) {
		this.countriesCities = countriesCities;
	}

	public ArrayList<String> getCities() {
		return cities;
	}

	public void setCities(ArrayList<String> cities) {
		this.cities = cities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((foundationYear == null) ? 0 : foundationYear.hashCode());
		result = prime * result + ((headquarters == null) ? 0 : headquarters.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Company)) {
			return false;
		}
		Company other = (Company) obj;
		if (cid == null) {
			if (other.cid != null) {
				return false;
			}
		} else if (!cid.equals(other.cid)) {
			return false;
		}
		if (size == null) {
			if (other.size != null) {
				return false;
			}
		} else if (!size.equals(other.size)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (foundationYear == null) {
			if (other.foundationYear != null) {
				return false;
			}
		} else if (!foundationYear.equals(other.foundationYear)) {
			return false;
		}
		if (headquarters == null) {
			if (other.headquarters != null) {
				return false;
			}
		} else if (!headquarters.equals(other.headquarters)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (website == null) {
			if (other.website != null) {
				return false;
			}
		} else if (!website.equals(other.website)) {
			return false;
		}
		return true;
	}
	
	public List<Job> loadUserCompanyUploadedJobs(int cid , int uid)
	{
		List<Job> jobs = new ArrayList<Job>();
		try 
		{
			DButil DBu = DButil.getInstance();
			jobs = DBu.getUserCompanyUploadedJobs(cid, uid);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		return jobs;
	}
	
	public void updateCities(Map<String,String> map ,ArrayList<String> arrayList,String string)
	{
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, String> entry : map.entrySet())
		{
		    if(entry.getKey().startsWith(string.toLowerCase()))
		    	list.add(entry.getValue());
		}
		arrayList.clear();
		arrayList.addAll(list);
	}
	
	public void updateTempCompanyId(int cid)
	{
		this.setTempCompanyId(cid);
	}
	
	public String uploadJobOffer(Job job,int uid)
	{
		try
		{
			if (job.getType().equals("-")) 
				job.setType(null);
			
			if(job.getExperienceLevel().equals("-"))
				job.setExperienceLevel(null);
			
			if(job.getWorkplace().equals("Remote"))
				job.setLocation(null);
			else
				job.setLocation(job.getLocationCity() + ", " + job.getLocationCountry());
			
			job.setPostDate(new Date());
			String expDate = job.getExpirationDateYear() + "-" + job.getExpirationDateMonth() + "-" + job.getExpirationDateDay();
			job.setExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse(expDate));
			job.setNumbOfApplicants(0);
			DButil DBu = DButil.getInstance();
			DBu.addJobToCompany(job, uid, this.tempCompanyId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "recuiter-home.xhtml";
	}
	
	public void deleteCompany(int cid , int uid)
	{
		try 
		{
			DButil DBu = DButil.getInstance();
			DBu.deleteCompany(cid);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	public Company searchCompany(int cid)
	{
		Company company= null;
		try 
		{
			DButil DBu = DButil.getInstance();
			company = DBu.getCompany(cid);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		return company;
	}
	
	
	
	
	
}
