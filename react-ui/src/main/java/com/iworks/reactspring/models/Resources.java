package com.iworks.reactspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table(name = "resources")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resources implements Serializable,Comparable<Resources> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourcesId;
    private Long parentId;
    private String resourceName;
    private long resourceCode;
    private short resourceGrade;
    private long resourceSort;
    private int resourceType;
    private String resourceValue;
    private String resourcePower;
    private int visitType;
    private String remarks;
    private int state;
    private Date createdDate;
    private String resourceNameCn;
    private String iconUrl;
    private int status;

    private List<Role> roleList;

    private Set<Resources> realSubResoSet;

    private Long realSubCount;

    private Long isChoice = 0L;

    private Long roleId;

    public Long getResourceSort() {
        return resourceSort;
    }

    @Override
    public int compareTo(Resources resources) {
        return Integer.parseInt(String.valueOf((getResourceSort()-resources.getResourceSort())));
    }
}

