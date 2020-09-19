package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Campaign;
import org.springframework.data.repository.CrudRepository;

public interface CampaignRepository extends CrudRepository<Campaign, Long>
{
}
