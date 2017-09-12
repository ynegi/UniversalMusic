package um.app;

import um.model.DistributionPartnerContract;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static um.app.UniversalMusicUtils.*;

public class UniversalMusic  {

    private static final Logger logger = Logger.getLogger(UniversalMusic.class.getName());

    private List<um.model.MusicContract> musicContracts = null;

    private List<DistributionPartnerContract> distributionPartnerContracts;

    public List<um.model.MusicContract>  loadMusicContractFile()  {

        try {
            try(Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource(MUSIC_CONTRACT_FILE).toURI()))) {
                musicContracts = lines.skip(1)
                        .map(line -> line.split("\\|", -1))
                        .map(snippets -> new um.model.MusicContract(
                                snippets[UniversalMusicUtils.MusicContract.ARTIST],
                                snippets[UniversalMusicUtils.MusicContract.TITLE],
                                snippets[UniversalMusicUtils.MusicContract.USAGES],
                                snippets[UniversalMusicUtils.MusicContract.START_DATE],
                                snippets[UniversalMusicUtils.MusicContract.END_DATE]))
                        .collect(Collectors.toList());
            }
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.INFO, "Exception occur in loadMusicContractFile", e);
        }

        return musicContracts;
    }

    public  List<DistributionPartnerContract>  loadDistributionContractFile() {

        try {
            try(Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource(PARTNER_CONTRACT_FILE).toURI()))) {
                distributionPartnerContracts = lines.skip(1)
                        .map(line -> line.split("\\|", -1))
                        .map(snippets -> new DistributionPartnerContract(
                                snippets[UniversalMusicUtils.PartnerContract.DISTRIBUTION_PARTNER],
                                snippets[UniversalMusicUtils.PartnerContract.DISTRIBUTION_USAGE]))
                        .collect(Collectors.toList());
            }
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.INFO, "Exception occur in loadDistributionContractFile", e);

        }
        return distributionPartnerContracts;
    }

    public  List<um.model.MusicContract> searchForActiveMusicContracts(String partner, String contractDate) {
        DistributionPartnerContract distributionPartnerContract = distributionPartnerContracts.stream()
                .filter(e -> e.getPartner().equals(partner))
                .findFirst()
                .get();

        LocalDate activeDate = LocalDate.parse(contractDate, DATE_TIME_FORMATTER);

        List<um.model.MusicContract> result = musicContracts.stream()
                .filter(e -> e.getUsages().contains(distributionPartnerContract.getUsage()))
                .filter(e -> ChronoUnit.DAYS.between(LocalDate.parse(e.getStartDate(), DATE_TIME_FORMATTER), activeDate) > 0)
                .collect(Collectors.toList());

        result.forEach(f -> f.setUsages(distributionPartnerContract.getUsage()));
        printResult(result);
        return result;
    }

    private void printResult(List<um.model.MusicContract> result) {
        System.out.printf("\n|%-20s|%-30s|%-20s|%-20s|%s|%n", "ARTIST", "TITLE", "USAGES", "START_DATE", "END_DATE");
        for (um.model.MusicContract musicContract: result) {
            System.out.println(String.format("|%-20s|%-30s|%-20s|%-20s|%s|", musicContract.getArtist(),
                    musicContract.getTitle(), musicContract.getUsages(), musicContract.getStartDate(), musicContract.getEndDate()));
        }
    }
}
