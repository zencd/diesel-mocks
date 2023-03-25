package mockey

class Utils {
    static File normalize(File file) {
        return file.toPath().toAbsolutePath().normalize().toFile()
    }
}
