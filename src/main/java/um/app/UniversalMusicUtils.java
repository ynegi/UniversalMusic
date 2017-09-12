package um.app;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class UniversalMusicUtils {

    private UniversalMusicUtils(){
    }

    public static final String MUSIC_CONTRACT_FILE = "musicContract.txt";
    public static final String PARTNER_CONTRACT_FILE = "distributionPartnerContract.txt";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("d['st']['nd']['rd']['th'] [MMMM][MMM] yyyy").withLocale(Locale.ENGLISH);

    public class MusicContract {
        public static final int ARTIST = 0;
        public static final int TITLE = 1;
        public static final int USAGES = 2;
        public static final int START_DATE = 3;
        public static final int END_DATE = 4;
    }

    public class PartnerContract {
        public static final int DISTRIBUTION_PARTNER = 0;
        public static final int DISTRIBUTION_USAGE = 1;
    }

}
