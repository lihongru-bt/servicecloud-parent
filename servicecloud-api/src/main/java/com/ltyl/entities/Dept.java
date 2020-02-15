package com.ltyl.entities;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Dept implements Serializable {

    private Long deptno;//主键
    private String dname;//部门名称
    private String db_source;//来自哪个数据库

    public Dept (String dname){
        super();

        this.dname = dname;

    }

}
