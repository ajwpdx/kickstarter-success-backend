package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.models.Campaign;
import com.lambdaschool.usermodel.services.CampaignService;
import com.lambdaschool.usermodel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/campaign")
public class CampaignController
{

    @Autowired
    CampaignService campaignService;

    @Autowired
    UserService userService;

// PreAuthorize this to Admin
    @GetMapping(value = "/all",
            produces = {"application/json"})
    public ResponseEntity<?> listAllCampaigns(HttpServletRequest request)
    {
        List<Campaign> allCampaigns = campaignService.findAll();
        return new ResponseEntity<>(allCampaigns, HttpStatus.OK)
    }
}
