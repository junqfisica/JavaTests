package tjunqueira.swing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;


public class CheckSums {
    
    
    /**
     * Creates a hex key from a file. Import the file can't exceed 2GB.  
     * @param method the method used to generate the hex. You can choose either MD5 or SHA1.
     * @param filePath The file absolute path. Include the file name.
     * @return The hex for this file.
     * @throws Exception - File exceed the size of 2GB.
     */
    public String createHex(ChecksumMethod method, String filePath) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance(method.toString());
        FileInputStream fileInputStream = new FileInputStream(filePath);
        
        
        byte[] dataBytes = new byte[4096];

        int nread = 0;
        //messageDigest.update(fileInputStream.toString().getBytes());
        
        while ((nread = fileInputStream.read(dataBytes)) != -1) {
          messageDigest.update(dataBytes, 0, nread);
        }
        
        fileInputStream.close();

        //byte[] mdbytes = messageDigest.digest();
        
        //fis.close();
        byte[] digest = messageDigest.digest();
        String result = new String(Hex.encodeHex(digest));
        return result;

        /**
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();*/
        
    }
    
    /**
     * Returns the file size in MB.
     * @param filePath The file absolute path. Include the file name.
     * @return The file size in MB.
     */
    public Float getFileSize(String filePath) {
        
        FileInputStream fileInputStream;
        Float size = 0.00f;
        
        try {
            fileInputStream = new FileInputStream(filePath);
            
            // Gets numbers of bytes in the file (Size).
            long fileSize = fileInputStream.available();
            
            // Convert from bytes to Mebibyte. 
            size = (float) (fileSize * 9.5367e-7);
          
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return size;
    }
    
    /**
     * Gets Hash of file.
     * 
     * @param file String path + filename of file to get hash.
     * @param hashAlgo Hash algorithm to use. <br/>
     *     Supported algorithms are: <br/>
     *     MD2, MD5 <br/>
     *     SHA-1 <br/>
     *     SHA-256, SHA-384, SHA-512
     * @return String value of hash. (Variable length dependent on hash algorithm used)
     * @throws IOException If file is invalid.
     * @throws HashTypeException If no supported or valid hash algorithm was found.
     */
    public String getHash(ChecksumMethod method, String filePath) throws Exception {

        try {
            MessageDigest md = MessageDigest.getInstance(method.toString());
            
            RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
            byte[] data = new byte[4096];  // Needs to be Byte Array only as MappedBuffer play only with Byte[]
            long pos = 0;
            long len = randomAccessFile.length();
            MappedByteBuffer mappedByteBuffer = null;
            
            while (pos < len) {
                long size = Math.min(data.length, len - pos);
                mappedByteBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, pos, size);
                int remaining = data.length;
                
                if(mappedByteBuffer.remaining() < remaining) {
              	  remaining = mappedByteBuffer.remaining();
                }
                mappedByteBuffer.get(data, 0, remaining); // data is modified with get, [] in java are objects and change its values within the method.
                md.update(data, 0, remaining);
                mappedByteBuffer.clear();
                pos += size;
            }
            
            /**
            while (mappedByteBuffer.hasRemaining()) {
              int remaining = data.length;
              if(mappedByteBuffer.remaining() < remaining) {
            	  remaining = mappedByteBuffer.remaining();
              }
              mappedByteBuffer.get(data, 0, remaining);
              md.update(data, 0, remaining);
              // do somthing with data
            }*/
            
            mappedByteBuffer.clear();
            randomAccessFile.close();
            byte[] digest = md.digest();
            String result = new String(Hex.encodeHex(digest));
            return result;
            /**
            byte[] mdbytes = md.digest();

            //convert the byte to hex format
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();*/

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public String getFileHash(ChecksumMethod method, String filePath) throws IOException, 
    NoSuchAlgorithmException {
        final int         BUFFER = 32 * 1024;
        File fis = new File(filePath);
        final Path        file = fis.toPath();
        try(final FileChannel fc   = FileChannel.open(file)) {
            final long        size = fc.size();
            final MessageDigest hash = MessageDigest.getInstance(method.toString());
            long position = 0;
            while(position < size) {
                final MappedByteBuffer data = fc.map(FileChannel.MapMode.READ_ONLY, 0, Math.min(size, BUFFER));
                if(!data.isLoaded()) data.load();
                System.out.println("POS:"+position);
                hash.update(data);
                position += data.limit();
                if(position >= size) break;
            }
            
            byte[] mdbytes = hash.digest();

            //convert the byte to hex format
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }
    }
}