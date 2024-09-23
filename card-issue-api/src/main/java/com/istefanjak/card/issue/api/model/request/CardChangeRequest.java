package com.istefanjak.card.issue.api.model.request;

import com.istefanjak.card.issue.core.model.CardStatus;

public record CardChangeRequest(CardStatus cardStatus) {}
