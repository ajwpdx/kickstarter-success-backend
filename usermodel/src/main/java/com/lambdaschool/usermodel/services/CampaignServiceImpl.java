package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.models.Campaign;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.repository.CampaignRepository;
import com.lambdaschool.usermodel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "campaignService")
public class CampaignServiceImpl implements CampaignService
{
    //Autowires
    @Autowired
    CampaignRepository campaignrepos;

    @Autowired
    UserRepository userrepos;

    @Autowired
    UserService userService;

    @Autowired
    private UserAuditing userAuditing;


    //Servicers
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

    //TODO
    @Transactional
    @Override
    public Campaign save(Campaign campaign)
    {
        return null;
    }

    //TODO
    @Transactional
    @Override
    public Campaign update(Campaign campaign, long campaignid)
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


