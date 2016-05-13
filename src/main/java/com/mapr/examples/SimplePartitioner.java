package com.mapr.examples;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner implements Partitioner {

	private final AtomicInteger counter = new AtomicInteger(new Random().nextInt());
	
	/*
	public SimplePartitioner (VerifiableProperties props) {
		
	}*/
	
	/* customize the partition rule */
	@Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        int partition = 0;
        System.out.println("number of partition: " + numPartitions);
        if (key == null || keyBytes == null) {
        	System.out.println("key bytes is null");       	
        	return numPartitions - 1;
        } else {
        	String k = (String)key;
        	partition = Math.abs(k.hashCode()) % numPartitions;
        	System.out.println("the key is: " + key.toString() + 
        			" key length: " + keyBytes.length +
        			" partition num: " + partition );
        	return partition;     	
        }
 
	}

	public void close() {}
	
	public void configure(Map<String, ?> configs) {}
}
