import java.io.*;
import java.util.*;
/**
 * The FileUtil class that loads a file and saves a file
 * @author Anu Datar
 * @version Dec 16, 2020
 **/
public class FileUtil
{

    /**
     * loads a file given the filename and passes an iterator to its string contents
     * @param fileName the file name
     * @return iterator to its string contents
     */    
    public static Iterator<String> loadFile(String fileName)
    {
        try
        {
            Scanner in = new Scanner(new File(fileName));
            List<String> list = new ArrayList<String>();
            while (in.hasNextLine())
                list.add(in.nextLine());
            in.close();
            return list.iterator();
        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * saves a file given the filename and and an iterator to its contents
     * @param fileName the file name
     * @param data iterator to its contents
     */
    public static void saveFile(String fileName, Iterator<String> data)
    {
        try
        {
            PrintWriter out = new PrintWriter(
                    new FileWriter(fileName), true);
            while (data.hasNext())
                out.println(data.next());
            out.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}