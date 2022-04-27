package de.digitalcollections.model.identifiable.resource;

import de.digitalcollections.model.file.MimeType;
import de.digitalcollections.model.identifiable.Identifiable;
import de.digitalcollections.model.identifiable.IdentifiableType;
import de.digitalcollections.model.legal.License;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;

/**
 * A FileResource (source) describes any file, regardless of its physical location, used storage
 * technology or required display means (aka "Viewer"). A FileResource can e.g. include an image, a
 * video file, an XML document, or a JSON file.
 */
public class FileResource extends Identifiable {

  protected FileResourceType fileResourceType;
  private String filename;

  private URL httpBaseUrl;
  private License license;
  private MimeType mimeType;
  private boolean readonly = false;
  private long sizeInBytes;
  private URI uri;

  public FileResource() {
    super();
    this.type = IdentifiableType.RESOURCE;
    this.fileResourceType = FileResourceType.UNDEFINED;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FileResource)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    FileResource that = (FileResource) o;
    return readonly == that.readonly
        && sizeInBytes == that.sizeInBytes
        && fileResourceType == that.fileResourceType
        && Objects.equals(filename, that.filename)
        && Objects.equals(httpBaseUrl, that.httpBaseUrl)
        && Objects.equals(license, that.license)
        && Objects.equals(mimeType, that.mimeType)
        && Objects.equals(uri, that.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(),
        fileResourceType,
        filename,
        httpBaseUrl,
        license,
        mimeType,
        readonly,
        sizeInBytes,
        uri);
  }

  public FileResourceType getFileResourceType() {
    return fileResourceType;
  }

  public String getFilename() {
    if (filename == null && uri != null) {
      try {
        filename = uri.toURL().getFile();
        if (filename.contains("/")) {
          filename = filename.substring(filename.lastIndexOf('/') + 1);
        }
        if (filename.contains("?")) {
          filename = filename.substring(0, filename.indexOf('?'));
        }
      } catch (MalformedURLException ex) {
        filename = null;
      }
    }
    return filename;
  }

  public String getFilenameExtension() {
    String filename = getFilename();
    if (filename == null) {
      return null;
    } else {
      int lastDotPosition = filename.lastIndexOf(".");
      if (lastDotPosition >= 0 && lastDotPosition < filename.length()) {
        String result = filename.substring(lastDotPosition + 1);
        if (result.trim().length() == 0) {
          return null;
        }
        return result;
      } else {
        return null;
      }
    }
  }

  /**
   * @return a base HTTP url for getting the file resource. Supposed to be extended by additional
   *     URL params.
   */
  public URL getHttpBaseUrl() {
    return httpBaseUrl;
  }

  public License getLicense() {
    return license;
  }

  public MimeType getMimeType() {
    return mimeType;
  }

  public long getSizeInBytes() {
    return sizeInBytes;
  }

  public URI getUri() {
    return this.uri;
  }

  public boolean isReadonly() {
    return this.readonly;
  }

  public void setFileResourceType(FileResourceType fileResourceType) {
    this.fileResourceType = fileResourceType;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public void setHttpBaseUrl(URL httpBaseUrl) {
    this.httpBaseUrl = httpBaseUrl;
  }

  public void setLicense(License license) {
    this.license = license;
  }

  public void setMimeType(MimeType mimeType) {
    this.mimeType = mimeType;
  }

  public void setReadonly(boolean readonly) {
    this.readonly = readonly;
  }

  public void setSizeInBytes(long sizeInBytes) {
    this.sizeInBytes = sizeInBytes;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

  @Override
  public String toString() {
    String mimetypeStr = getMimeType() == null ? null : getMimeType().getTypeName();

    return this.getClass().getSimpleName()
        + "{"
        + "created="
        + created
        + ", lastModified="
        + lastModified
        + ", uuid="
        + String.valueOf(getUuid())
        + ", description="
        + description
        + ", identifiers="
        + identifiers
        + ", label="
        + label
        + ", localizedUrlAliases="
        + localizedUrlAliases
        + ", previewImage="
        + previewImage
        + ", previewImageRenderingHints="
        + previewImageRenderingHints
        + ", type="
        + type
        + ", fileResourceType="
        + fileResourceType
        + ", filename='"
        + filename
        + '\''
        + ", httpBaseUrl="
        + httpBaseUrl
        + ", license="
        + license
        + ", mimeType="
        + mimetypeStr
        + ", readonly="
        + readonly
        + ", sizeInBytes="
        + sizeInBytes
        + ", uri="
        + String.valueOf(uri)
        + '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder<F extends FileResource, B extends Builder> extends Identifiable.Builder<F, B> {

    @Override
    protected IdentifiableType getIdentifiableType() {
      return IdentifiableType.RESOURCE;
    }

    public B withFilename(String filename) {
      identifiable.setFilename(filename);
      return (B) this;
    }

    public B withType(FileResourceType fileResourceType) {
      identifiable.setFileResourceType(fileResourceType);
      return (B) this;
    }

    public B withMimeType(MimeType mimeType) {
      identifiable.setMimeType(mimeType);
      return (B) this;
    }

    public B withHttpBaseUrl(String httpBaseUrl) {
      try {
        identifiable.setHttpBaseUrl(new URL(httpBaseUrl));
      } catch (MalformedURLException e) {
        throw new RuntimeException("Cannot set httpBaseURL=" + httpBaseUrl + ": " + e, e);
      }
      return (B) this;
    }

    public B withLicenseOfName(String licenseName) {
      License license = License.builder()
              .withLabel(Locale.GERMAN, licenseName)
              .withAcronym(licenseName)
              .withUrl("https://localhost/licence/" + licenseName)
              .build();

      identifiable.setLicense(license);
      return (B) this;
    }

    public B readwrite() {
      identifiable.setReadonly(false);
      return (B) this;
    }

    public B readonly() {
      identifiable.setReadonly(true);
      return (B) this;
    }

    public B withSizeInBytes(long size) {
      identifiable.setSizeInBytes(size);
      return (B) this;
    }

    public B withUri(String uri) {
      try {
        identifiable.setUri(new URI(uri));
      } catch (URISyntaxException e) {
        throw new RuntimeException("Invalid URI=" + uri + ": " + e);
      }
      return (B) this;
    }
  }
}
