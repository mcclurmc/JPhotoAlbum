package fi.iki.jka;

import org.junit.runners.JUnit4;

import java.awt.event.ActionEvent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JPhotoFrameTest {
    @org.junit.Test
    public void testActionPerformedNoPhotosWRONG() throws Exception {
        String[] files = new String[1];
        files[0] = "/Users/localuser/JPhotoAlbum/resources/pics/JPhotoAlbum64.png";

        // Frame with no photos
        JPhotoFrame frame = new JPhotoFrame(null, files);

        // Event for slideshow
        ActionEvent event = new ActionEvent(this, 0, JPhotoMenu.A_SLIDESHOW);

        // Call the thing
        frame.actionPerformed(event);
    }

    @org.junit.Test
    public void testActionPerformedNoPhotos() throws Exception {
        // Frame with no photos
        JPhotoFrame frame = JPhotoFrame.test_init(new JPhotoCollection());
        TestMessageShower shower = new TestMessageShower();
        frame.performASlideshowAction(shower);
        assertThat(shower.message, is("No photos to show!"));
    }


    @org.junit.Test
    public void testActionPerformedWithPhotos() throws Exception {
        JPhotoFrame frame = JPhotoFrame.test_init(new JPhotoCollection() {
            public int getSize() {
                return 1;
            }
        });
        JPhotoShow show = frame.performASlideshowAction(new TestMessageShower());
        assertNotNull(show);
        assertTrue(show.isVisible());
    }

    public class TestMessageShower implements JPhotoFrame.MessageShower {
        public String message;

        public void ShowMessage(String message) {
            this.message = message;
        }
    }

}

