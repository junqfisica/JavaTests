package tjunqueira.swing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

/**
 * Scan folders and files.
 * @author thiago.junqueira
 *
 */
public class ScanFolders {
    
    
    /**
     * Get all images files within the given folder.
     * @param dirPath The folder's path were it should retrieve the files. 
     * @return All the files(*.tif, *.jpg, *.jpeg) within the folder.
     * @throws IOException Signals that an I/O exception of some sort has occurred. 
     *      This class is the general class of exceptions produced by failed or interrupted 
     *      I/O operations.
     */
    public static List<File> getImageFiles(String dirPath) throws IOException {
        
        // How deep the search should go in the folder tree.
        int maxDepth = 1;     
        
        // Filter the files it should get.
        BiPredicate<Path, BasicFileAttributes> bp = (path, filter) -> {
            return filter.isRegularFile() 
                    && (path.getFileName().toString().matches(".*\\.tif") 
                            || path.getFileName().toString().matches(".*\\.jpg")
                            || path.getFileName().toString().matches(".*\\.jpeg")
                            || path.getFileName().toString().matches(".*\\.tiff"));
        };
        
        Stream<Path> streamPath = Files.find(Paths.get(dirPath), maxDepth, bp); 
        List<File> files = new ArrayList<File>();
        streamPath.forEachOrdered((p) -> files.add(p.toFile()));
        streamPath.close();
        return files;
    }
    
    /**
     * Get all xml files within the given folder.
     * @param dirPath The folder's path were it should retrieve the files. 
     * @return All the files(*.xml) within the folder.
     * @throws IOException Signals that an I/O exception of some sort has occurred. 
     *      This class is the general class of exceptions produced by failed or interrupted 
     *      I/O operations.
     */
    public static List<File> getXmlFiles(String dirPath) throws IOException {
        
        // How deep the search should go in the folder tree.
        int maxDepth = 1;     
        
        // Filter the files it should get.
        BiPredicate<Path, BasicFileAttributes> bp = (path, filter) -> {
            return filter.isRegularFile() 
                    && path.getFileName().toString().matches(".*\\.xml");
        };
        
        Stream<Path> streamPath = Files.find(Paths.get(dirPath), maxDepth, bp); 
        List<File> files = new ArrayList<File>();
        streamPath.forEachOrdered((p) -> files.add(p.toFile()));
        streamPath.close();
        return files;
    }
    
    /**
     * Get all the folders with the root directory.
     * @param rootDir The path of the root directory.
     * @return All folders with the root.
     * @throws IOException IOException Signals that an I/O exception of some sort has occurred. 
     *      This class is the general class of exceptions produced by failed or interrupted 
     *      I/O operations.
     */
    public static List<File> getFolders(String rootDir) throws IOException {
        
        BiPredicate<Path, BasicFileAttributes> bp = (path, filter) -> {
            return filter.isDirectory(); };
        
        Stream<Path> streamPath = Files.find(Paths.get(rootDir), 1, bp);
        List<File> directories = new ArrayList<File>();
        streamPath.forEachOrdered((p) -> directories.add(p.toFile()));
        streamPath.close();
        // Remove root directory.
        directories.remove(0);
        return directories;
    }
}
