package de.digitalcollections.model.impl.identifiable.resource;

import static org.assertj.core.api.Assertions.assertThat;

import de.digitalcollections.model.api.identifiable.resource.MimeType;
import java.net.URI;
import org.junit.jupiter.api.Test;

public class FileResourceImplTest {

  /** Test of getFilenameExtension method, of class FileResourceImpl. */
  @Test
  public void testGetFilenameExtension() {
    FileResourceImpl instance = new ImageFileResourceImpl();

    instance.setFilename("image001.jpg");
    String expResult = "jpg";
    String result = instance.getFilenameExtension();
    assertThat(result).isEqualTo(expResult);

    instance.setFilename("image001.txt.jpg");
    expResult = "jpg";
    result = instance.getFilenameExtension();
    assertThat(result).isEqualTo(expResult);

    instance.setFilename(".bashrc");
    expResult = "bashrc";
    result = instance.getFilenameExtension();
    assertThat(result).isEqualTo(expResult);

    instance.setFilename("image.j");
    expResult = "j";
    result = instance.getFilenameExtension();
    assertThat(result).isEqualTo(expResult);

    instance.setFilename("image.");
    expResult = null;
    result = instance.getFilenameExtension();
    assertThat(result).isEqualTo(expResult);

    instance.setFilename("image");
    expResult = null;
    result = instance.getFilenameExtension();
    assertThat(result).isEqualTo(expResult);

    instance.setFilename(".");
    expResult = null;
    result = instance.getFilenameExtension();
    assertThat(result).isEqualTo(expResult);

    instance.setFilename("..");
    expResult = null;
    result = instance.getFilenameExtension();
    assertThat(result).isEqualTo(expResult);

    instance.setFilename(null);
    expResult = null;
    result = instance.getFilenameExtension();
    assertThat(result).isEqualTo(expResult);
  }

  @Test
  public void testToString() {
    FileResourceImpl instance = new ImageFileResourceImpl();
    instance.setFilename("image001.xyz123");
    instance.setMimeType(MimeType.fromExtension("xyz123"));
    String result = instance.toString();
    assertThat(result).isNotEqualTo(null);
  }

  @Test
  public void testGetFilenameFromUri() {
    FileResourceImpl instance = new FileResourceImpl();
    instance.setUri(URI.create("http://localhost/iiif/some/other/default.jpg"));
    String result = instance.getFilename();
    String expResult = "default.jpg";
    assertThat(result).isEqualTo(expResult);
  }

  @Test
  public void testGetFilenameAndExtensionFromUri() {
    FileResourceImpl instance = new FileResourceImpl();
    instance.setUri(
        URI.create(
            "https://commons.wikimedia.org/wiki/Special:FilePath/DFG-logo-blau.svg?width=270"));
    String result = instance.getFilename();
    String expResult = "DFG-logo-blau.svg";
    assertThat(result).isEqualTo(expResult);

    String extension = instance.getFilenameExtension();
    assertThat(extension).isEqualTo("svg");
  }
}
