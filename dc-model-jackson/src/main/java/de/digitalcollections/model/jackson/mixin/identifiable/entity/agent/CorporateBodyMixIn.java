package de.digitalcollections.model.jackson.mixin.identifiable.entity.agent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.digitalcollections.model.identifiable.entity.agent.CorporateBody;

@JsonDeserialize(as = CorporateBody.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("CORPORATE_BODY")
public interface CorporateBodyMixIn {}
