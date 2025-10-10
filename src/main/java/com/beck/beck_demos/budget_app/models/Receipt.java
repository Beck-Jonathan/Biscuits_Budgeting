package com.beck.beck_demos.budget_app.models;

import org.jetbrains.annotations.NotNull;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Receipt implements Comparable<Receipt> {
  private String Receipt_ID;
  private String Transaction_ID;
  private String Image_Link;
  private String Name;
  private String Description;

  public Receipt(){}

  public Receipt(String Receipt_ID, String Transaction_ID, String Image_Link, String Name, String Description) {

    this.Receipt_ID = Receipt_ID;
    this.Transaction_ID = Transaction_ID;
    this.Image_Link = Image_Link;
    this.Name = Name;
    this.Description = Description;
  }

  public Receipt(String Receipt_ID) {

    this.Receipt_ID = Receipt_ID;
  }

  /**
   * <p> Gets the Receipt_ID of the associated Receipt object </p>
   * @return the Receipt_ID of this Receipt object.
   */
  public String getReceipt_ID() {
    return Receipt_ID;
  }

  /**
   * <p> Sets the Receipt_ID of the associated Receipt object </p>
   * @param Receipt_ID the receipt_id of the receipt,
   * throws IllegalArgumentException if Receipt_ID under 3 characters or longer than 36 characters
   */
  public void setReceipt_ID(String Receipt_ID) {

    if(Receipt_ID.length()<36){
      throw new IllegalArgumentException("Receipt_ID is too short.");
    }
    if(Receipt_ID.length()>36){
      throw new IllegalArgumentException("Receipt_ID is too long.");
    }
    this.Receipt_ID = Receipt_ID;
  }

  /**
   * <p> Gets the Transaction_ID of the associated Receipt object </p>
   * @return the Transaction_ID of this Receipt object.
   */
  public String getTransaction_ID() {
    return Transaction_ID;
  }

  /**
   * <p> Sets the Transaction_ID of the associated Receipt object </p>
   * @param Transaction_ID the transaction_id of the receipt,
   * throws IllegalArgumentException if Transaction_ID under 3 characters or longer than 36 characters
   */
  public void setTransaction_ID(String Transaction_ID) {

    if(Transaction_ID.length()<36){
      throw new IllegalArgumentException("Transaction_ID is too short.");
    }
    if(Transaction_ID.length()>36){
      throw new IllegalArgumentException("Transaction_ID is too long.");
    }
    this.Transaction_ID = Transaction_ID;
  }

  /**
   * <p> Gets the Image_Link of the associated Receipt object </p>
   * @return the Image_Link of this Receipt object.
   */
  public String getImage_Link() {
    return Image_Link;
  }

  /**
   * <p> Sets the Image_Link of the associated Receipt object </p>
   * @param Image_Link the image_link of the receipt,
   * throws IllegalArgumentException if Image_Link under 3 characters or longer than 255 characters
   */
  public void setImage_Link(String Image_Link) {
    Image_Link = Image_Link.replaceAll("^[A-Za-z0-9]+^/.*_","");
    if(Image_Link.length()<4){
      throw new IllegalArgumentException("Image_Link is too short.");
    }
    if(Image_Link.length()>255){
      throw new IllegalArgumentException("Image_Link is too long.");
    }
    this.Image_Link = Image_Link;
  }

  /**
   * <p> Gets the Name of the associated Receipt object </p>
   * @return the Name of this Receipt object.
   */
  public String getName() {
    return Name;
  }

  /**
   * <p> Sets the Name of the associated Receipt object </p>
   * @param Name the name of the receipt,
   * throws IllegalArgumentException if Name under 3 characters or longer than 255 characters
   */
  public void setName(String Name) {
    Name = Name.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Name.length()<4){
      throw new IllegalArgumentException("Name is too short.");
    }
    if(Name.length()>255){
      throw new IllegalArgumentException("Name is too long.");
    }
    this.Name = Name;
  }

  /**
   * <p> Gets the Description of the associated Receipt object </p>
   * @return the Description of this Receipt object.
   */
  public String getDescription() {
    return Description;
  }

  /**
   * <p> Sets the Description of the associated Receipt object </p>
   * @param Description the description of the receipt,
   * throws IllegalArgumentException if Description under 3 characters or longer than 1000 characters
   */
  public void setDescription(String Description) {
    Description = Description.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Description.length()<4){
      throw new IllegalArgumentException("Description is too short.");
    }
    if(Description.length()>1000){
      throw new IllegalArgumentException("Description is too long.");
    }
    this.Description = Description;
  }
  @Override
  public int compareTo(@NotNull Receipt o) {
    if (this.Receipt_ID.compareTo(o.Receipt_ID)<0){
      return -1;
    }
    else if(this.Receipt_ID.compareTo(o.Receipt_ID) > 0){
      return 1;
    }
    if (this.Transaction_ID.compareTo(o.Transaction_ID)<0){
      return -1;
    }
    else if(this.Transaction_ID.compareTo(o.Transaction_ID) > 0){
      return 1;
    }
    if (this.Image_Link.compareTo(o.Image_Link)<0){
      return -1;
    }
    else if(this.Image_Link.compareTo(o.Image_Link) > 0){
      return 1;
    }
    if (this.Name.compareTo(o.Name)<0){
      return -1;
    }
    else if(this.Name.compareTo(o.Name) > 0){
      return 1;
    }
    if (this.Description.compareTo(o.Description)<0){
      return -1;
    }
    else if(this.Description.compareTo(o.Description) > 0){
      return 1;
    }
    return 0;
  }

}

