package de.digitalcollections.model.impl.identifiable.resource;

import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import de.digitalcollections.model.api.identifiable.resource.IiifManifest;
import de.digitalcollections.model.api.identifiable.resource.ResourceType;

public class IiifManifestImpl extends ResourceImpl implements IiifManifest {

  private HashMap<Locale, String> attributions;
  private HashMap<Locale, String> descriptions;
  private URL logoUrl;
  private HashMap<Locale, String> manifestLabels;
  private URL manifestUrl;

  public IiifManifestImpl() {
    super();
    this.resourceType = ResourceType.IIIF_MANIFEST;
  }

  @Override
  public URL getManifestUrl() {
    return manifestUrl;
  }

  @Override
  public void setManifesturl(URL manifestUrl) {
    this.manifestUrl = manifestUrl;
  }

  @Override
  public URL getLogoUrl() {
    return logoUrl;
  }

  @Override
  public void setLogoUrl(URL logoUrl) {
    this.logoUrl = logoUrl;
  }

  @Override
  public HashMap<Locale, String> getManifestLabels() {
    return manifestLabels;
  }

  @Override
  public void setManifestLabels(HashMap<Locale, String> manifestLabels) {
    this.manifestLabels = manifestLabels;
  }

  @Override
  public HashMap<Locale, String> getDescriptions() {
    return descriptions;
  }

  @Override
  public void setDescriptions(HashMap<Locale, String> descriptions) {
    this.descriptions = descriptions;
  }

  @Override
  public HashMap<Locale, String> getAttributions() {
    return attributions;
  }

  @Override
  public void setAttributions(HashMap<Locale, String> attributions) {
    this.attributions = attributions;
  }
}
