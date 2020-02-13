package com.adelean.elasticsearch.word2vec.upload;

import com.github.tomaslanger.chalk.Chalk;
import com.github.tomaslanger.cli.progress.ProgressBar;
import com.github.tomaslanger.cli.progress.StatusLoc;
import org.apache.commons.lang3.time.DurationFormatUtils;

public final class UploadProgressBar {
    private static final String STATUS_FORMAT = "%12s / %s  %s";

    private final long fileSizeBytes;
    private final String fileSizeStr;
    private final long startTimestamp;
    private final ProgressBar progressBar;

    private long totalUploadedBytes = 0;

    public UploadProgressBar(long fileSizeBytes, long startTimestamp) {
        this.fileSizeBytes = fileSizeBytes;
        this.startTimestamp = startTimestamp;
        this.fileSizeStr = ByteSizeUtils.humanReadableByteCountBin(fileSizeBytes);
        this.progressBar = new ProgressBar
                .Builder()
                .noColors()
                .setCharCount(50)
                .setStatusLocation(StatusLoc.SAME_LINE)
                .setBaseChar(' ')
                .setProgressChar('=')
                .setBeginString(Chalk
                        .on("[ ")
                        .bold()
                        .gray()
                        .toString())
                .setEndString(Chalk
                        .on(" ]")
                        .bold()
                        .gray()
                        .toString())
                .build();
    }

    public void begin() {
        progressBar.begin();
    }

    public void bytesUploaded(long bytesUploaded) {
        totalUploadedBytes += bytesUploaded;
        int advancePercent = (int) (totalUploadedBytes * 100 / fileSizeBytes);

        progressBar.setProgress(advancePercent, status());
    }

    public void end() {
        progressBar.end();
    }

    private String status() {
        long duration = durationMillis();
        return String.format(
                STATUS_FORMAT,
                ByteSizeUtils.humanReadableByteCountBin(totalUploadedBytes),
                fileSizeStr,
                DurationFormatUtils.formatDurationWords(duration, true, true));
    }

    private long durationMillis() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis - startTimestamp;
    }
}
