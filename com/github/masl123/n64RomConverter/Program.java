package com.github.masl123.n64RomConverter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class Program {

	public static void main(String[] args) {
		System.out.println("N64CONVERTER - Converts n64 Roms from [n64,v64,z64] to [n64,v64,z64]\n");
		
		if(args.length != 4 || !args[0].equalsIgnoreCase("-i") || !args[2].equalsIgnoreCase("-o")){
			printUsage();
			return;
		}
		RandomAccessFile in = null, out = null;
		
		try {
			//create input File
			in = new RandomAccessFile(args[1], "r");
			
			//create output File
			File f = new File(args[3]);
			if(f.exists()){
				System.out.println("Overwriting File" + args[3]);
				f.delete();
				f.createNewFile();
			}
			out = new RandomAccessFile(f, "rw");
			
			//Read input File;
			//Just be sure we don't try to read a huge file into memory. (Max File size is 128 mb)
			if(in.length()>134217728){
				printUsage();
				in.close();
				out.close();
				return;
			}
			
			byte[] inBuffer = new byte[(int)in.length()];
			in.read(inBuffer);
			
			
			
			//get Extension
			String outEx = args[3].substring(args[3].lastIndexOf('.'), args[3].length());
			
			System.out.println("Converting from to "+outEx);
			
			ByteBuffer bb = ByteBuffer.allocate(4);
			bb.put(inBuffer, 0, 4);
			bb.clear();
			int head = bb.getInt();
			
			
			
			
			//get the right Format
			if(head == 0x40123780){ //n64
				if(outEx.equalsIgnoreCase(".n64")){
					//nothing to Do here
				}else if(outEx.equalsIgnoreCase(".z64")){
					dWordSwap(inBuffer);
				}else if(outEx.equalsIgnoreCase(".v64")){
					wordSwap(dWordSwap(inBuffer));
				}
			}else if(head == 0x80371240){ //z64
				if(outEx.equalsIgnoreCase(".n64")){
					
					
					dWordSwap(inBuffer);
				}else if(outEx.equalsIgnoreCase(".z64")){
					//nothing to Do here
				}else if(outEx.equalsIgnoreCase(".v64")){
					wordSwap(inBuffer);
				}
			}else if(head == 0x37804012){ //v64
				if(outEx.equalsIgnoreCase(".n64")){
					dWordSwap(wordSwap(inBuffer));
					
				}else if(outEx.equalsIgnoreCase(".z64")){
					wordSwap(inBuffer);
				}else if(outEx.equalsIgnoreCase(".v64")){
					//nothing to Do here
				}
			}
			
			
			out.write(inBuffer);

			in.close();
			out.close();
			
			System.out.println("DONE");
		} catch (IOException e) {
			if(in!=null){
				try {in.close();} catch (IOException e1) {}
			}
			if(out!=null){
				try {out.close();} catch (IOException e1) {}
			}
			printUsage();
		}
	}

	
	
	public static byte[] wordSwap(byte[] in){
		for(int i = 0; i<in.length; i+=2){
			wordSwap(in, i, i+1);
		}
		return in;
	}
	
	
	public static byte[] wordSwap(byte[] in, int a, int b){
		byte temp = in[a];
		in[a] = in[b];
		in[b] = temp;
		return in;
	}
	
	
	
	public static byte[] dWordSwap(byte[] in){
		for(int i = 0; i<in.length; i+=4){
			wordSwap(in, i, i+3);
			wordSwap(in, i+1, i+2);
		}
		return in;
	}
	
	
	public static void printUsage(){
		System.out.println("USAGE: N64CONVERTER -i [INPUT] -o [OUTPUT] \n - [OUTPUT] must have one of these Extensions: n64, v64, z64");
	}
}
