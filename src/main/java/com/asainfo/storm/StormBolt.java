package com.asainfo.storm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class StormBolt extends BaseRichBolt{

	@Override
	public void execute(Tuple arg0) {
		
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		
	}
}
