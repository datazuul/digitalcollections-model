package de.digitalcollections.model.jackson.mixin.identifiable.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.digitalcollections.model.identifiable.web.Webpage;
import de.digitalcollections.model.jackson.mixin.identifiable.IdentifiableMixIn;

@JsonDeserialize(as = Webpage.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("webpage")
public interface WebpageMixIn extends IdentifiableMixIn {}
