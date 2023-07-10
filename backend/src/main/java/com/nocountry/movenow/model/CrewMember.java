package com.nocountry.movenow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CrewMember {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    String name;

    String lastname;

    String phoneNumber;


}
