package com.mapr.examples;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This producer will send a bunch of messages to topic "fast-messages". Every so often,
 * it will send a message to "slow-messages". This shows how messages can be sent to
 * multiple topics. On the receiving end, we will see both kinds of messages but will
 * also see how the two topics aren't really synchronized.
 */
public class Producer {
	/* Solution 1
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        Properties props = new Properties();
//      props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.100:19092,192.168.56.101:19092,192.168.56.102:19092");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.117:9092,192.168.1.118:9092,192.168.1.119:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, "3");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "none");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 200);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//      props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        //"1"是partition的key，决定放到物理上哪个分区的，一般重载partition，可以在Properties里面指定
        //producer.send(new ProducerRecord<>("page_visits", "1", "who cares risk?")).get();
        
        ProducerRecord myrecord = new ProducerRecord<String, String>(
                "page_visits",
                2,
                "guid",
                "123456"
        		);
        
        producer.send(myrecord);
        producer.flush();
        producer.close();

    }*/
	
	// Solution 2
    public static void main(String[] args) throws IOException {
        // set up the producer
        KafkaProducer<String, String> producer;
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            String server = properties.getProperty("bootstrap.servers");
            System.out.println("server: " + server);
            String partition_class = properties.getProperty("partitioner.class");
            System.out.println("partition class: " + partition_class);
            //properties.put("partitioner.class", "com.eengoo.example.SimplePartition");
            producer = new KafkaProducer<>(properties);
        }
        
        ProducerRecord myrecord = new ProducerRecord<String, String>(
                "hongtao",
                "key1",
                String.format("{\"type\":\"test\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, 0));

        producer.send(myrecord);
        
        producer.send(new ProducerRecord<String, String>(
                "hongtao",
                String.format("{\"type\":\"marker\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, 1)));
        
        producer.send(new ProducerRecord<String, String>(
                "hongtao",
                "key3",
                String.format("{\"type\":\"other\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, 2)));
        
        producer.flush();
        
        System.out.println("finished");
        producer.close();
        /*
        try {
            for (int i = 0; i < 1000000; i++) {
                // send lots of messages
                producer.send(new ProducerRecord<String, String>(
                        "fast-messages",
                        String.format("{\"type\":\"test\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i)));

                // every so often send to a different topic
                if (i % 1000 == 0) {
                    producer.send(new ProducerRecord<String, String>(
                            "fast-messages",
                            String.format("{\"type\":\"marker\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i)));
                    producer.send(new ProducerRecord<String, String>(
                            "summary-markers",
                            String.format("{\"type\":\"other\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i)));
                    producer.flush();
                    System.out.println("Sent msg number " + i);
                }
            }
        } catch (Throwable throwable) {
            System.out.printf("%s", throwable.getStackTrace());
        } finally {
            producer.close();
        } */  	
    }
}
