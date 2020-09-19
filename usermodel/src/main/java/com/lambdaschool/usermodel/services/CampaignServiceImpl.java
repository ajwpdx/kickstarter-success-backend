package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.models.Campaign;
import com.lambdaschool.usermodel.repository.CampaignRepository;
import com.lambdaschool.usermodel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class CampaignServiceImpl implements CampaignService
{
    //autowires
    @Autowired
    CampaignRepository campaignrepos;

    @Autowired
    UserRepository userrepos;

    @Autowired
    UserService userService;

    @Autowired
    private UserAuditing userAuditing;


    //servicers
    @Override
    public List<Campaign> findAll()
    {
        List<Campaign> list = new ArrayList<>();
        campaignrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Campaign findCampaignById(long id)
    {
        return campaignrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign with id " + id + " not found!"));
    }

    @Override
    public void delete(long id)
    {
        if (campaignrepos.findById(id)
                .isPresent())
        {
            campaignrepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException("Campaign with id " + id + " not found!");
        }
    }

    @Transactional
    @Override
    public Campaign save(Campaign campaign, long postid)
    {
        Campaign newCampaign = new Campaign();

        if (campaign.getCampaignid() != 0)
        {
            campaignrepos.findById(campaign.getCampaignid())
                    .orElseThrow(() -> new ResourceNotFoundException("Post id " + campaign.getCampaignid() + " not found!"));
        }

    ///write out the rest of this

        return null;
    }

    @Transactional
    @Override
    public Campaign update(Campaign campaign, long postid)
    {
        return null;
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        campaignrepos.deleteAll();
    }
}

}
