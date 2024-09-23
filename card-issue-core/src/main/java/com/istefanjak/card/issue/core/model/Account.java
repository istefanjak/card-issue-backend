package com.istefanjak.card.issue.core.model;

import com.istefanjak.card.issue.core.validation.oib.Oib;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Oib
  @Column(unique = true)
  private String oib;

  @NotBlank(message = "First name is required.")
  private String firstName;

  @NotBlank(message = "Last name is required.")
  private String lastName;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
  private List<Card> cards = new ArrayList<>();

  public Account() {
  }

  public Account(String oib, String firstName, String lastName, List<Card> cards) {
    this.oib = oib;
    this.firstName = firstName;
    this.lastName = lastName;
    this.cards = cards;
  }

  public Account(String oib, String firstName, String lastName) {
    this.oib = oib;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOib() {
    return oib;
  }

  public void setOib(String oib) {
    this.oib = oib;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  public void addCards(Card... cards) {
    var cardsList = Arrays.stream(cards).toList();
    getCards().addAll(cardsList);
    setCards(cardsList);
    cardsList.forEach(card -> card.setAccount(this));
  }

  @Override
  public String toString() {
    return "Account{"
        + "id="
        + id
        + ", oib='"
        + oib
        + '\''
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + '}';
  }
}
