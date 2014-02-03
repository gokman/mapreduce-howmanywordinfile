package com.gokman.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountEachFileReducer extends
		Reducer<Text, IntWritable, Text, IntWritable> {

	protected static final String TARGET_WORD = "Watson";

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		
		if (key.toString().substring(0, key.toString().indexOf(":")).equals(TARGET_WORD)){
			int kelimeSayisi = 0;
			for (IntWritable value : values) {
				kelimeSayisi += value.get();
			}
			context.write(key, new IntWritable(kelimeSayisi));
		}
		
	}
}
