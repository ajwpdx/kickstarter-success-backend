package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Campaign;

import java.util.List;

public interface CampaignService
{
    List<Campaign> findAll();

    Campaign findCampaignById(long id);

    void delete(long id);

    Campaign save(Campaign campaign, long postid);

    Campaign update(Campaign campaign, long postid);

    public void deleteAll();
}
