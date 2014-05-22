package com.team.domain;
// Generated May 22, 2014 3:25:47 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LangBad generated by hbm2java
 */
@Entity
@Table(name="lang_bad"
    ,catalog="rlrg"
)
public class LangBad  implements java.io.Serializable {


     private Integer id;
     private Badge badge;
     private Language language;
     private String name;
     private String description;

    public LangBad() {
    }

	
    public LangBad(Badge badge, Language language) {
        this.badge = badge;
        this.language = language;
    }
    public LangBad(Badge badge, Language language, String name, String description) {
       this.badge = badge;
       this.language = language;
       this.name = name;
       this.description = description;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="badge_id", nullable=false)
    public Badge getBadge() {
        return this.badge;
    }
    
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="language_id", nullable=false)
    public Language getLanguage() {
        return this.language;
    }
    
    public void setLanguage(Language language) {
        this.language = language;
    }

    
    @Column(name="name", length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="description", length=45)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


