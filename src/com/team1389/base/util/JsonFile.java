package com.team1389.base.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Something for saving/loading Java objects to/from files as JSON objects without dealing with I/O streams and {@linkplain IOException exceptions} and stuff over and over again.
 * 
 * @param <T> the type of the objects which can be saved/loaded with this particular <tt>JsonFile</tt>
 */
@SuppressWarnings("unchecked") // Yeah, reflection sucks in terms of the way it works
public class JsonFile<T> {

	/**
	 * The pointer to the JSON file which instances of T will be saved to/loaded from
	 */
	private File file;
	/**
	 * The name of this <tt>JsonFile</tt>. Used for debugging
	 */
	private String name;
	private Class<T> tDotClass;
	private static Gson interface_ = new GsonBuilder().serializeNulls().create();
	// The underscore at the end of identifier distinguishes the identifier from the keyword "interface"
	
	/**
	 * Creates a <tt>JsonFile</tt> where <tt>filepath</tt> is the path of the JSON file which instances of T will be saved to/loaded from.
	 * @param filepath the path of the JSON file which instances of T will be saved to/loaded from - use forward slashes instead of backslashes!
	 * @param sampleInstance this is for some mechanism that properly loads instances of T from the JSON file
	 */
	public JsonFile(String filepath, String name, T sampleInstance){
		this(new File(filepath), name, sampleInstance);
	}

	/**
	 * Creates a <tt>JsonFile</tt> where <tt>file</tt> points to the JSON file which instances of T will be saved to/loaded from.
	 * @param file pointer to the JSON file which instances of T will be saved to/loaded from
	 * @param sampleInstance this is for some mechanism that properly loads instances of T from the JSON file
	 */
	public JsonFile(File file, String name, T sampleInstance){
		this.file = file;
		this.name = name;
		tDotClass = (Class<T>) sampleInstance.getClass(); // Also, T.class doesn't work
		try{
			if(this.file.createNewFile())
				System.out.println("Created new file for the JsonFile "+name+" at "+file.getAbsolutePath());
		}catch(IOException e){
			System.err.println("ERROR IN "+name+": Can't check whether the file at "+file.getAbsolutePath()+" exists or not!");
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Saves <tt>arg0</tt> by writing it as a JSON object to {@link #file}.
	 * @param arg0 the instance of T that'll be saved
	 * @return <tt>arg0</tt>
	 */
	public T save(T arg0){ // :)
		try(Writer writer = new FileWriter(file)){
			interface_.toJson(arg0, writer); // This is the line that matters
		} catch (IOException e) {
			System.err.println("ERROR IN "+name+": "+e.getMessage());
		}
		return arg0;
	}
	
	/**
	 * Loads and returns an instance of T from the JSON file which {@link #file} points to.
	 * @return an instance of T from the JSON file which {@link #file} points to, or <tt>null</tt> if something goes wrong
	 */
	public T load(){
		T loaded = null;
		try(Reader reader = new FileReader(file)){
			loaded = interface_.fromJson(reader, tDotClass);
		} catch (IOException e) {
			System.err.println("ERROR IN "+name+": "+e.getMessage());
		}
		return loaded;
	}
	
	// Getters & setters
	
	public String name(){
		return name;
	}
	
	public String filepath(){
		return file.getAbsolutePath();
	}
	
	/**
	 * A {@link File} object is a pointer to a file on the computer after all, isn't it?
	 */
	public File filePointer(){
		return new File(filepath()); // Is this hacked?
	}
	
	/**
	 * Renames this <tt>JsonFile</tt>. <b>This operation does NOT change the name of the JSON file stored on the computer.</b>
	 * @return <tt>this</tt>
	 */
	public JsonFile<T> rename(String newName){
		name = newName;
		return this;
	}
	
	/**
	 * Changes the JSON file used by this <tt>JsonFile</tt>.
	 * @param newPath
	 * @param discardCurrentlySavedInstance if <tt>true</tt>, the saved instance of <tt>T</tt> will be discarded
	 * @return <tt>this</tt>
	 * @throws IOException_wrapped_in_an_IOError if something goes wrong
	 */
	public JsonFile<T> changeFile(String newPath, boolean discardCurrentlySavedInstance){
		File newFile = new File(newPath);
		try{newFile.createNewFile();}catch(IOException e){throw new java.io.IOError(e);}
		// Why add a throws declaration because of something that shouldn't happen?
		T currentlySavedInstance = discardCurrentlySavedInstance ? null : load();
		file = newFile;
		if(!discardCurrentlySavedInstance)
			save(currentlySavedInstance);
		return this;
	}// Well, this is a complex setter...
}
