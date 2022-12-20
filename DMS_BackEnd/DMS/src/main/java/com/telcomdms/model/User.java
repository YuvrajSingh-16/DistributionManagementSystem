package com.telcomdms.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private long userId;
      @Column(nullable = false,unique = true)
      private String emailId;
      @Column(nullable = false)
      private String firstName;
      @Column(nullable=false,unique = true)
      private String contactDetails;
      @Column(nullable = false)
      private String lastName;
      @Column(nullable = false)
      private String password;
      @Column(nullable = false,unique = true)
      private String address;
      @Column(nullable = false)
      private String role;
      @Column(nullable =false)
      private String pincode;
      @Column(nullable = false)
      private boolean enable=true;
      
      @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="user")
      @Column(name = "stock_details")
      @JsonManagedReference
      private List<AvailableStock> availableStock;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<Orders> orderlist;

    public User() {
    }

    public List<AvailableStock> getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(List<AvailableStock> availableStock) {
        this.availableStock = availableStock;
    }

    public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


      public long getUserId() {
            return userId;
      }

      public void setUserId(long userId) {
            this.userId = userId;
      }

      public String getEmailId() {
            return emailId;
      }

      public void setEmailId(String emailId) {
            this.emailId = emailId;
      }

      public String getFirstName() {
            return firstName;
      }

      public void setFirstName(String firstName) {
            this.firstName = firstName;
      }

      public String getContactDetails() {
            return contactDetails;
      }

      public void setContactDetails(String contactDetails) {
            this.contactDetails = contactDetails;
      }

      public String getLastName() {
            return lastName;
      }

      public void setLastName(String lastName) {
            this.lastName = lastName;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public String getAddress() {
            return address;
      }

      public void setAddress(String address) {
            this.address = address;
      }

      public List<Orders> getOrderlist() {
            return orderlist;
      }

      public void setOrderlist(List<Orders> orderlist) {
            this.orderlist = orderlist;
      }


      public String getRole() {
            return role;
      }

      public void setRole(String role) {
            this.role = role;
      }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> set = new HashSet<>();
		set.add(new Authority(this.getRole()));
		
		return set;
	}

	@Override
	public String getUsername() {
		return this.emailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return this.enable;
	}
}
