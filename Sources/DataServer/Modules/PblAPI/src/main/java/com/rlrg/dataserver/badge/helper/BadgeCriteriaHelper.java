package com.rlrg.dataserver.badge.helper;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.rlrg.dataserver.badge.entity.Badge;

public class BadgeCriteriaHelper {
	
	public static Specification<Badge> findAvaiableBadgeByEligibilityAndUserId(final List<Integer> usersAchie, final String...params){
		return new Specification<Badge>() {
			@Override
			public Predicate toPredicate(Root<Badge> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate[] critList = new Predicate[params.length+1];
				Predicate avaiBadge = cb.not(root.<Integer>get("id").in(usersAchie));
				critList[0] = avaiBadge;
				//
				int i = 1;
				//Predicate test = cb.and(critList);
				for(String param : params){
					Predicate temp = cb.like(root.<String>get("eligibility"), 
							new StringBuffer("%").append(param).append("%").toString());
					critList[i] = temp;
					i++;
				}
				return query.where(critList).getRestriction();
			}
		};
	}
	
}
