package um.test;

import org.junit.Before;
import org.junit.Test;
import um.app.UniversalMusic;

import java.io.IOException;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertEquals;

public class UniversalMusicTest {

    UniversalMusic universalMusic = new UniversalMusic();

    @Before
    public void loadDataFiles() throws IOException, URISyntaxException {
         universalMusic.loadMusicContractFile();
         universalMusic.loadDistributionContractFile();
    }

    @Test
    public void searchForActiveMusicContractsByItunes() throws IOException {
         assertEquals("Should return 4 records", 4, universalMusic.searchForActiveMusicContracts("ITunes", "1st March 2012").size());
    }

    @Test
    public void searchForActiveMusicContractsByYouTube() throws IOException {
        assertEquals("Should return 4 records", 4, universalMusic.searchForActiveMusicContracts("YouTube", "27th Dec 2012").size());
    }


    @Test
    public void searchForActiveMusicContractsByYouTubeByOtherDate() throws IOException {
        assertEquals("Should return 2 records", 2, universalMusic.searchForActiveMusicContracts("YouTube", "1st April 2012").size());
    }
}
