package com.orc.demo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author orcki
 */
@Getter
@Setter
@ToString(exclude = "id")
public class User implements Serializable {

    private static final long serialVersionUID = -7262439592239164209L;
    private int id;

    private String userName;

    private String email;

    private  String phone;

    private  String address;

    private Date registerDate;

    private String info;

}
