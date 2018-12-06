package com.dj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.dj.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.CascadeType;
import javax.faces.bean.SessionScoped;
import com.DButil.DButil;

@ManagedBean(eager=true)
@SessionScoped

@Entity
@Table(name = "job")
public class Job 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jobid")
	private Integer jobid;
	
	@Transient
	private int tempJobId;
	
	@Column(name = "title")
	private String title;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "company")
	private Company company;
	
	@Transient
	private String locationCountry;
	@Transient
	private String locationCity;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "workplace")
	private String workplace;
	
	@Column(name = "exp_level")
	private String experienceLevel;
	
	@Column(name = "post_date")
	@Temporal(TemporalType.DATE)
	private Date postDate;
	
	@Transient
	private String expirationDateYear;
	@Transient
	private String expirationDateMonth;
	@Transient
	private String expirationDateDay;
	
	
	@Column(name = "expiration_date")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	@Column(name = "job_desc")
	private String jobDescription;
	
	@Column(name = "numb_of_applicants")
	private Integer numbOfApplicants;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "uploader_id")
	private User uploader;
	
	@Transient
	@ManagedProperty(value= "#{user}")
	private User userBean ;
	
	@Transient
	private Map<String,String> countriesCities1;
	@Transient
	private Map<String,String> countriesCities2;
	@Transient 
	private ArrayList<String> countries;
	@Transient
	private ArrayList<String> cities1;
	@Transient
	private ArrayList<String> cities2;
	@Transient
	private String searchKeywords;
	@Transient
	private String searchLocationCountry;
	@Transient
	private String searchLocationCity;
	@Transient
	private ArrayList<Job> searchedJobs;
	
	@OneToMany(mappedBy = "id.job" ,cascade = {CascadeType.ALL})
	private List<Application> jobApplications;
	
	@OneToMany(mappedBy = "id.job" ,cascade = {CascadeType.ALL})
	private List<SavedJob> savings;
	
	public Job()
	{
		countriesCities1 = new HashMap<String,String>();
		countriesCities1.put("usa1", "California");countriesCities1.put("usa2", "NewYork");countriesCities1.put("usa3", "Michigan");
		countriesCities1.put("germany1", "Berlin");countriesCities1.put("germany2", "Frankfurt");countriesCities1.put("germany3", "Dortmund");
		countriesCities1.put("tunisia1", "Ariana");countriesCities1.put("tunisia2", "Tunis");countriesCities1.put("tunisia3", "Sfax");
		
		countriesCities2 = new HashMap<String,String>();
		countriesCities2.put("usa1", "California");countriesCities2.put("usa2", "NewYork");countriesCities2.put("usa3", "Michigan");countriesCities2.put("usa4", "All");
		countriesCities2.put("germany1", "Berlin");countriesCities2.put("germany2", "Frankfurt");countriesCities2.put("germany3", "Dortmund");countriesCities2.put("germany4", "All");
		countriesCities2.put("tunisia1", "Ariana");countriesCities2.put("tunisia2", "Tunis");countriesCities2.put("tunisia3", "Sfax");countriesCities2.put("tunisia4", "All");
		
		ArrayList<String> a0 = new ArrayList<String>();
		ArrayList<String> a1 = new ArrayList<String>();
		ArrayList<String> a2 = new ArrayList<String>();
		
		a0.add("USA");a0.add("Germany");a0.add("Tunisia");
		a1.add("California");a2.add("NewYork");a1.add("Michigan");
		a2.add("California");a2.add("NewYork");a2.add("Michigan");a2.add("All");
		this.setCountries(a0);
		this.setCities1(a1);
		this.setCities2(a2);
	}


	public Job(Integer jobid, String title, Company company, String location, String type, String workplace,
			String experienceLevel, Date postDate, Date expirationDate, String jobDescription, Integer numbOfApplicants,User uploader) {
		super();
		this.jobid = jobid;
		this.title = title;
		this.company = company;
		this.location = location;
		this.type = type;
		this.workplace = workplace;
		this.experienceLevel = experienceLevel;
		this.postDate = postDate;
		this.expirationDate = expirationDate;
		this.jobDescription = jobDescription;
		this.numbOfApplicants = numbOfApplicants;
		this.uploader = uploader;
	}

	public Job(String title, Company company, String location, String type, String workplace, String experienceLevel,
			Date postDate, Date expirationDate, String jobDescription, Integer numbOfApplicants , User uploader) {
		super();
		this.title = title;
		this.company = company;
		this.location = location;
		this.type = type;
		this.workplace = workplace;
		this.experienceLevel = experienceLevel;
		this.postDate = postDate;
		this.expirationDate = expirationDate;
		this.jobDescription = jobDescription;
		this.numbOfApplicants = numbOfApplicants;
		this.uploader = uploader;
	}

	public Job(Integer jobid) {
		super();
		this.jobid = jobid;
	}


	public Integer getJobid() {
		return jobid;
	}

	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}

	public int getTempJobId() {
		return tempJobId;
	}

	public void setTempJobId(int tempJobId) {
		this.tempJobId = tempJobId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	public Integer getNumbOfApplicants() {
		return numbOfApplicants;
	}

	public void setNumbOfApplicants(Integer numbOfApplicants) {
		this.numbOfApplicants = numbOfApplicants;
	}
	
	public User getUploader() {
		return uploader;
	}


	public void setUploader(User uploader) {
		this.uploader = uploader;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	///////////////
	
	public String getLocationCountry() {
		return locationCountry;
	}

	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	////////////////
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}
	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	
//////////////////	

	public Date getPostDate() {
		return postDate;
	}


	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	public String getExpirationDateYear() {
		return expirationDateYear;
	}

	public void setExpirationDateYear(String expirationDateYear) {
		this.expirationDateYear = expirationDateYear;
	}

/////////////////	
	
	public String getExpirationDateMonth() {
		return expirationDateMonth;
	}


	public void setExpirationDateMonth(String expirationDateMonth) {
		this.expirationDateMonth = expirationDateMonth;
	}


	public String getExpirationDateDay() {
		return expirationDateDay;
	}


	public void setExpirationDateDay(String expirationDateDay) {
		this.expirationDateDay = expirationDateDay;
	}


	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public User getUserBean() {
		return userBean;
	}


	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	public Map<String, String> getCountriesCities1() {
		return countriesCities1;
	}

	public void setCountriesCities1(Map<String, String> countries_cities1) {
		this.countriesCities1 = countries_cities1;
	}

	public Map<String, String> getCountriesCities2() {
		return countriesCities2;
	}

	public void setCountriesCities2(Map<String, String> countriesCities2) {
		this.countriesCities2 = countriesCities2;
	}
	
	

	public ArrayList<String> getCountries() {
		return countries;
	}


	public void setCountries(ArrayList<String> countries) {
		this.countries = countries;
	}


	public ArrayList<String> getCities1() {
		return cities1;
	}

	public void setCities1(ArrayList<String> cities1) {
		this.cities1 = cities1;
	}

	public ArrayList<String> getCities2() {
		return cities2;
	}

	public void setCities2(ArrayList<String> cities2) {
		this.cities2 = cities2;
	}

	public String getSearchKeywords() {
		return searchKeywords;
	}

	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}

	public String getSearchLocationCountry() {
		return searchLocationCountry;
	}

	public void setSearchLocationCountry(String searchLocationCountry) {
		this.searchLocationCountry = searchLocationCountry;
	}

	public String getSearchLocationCity() {
		return searchLocationCity;
	}

	public void setSearchLocationCity(String searchLocationCity) {
		this.searchLocationCity = searchLocationCity;
	}

	public ArrayList<Job> getSearchedJobs() {
		return searchedJobs;
	}


	public void setSearchedJobs(ArrayList<Job> searchedJobs) {
		this.searchedJobs = searchedJobs;
	}

	public List<Application> getJobApplications() {
		return jobApplications;
	}


	public void setJobApplications(List<Application> jobApplications) {
		this.jobApplications = jobApplications;
	}


	public List<SavedJob> getSavings() {
		return savings;
	}


	public void setSavings(List<SavedJob> savings) {
		this.savings = savings;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((experienceLevel == null) ? 0 : experienceLevel.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((jobDescription == null) ? 0 : jobDescription.hashCode());
		result = prime * result + ((jobid == null) ? 0 : jobid.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((numbOfApplicants == null) ? 0 : numbOfApplicants.hashCode());
		result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uploader == null) ? 0 : uploader.hashCode());
		result = prime * result + ((workplace == null) ? 0 : workplace.hashCode());
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
		if (!(obj instanceof Job)) {
			return false;
		}
		Job other = (Job) obj;
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		if (experienceLevel == null) {
			if (other.experienceLevel != null) {
				return false;
			}
		} else if (!experienceLevel.equals(other.experienceLevel)) {
			return false;
		}
		if (expirationDate == null) {
			if (other.expirationDate != null) {
				return false;
			}
		} else if (!expirationDate.equals(other.expirationDate)) {
			return false;
		}
		if (jobDescription == null) {
			if (other.jobDescription != null) {
				return false;
			}
		} else if (!jobDescription.equals(other.jobDescription)) {
			return false;
		}
		if (jobid == null) {
			if (other.jobid != null) {
				return false;
			}
		} else if (!jobid.equals(other.jobid)) {
			return false;
		}
		if (location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!location.equals(other.location)) {
			return false;
		}
		if (numbOfApplicants == null) {
			if (other.numbOfApplicants != null) {
				return false;
			}
		} else if (!numbOfApplicants.equals(other.numbOfApplicants)) {
			return false;
		}
		if (postDate == null) {
			if (other.postDate != null) {
				return false;
			}
		} else if (!postDate.equals(other.postDate)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (uploader == null) {
			if (other.uploader != null) {
				return false;
			}
		} else if (!uploader.equals(other.uploader)) {
			return false;
		}
		if (workplace == null) {
			if (other.workplace != null) {
				return false;
			}
		} else if (!workplace.equals(other.workplace)) {
			return false;
		}
		return true;
	}

	public void apply(int uid, int jobid)
	{
		DButil DBu = DButil.getInstance();
		DBu.apply(uid, jobid);		
	}
	
	public void save(int uid, int jobid)
	{
		DButil DBu = DButil.getInstance();
		DBu.saveJob(uid, jobid);		
	}
	
	
	public void deleteApplication(int uid, int jobid)
	{
		DButil DBu = DButil.getInstance();
		DBu.deleteApplication(uid, jobid);
	}
	public void deleteSavedJob(int uid, int jobid)
	{
		DButil DBu = DButil.getInstance();
		DBu.deleteSavedJob(uid, jobid);
	}
	
	public String formatJobDescription(String s)
	{
		s=s.replaceAll("\n", "<br/>");
		return s;
	}
	
	public void updateTempJobId(int jobid)
	{
		this.setTempJobId(jobid);
	}
	
	public void updateLocation(String string)
	{
		ArrayList<String> list = new ArrayList<>();
		list.add("USA");list.add("Germany");list.add("Tunisia");
		
		if(string.equals("Remote"))
		{
			setCountries(new ArrayList<String>());
			setCities1(new ArrayList<String>());
		}
		else
		{
			setCountries(list);
			setLocationCountry("USA");
			updateCities(countriesCities1 ,cities1 ,locationCountry);
		}
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
	
	
	public void searchJobs()
	{
		searchKeywords = searchKeywords.toLowerCase();
		ArrayList<Job> results = new ArrayList<Job>();
		ArrayList<Job> descResults = new ArrayList<Job>();
		ArrayList<Company> searchedCompanies = null;
		DButil DBu = DButil.getInstance();		
		String[] wordsToRemove = {"of","with","at","from","into","during","including","until","against","among","throughout","despite","towards","upon","concerning","to","in","for","on","by","about","like","through","over","before","between","after","since","without","under","within","along","following","across","behind","beyond","plus","except","but","up","out","around","down","off","above","near","and","or","but","nor","so","for","yet","after","although","as","as if","as long as","because","before","even if","even though","once","since","so that","though","till","unless","until","what","when","whenever","wherever","whether","while","i","me","my","mine","myself","yourself","he","him","his","himself","she","her","her","hers","herself","it","its","itself","we","us","our","ours","ourselves","you","your","yours","yourselves","they","them","their","theirs","themselves","a few","few","fewer","fewest","every","most","that","a little","little","half","much","the","another","other","a","an","neither","these","all","no","this","any","those","both","least","our","what","each","less","several","which","either","many","some","whose","enough","more","such"};
		if(this.searchKeywords.equals("") && this.searchLocationCountry.equals("WorldWide"))
		{
			results = DBu.getAllJobs();
			setSearchedJobs(results);
		}
		
		else if(this.searchKeywords.equals("") && !this.searchLocationCountry.equals("WorldWide"))
		{
			results = DBu.searchJobsByLocation(this.searchLocationCountry, this.searchLocationCity, DBu.getAllJobs());
			setSearchedJobs(results);
		}
		
		else if(!this.searchKeywords.equals("") && this.searchLocationCountry.equals("WorldWide"))
		{	
			for (Company company : DBu.getAllCompanies()) 
			{
				if (Pattern.compile("(^| )" + company.getName().toLowerCase() + "( |$)").matcher(searchKeywords).find())
				{
					searchedCompanies = new ArrayList<Company>();
					searchedCompanies.add(company);
					//remove company
					searchKeywords = searchKeywords.replaceAll(company.getName().toLowerCase(), "");
				}
			}
			
			//cleaning
			for (String string : wordsToRemove) 
			{
				if (Pattern.compile("(^| )" + string + "( |$)").matcher(searchKeywords).find()) 
				{
					searchKeywords = searchKeywords.replaceAll(string, "");
				}
			}
			searchKeywords =  searchKeywords.replaceAll("(^| )[[^[^a-zA-Z]\\s]\\d]+( |$)", " ").replaceAll("(\\s{2,})", " ").trim();
			
			if (searchKeywords.equals("")&& searchedCompanies != null) 
			{
				for (Company company : searchedCompanies)
				{
					for (Job job : DBu.getCompanyJobs(company.getCid()))  
					{
						results.add(job);
					}
				}
				 setSearchedJobs(results);
				 return;
			}
			else if (!searchKeywords.equals("") && searchedCompanies != null) 
			{
				for (Company company : searchedCompanies)
				{
					for (Job job : DBu.getCompanyJobs(company.getCid()))
					{
						for (String string : searchKeywords.split(" ")) 
						{
							if (Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getTitle().toLowerCase()).find()) 
							{
								results.add(job);
								break;
							}
							else if(Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getJobDescription().toLowerCase()).find())
							{
								descResults.add(job);
								break;
							}
							else
								continue;
						}
					}
				}
			}
			else if(!searchKeywords.equals("") && searchedCompanies == null)
			{
				for (Job job : DBu.getAllJobs()) 
				{
					for (String string : searchKeywords.split(" ")) 
					{
						if (Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getTitle().toLowerCase()).find()) 
						{
							results.add(job);
							break;
						}
						else if(Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getJobDescription().toLowerCase()).find())
						{
							descResults.add(job);
							break;
						}
						else
							continue;
					}
				}
			}
			else
			{
				setSearchedJobs(results);
				return ;
			}
			
			results.addAll(descResults);
			setSearchedJobs(results);
		}
		else
		{
			for (Company company : DBu.getAllCompanies()) 
			{
				if (Pattern.compile("(^| )" + company.getName().toLowerCase() + "( |$)").matcher(searchKeywords).find())
				{
					searchedCompanies = new ArrayList<Company>();
					searchedCompanies.add(company);
					//remove company
					searchKeywords = searchKeywords.replaceAll(company.getName().toLowerCase() , "");
				}
			}
			
			//cleaning
			for (String string : wordsToRemove) 
			{
				if (Pattern.compile("(^| )" + string + "( |$)").matcher(searchKeywords).find()) 
				{
					searchKeywords = searchKeywords.replaceAll(string, "");
				}
			}
			searchKeywords =  searchKeywords.replaceAll("(^| )[[^[^a-zA-Z]\\s]\\d]+( |$)", " ").replaceAll("(\\s{2,})", " ").trim();
			
			if (searchKeywords.equals("") && searchedCompanies!= null) 
			{
				for (Company company : searchedCompanies)
				{
					results.addAll(DBu.searchJobsByLocation(searchLocationCountry, searchLocationCity, DBu.getCompanyJobs(company.getCid())));
				}
				setSearchedJobs(results);
				return;
			}
			
			else if (!searchKeywords.equals("") && searchedCompanies != null) 
			{
				for (Company company : searchedCompanies)
				{
					for (Job job : DBu.searchJobsByLocation(searchLocationCountry, searchLocationCity, DBu.getCompanyJobs(company.getCid())))
					{
						for (String string : searchKeywords.split(" ")) 
						{
							if (Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getTitle().toLowerCase()).find()) 
							{
								results.add(job);
								break;
							}
							else if(Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getJobDescription().toLowerCase()).find())
							{
								descResults.add(job);
								break;
							}
							else
								continue;
						}
					}
				}
				
			}
			else if(!searchKeywords.equals("") && searchedCompanies == null)
			{
				for (Job job : DBu.searchJobsByLocation(searchLocationCountry, searchLocationCity, DBu.getAllJobs())) 
				{
					for (String string : searchKeywords.split(" ")) 
					{
						if (Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getTitle().toLowerCase()).find()) 
						{
							results.add(job);
							break;
						}
						else if(Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getJobDescription().toLowerCase()).find())
						{
							descResults.add(job);
							break;
						}
						else
							continue;
					}
				}
			}
			else
			{
				setSearchedJobs(results);
				return;
			}
			
			results.addAll(descResults);
			setSearchedJobs(results);
		}
			
	}
	
	public void deleteJob(int jobid)
	{
		try 
		{
			DButil DBu = DButil.getInstance();
			DBu.deleteJob(jobid);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	public List<User> loadJobCandidates()
	{
		List<User> candidates = null;
		try 
		{
			DButil DBu = DButil.getInstance();
			candidates = DBu.getJobCandidates(tempJobId);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		return candidates;
	}
	
	public Job searchJob(int jobid)
	{
		Job job = null;
		try 
		{
			DButil DBu = DButil.getInstance();
			job = DBu.getJob(jobid);
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return job;
	}
	
	public void searchJobs1(int cid , int uid)
	{
		searchKeywords = searchKeywords.toLowerCase();
		ArrayList<Job> results = new ArrayList<Job>();
		ArrayList<Job> descResults = new ArrayList<Job>();
		DButil DBu = DButil.getInstance();		
		String[] wordsToRemove = {"of","with","at","from","into","during","including","until","against","among","throughout","despite","towards","upon","concerning","to","in","for","on","by","about","like","through","over","before","between","after","since","without","under","within","along","following","across","behind","beyond","plus","except","but","up","out","around","down","off","above","near","and","or","but","nor","so","for","yet","after","although","as","as if","as long as","because","before","even if","even though","once","since","so that","though","till","unless","until","what","when","whenever","wherever","whether","while","i","me","my","mine","myself","yourself","he","him","his","himself","she","her","her","hers","herself","it","its","itself","we","us","our","ours","ourselves","you","your","yours","yourselves","they","them","their","theirs","themselves","a few","few","fewer","fewest","every","most","that","a little","little","half","much","the","another","other","a","an","neither","these","all","no","this","any","those","both","least","our","what","each","less","several","which","either","many","some","whose","enough","more","such"};
		
		
		if(this.searchKeywords.equals("") && this.searchLocationCountry.equals("WorldWide"))
		{
			results = DBu.getUserCompanyUploadedJobs(cid, uid);
			setSearchedJobs(results);
		}
		
		else if(this.searchKeywords.equals("") && !this.searchLocationCountry.equals("WorldWide"))
		{
			results =  DBu.searchJobsByLocation(this.searchLocationCountry, this.searchLocationCity, DBu.getUserCompanyUploadedJobs(cid, uid));
			setSearchedJobs(results);
		}
		
		else if(!this.searchKeywords.equals("") && this.searchLocationCountry.equals("WorldWide"))
		{
			//cleaning
			for (String string : wordsToRemove) 
			{
				if (Pattern.compile("(^| )" + string + "( |$)").matcher(searchKeywords).find()) 
				{
					searchKeywords = searchKeywords.replaceAll(string, "");
				}
			}
			searchKeywords =  searchKeywords.replaceAll("(^| )[[^[^a-zA-Z]\\s]\\d]+( |$)", " ").replaceAll("(\\s{2,})", " ").trim();
			
			if (searchKeywords.equals("")) 
			{
				setSearchedJobs(results);
				return;
			}
			else
			{
				for (Job job : DBu.getUserCompanyUploadedJobs(cid, uid)) 
				{
					for (String string : searchKeywords.split(" ")) 
					{
						if (Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getTitle().toLowerCase()).find()) 
						{
							results.add(job);
							break;
						}
						else if(Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getJobDescription().toLowerCase()).find())
						{
							descResults.add(job);
							break;
						}
						else
							continue;
					}
				}
			}
			results.addAll(descResults);
			setSearchedJobs(results);
		}
		else
		{	
			//cleaning
			for (String string : wordsToRemove) 
			{
				if (Pattern.compile("(^| )" + string + "( |$)").matcher(searchKeywords).find()) 
				{
					searchKeywords = searchKeywords.replaceAll(string, "");
				}
			}
			searchKeywords =  searchKeywords.replaceAll("(^| )[[^[^a-zA-Z]\\s]\\d]+( |$)", " ").replaceAll("(\\s{2,})", " ").trim();
			
			if (searchKeywords.equals("")) 
			{
				setSearchedJobs(results);
				return;
			}
			
			else
			{
				for (Job job : DBu.searchJobsByLocation(searchLocationCountry, searchLocationCity, DBu.getUserCompanyUploadedJobs(cid, uid))) 
				{
					for (String string : searchKeywords.split(" ")) 
					{
						if (Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getTitle().toLowerCase()).find()) 
						{
							results.add(job);
							break;
						}
						else if(Pattern.compile("(^|[^a-zA-Z]+)" + string + "([^a-zA-Z]+|$)").matcher(job.getJobDescription().toLowerCase()).find())
						{
							descResults.add(job);
							break;
						}
						else
							continue;
					}
				}
			}
			
			results.addAll(descResults);
			setSearchedJobs(results);
		}	
	}

	public boolean hasExpired(int jobid)
	{
		DateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
		DButil DBu = DButil.getInstance();
		System.out.println(jobid);
		Job job =DBu.getJob(jobid);
		String[] parts = new SimpleDateFormat("yyyy-MM-dd").format(job.getExpirationDate()).split("-");
		if(Integer.parseInt(parts[0]) < Integer.parseInt(frmt.format(new Date()).split("-")[0]))
		{
			return true;
		}
		else if(Integer.parseInt(parts[0]) > Integer.parseInt(frmt.format(new Date()).split("-")[0]))
		{
			return false;
		}
		else
		{
			if(Integer.parseInt(parts[1]) < Integer.parseInt(frmt.format(new Date()).split("-")[1]))
			{
						return true;
			}
			else if(Integer.parseInt(parts[1]) > Integer.parseInt(frmt.format(new Date()).split("-")[1]))
			{
				return false;
			}
			else
			{
				if(Integer.parseInt(parts[2]) <= Integer.parseInt(frmt.format(new Date()).split("-")[2]))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
	}
	
}











