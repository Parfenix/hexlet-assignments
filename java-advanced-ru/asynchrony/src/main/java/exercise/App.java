package exercise;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.io.File;
import java.util.concurrent.CompletionException;
import java.util.stream.Stream;

class App {

    // BEGIN
    /**
     * Асинхронно читает содержимое двух файлов, объединяет их и записывает в третий файл
     * @param file1Path путь к первому файлу-источнику
     * @param file2Path путь ко второму файлу-источнику
     * @param destPath путь к файлу назначения
     * @return CompletableFuture<String> с результатом операции
     */
    public static CompletableFuture<String> unionFiles(String file1Path, String file2Path, String destPath) {
        CompletableFuture<String> file1Content = CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(file1Path);
                return Files.readString(path);
            } catch (IOException e) {
                throw new CompletionException("Error reading file: " + file1Path, e);
            }
        });

        CompletableFuture<String> file2Content = CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(file2Path);
                return Files.readString(path);
            } catch (IOException e) {
                throw new CompletionException("Error reading file: " + file2Path, e);
            }
        });

        return file1Content.thenCombine(file2Content, (content1, content2) -> {
            try {
                Path destPathObj = Paths.get(destPath);

                if (destPathObj.getParent() != null) {
                    Files.createDirectories(destPathObj.getParent());
                }

                String combinedContent = content1 + content2;

                Files.writeString(destPathObj, combinedContent,
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                return "Files successfully combined into: " + destPath;

            } catch (IOException e) {
                throw new CompletionException("Error writing to destination file: " + destPath, e);
            }
        }).exceptionally(throwable -> {
            Throwable cause = throwable.getCause();
            if (cause instanceof NoSuchFileException) {
                System.out.println("NoSuchFileException: " + cause.getMessage());
            } else {
                System.out.println("Exception occurred: " + throwable.getMessage());
            }

            if (cause != null) {
                cause.printStackTrace();
            } else {
                throwable.printStackTrace();
            }
            return "Operation failed: " + throwable.getMessage();
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String directoryPath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path dirPath = Paths.get(directoryPath);

                if (!Files.exists(dirPath)) {
                    throw new RuntimeException("Directory not found: " + directoryPath);
                }

                if (!Files.isDirectory(dirPath)) {
                    throw new RuntimeException("Path is not a directory: " + directoryPath);
                }

                try (Stream<Path> files = Files.list(dirPath)) {
                    return files
                            .filter(Files::isRegularFile)
                            .mapToLong(path -> {
                                try {
                                    return Files.size(path);
                                } catch (IOException e) {
                                    System.err.println("Error getting size of file: " + path);
                                    return 0L;
                                }
                            })
                            .sum();
                }

            } catch (IOException e) {
                throw new CompletionException("Error calculating directory size: " + directoryPath, e);
            }
        }).exceptionally(throwable -> {
            System.out.println("Exception occurred while calculating directory size: " + throwable.getMessage());
            if (throwable.getCause() != null) {
                throwable.getCause().printStackTrace();
            } else {
                throwable.printStackTrace();
            }
            return 0L;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        System.out.println("Starting asynchronous file operations...");

        CompletableFuture<String> result = unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/combined_output.txt"
        );

        try {
            String message = result.get(); // Получаем результат синхронно
            System.out.println("Union files result: " + message);
        } catch (Exception e) {
            System.out.println("Error during file union: " + e.getMessage());
        }

        System.out.println("File union operation completed!");

        System.out.println("\nCalculating directory size...");
        CompletableFuture<Long> sizeResult = getDirectorySize("src/main/resources");

        try {
            Long size = sizeResult.get();
            System.out.println("Directory size: " + size + " bytes");
        } catch (Exception e) {
            System.out.println("Error calculating directory size: " + e.getMessage());
        }

        System.out.println("All operations completed!");

        try {
            String content = Files.readString(Paths.get("src/main/resources/combined_output.txt"));
            System.out.println("\nContent of combined file:");
            System.out.println(content);
        } catch (Exception e) {
            System.out.println("Could not read combined file: " + e.getMessage());
        }
        // END
    }
}

