package com.beck.beck_demos.learnx.Models;

import java.time.Instant;

public class JobListing {
  private int job_id;
  private Department department;
  private boolean featured;
  private String position;
  private Instant posted_at;
  private String contract;
  private String location;
  private String description;

  public JobListing(int job_id, Department department, boolean featured, String position, Instant posted_at, String contract, String location, String description) {
    this.job_id = job_id;
    this.department = department;
    this.featured = featured;
    this.position = position;
    this.posted_at = posted_at;
    this.contract = contract;
    this.location = location;
    this.description = description;
  }

  public JobListing() {

  }

  public int getJob_id() {
    return job_id;
  }

  public void setJob_id(int job_id) {
    this.job_id = job_id;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public boolean isFeatured() {
    return featured;
  }

  public void setFeatured(boolean featured) {
    this.featured = featured;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Instant getPosted_at() {
    return posted_at;
  }

  public void setPosted_at(Instant posted_at) {
    this.posted_at = posted_at;
  }

  public String getContract() {
    return contract;
  }

  public void setContract(String contract) {
    this.contract = contract;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "JobListing{" +
        "job_id=" + job_id +
        ", department=" + department +
        ", featured=" + featured +
        ", position='" + position + '\'' +
        ", posted_at=" + posted_at +
        ", contract='" + contract + '\'' +
        ", location='" + location + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
