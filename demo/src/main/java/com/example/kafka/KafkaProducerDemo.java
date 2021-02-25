package com.example.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author Chen Xiao
 * @since 2020-10-12 10:57
 */
public class KafkaProducerDemo {

    public static void main(String[] args) throws Exception {
        Properties kafkaPropertie = new Properties();
        //配置broker地址，配置多个容错
        kafkaPropertie.put("bootstrap.servers", "kafka0.lizhi.fm:9092,kafka10.lizhi.fm:9092,kafka11.lizhi.fm:9092");
        //配置key-value允许使用参数化类型
        kafkaPropertie.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaPropertie.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer kafkaProducer = new KafkaProducer(kafkaPropertie);

        //发送参数
        JSONObject jsonObject = getGiftMsg();
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("lz_topic_pp_gift_gift_msg", "", jsonObject.toJSONString());

        kafkaProducer.send(record);

    }

    private static JSONObject getGiftMsg(){
        JSONObject map = new JSONObject();
        map.put("appId", 10919088L);
        map.put("transactionId", 2640877571050324524L);
        map.put("sendUserId", 2640877571050324524L);
        map.put("recUserId", 2674830449313846828L);
        map.put("recTargetUserId", 2674830449313846828L);
        map.put("giftId", 123344L);
        map.put("giftAmount", 100);
        map.put("litchiAmount", 100);
        map.put("createTime", 100);
        map.put("modifyTime", 100);
        map.put("source", 1);
        map.put("giftType", 2);
        map.put("value", 100);
        map.put("liveId", 100);
        map.put("giftCoin", 100);
        map.put("proportion", 1);
        map.put("actualGiftId", 100);

        return map;
    }
}
