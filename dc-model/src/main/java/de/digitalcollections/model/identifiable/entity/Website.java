package de.digitalcollections.model.identifiable.entity;

import de.digitalcollections.model.identifiable.IdentifiableType;
import de.digitalcollections.model.identifiable.web.Webpage;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import lombok.experimental.SuperBuilder;

/** A Website. */
@SuperBuilder(buildMethodName = "prebuild")
public class Website extends Entity {

  private LocalDate registrationDate;
  private List<? extends Webpage> rootPages;
  private URL url;

  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }

  public List<? extends Webpage> getRootPages() {
    return rootPages;
  }

  public void setRootPages(List<? extends Webpage> rootPages) {
    this.rootPages = rootPages;
  }

  public Website() {
    super();
    this.entityType = EntityType.WEBSITE;
  }

  public Website(URL url) {
    this(null, url, null);
  }

  public Website(List<Webpage> rootPages, URL url, LocalDate registrationDate) {
    this();
    this.registrationDate = registrationDate;
    this.rootPages = rootPages;
    this.url = url;
  }

  public abstract static class WebsiteBuilder<C extends Website, B extends WebsiteBuilder<C, B>>
      extends EntityBuilder<C, B> {

    public B url(String url) {
      try {
        this.url = new URL(url);
      } catch (MalformedURLException e) {
        throw new RuntimeException(e);
      }
      return self();
    }

    public B registrationDate(String registrationDate) {
      this.registrationDate = LocalDate.parse(registrationDate);
      return self();
    }

    @Override
    public C build() {
      C c = prebuild();
      c.setType(IdentifiableType.ENTITY);
      c.setEntityType(EntityType.WEBSITE);
      setInternalReferences(c);
      return c;
    }
  }
}
