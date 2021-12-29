package com.example.demo.dto;
import java.util.Set;

import javax.validation.constraints.*;
public class SignupRequest {
	 @NotBlank
	    @Size(min = 3, max = 20)
	    private String username;
	 
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	    
	    @NotBlank
	    @Size(max = 30)
	    private String dateOfBirth;
	    
	    
	    @NotBlank
	    @Size(max = 30)
	    private String userType;
	    
	    private Set<String> role;
	    
	    @NotBlank
	    @Size(min = 6, max = 40)
	    private String password;
	    
	    private String newUpdates;
	  
	    public String getUsername() {
	        return username;
	    }
	 
	    public void setUsername(String username) {
	        this.username = username;
	    }
	    
	    public String getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

	 
	    public String getEmail() {
	        return email;
	    }
	 
	    public void setEmail(String email) {
	        this.email = email;
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public Set<String> getRole() {
	      return this.role;
	    }
	    
	    public void setRole(Set<String> role) {
	      this.role = role;
	    }

		public String getUserType() {
			return userType;
		}

		public void setUserType(String userType) {
			this.userType = userType;
		}

		public String getNewUpdates() {
			return newUpdates;
		}

		public void setNewUpdates(String newUpdates) {
			this.newUpdates = newUpdates;
		}
	    
	    
}
