package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name="buliding")
@Setter
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "build_id")
    private Long id;


    private String name;

    private String electricKwh; // 전기 (KWH)

    private String heatingCoolingArea; // 냉난방면적

    private String gasKwh; // 가스 (KWH)

    private String floorArea; // 연면적




    public Building(String name, String electricKwh, String heatingCoolingArea, String gasKwh, String floorArea) {
        this.id=id;
        this.name = name;
        this.electricKwh = electricKwh;
        this.heatingCoolingArea = heatingCoolingArea;
        this.gasKwh = gasKwh;
        this.floorArea = floorArea;
    }


    public Building() {

    }
}
