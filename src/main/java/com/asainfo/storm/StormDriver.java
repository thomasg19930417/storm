package com.asainfo.storm;

import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

public class StormDriver {
	
	
public static void main(String[] args) {
	TopologyBuilder topologyBuilder = new TopologyBuilder();
	SpoutConfig spoutConfig = new SpoutConfig(new ZkHosts(""), "test", "/myKafka", "kafkaSpout");
	topologyBuilder.setSpout("kafkaSpout", new KafkaSpout(spoutConfig), 1);
	//topologyBuilder.setBolt("", bolt);
	
	Config config = new Config();
	//����worker��
	config.setNumWorkers(1);
	//3���ύ����  -----����ģʽ ����ģʽ�ͼ�Ⱥģʽ
    if (args.length>0) {
        try {
			StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
		} catch (AlreadyAliveException | InvalidTopologyException e) {
			e.printStackTrace();
		}
    }else {
    	//�Ա���ģʽ�ύ
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("storm2kafka", config, topologyBuilder.createTopology());
    }
}
}
