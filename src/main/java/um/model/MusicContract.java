package um.model;

public class MusicContract {

    private String artist;
    private String title;
    private String usages;
    private String startDate;
    private String endDate;

    public MusicContract(String artist, String title, String usages, String startDate, String endDate) {
        this.artist = artist;
        this.title = title;
        this.usages = usages;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "MusicContract{" +
                "artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", usages='" + usages + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
