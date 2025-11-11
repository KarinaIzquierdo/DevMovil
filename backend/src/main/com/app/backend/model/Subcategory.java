package com.app.backend.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.list;
import java.util.Locale.Category;

@Data
@Entity
@Table(name="subcategories")


public class Subcategory {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String name;

@Column(length = 500)
private String description;

@Column(nullable = false)
private Boolean active = true;


@ManyToOne
@JoinColumn(name="category_id",nullable = false)
private Category category;

@OneToMany(mappedBy = "category", cascade =CascadeType.ALL)
@JsonIgnore
private list<Product>products;

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


public  Category getCategory(
){
    return category;
}


public  Void setCategory(Category category)
{
    this.category = category;
}


}
