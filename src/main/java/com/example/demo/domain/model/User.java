package com.example.demo.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "user_table")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private String password;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createDateTime;

  @ManyToMany
  @JoinTable(
    name = "user_group",
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "group_id")
  )
  private List<Group> groups = new ArrayList<>();
}
