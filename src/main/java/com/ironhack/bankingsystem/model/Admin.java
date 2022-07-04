package com.ironhack.bankingsystem.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="admin_id")
public class Admin extends User{
}
