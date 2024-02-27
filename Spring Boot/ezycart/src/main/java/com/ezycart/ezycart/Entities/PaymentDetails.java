package com.ezycart.ezycart.Entities;

import com.ezycart.ezycart.User.Domain.PaymentMethod;
import com.ezycart.ezycart.User.Domain.PaymentStatus;


public class PaymentDetails {

  private PaymentMethod paymentMethod;
  private PaymentStatus paymentStatus;

  private String paymentId;
  private String razorpayPaymentLinkId;
  private String razorpayPaymentLinkreferenceId;
  private String razorpayPaymentLikStatus;
  private String razorpayPaymentId;

  public PaymentDetails() {}

  public PaymentDetails(
    PaymentMethod paymentMethod,
    PaymentStatus paymentStatus,
    String paymentId,
    String razorpayPaymentLinkId,
    String razorpayPaymentLinkreferenceId,
    String razorpayPaymentLikStatus,
    String razorpayPaymentId
  ) {
    this.paymentMethod = paymentMethod;
    this.paymentStatus = paymentStatus;
    this.paymentId = paymentId;
    this.razorpayPaymentLinkId = razorpayPaymentLinkId;
    this.razorpayPaymentLinkreferenceId = razorpayPaymentLinkreferenceId;
    this.razorpayPaymentLikStatus = razorpayPaymentLikStatus;
    this.razorpayPaymentId = razorpayPaymentId;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getRazorpayPaymentLinkId() {
    return razorpayPaymentLinkId;
  }

  public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
    this.razorpayPaymentLinkId = razorpayPaymentLinkId;
  }

  public String getRazorpayPaymentLinkreferenceId() {
    return razorpayPaymentLinkreferenceId;
  }

  public void setRazorpayPaymentLinkreferenceId(
    String razorpayPaymentLinkreferenceId
  ) {
    this.razorpayPaymentLinkreferenceId = razorpayPaymentLinkreferenceId;
  }

  public String getRazorpayPaymentLikStatus() {
    return razorpayPaymentLikStatus;
  }

  public void setRazorpayPaymentLikStatus(String razorpayPaymentLikStatus) {
    this.razorpayPaymentLikStatus = razorpayPaymentLikStatus;
  }

  public String getRazorpayPaymentId() {
    return razorpayPaymentId;
  }

  public void setRazorpayPaymentId(String razorpayPaymentId) {
    this.razorpayPaymentId = razorpayPaymentId;
  }
}
