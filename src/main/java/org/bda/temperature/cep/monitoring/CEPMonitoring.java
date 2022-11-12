package org.bda.temperature.cep.monitoring;

import java.util.List;
import java.util.Map;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import org.bda.temperature.cep.monitoring.events.MonitoringEvent;
import org.bda.temperature.cep.monitoring.events.InsideAlert;
import org.bda.temperature.cep.monitoring.events.OutsideAlert;


public class CEPMonitoring {
    private static final double TEMPERATURE_THRESHOLD = 34.435067912853846;


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream < String > text = env.socketTextStream("localhost", 9020, "\n");
        DataStream < MonitoringEvent > inputEventStream = text.flatMap(new FlatMapFunction < String, MonitoringEvent > () {
            @Override
            public void flatMap(String value, Collector < MonitoringEvent > out) throws Exception {
                out.collect(new MonitoringEvent(Double.parseDouble(value)));
            }
        });
        inputEventStream.print();

        // 1.16 code starts
        Pattern < MonitoringEvent, ? > outsidePattern = Pattern. < MonitoringEvent > begin("start").where(
            new SimpleCondition < MonitoringEvent > () {
                @Override
                public boolean filter(MonitoringEvent event) {
                    return event.getTemp() >= TEMPERATURE_THRESHOLD;
                }
            }
        );

        PatternStream < MonitoringEvent > outsidePatternStream = CEP.pattern(inputEventStream, outsidePattern);

        DataStream < OutsideAlert > outsideAlerts = outsidePatternStream.select(new PatternSelectFunction < MonitoringEvent, OutsideAlert > () {
            @Override
            public OutsideAlert select(Map < String, List < MonitoringEvent >> pattern) throws Exception {
                return new OutsideAlert();
            }
        });


        Pattern < MonitoringEvent, ? > insidePattern = Pattern. < MonitoringEvent > begin("start").where(
            new SimpleCondition < MonitoringEvent > () {
                @Override
                public boolean filter(MonitoringEvent event) {
                    return event.getTemp() < TEMPERATURE_THRESHOLD;
                }
            }
        );

        PatternStream < MonitoringEvent > insidePatternStream = CEP.pattern(inputEventStream, insidePattern);

        DataStream < InsideAlert > insideAlerts = insidePatternStream.select(new PatternSelectFunction < MonitoringEvent, InsideAlert > () {
            @Override
            public InsideAlert select(Map < String, List < MonitoringEvent >> pattern) throws Exception {
                return new InsideAlert();
            }
        });

        insideAlerts.print();
        outsideAlerts.print();

        env.execute("CEP monitoring job");
    }
}