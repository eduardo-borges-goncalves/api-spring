package com.example.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "group_table")
public class Group {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  
  @ManyToMany
  @JoinTable( 
    name = "group_permission", 
    joinColumns = @JoinColumn(name = "group_id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id")
  )
  private List<Permission> permissions = new ArrayList<>();
}
