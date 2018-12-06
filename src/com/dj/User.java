package com.dj;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.dj.Job;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.DButil.DButil;

@ManagedBean(eager=true)
@SessionScoped

@Entity
@Table(name = "user")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_numb")
	private String phoneNumber;
	
	@Column(name = "country")
	private String country;
	
	@Transient
	private String birthdayYear;
	@Transient
	private String birthdayMonth;
	@Transient
	private String birthdayDay;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "password")
	private String password;
	
	@Transient
	private ArrayList<String> countryOptions;
	
	@Transient
	private Part CV0;
	
	@Column(name = "cv")
	@Lob
	private byte[] CV ;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "user_company",
			   joinColumns = @JoinColumn(name = "uid"),
			   inverseJoinColumns = @JoinColumn(name = "cid"))
	private List<Company> managedCompanies ;
	
	@Transient
	private List<Company> managedCompanies1;
	
	@OneToMany(mappedBy = "uploader" ,cascade = {CascadeType.ALL})
	private List<Job> uploadedJobs;
	
	@OneToMany(mappedBy = "id.user" ,cascade = {CascadeType.ALL})
	private List<Application> userApplications;
	
	@OneToMany(mappedBy = "id.user" ,cascade = {CascadeType.ALL})
	private List<SavedJob> savedJobs;
	
	@Transient
	@ManagedProperty(value= "#{company}")
	private Company companyBean ;
	
	//default constructor
	public User () throws Exception 
	{
		countryOptions =new ArrayList<String>();
		countryOptions.add("USA");
		countryOptions.add("Germany");
		countryOptions.add("Tunisia");
	}
	
	
	
	public User(int uid, String firstName, String lastName, String email, String phoneNumber, String country,
			Date birthday, String password, byte[] CV) {
		super();
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.birthday = birthday;
		this.password = password;
		this.CV = CV;
	}

	public User(String firstName, String lastName, String email, String phoneNumber, String country, Date birthday,
			String password, byte[] CV) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.birthday = birthday;
		this.password = password;
		this.CV = CV;
	}



	public User(int uid) {
		super();
		this.uid = uid;
	}

	public ArrayList<String> getCountryOptions() {
		return countryOptions;
	}
	public void setCountryOptions(ArrayList<String> countryOptions) 
	{
		this.countryOptions = countryOptions;
	}
	public String getCountry() 
	{
		return country;
	}
	public void setCountry(String Country) 
	{
			this.country=Country;
	}
	
	
	
	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
		
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthdayYear() {
		return birthdayYear;
	}



	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}



	public String getBirthdayMonth() {
		return birthdayMonth;
	}



	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}



	public String getBirthdayDay() {
		return birthdayDay;
	}



	public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}
	
	public Part getCV0() {
		return CV0;
	}


	public void setCV0(Part CV0) {
		this.CV0 = CV0;
	}


	public byte[] getCV() {
		return CV;
	}

	public void setCV(byte[] CV) {
		this.CV = CV;
	}

	public List<Company> getManagedCompanies() {
		return managedCompanies;
	}

	public void setManagedCompanies(List<Company> managedCompanies) {
		this.managedCompanies = managedCompanies;
	}

	public List<Company> getManagedCompanies1() {
		return managedCompanies1;
	}

	public void setManagedCompanies1(List<Company> managedCompanies1) {
		this.managedCompanies1 = managedCompanies1;
	}


	public List<Job> getUploadedJobs() {
		return uploadedJobs;
	}


	public void setUploadedJobs(List<Job> uploadedJobs) {
		this.uploadedJobs = uploadedJobs;
	}
	

	public List<Application> getUserApplications() {
		return userApplications;
	}

	public void setUserApplications(List<Application> userApplications) {
		this.userApplications = userApplications;
	}

	public List<SavedJob> getSavedJobs() {
		return savedJobs;
	}

	public void setSavedJobs(List<SavedJob> savedJobs) {
		this.savedJobs = savedJobs;
	}

	public Company getCompanyBean() {
		return companyBean;
	}

	public void setCompanyBean(Company companyBean) {
		this.companyBean = companyBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + uid;
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
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (birthday == null) {
			if (other.birthday != null) {
				return false;
			}
		} else if (!birthday.equals(other.birthday)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}

		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (phoneNumber == null) {
			if (other.phoneNumber != null) {
				return false;
			}
		} else if (!phoneNumber.equals(other.phoneNumber)) {
			return false;
		}
		if (uid != other.uid) {
			return false;
		}
		return true;
	}

	
	public void validatePassword(FacesContext context, UIComponent component ,Object value) throws ValidatorException
	{
		String email = value.toString();
		
		UIInput Password = (UIInput)component.getAttributes().get("passwrd");
		String password = Password.getSubmittedValue().toString();

		DButil DBu = DButil.getInstance();
		Map<String,String> map= DBu.getEmailAndPassword();
		for(Map.Entry<String,String> m : map.entrySet())
		{
			if(m.getKey().equals(email))
			{
				if(!m.getValue().equals(password)&& !password.isEmpty())
				{
					FacesMessage message2 = new FacesMessage("invalid password!");
					throw new ValidatorException(message2);
				}
				else
					return;
			}
		}
		FacesMessage message1 = new FacesMessage("Entered email is invalid!");
		throw new ValidatorException(message1);
		
	}
	public void verifyEmailNonExistance(FacesContext context, UIComponent component ,Object value)throws ValidatorException
	{
		String email = value.toString();
	    String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
	    java.util.regex.Matcher m = p.matcher(email);
	    if(!m.matches())
	    {
			FacesMessage message1 = new FacesMessage("email format is invalid");
			throw new ValidatorException(message1);
	    }
		DButil DBu = DButil.getInstance();
		boolean existance = DBu.verifyEmail(email);
		
		
		if(existance)
		{
			FacesMessage message1 = new FacesMessage("this email is already taken !");
			throw new ValidatorException(message1);
		}
		
	}	
	
	public String addUser()
	{
		try
		{
			String Sbirthday = birthdayYear + "-" + birthdayMonth + "-" + birthdayDay;
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(Sbirthday);
			User u = new User(firstName , lastName , email , phoneNumber , country , birthday , password,null);
			DButil DBu = DButil.getInstance();
			DBu.addUser(u);
		}
		catch(ParseException pe)
		{
			pe.printStackTrace();
		}
		return updateUser();
	}
	public String updateUser()
	{
		DButil DBu = DButil.getInstance();
		User user = DBu.getUserAfterLogin(this.getEmail());
		this.setUid(user.getUid());this.setFirstName(user.getFirstName());this.setLastName(user.getLastName());this.setPhoneNumber(user.getPhoneNumber());
		this.setCountry(user.getCountry());this.setBirthday(user.getBirthday());this.setCV(user.getCV());
		return "user-home.xhtml";
	}
	public boolean possibilityToApply(int jobid)
	{
		ArrayList<Job> appliedJobs ;
		DButil DBu = DButil.getInstance();
		appliedJobs = DBu.getUserAppliedJobs(getUid());
		for(int i=0;i<appliedJobs.size();i++)
		{
			if(jobid == appliedJobs.get(i).getJobid())
			{
				return false;
			}
		}
		return true;
	}
	public boolean possibilityToSave(int jobid)
	{
		ArrayList<Job> savedJobs ;
		DButil DBu = DButil.getInstance();
		savedJobs = DBu.getUserSavedJobs(getUid());
		for(int i=0;i<savedJobs.size();i++)
		{
			if(jobid==savedJobs.get(i).getJobid())
			{
				return false;
			}
		}
		return true;
	}

	//method for saving cv to file system
	
	public void downloadUserCV(int uid)
	{
	    try 
	    {
	    	DButil DBu = DButil.getInstance();
	    	InputStream input = new ByteArrayInputStream(DBu.getCV(uid));
	    	File userDownloadsDirectory = new File(System.getProperty("user.home") + "/Downloads");
	        Files.copy(input, new File(userDownloadsDirectory, DBu.getUser(uid).getFirstName() + " " + DBu.getUser(uid).getLastName() + " " + "CV").toPath(), StandardCopyOption.REPLACE_EXISTING);
	    }
	    catch (IOException ioe) 
	    {
	        ioe.printStackTrace();
	    }
	}
	
	//method for saving cv to database
	public void saveCvToDB(int uid)
	{
		try
		{
			if(CV0 == null)
			{
				return;
			}
			DButil DBu = DButil.getInstance();
			InputStream input = CV0.getInputStream();
			CV = IOUtils.toByteArray(input); 
			DBu.saveCvToDB(uid, CV);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	//method for getting cv from database and save it to file system
	/*
	public void downloadUserCV(int uid) throws Exception
	{
		File destination = new File("C:\\uploads\readCv.pdf");
		FileOutputStream fos = new FileOutputStream(destination);
		InputStream input = result.getBinaryStream("cv");
		byte[] buffer = new byte[1024];
		while(input.read(buffer)>0)
		{
			fos.write(buffer);
		}
	}
	*/
	
	// if cv is already uploaded , return true , else return false
	public boolean CVIsUploaded()
	{
		byte[] b ;
		DButil DBu = DButil.getInstance();
		
		b = DBu.getCV(this.getUid());
		if (b == null)
			return false;
		else
			return true;
	}
	
	//if size is more than 16MB return true ,else return false
	public boolean verifyCVSize() throws IOException
	{
		if (CV0== null)
			return false;

		if(IOUtils.toByteArray(CV0.getInputStream()).length>16777215)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public List<Company> loadManagedCompanies()
	{
		List<Company> managedCompanies = new ArrayList<Company>();
		
		try
		{
		DButil DBu = DButil.getInstance();
		managedCompanies = DBu.getUMCompanies(this.uid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return managedCompanies;
	}
	
	public String addCompany(Company company)
	{
		try
		{
			if(companyBean.getFoundationYear().equals("-"))
				company.setFoundationYear(null);
			
			if(companyBean.getType().equals("-"))
				company.setType(null);
			
			if(companyBean.getSize().equals("-"))
				company.setSize(null);
			
			if(companyBean.getHeadquartersCountry().equals("-"))
				company.setHeadquarters(null);
			else
				company.setHeadquarters(companyBean.getHeadquartersCity() + ", " + companyBean.getHeadquartersCountry());
			
			if (companyBean.getCompanyLogo0() != null) 
			{
				InputStream input = companyBean.getCompanyLogo0().getInputStream();
				company.setCompanyLogo(IOUtils.toByteArray(input));
			}
			else
			{	
				Path fileLocation = Paths.get("C:/Users/clark/eclipse-neon-3-workspace/DreamJob/WebContent/resources/images/undefined.png");
				byte[] data = Files.readAllBytes(fileLocation);
				company.setCompanyLogo(data);
			}
			
			DButil DBu = DButil.getInstance();
			DBu.addCompany(company, uid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "recuiter-home.xhtml";
	}
	
	
	public ArrayList<Job> loadAppliedJobs()
	{
		ArrayList<Job> appliedJobs = null;
		try
		{
			DButil DBu = DButil.getInstance();
			appliedJobs = DBu.getUserAppliedJobs(uid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return appliedJobs;
	}
	
	public ArrayList<Job> loadSavedJobs()
	{
		ArrayList<Job> savedJobs = null;
		try
		{
			DButil DBu = DButil.getInstance();
			savedJobs = DBu.getUserSavedJobs(uid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return savedJobs;
	}
	
	public ArrayList<Job> loadAllJobs()
	{
		ArrayList<Job> allJobs = null;
		try
		{
			DButil DBu = DButil.getInstance();
			allJobs = DBu.getAllJobs();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return allJobs;
	}
}