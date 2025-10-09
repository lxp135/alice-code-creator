package alice.code.creator.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

/**
 * 单元测试：验证 FileZipUtils 在 Zip4j 2.11.3 下的压缩与解压逻辑
 */
public class FileZipUtilsTest {

    private File tempDir;

    private File createTempDir() throws IOException {
        File dir = Files.createTempDirectory("fzu-" + UUID.randomUUID()).toFile();
        dir.deleteOnExit();
        return dir;
    }

    private File createFile(File dir, String name, String content) throws IOException {
        File f = new File(dir, name);
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        }
        Assertions.assertTrue(f.exists());
        return f;
    }

    @AfterEach
    void cleanup() throws IOException {
        // 清理临时目录
        if (tempDir != null && tempDir.exists()) {
            Files.walk(tempDir.toPath())
                    .map(java.nio.file.Path::toFile)
                    .sorted((a,b) -> -a.compareTo(b))
                    .forEach(File::delete);
        }
    }

    @Test
    void zipAndUnzipFolder_NoPassword_CreateDirTrue() throws Exception {
        tempDir = createTempDir();
        // 准备目录与文件
        File folder = new File(tempDir, "src");
        Assertions.assertTrue(folder.mkdirs());
        createFile(folder, "a.txt", "hello A");
        createFile(folder, "b.txt", "hello B");

        String destZip = new File(tempDir, "src.zip").getAbsolutePath();
        String zipPath = FileZipUtils.zip(folder.getAbsolutePath(), destZip, "");
        Assertions.assertNotNull(zipPath);
        Assertions.assertTrue(new File(zipPath).exists());

        // 解压到新目录
        File outDir = new File(tempDir, "out");
        Assertions.assertTrue(outDir.mkdirs());
        File[] files = FileZipUtils.unzip(zipPath, outDir.getAbsolutePath(), "");
        Assertions.assertNotNull(files);
        // 应包含两个文件
        Assertions.assertEquals(2, files.length);
        Assertions.assertTrue(new File(outDir, "src/a.txt").exists());
        Assertions.assertTrue(new File(outDir, "src/b.txt").exists());
    }

    @Test
    void zipAndUnzipFolder_NoPassword_CreateDirFalse_Flatten() throws Exception {
        tempDir = createTempDir();
        File folder = new File(tempDir, "src2");
        Assertions.assertTrue(folder.mkdirs());
        createFile(folder, "c.txt", "hello C");
        createFile(folder, "d.txt", "hello D");

        String destZip = new File(tempDir, "flat.zip").getAbsolutePath();
        String zipPath = FileZipUtils.zip(folder.getAbsolutePath(), destZip, false, "");
        Assertions.assertNotNull(zipPath);
        Assertions.assertTrue(new File(zipPath).exists());

        File outDir = new File(tempDir, "out2");
        Assertions.assertTrue(outDir.mkdirs());
        File[] files = FileZipUtils.unzip(zipPath, outDir.getAbsolutePath(), "");
        Assertions.assertNotNull(files);
        Assertions.assertEquals(2, files.length);
        // 不创建目录时，解包后应为直接文件
        Assertions.assertTrue(new File(outDir, "c.txt").exists());
        Assertions.assertTrue(new File(outDir, "d.txt").exists());
    }

    @Test
    void zipAndUnzipFile_WithPassword() throws Exception {
        tempDir = createTempDir();
        File file = createFile(tempDir, "secret.txt", "top-secret");

        String destZip = new File(tempDir, "secret.zip").getAbsolutePath();
        String password = "P@ssw0rd";
        String zipPath = FileZipUtils.zip(file.getAbsolutePath(), destZip, password);
        Assertions.assertNotNull(zipPath);
        Assertions.assertTrue(new File(zipPath).exists());

        File outDir = new File(tempDir, "out3");
        Assertions.assertTrue(outDir.mkdirs());
        File[] files = FileZipUtils.unzip(zipPath, outDir.getAbsolutePath(), password);
        Assertions.assertNotNull(files);
        Assertions.assertEquals(1, files.length);
        Assertions.assertTrue(new File(outDir, "secret.txt").exists());
    }
}
