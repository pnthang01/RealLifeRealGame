package com.rlrg.dataserver.badge.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.rlrg.dataserver.badge.entity.Badge;

public class BadgeCriteriaHelper {
	
	public static Specification<Badge> findAvaiableBadgeByEligibilityAndUserId(final List<Integer> usersAchie, final List<String> params){
		return new Specification<Badge>() {
			@Override
			public Predicate toPredicate(Root<Badge> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> critList = new ArrayList<Predicate>();
				for(String param : params){
					Predicate temp = cb.like(root.<String>get("eligibility"), 
							new StringBuffer("%").append(param).append("%").toString());
					if(critList.size() == 0){
						critList.add(temp);
					} else {
						critList.set(0, cb.or(critList.get(0), temp));
					}
				}
				if(usersAchie.size() > 0){
					Predicate avaiBadge = cb.not(root.<Integer>get("id").in(usersAchie));
					critList.add(avaiBadge);	
				}
				//				
				return query.where(critList.toArray(new Predicate[critList.size()])).getRestriction();
			}
		};
	}
	
}
