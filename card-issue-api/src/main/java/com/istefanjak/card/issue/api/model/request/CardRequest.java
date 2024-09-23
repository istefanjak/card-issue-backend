package com.istefanjak.card.issue.api.model.request;

import com.istefanjak.card.issue.core.model.CardStatus;

public record CardRequest(String oib, CardStatus cardStatus) {}
