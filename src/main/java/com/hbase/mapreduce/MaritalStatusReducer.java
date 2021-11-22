package com.hbase.mapreduce;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;

public  class MaritalStatusReducer extends
        TableReducer<ImmutableBytesWritable, IntWritable, ImmutableBytesWritable> {

    public static final byte[] CF = "marital_status".getBytes();
    public static final byte[] COUNT = "count".getBytes();

    public void reduce(ImmutableBytesWritable key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        Integer sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }

        Put put = new Put(key.get());
        put.addColumn(CF, COUNT, Bytes.toBytes(sum.toString()));

        context.write(key, put);
    }
}
