package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "campaigns")
public class Campaign
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long campaignid;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String category;

    @Column(nullable = true)
    private long goal;

    @Column(nullable = true)
    private String currency;

    @Column(nullable = true)
    private Date launchdate;

    @Column(nullable = true)
    private boolean successprediction;

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties(value = "campaigns", allowSetters = true)
    private User user;

    //default constructor
    public Campaign()
    {
    }

    public Campaign(String name, String category, long goal, String currency, Date launchdate, boolean successprediction, User user)
    {
        this.name = name;
        this.category = category;
        this.goal = goal;
        this.currency = currency;
        this.launchdate = launchdate;
        this.successprediction = successprediction;
        this.user = user;
    }

    public Campaign(String name, String category, long goal, String currency, Date launchdate, boolean successprediction)
    {
        this.name = name;
        this.category = category;
        this.goal = goal;
        this.currency = currency;
        this.launchdate = launchdate;
        this.successprediction = successprediction;

    }

    public long getCampaignid()
    {
        return campaignid;
    }

    public void setCampaignid(long campaignid)
    {
        this.campaignid = campaignid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public long getGoal()
    {
        return goal;
    }

    public void setGoal(long goal)
    {
        this.goal = goal;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public Date getLaunchdate()
    {
        return launchdate;
    }

    public void setLaunchdate(Date launchdate)
    {
        this.launchdate = launchdate;
    }

    public boolean isSuccessprediction()
    {
        return successprediction;
    }

    public void setSuccessprediction(boolean successprediction)
    {
        this.successprediction = successprediction;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
