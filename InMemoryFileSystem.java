package filesystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

public class InMemoryFileSystem {

	 public void createDir(String path){
	 File file = new File(path);
	 if (file.mkdir()){
	 System.out.println("Folder is created!");
	 }else{
	 System.out.println("Folder already exists.");
	 }
  }
	 public void createFile(String path){
	 File file = new File(path);
	 if(!file.isDirectory()){
	 try {
		if(file.createNewFile()){
		 System.out.println("File is created!");
		 }else{
		 System.out.println("File already exists.");
		 }
	} catch (IOException e) {
		e.printStackTrace();
	}
	 }else{
		 System.out.println("The direcotry doest not exist");
	 }
  }
	 public void appendText(String path,String text){
		 File file = new File(path);
		 if(file.exists()){
		 try {
			FileWriter fileWriter = new FileWriter(file,true);
			BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
			fileWriter.append(text);
			bufferFileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	  }else{
		  System.out.println("The file doest not exist to append");
	  }
	}
	 public void copyFiles(File source, File dest)
	 {
	  try
	  {
	   Files.copy(source.toPath(), dest.toPath(),StandardCopyOption.REPLACE_EXISTING);
	   System.out.println("Copied file from source to destination sucessfully");
	  }
	  catch(Exception e)
	  {
	   System.out.println(e.getMessage());
	  }
	 }
	 public void printFileContent(String path){
     	 BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
				try {
					while ((line = br.readLine()) != null) {
					   System.out.println(line);
					 }
				} catch (IOException e) {
					e.printStackTrace();
				}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
	 }
	 public void printFolderContent(String path){
		 File file = new File(path);
		 if(file.isDirectory()){
		 ArrayList<String> names = new ArrayList<String>(Arrays.asList(file.list()));
		 for(int i=0;i<names.size();i++){
			 System.out.println(names.get(i));
		 }
		}else{
			System.out.println("The directory does not exist to list the content");
		}
	 }
	 public void findFile(String file){
		   File root = new File("/Users/venku/Desktop/maindir");
		   String fileName = file;
		   try 
		    {
		     boolean recursive = true;
		     Collection files = FileUtils.listFiles(root, null, recursive);
		       for (Iterator iterator = files.iterator(); iterator.hasNext(); ) 
		       {
		        File file1 = (File) iterator.next();
		          if (file1.getName().equals(fileName))
		          System.out.println(file1.getAbsolutePath());
		       }
		     } 
		     catch (Exception e) 
		     {
		      e.printStackTrace();
		     }
	 }
	 public void findFileTwo(String path, String file){
		 File root = new File(path);
		   String fileName = file;
		   try 
		    {
		     boolean recursive = true;
		     Collection files = FileUtils.listFiles(root, null, recursive);
		       for (Iterator iterator = files.iterator(); iterator.hasNext(); ) 
		       {
		        File file1 = (File) iterator.next();
		          if (file1.getName().equals(fileName))
		          System.out.println(file1.getAbsolutePath());
		       }
		     } 
		     catch (Exception e) 
		     {
		      e.printStackTrace();
		     }
	 }
	 public void copyFolders(File sourceFolder, File destFolder)
	 {
	  try
	  {
	   FileUtils.copyDirectory(sourceFolder, destFolder);
	  }
	  catch(Exception e)
	  {
	   System.out.println(e.getMessage());
	  }
	 }
		    		
	 @Test
	 public void fileTest()
	 {
	  String mainDir = "/Users/venku/Desktop/maindir/";
	  String dir1 = "/Users/venku/Desktop/maindir/dir1/";
	  String dir2 = "/Users/venku/Desktop/maindir/dir2/";
	  String dir3 = "/Users/venku/Desktop/maindir/dir3/";
	  
	  String file1 = "testFile1.txt";
	  String file2 = "testFile2.txt";
	  
	  File sourceFile = new File(dir1 +file1);
	  File destFile = new File(dir2 +file2);
	  
	  File sourceFolder = new File(dir1);
	  File destFolder = new File(dir3);
	  
	  createDir(mainDir);
	  createDir(dir1);
	  createDir(dir2);
	  createFile(dir1 +file1);
	  createFile(dir2 +file2);
	  appendText(dir1 +file1, "Test Data ");
	  copyFiles(sourceFile, destFile);
	  printFolderContent(dir2);
	  printFileContent(dir1 +file1);
	  findFile(file1);
	  findFileTwo(mainDir,file1);
	  copyFolders(sourceFolder, destFolder);
	 }
}
