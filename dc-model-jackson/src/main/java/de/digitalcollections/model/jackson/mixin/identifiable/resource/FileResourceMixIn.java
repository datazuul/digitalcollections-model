package de.digitalcollections.model.jackson.mixin.identifiable.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.digitalcollections.model.impl.identifiable.resource.FileResourceImpl;

@JsonDeserialize(as = FileResourceImpl.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("fileresource")
public interface FileResourceMixIn {

}
