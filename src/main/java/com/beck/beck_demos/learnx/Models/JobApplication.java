package com.beck.beck_demos.learnx.Models;

import java.time.Instant;
import java.util.Date;

public class JobApplication {
  private Integer applicationId;
  private Integer jobId;
  private String firstName;
  private String lastName;
  private String email;
  private double desiredSalary;
  private Date earliestStartDate;
  private Instant dateSubmitted;
  private String status;

  public JobApplication(){}

  public JobApplication(Integer applicationId, Integer jobId, String firstName, String lastName, String email, double desiredSalary, Date earliestStartDate, Instant dateSubmitted, String status) {

    this.applicationId = applicationId;
    this.jobId = jobId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.desiredSalary = desiredSalary;
    this.earliestStartDate = earliestStartDate;
    this.dateSubmitted = dateSubmitted;
    this.status = status;
  }
  public Integer getapplicationId() {
    return applicationId;
  }
  public void setapplicationId(Integer applicationId) {
    this.applicationId = applicationId;
  }
  public Integer getjobId() {
    return jobId;
  }
  public void setjobId(Integer jobId) {
    this.jobId = jobId;
  }
  public String getfirstName() {
    return firstName;
  }
  public void setfirstName(String firstName) {
    firstName = firstName.replaceAll("[^A-Za-z0-9 - ]","");
    if(firstName.length()<4){
      throw new IllegalArgumentException("firstName is too short.");
    }
    if(firstName.length()>255){
      throw new IllegalArgumentException("firstName is too long.");
    }
    this.firstName = firstName;
  }
  public String getlastName() {
    return lastName;
  }
  public void setlastName(String lastName) {
    lastName = lastName.replaceAll("[^A-Za-z0-9 - ]","");
    if(lastName.length()<4){
      throw new IllegalArgumentException("lastName is too short.");
    }
    if(lastName.length()>255){
      throw new IllegalArgumentException("lastName is too long.");
    }
    this.lastName = lastName;
  }
  public String getemail() {
    return email;
  }
  public void setemail(String email) {
    email = email.replaceAll("[^A-Za-z0-9 - ]","");
    if(email.length()<4){
      throw new IllegalArgumentException("email is too short.");
    }
    if(email.length()>255){
      throw new IllegalArgumentException("email is too long.");
    }
    this.email = email;
  }
  public double getdesiredSalary() {
    return desiredSalary;
  }
  public void setdesiredSalary(double desiredSalary) {
    if(desiredSalary<0){
      throw new IllegalArgumentException("Salary must be non-negative");
    }
    this.desiredSalary = desiredSalary;
  }
  public Date getearliestStartDate() {
    return earliestStartDate;
  }
  public void setearliestStartDate(Date earliestStartDate) {
    this.earliestStartDate = earliestStartDate;
  }
  public Instant getdateSubmitted() {
    return dateSubmitted;
  }
  public void setdateSubmitted(Instant dateSubmitted) {
    this.dateSubmitted = dateSubmitted;
  }
  public String getstatus() {
    return status;
  }
  public void setstatus(String status) {
    status = status.replaceAll("[^A-Za-z0-9 - ]","");
    if(status.length()<4){
      throw new IllegalArgumentException("status is too short.");
    }
    if(status.length()>255){
      throw new IllegalArgumentException("status is too long.");
    }
    this.status = status;
  }

}


