package com.istefanjak.card.issue.core.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private CardStatus status;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "accountId")
  private Account account;

  public Card() {
  }

  public Card(CardStatus status, Account account) {
    this.status = status;
    this.account = account;
  }

  public Card(CardStatus status) {
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CardStatus getStatus() {
    return status;
  }

  public void setStatus(CardStatus cardStatus) {
    this.status = cardStatus;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public String toString() {
    return "Card{" +
        "id=" + id +
        ", cardStatus=" + status +
        '}';
  }
}
