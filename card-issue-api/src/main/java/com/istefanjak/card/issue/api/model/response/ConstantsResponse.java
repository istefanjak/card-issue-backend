package com.istefanjak.card.issue.api.model.response;

import com.istefanjak.card.issue.core.model.CardStatus;
import java.util.List;

public record ConstantsResponse(List<CardStatus> cardStatus) {}
