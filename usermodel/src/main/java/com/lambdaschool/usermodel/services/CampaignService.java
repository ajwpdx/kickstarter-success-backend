package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Campaign;

import java.util.List;

public interface CampaignService
{
    List<Campaign> findAll();

    Campaign findCampaignById(long id);

    void delete(long id);

    Campaign save(Campaign campaign);

    Campaign update(Campaign campaign, long campaignid);

    public void deleteAll();
}
