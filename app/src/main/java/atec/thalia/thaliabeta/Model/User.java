package atec.thalia.thaliabeta.Model;

import java.util.ArrayList;


public class User {

	public static final int FEMALE=1,MALE=2,WATCHED=1,WATCHING=2;

	String id;
	
	 String firstname;
	 String pathimage;

	 String lastname;
	 
	 String email;

	 String birthdate;

	 int gender;

	 String city;

	 String country;

	 int type;

	 String phonenumber;

	int tokkensquantity;
	
	Boolean status, accactivated;
	Category category;

	ArrayList <Tag> preferences;
	
	ArrayList <Watch> watching,watched;
	
	ArrayList<String> hashes;


	public User() {
	}

	public User(String firstname, String lastname, String email, String pathimage, String birthdate, int gender,
				String city, String country, int type, String phonenumber, int tokkensquantity, Boolean status,
				Boolean accactivated, Category category) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.pathimage = pathimage;
		this.birthdate = birthdate;
		this.gender = gender;
		this.city = city;
		this.country = country;
		this.type = type;
		this.phonenumber = phonenumber;
		this.tokkensquantity = tokkensquantity;
		this.status = status;
		this.accactivated = accactivated;
		this.category = category;
		this.preferences = new ArrayList<>();
		this.hashes = new ArrayList<>();
		this.watched = new ArrayList<>();
		this.watching = new ArrayList<>();
		
	}


	

	public String getFirstname() {
		return firstname;
	}




	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}




	public String getLastname() {
		return lastname;
	}




	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


    public String getPathimage() {
        return pathimage;
    }

    public void setPathimage(String pathimage) {
        this.pathimage = pathimage;
    }

    public String getBirthdate() {
		return birthdate;
	}




	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}




	public String getPhonenumber() {
		return phonenumber;
	}




	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}




	public int getTokkensquantity() {
		return tokkensquantity;
	}




	public void setTokkensquantity(int tokkensquantity) {
		this.tokkensquantity = tokkensquantity;
	}




	public Boolean getAccactivated() {
		return accactivated;
	}




	public void setAccactivated(Boolean accactivated) {
		this.accactivated = accactivated;
	}




	public Category getCategory() {
		return category;
	}




	public void setCategory(Category category) {
		this.category = category;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	

	public ArrayList<Tag> getPreferences() {
		return preferences;
	}

	public ArrayList<Watch> getWatching() {
		return watching;
	}

	public ArrayList<Watch> getWatched() {
		return watched;
	}

	public ArrayList<String> getHashes() {
		return hashes;
	}
	
	
	public ArrayList<Tag> mockupdata(){
		
		ArrayList<Tag> aux = new ArrayList<>();
		aux.add(new Tag("Musica",false));
		return aux;
		
	}
	
	

}
