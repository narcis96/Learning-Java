/**
 * Created by Narcis on 7/10/2016.
 */
import sun.invoke.empty.Empty;

import javax.swing.border.EmptyBorder;
import java.io.*;
import java.util.*;
public class First {

    public static void main(String[] args) throws IOException , Exception{

        if(args.length < 2) {
            throw  new Exception("Not enought parameters");
        }

        int a = Integer.parseInt(args[0]), b = Integer.parseInt(args[1]), sum = 0;
        System.out.println(Integer.toString(a) + " + "  + Integer.toString(b) + " = " +  (a + b));

        String line;
        BufferedReader console =  new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(console.readLine());
        for(int i = 1;i <= n; i += 1) {
            sum += i;
        }
        System.out.println(sum);


        //for reading and writing in files
        try {
            Scanner fileIn = new Scanner(new FileInputStream(".\\Resources\\data.in"));
            PrintWriter fileOut = new PrintWriter(".\\Resources\\data.out");

            int x = fileIn.nextInt();
            fileOut.print(x);

            fileIn.close();
            fileOut.close();
        }
        catch (IOException exception) {
            System.out.println("exception happened: ");
            exception.printStackTrace();
            System.exit(-1);
        }



        try {
            Runtime r = Runtime.getRuntime();
            ProcessBuilder pb = new ProcessBuilder(".\\Resources\\proc.exe");
            Process p = /*pb.start();*/     r.exec("python .\\Resources\\Time.py");

            BufferedInputStream buf = new BufferedInputStream(p.getInputStream());
            InputStreamReader inread = new InputStreamReader(buf);
            BufferedReader bufferedreader = new BufferedReader(inread);


            while ((line = bufferedreader.readLine()) != null) {
                System.out.println(line);
            }

            try {
                if (p.waitFor() != 0) { //make curent thread to wait for the exit value of the process p
                    System.err.println("exit value = " + p.exitValue());
                }
            } catch (InterruptedException e) {
                System.err.println(e);
            } finally {
                // Close the InputStream
                bufferedreader.close();
                inread.close();
                buf.close();
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
            System.exit(-2);
        }
    }
}
