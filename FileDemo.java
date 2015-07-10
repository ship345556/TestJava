
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Scanner;
public class FileDemo {

	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
        boolean success = false;

        System.out.println("Enter path of directory to create");
        String dir = reader.nextLine();

        // Creating new directory in Java, if it doesn't exists
        File directory = new File(dir);
        if (directory.exists()) {
            System.out.println("Directory already exists ...");

        } else {
            System.out.println("Directory not exists, creating now");

            success = directory.mkdir();
            if (success) {
                System.out.printf("Successfully created new directory : %s%n", dir);
            } else {
                System.out.printf("Failed to create new directory: %s%n", dir);
            }
        }

        // Creating new file in Java, only if not exists
        System.out.println("Enter file name to be created ");
        String filename = reader.nextLine();

        File f = new File(dir,filename);
        if (f.exists()) {
            System.out.println("File already exists");

        } else {
            System.out.println("No such file exists, creating now");
            success = f.createNewFile();
            if (success) {
                System.out.printf("Successfully created new file: %s%n", f);
            } else {
                System.out.printf("Failed to create new file: %s%n", f);
            }
        }
        
     // Read the full path of the file to be read
        System.out.println("Enter the file name to be written to : ");
                String Readfilename = reader.nextLine();
        	File f1 = new File(dir,Readfilename);

         if (f1.exists()) {
                    System.out.println("File exists. We should be okay to write to it");

                } else {            
                        System.out.printf("File doesnt exist. Cant write anything: %s%n", f1);
                    }

        System.out.printf("Enter the content to be written to the file: %s%n", f1);

                String ContentToWrite = reader.nextLine();
                FileWriter fw = new FileWriter(f1.getAbsoluteFile());

                BufferedWriter bw = new BufferedWriter(fw);
        	bw.write(ContentToWrite);
        	bw.close(); 
        	System.out.println("Done");

    
        
     // Read the destination file
		System.out.println("Enter the source filename : ");
        String sourceFile = reader.nextLine();	
        File f2 = new File(dir,sourceFile);
         if (f2.exists()) {
            System.out.println("Good. File exists");

        } else {
            System.out.println("Please enter a valid file name ");
        }
                
        System.out.println("Enter the destination filename: ");
         String destFile = reader.nextLine();
         
         File f3 = new File(dir,destFile);
         if (f3.exists()) {
             System.out.println("File already exists");

         } else {
             System.out.println("No such file exists, creating now");
             success = f3.createNewFile();
             if (success) {
                 System.out.printf("Successfully created new file: %s%n", f3);
             } else {
                 System.out.printf("Failed to create new file: %s%n", f3);
             }
         }
        
        
        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(f2).getChannel();
            destination = new FileOutputStream(f3).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
        
        System.out.println("Enter the file to print the content of : ");
        String readFile2 = reader.nextLine();
        
        File f4 = new File(dir,readFile2);
        if (f4.exists()) {
            System.out.println("File exists. Will now read the file");
        }
        
        BufferedReader in = new BufferedReader(new FileReader(f4));
        
        String line;
        while((line = in.readLine()) != null)
        {
            System.out.println(line);
        }
        in.close();
        
        
        // close Scanner to prevent resource leak
        reader.close();
        
    }

	}


