package com.sofency.ticket.pojo;

import java.util.Date;

public class GrabTicket extends GrabTicketKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.activity_name
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private String activityName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.begin_time
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private Date beginTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.end_time
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.activity_ticket_level
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private Integer activityTicketLevel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.activity_ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private Integer activityTicket;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.activity_image
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private String activityImage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.sign_out
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private Integer signOut;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.sign_in
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private Integer signIn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.status
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.sign_in_image
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private String signInImage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grabticket.sign_out_image
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    private String signOutImage;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.activity_name
     *
     * @return the value of grabticket.activity_name
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.activity_name
     *
     * @param activityName the value for grabticket.activity_name
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.begin_time
     *
     * @return the value of grabticket.begin_time
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.begin_time
     *
     * @param beginTime the value for grabticket.begin_time
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.end_time
     *
     * @return the value of grabticket.end_time
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.end_time
     *
     * @param endTime the value for grabticket.end_time
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.activity_ticket_level
     *
     * @return the value of grabticket.activity_ticket_level
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public Integer getActivityTicketLevel() {
        return activityTicketLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.activity_ticket_level
     *
     * @param activityTicketLevel the value for grabticket.activity_ticket_level
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setActivityTicketLevel(Integer activityTicketLevel) {
        this.activityTicketLevel = activityTicketLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.activity_ticket
     *
     * @return the value of grabticket.activity_ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public Integer getActivityTicket() {
        return activityTicket;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.activity_ticket
     *
     * @param activityTicket the value for grabticket.activity_ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setActivityTicket(Integer activityTicket) {
        this.activityTicket = activityTicket;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.activity_image
     *
     * @return the value of grabticket.activity_image
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public String getActivityImage() {
        return activityImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.activity_image
     *
     * @param activityImage the value for grabticket.activity_image
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage == null ? null : activityImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.sign_out
     *
     * @return the value of grabticket.sign_out
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public Integer getSignOut() {
        return signOut;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.sign_out
     *
     * @param signOut the value for grabticket.sign_out
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setSignOut(Integer signOut) {
        this.signOut = signOut;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.sign_in
     *
     * @return the value of grabticket.sign_in
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public Integer getSignIn() {
        return signIn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.sign_in
     *
     * @param signIn the value for grabticket.sign_in
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setSignIn(Integer signIn) {
        this.signIn = signIn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.status
     *
     * @return the value of grabticket.status
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.status
     *
     * @param status the value for grabticket.status
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.sign_in_image
     *
     * @return the value of grabticket.sign_in_image
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public String getSignInImage() {
        return signInImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.sign_in_image
     *
     * @param signInImage the value for grabticket.sign_in_image
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setSignInImage(String signInImage) {
        this.signInImage = signInImage == null ? null : signInImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grabticket.sign_out_image
     *
     * @return the value of grabticket.sign_out_image
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public String getSignOutImage() {
        return signOutImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grabticket.sign_out_image
     *
     * @param signOutImage the value for grabticket.sign_out_image
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    public void setSignOutImage(String signOutImage) {
        this.signOutImage = signOutImage == null ? null : signOutImage.trim();
    }
}