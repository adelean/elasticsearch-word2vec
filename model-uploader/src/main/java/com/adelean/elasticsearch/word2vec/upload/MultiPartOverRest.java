package com.adelean.elasticsearch.word2vec.upload;

import com.github.tomaslanger.chalk.Chalk;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Ansi;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Command(
        name = "model-upload",
        mixinStandardHelpOptions = true,
        description = "Uploads word2vec model to Elasticsearch.")
public final class MultiPartOverRest implements Runnable {
    private static final int CHUNK_SIZE = 10 * 3 * 1024;

    private static final String MSG_START_UPLOAD = "@|bold Uploading model|@\n"
            + "@|blue Model|@ @|faint :|@ %s\n"
            + "@|blue From|@  @|faint :|@ %s @|faint (size: %s)|@\n"
            + "@|blue To|@    @|faint :|@ %s";

    private static final String MSG_FINISH_UPLOAD = Chalk
            .on("Model %s was uploaded successfully! Parts: %d. Took: %s")
            .green()
            .toString();

    @Parameters(index = "0", description = "The word2vec model file.")
    private Path modelFile;

    @Option(
            names = {"-s", "--server"},
            defaultValue = "http://localhost:9200",
            description = "The URL of Elasticsearch server (default: ${DEFAULT-VALUE}).")
    private URI elasticsearchUrl;

    @Option(
            names = {"-m", "--model"},
            required = false,
            description = "The model name.")
    private String model;

    private UploadApi uploadApi;
    private long fileSizeBytes;
    private long startTimestamp;
    private long partsCount = 0;
    private UploadProgressBar progressBar;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MultiPartOverRest()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        beforeStart();

        UUID uploadId = startUpload();
        uploadParts(uploadId);
        finishUpload(uploadId);
    }

    private void beforeStart() {
        this.startTimestamp = System.currentTimeMillis();

        this.uploadApi = client(elasticsearchUrl);

        if (this.model == null) {
            this.model = modelNameFromFile(modelFile);
        }

        try {
            this.fileSizeBytes = Files.size(modelFile);
        } catch (IOException fileReadException) {
            throw new RuntimeException(fileReadException);
        }

        this.progressBar = new UploadProgressBar(fileSizeBytes, startTimestamp);
    }

    private UUID startUpload() {
        printStartUpload();
        UploadApi.StartUploadResponse startUploadResponse = uploadApi.startUpload(model);
        return startUploadResponse.getUploadId();
    }

    private void uploadParts(UUID uploadId) {
        progressBar.begin();

        ByteBuffer buffer = ByteBuffer.allocate(CHUNK_SIZE);
        long readBytes;
        try (FileChannel fileChannel = FileChannel.open(modelFile)) {
            while ((readBytes = fileChannel.read(buffer)) > 0) {
                byte[] bytes = buffer.array();
                UploadApi.Part part = new UploadApi.Part(bytes);
                uploadApi.storePart(uploadId, partsCount++, part);
                buffer.clear();
                progressBar.bytesUploaded(readBytes);
            }
        } catch (IOException fileReadException) {
            throw new RuntimeException(fileReadException);
        }

        progressBar.end();
    }

    private void finishUpload(UUID uploadId) {
        uploadApi.finishUpload(uploadId);
        printFinishUpload();
    }

    private static UploadApi client(URI elasticsearchUrl) {
        Client client = ResteasyClientBuilder.newClient();
        ResteasyWebTarget target = (ResteasyWebTarget) client.target(elasticsearchUrl);
        return target.proxy(UploadApi.class);
    }

    static String modelNameFromFile(Path modelFile) {
        return FilenameUtils.removeExtension(modelFile.getFileName().toString());
    }

    void printStartUpload() {
        String msg = String.format(
                MSG_START_UPLOAD,
                model,
                modelFile,
                ByteSizeUtils.humanReadableByteCountBin(fileSizeBytes),
                elasticsearchUrl);
        printAnsi(msg);
    }

    void printFinishUpload() {
        long finishTimestamp = System.currentTimeMillis();
        long duration = finishTimestamp - startTimestamp;

        String msg = String.format(
                MSG_FINISH_UPLOAD,
                model,
                partsCount,
                DurationFormatUtils.formatDurationWords(duration, true, true));
        printAnsi(msg);
    }

    static void printAnsi(String str) {
        str = Ansi.AUTO.string(str);
        System.out.println(str);
    }
}
