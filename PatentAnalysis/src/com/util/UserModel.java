package com.util;



public class UserModel implements NewInterface {
    private String uid;
    private String uname;
    private String pwd;
    private String address;
    private String phone;
    private String name;
    private String role;
    private String uses;

    @Override
public String getUid() {
	return uid;
}

    @Override
public void setUid(String uid) {
	this.uid = uid;
}

    @Override
public String getUname() {
	return uname;
}

    @Override
public void setUname(String uname) {
	this.uname = uname;
}

    @Override
public String getPwd() {
	return pwd;
}

    @Override
public void setPwd(String pwd) {
	this.pwd = pwd;
}

    @Override
public String getName() {
	return name;
}

    @Override
public void setName(String name) {
	this.name = name;
}



    @Override
public String getPhone() {
	return phone;
}

    @Override
public void setPhone(String phone) {
	this.phone = phone;
}

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the use
     */
    public String getUses() {
        return uses;
    }

    /**
     * @param use the use to set
     */
    public void setUses(String use) {
        this.uses = use;
    }





}
