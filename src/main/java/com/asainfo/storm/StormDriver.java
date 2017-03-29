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
	//设置worker数
	config.setNumWorkers(1);
	//3、提交任务  -----两种模式 本地模式和集群模式
    if (args.length>0) {
        try {
			StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
		} catch (AlreadyAliveException | InvalidTopologyException e) {
			e.printStackTrace();
		}
    }else {
    	//以本地模式提交
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("storm2kafka", config, topologyBuilder.createTopology());
    }
}
}
