package com.gokman.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class WordCountEachFileMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text kelime = new Text();

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		//dosya adını çek
		FileSplit fileSplit=(FileSplit)context.getInputSplit();
		String dosyaAdi=fileSplit.getPath().getName();
		
		String satir = value.toString();
		StringTokenizer token = new StringTokenizer(satir);

		while (token.hasMoreTokens()) {
			String  temizKelime = token.nextToken()
					.replaceAll("[^\\p{L}\\p{N}]", "");
			kelime.set(temizKelime+":"+dosyaAdi);
		
			//çıktı oluşturulur
			context.write(kelime, new IntWritable(1));
		}
	}

}
