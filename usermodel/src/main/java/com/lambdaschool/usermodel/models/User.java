package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User
        extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;


    @Column(nullable = false,
            unique = true)
    private String username;


    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<Campaign> campaigns = new ArrayList<>();


    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<UserRoles> roles = new HashSet<>();


    public User()
    {
    }

    public User(
            String username,
            String password)
    {
        setUsername(username);
        setPassword(password);
    }


    public long getUserid()
    {
        return userid;
    }


    public void setUserid(long userid)
    {
        this.userid = userid;
    }


    public String getUsername()
    {
        return username;
    }


    public void setUsername(String username)
    {
        this.username = username.toLowerCase();
    }


    public String getPassword()
    {
        return password;
    }


    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }


    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public List<Campaign> getCampaigns()
    {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns)
    {
        this.campaigns = campaigns;
    }


    public Set<UserRoles> getRoles()
    {
        return roles;
    }


    public void setRoles(Set<UserRoles> roles)
    {
        this.roles = roles;
    }


    @Override
    public String toString()
    {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", campaigns=" + campaigns +
                ", roles=" + roles +
                '}';
    }

    /**
     * Internally, user security requires a list of authorities, roles, that the user has. This method is a simple way to provide those.
     * Note that SimpleGrantedAuthority requests the format ROLE_role name all in capital letters!
     */
    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.roles)
        {
            String myRole = "ROLE_" + r.getRole()
                    .getName()
                    .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }
}
