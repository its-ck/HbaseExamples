package com.hbase.mapreduce;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.IntWritable;

public  class MaritalStatusMapper extends TableMapper<ImmutableBytesWritable, IntWritable> {
    public static final byte[] CF = "personal".getBytes();
    public static final byte[] MARITAL_STATUS = "marital_status".getBytes();

    private final IntWritable ONE = new IntWritable(1);
    private ImmutableBytesWritable key = new ImmutableBytesWritable();

    public void map(ImmutableBytesWritable row, Result value, Context context)
            throws IOException, InterruptedException {
        key.set(value.getValue(CF, MARITAL_STATUS));

        context.write(key, ONE);
    }
}
