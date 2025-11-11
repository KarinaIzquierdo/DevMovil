package com.app.backend.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.list;

@Data
@Entity
@Table(name="categories")


public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String name;

@Column(length = 500)
private String description;

@Column(nullable = false)
private Boolean active = true;

@OneToMany(mappedBy = "category", cascade =CascadeType.ALL)
@JsonIgnore
private list<Subcategory>Subcategories;

public Long getId(){
    return id;
}


public void setId(){
    this.id = id;
}


public String getName(){
    this.name=name;
}


public void setName(String name){
    this.name = name;
}


public String getDescription(
){
    return description;
}


public void setDescription(String description
){
    this.description=description;
}




public boolean getActive(){
    return active;
}


public Void setActive(Boolean active){
    this.active=active;
}


public  List<Subcategory> getSubcategories(
){
    return subcategories;
}


public  Void setSubcategories(List<Subcategory>subcategories)
{
    this.setSubcategories=setSubcategories;
}


}
